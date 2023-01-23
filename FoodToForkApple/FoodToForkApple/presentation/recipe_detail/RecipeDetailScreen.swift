//
//  RecipeDetailScreen.swift
//  FoodToForkApple
//
//  Created by samson akisanya on 23/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeDetailScreen: View {
    private let cacheModule : CacheModule
    private let getRecipeModule : GetRecipeModule
    private let recipeId : Int
    private let datetimeUtil = DatetimeUtil()

    @ObservedObject var viewModel : RecipeDetailViewModel
    
    
    init(cacheModule: CacheModule, recipeId: Int) {
        self.cacheModule = cacheModule
        self.recipeId = recipeId
        self.getRecipeModule = GetRecipeModule(cacheModule: self.cacheModule)
        viewModel = RecipeDetailViewModel(recipeId: self.recipeId, getRecipe: self.getRecipeModule.getRecipe)
    }

    var body: some View {
        Text("\(recipeId)")
    }
}

//struct RecipeDetailScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeDetailScreen()
//    }
//}
