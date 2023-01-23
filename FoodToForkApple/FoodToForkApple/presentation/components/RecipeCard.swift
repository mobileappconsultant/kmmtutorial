//
//  RecipeCard.swift
//  FoodToForkApple
//
//  Created by samson akisanya on 22/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct RecipeCard: View {
    
    let recipe: Recipe
    
    init(recipe: Recipe) {
        self.recipe = recipe
    }
    
    var body: some View {
            VStack(alignment: .leading){
                WebImage(url: URL(string: recipe.featuredImage))
                            .resizable()
                            .placeholder(Image(systemName: "photo")) // Placeholder Image
                            .placeholder {
                                Rectangle().foregroundColor(.white)
                            }
                            .indicator(.activity)
                            .transition(.fade(duration: 0.5))
                            .scaledToFill() // 1
                            .frame(height: 250, alignment: .center) // 2
                            .clipped() // 3
                    
                HStack(alignment: .lastTextBaseline){
                    Text(recipe.title)
                        .font(.body)
                        .frame(alignment: .center)
                    
                    Spacer()
                    
                    Text(String(recipe.rating))
                        .frame(alignment: .trailing)
                }
                .padding(.top, 8)
                .padding(.leading, 8)
                .padding(.trailing, 8)
                .padding(.bottom, 12)
            }
            .background(Color.white)
            .cornerRadius(8)
            .shadow(radius: 5)
        }
}
