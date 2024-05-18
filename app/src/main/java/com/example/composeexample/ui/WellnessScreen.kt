package com.example.composeexample.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composeexample.WellnessTask
import com.example.composeexample.WellnessViewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    viewModel: WellnessViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    Column(modifier = modifier) {

        StateFullCounter()
        WellnessTasksList(
            modifier = modifier,
            onCloseTask = { viewModel.remove(it) },
            list = viewModel.taskLists
        )
    }

}

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask>,
    onCloseTask: (WellnessTask) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(list, key = { list -> list.id }) { task ->
            WellnessTaskItem(taskName = task.label, onClose = { onCloseTask(task) })
        }
    }
}