//
//  RecipeListScreen.swift
//  FoodToForkApple
//
//  Created by samson akisanya on 19/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeListScreen: View {
    
    //dependencies
    
    private let networkModule : NetworkModule
    private let cacheModule : CacheModule
    private let searchRecipesModule : SearchModule
    @ObservedObject var viewModel : RecipeListViewModel
    
    init(networkModule: NetworkModule, cacheModule: CacheModule) {
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchModule(networkModule:self.networkModule,
                                                cacheModule:self.cacheModule)
        self.viewModel = RecipeListViewModel(
            searchRecipes: self.searchRecipesModule.searchRecipes,
            foodCategoryUtil: FoodCategoryUtil())
    }
    
    
    var body: some View {
        List{
            ForEach(viewModel.state.recipes, id: \.self.id){recipe in
                Text(recipe.title)
                    .onAppear(perform: {
                        if viewModel.shouldQueryNextPage(recipe: recipe){
                            viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
                        }
                    })
            }
        }
    }
}
