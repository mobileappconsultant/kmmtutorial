//
//  RecipeListScreen.swift
//  FoodToForkApple
//
//  Created by samson akisanya on 19/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

import SwiftUI
import shared

struct RecipeListScreen: View {
    
    // dependencies
    private let networkModule: NetworkModule
    private let cacheModule: CacheModule
    private let searchRecipesModule: SearchModule
    private let foodCategories: [FoodCategory]
    
    @ObservedObject var viewModel: RecipeListViewModel
    
    init(
        networkModule: NetworkModule,
        cacheModule: CacheModule
    ) {
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchModule(
            networkModule: self.networkModule,
            cacheModule: self.cacheModule
        )
        let foodCategoryUtil = FoodCategoryUtil()
        self.viewModel = RecipeListViewModel(
            searchRecipes: searchRecipesModule.searchRecipes,
            foodCategoryUtil: foodCategoryUtil
        )
        self.foodCategories = foodCategoryUtil.getAllFoodCategories()
    }
    
    var body: some View {
        NavigationView{
            ZStack{
                VStack{
                    SearchAppBar(
                        query: viewModel.state.query,
                        foodCategories: foodCategories,
                        selectedFoodCategory: viewModel.state.selectedCategory,
                        onTriggerEvent: { event in
                            viewModel.onTriggerEvent(stateEvent: event)
                        }
                    )
                    List {
                        ForEach(viewModel.state.recipes, id: \.self.id){ recipe in
                            ZStack{
                                VStack{
                                    RecipeCard(recipe: recipe)
                                        .onAppear(perform: {
                                            if viewModel.shouldQueryNextPage(recipe: recipe){
                                                viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
                                            }
                                        })
                                }
                                NavigationLink(
                                    destination: RecipeDetailScreen(
                                        cacheModule: self.cacheModule, recipeId: Int(recipe.id)
                                    )
                                ){
                                    // workaround for hiding arrows
                                    EmptyView()
                                }.hidden().frame(width: 0)
                            }
                            .listRowInsets(EdgeInsets())
                            .padding(.top, 10)
                        }
                    }
                    .listStyle(PlainListStyle())
                    .background(Color.gray)
                }
                if viewModel.state.isLoading {
                    ProgressView("Searching recipes...")
                }
            }
            .navigationBarHidden(true)
        }
    }
}
