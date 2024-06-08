package com.example.composeexample

import androidx.compose.runtime.compositionLocalOf

data class Person(val name: String, val age: Int)

val localPerson = compositionLocalOf<Person>{ error("No person") }
