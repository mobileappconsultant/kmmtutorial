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
    @ObservedObject var viewModel : RecipelistViewModel
    
    init(networkModule: NetworkModule, cacheModule: CacheModule) {
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchModule(networkModule:self.networkModule,
                                                cacheModule:self.cacheModule)
        self.viewModel = RecipelistViewModel(
            searchRecipes: self.searchRecipesModule.searchRecipes,
            foodCategoryUtil: FoodCategoryUtil())
    }
    
    
    var body: some View {
        VStack{
            Text("\(viewModel.state.page)")
            Button(action: {
                viewModel.updateState(page: Int(viewModel.state.page) + 1)
            }, label: {
                Text("IncrementPage")
            })
        }
    }
}
