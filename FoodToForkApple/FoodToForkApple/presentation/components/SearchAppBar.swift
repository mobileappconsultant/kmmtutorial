//
//  SearchAppBar.swift
//  FoodToForkApple
//
//  Created by samson akisanya on 20/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchAppBar: View {
    
    @State var query:String
    
    private let onTriggerEvent :(RecipeListEvents) -> Void
    private let foodCategories : [FoodCategory]
    private let selectedFoodCategory : FoodCategory?
    
    init(query: String, foodCategories : [FoodCategory], selectedFoodCategory: FoodCategory?, onTriggerEvent: @escaping (RecipeListEvents) -> Void) {
        self._query = State(initialValue: query)
        self.onTriggerEvent = onTriggerEvent
        self.foodCategories = foodCategories
        self.selectedFoodCategory = selectedFoodCategory
    }
    
    var body: some View {
        VStack{
            HStack{
                Image(systemName: "magnifyingglass")
                TextField(
                "Search...", text: $query,
                onCommit: {
                    onTriggerEvent(RecipeListEvents.NewSearch())
                }
                ).onChange(of: query, perform: {value in
                    
                    onTriggerEvent(RecipeListEvents.OnUpdateQuery(query: value))
                })
            }
            .padding(.bottom,8)
            ScrollView(.horizontal){
                HStack(spacing: 1){
                    ForEach(foodCategories,id: \.self){category in
                        FoodCategoryChip(category: category.value, isSelected: selectedFoodCategory==category).padding(2)
                            .onTapGesture {
                                query = category.value
                                onTriggerEvent(RecipeListEvents.OnSelectCategory(category: category))
                            }
                    }
                }
            }
        }
        .padding(.top,8).padding(.leading,8).padding(.trailing,8).background(Color.white.opacity(0))
    }
}

