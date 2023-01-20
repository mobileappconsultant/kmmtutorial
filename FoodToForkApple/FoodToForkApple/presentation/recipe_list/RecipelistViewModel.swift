//
//  RecipelistViewModel.swift
//  FoodToForkApple
//
//  Created by samson akisanya on 19/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeListViewModel: ObservableObject {
    
    // Dependencies
    let searchRecipes: SearchRecipe
    let foodCategoryUtil: FoodCategoryUtil

    // State
    @Published var state: RecipeListState = RecipeListState()
    
    init(
            searchRecipes: SearchRecipe,
            foodCategoryUtil: FoodCategoryUtil
        ){
            self.searchRecipes = searchRecipes
            self.foodCategoryUtil = foodCategoryUtil
            onTriggerEvent(stateEvent: RecipeListEvents.LoadRecipes())
        }
    
    func onTriggerEvent(stateEvent: RecipeListEvents){
        switch stateEvent {
        case is RecipeListEvents.LoadRecipes:
            loadRecipes()
        case is RecipeListEvents.NewSearch:
            doNothing()
        case is RecipeListEvents.NextPage:
            nextPage()
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
    
    private func nextPage(){
        let currentState = (self.state.copy() as! RecipeListState)
        updateState(page: Int(currentState.page) + 1)
        loadRecipes()
    }
    
    private func loadRecipes(){
        let currentState = (self.state.copy() as! RecipeListState)
        do{
            
            try searchRecipes.execute(
                page: Int32(currentState.page),
                query: currentState.query
            ).collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                if dataState != nil {
                    let data = dataState?.data
                    let message = dataState?.message
                    let loading = dataState?.isLoading ?? false

                    self.updateState(isLoading: loading)

                    if(data != nil){
                        self.appendRecipes(recipes: data as! [Recipe])
                    }
                    if(message != nil){
                        self.handleMessageByUIComponentType(message!.build())
                    }
                }
            })
        }catch{
            // build an error
            //self.handleMessageByUIComponentType(message!.build())
        }
    }
    
    private func onUpdateBottomRecipe(recipe: Recipe){
            updateState(bottomRecipe: recipe)
        }
    
    private func appendRecipes(recipes: [Recipe]){
            var currentState = (self.state.copy() as! RecipeListState)
            var currentRecipes = currentState.recipes
            currentRecipes.append(contentsOf: recipes)
            self.state = self.state.doCopy(
                isLoading: currentState.isLoading,
                page: currentState.page,
                query: currentState.query,
                selectedCategory: currentState.selectedCategory,
                recipes: currentRecipes, // update recipes
                bottomRecipe: currentState.bottomRecipe,
                queue: currentState.queue
            )
            currentState = (self.state.copy() as! RecipeListState)
            self.onUpdateBottomRecipe(recipe: currentState.recipes[currentState.recipes.count - 1])
        }
    
    private func handleMessageByUIComponentType(_ message: GenericMessageInfo){
        // TODO("append to queue or 'None'")
    }
    
    private func doNothing(){
        // does nothing
    }
    
    func shouldQueryNextPage(recipe: Recipe) -> Bool {
            // check if looking at the bottom recipe
            // if lookingAtBottom -> proceed
            // if PAGE_SIZE * page <= recipes.length
            // if !queryInProgress
            // else -> do nothing
            let currentState = (self.state.copy() as! RecipeListState)
            if(recipe.id == currentState.bottomRecipe?.id){
                if(RecipeListState.Companion().RECIPE_PAGINATION_PAGE_SIZE * currentState.page <= currentState.recipes.count){
                    if(!currentState.isLoading){
                        return true
                    }
                }
            }
            return false
        }
    
    /**
     *  Not everything can be conveniently updated with this function.
     *  Things like recipes and selectedCategory must have their own functions.
     *  Basically if more then one action must be taken then it cannot be updated with this function.
     *  ex: updating selected category requires us to: 1) update category, 2) update the query, 3) trigger new search event
     */
        func updateState(
            isLoading: Bool? = nil,
            page: Int? = nil,
            query: String? = nil,
            bottomRecipe: Recipe? = nil,
            queue: Queue<GenericMessageInfo>? = nil
        ){
            let currentState = (self.state.copy() as! RecipeListState)
            self.state = self.state.doCopy(
                isLoading: isLoading ?? currentState.isLoading,
                page: Int32(page ?? Int(currentState.page)),
                query: query ?? currentState.query,
                selectedCategory: currentState.selectedCategory,
                recipes: currentState.recipes,
                bottomRecipe:  bottomRecipe ?? currentState.bottomRecipe,
                queue: queue ?? currentState.queue
            )
        }
    
}
