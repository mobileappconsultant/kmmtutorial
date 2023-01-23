//
//  FoodCategoryChip.swift
//  FoodToForkApple
//
//  Created by samson akisanya on 22/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct FoodCategoryChip: View {
    private let category :String
    private let isSelected: Bool
    
    init(category: String, isSelected: Bool) {
        self.category = category
        self.isSelected = isSelected
    }
    
    var body: some View {
        HStack{
            Text(category).padding(8).background(isSelected ? Color.gray : Color.blue).foregroundColor(Color.white).cornerRadius(10.0)
        }
    }
}

//struct FoodCategoryChip_Previews: PreviewProvider {
//    static var previews: some View {
//        FoodCategoryChip(category: "Chicken", isSelected: false)
//    }
//}
