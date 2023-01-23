//
//  RecipeDetailViewModel.swift
//  FoodToForkApple
//
//  Created by samson akisanya on 23/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

class RecipeDetailViewModel : ObservableObject{
    
    //dependencies
    private let getRecipe : GetRecipe
    @State var state: RecipeDetailState = RecipeDetailState()
    
    
    init(recipeId: Int, getRecipe: GetRecipe) {
        self.getRecipe = getRecipe
        //TODO ("Get the recipe from the cache")
        self.state = state
    }
    
    
}
