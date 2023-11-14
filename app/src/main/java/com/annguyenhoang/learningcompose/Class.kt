package com.annguyenhoang.learningcompose

import kotlin.reflect.KProperty

class Example {
    var p: String by Delegate()

    fun sayHello() {
        println(p)
    }

    fun sayHello2() {
        p = "asdasd"
    }
}

// Example@33a17727, thank you for delegating 'p' to me!
// NEW has been assigned to 'p' in Example@33a17727.

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}