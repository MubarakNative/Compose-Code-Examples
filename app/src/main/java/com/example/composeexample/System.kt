package com.example.composeexample

import androidx.compose.runtime.compositionLocalOf

data class System(val theme: String = "Light")

val LocalSystem = compositionLocalOf { System() } // must be declared in top level of the class/file so the composable can access it and also
// providing default value is good practise that helps us to showing previews in the IDE and creating a test

// TODO: here we using [compositionLocalOf] Changing the value here (isSystemInDarkTheme) invalidates only the content that reads its current value here the [Text composable].
// TODO: by using [staticCompositionLocalOf] the reads are not tracked by composable, By changing in the value here conditionally (isSystemInDarkTheme) the entire content lambda in a CompositionLocalProvider]
//  will be recomposed instead of current value reads in a [compositionLocalOf]