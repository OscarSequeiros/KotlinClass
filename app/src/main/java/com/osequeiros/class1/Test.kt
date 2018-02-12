package com.osequeiros.class1

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

open class Persona(name : String = "Peter", val age : Int = 22) {
    var name = name
        get() = "Name : $field"
        set(value) {
            if (value != field) {
                field = value
            }
        }
}

fun <T: Any> T.apply2(f: T.() -> Unit): T {
    f()
    return this
}

fun myFunction(myInt: Int): Int {
    return myInt
}

//private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

fun generateInt(i: Int) = i * (i + 1)

fun test() {
    val x = 12f
    val y = x.toString()
    val z: List<Int> = listOf(1, 2, 3)
}

data class Person(val name : String, val age : Int)

class Developer: Persona {
    constructor(name: String) : super(name)
    //  constructor(age: Int) : super(age)
}

fun test(list : List<Person>) {
    for ((name, age) in list) {
        print(name)
        print(age)
    }
    val persona = Persona("Oscar", 24)
    val name = persona.name

    val persona2 = Persona("Sergio")
    val persona3 = Persona(age = 24)

    val f: (Int, Int) -> Int = { x, y  -> x + y }

    val x = 9 addition 10
}

fun whenTest(view: View) {

    val String = when (view) {
        is TextView -> "I'm text"
        is ImageView -> "I'm ImageView"
        else -> "I'm not view"
    }

    val myInt = if (view is TextView) 20 else 0

}

interface Callback {
    fun onCallback(result: String)
}

fun doAsync(x: Int, callback: Callback) {
    // Background
    callback.onCallback("finished")
}

fun doAsyncLambda3(x: Int, callback: (String) -> Unit) {
    callback("result")
}

fun doAsyncLambda(x: Int, callback: (String) -> Unit) {
    callback("finished")
}

fun doAsyncLambda2(x: Int, callback: (String) -> Unit) = callback("hello lambda")

fun lambdasTest() {
    val sum = { a: Int, b: Int -> a + b }
    applyOp(2, 4, sum)

    val mul = { a: Int, b: Int -> a * b }
    applyOp(4, 6, mul)

    applyOp(7,2) { x, y -> x - y }

    doAsync(20, object: Callback {
        override fun onCallback(result: String) {
            print(result)
        }
    })

    val lambda = { s: String -> print(s) }
    doAsyncLambda(20, lambda)

    doAsyncLambda2(20) { result -> print(result) }
}

fun applyOp(x: Int, y: Int, f: (Int, Int) -> Int): Int = f(x, y)

fun operacionesListas(items: List<MediaItem>) {
    val urlList = items.filter { it.type == MediaItem.Type.PHOTO }
            .sortedBy { it.title }
            .map { it.url }

    val mutableUrlList = urlList.toMutableList()

    val mutableList: MutableList<Int> = mutableListOf(1, 2)

}

infix fun Int.addition(other: Int) = this + other


fun testNull() {

    val myInt : Int? = null
    val myLong : Long = myInt?.toLong() ?: 0L
}
