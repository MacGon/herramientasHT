package com.simaht.utils

import com.baz.simaht.utils.CoConstants.*

class SelectableItem<T: Any> (val item: T) {
    var selected: Boolean = false

    var action: ACTIONS? = null
}