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
    
    init(query: String, onTriggerEvent: @escaping (RecipeListEvents) -> Void) {
        self._query = State(initialValue: query)
        self.onTriggerEvent = onTriggerEvent
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
        }
        .padding(.top,8).padding(.leading,8).padding(.trailing,8).background(Color.white.opacity(0))
    }
}

