package com.example.composeexample

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel:ViewModel() {

    private val _taskLists = getWellnessTasks().toMutableStateList()
    val taskLists: List<WellnessTask> = _taskLists

    fun remove(item: WellnessTask) {
     _taskLists.remove(item)
    }

    fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

}