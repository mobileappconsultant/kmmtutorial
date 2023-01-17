package com.example.kmmfoodtofork.domain.model.util

import com.example.kmmfoodtofork.domain.model.GenericMessageInfo

class GenericMessageInfoUtil {
    fun doesMessageAlreadyExistInQueue(
        queue: Queue<GenericMessageInfo>,
        messageInfo: GenericMessageInfo
    ): Boolean {
       return queue.items.any { it.id == messageInfo.id }
    }
}
