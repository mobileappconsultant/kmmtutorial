//
//  RecipelistViewModel.swift
//  FoodToForkApple
//
//  Created by samson akisanya on 19/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipelistViewModel :ObservableObject{
    
    //dependencies
    let searchRecipes : SearchRecipe
    let foodCategoryUtil : FoodCategoryUtil
    
    
    @Published var state : RecipeListState = RecipeListState()
    
    
    init(searchRecipes: SearchRecipe, foodCategoryUtil: FoodCategoryUtil) {
        self.searchRecipes = searchRecipes
        self.foodCategoryUtil = foodCategoryUtil
        
    }
    
    func updateState(
        isLoading : Bool? = nil,
        page:Int? = nil,
        query : String? = nil,
        queue: Queue<GenericMessageInfo>? = nil){
            
            let currentState = (self.state.copy() as! RecipeListState)
            self.state = self.state.doCopy(isLoading: isLoading ?? currentState.isLoading,
                                           page: Int32(page ?? Int(Int32(currentState.page))), query: query ?? currentState.query, selectedCategory: currentState.selectedCategory, recipes: currentState.recipes, queue: currentState.queue)
            
        }
    
    
    func onTriggerEvent(stateEvent: RecipeListEvents){
            switch stateEvent {
            case is RecipeListEvents.LoadRecipes:
                doNothing()
            case is RecipeListEvents.NewSearch:
                doNothing()
            case is RecipeListEvents.NextPage:
                doNothing()
            case is RecipeListEvents.OnUpdateQuery:
                doNothing()
            case is RecipeListEvents.OnSelectCategory:
                doNothing()
            case RecipeListEvents.OnRemoveHeadMessageFromQueue():
                doNothing()
            default:
                doNothing()
            }
        }
        
    func doNothing(){
        
    }
}
