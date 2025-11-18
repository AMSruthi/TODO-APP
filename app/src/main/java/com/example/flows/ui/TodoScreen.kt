package com.example.flows.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flows.data.Todo
import com.example.flows.viewmodel.TodoViewModel

@Composable
fun TodoScreen(
    viewModel: TodoViewModel = hiltViewModel()
) {

    val todos by viewModel.todos.collectAsState()

    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.Companion
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.Companion.height(14.dp))

        Text(text = "TO DO LIST", style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold
        ))

        Spacer(modifier = Modifier.Companion.height(16.dp))

        // Input Row
        Row(
            verticalAlignment = Alignment.Companion.CenterVertically,
            modifier = Modifier.Companion.fillMaxWidth()
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.Companion.weight(1f),
                placeholder = { Text("Add task...") }
            )

            Spacer(modifier = Modifier.Companion.width(8.dp))

            Button(
                shape = RoundedCornerShape(4.dp),
                onClick = {
                    if (text.isNotBlank()) {
                        viewModel.add(text)
                        text = ""
                    }
                }
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.Companion.height(16.dp))

        // List of Todos
        LazyColumn(modifier = Modifier.Companion.fillMaxSize()) {
            items(todos) { todo ->
                TodoRow(
                    todo = todo,
                    onToggle = { viewModel.toggle(it) },
                    onDelete = { viewModel.delete(it) }
                )
            }
        }
    }
}

@Composable
fun TodoRow(
    todo: Todo,
    onToggle: (Todo) -> Unit,
    onDelete: (Todo) -> Unit
) {
    Row(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .clickable { onToggle(todo) }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Companion.CenterVertically
    ) {

        Checkbox(
            checked = todo.isDone,
            onCheckedChange = { onToggle(todo) }
        )

        Spacer(modifier = Modifier.Companion.width(8.dp))

        Text(
            text = todo.title,
            modifier = Modifier.Companion.weight(1f),
            style = if (todo.isDone)
                TextStyle(textDecoration = TextDecoration.Companion.LineThrough)
            else
                TextStyle.Companion.Default
        )

        IconButton(onClick = { onDelete(todo) }) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoRowPreview() {
    TodoRow(
        todo = Todo(id = 1, title = "Buy groceries", isDone = false),
        onToggle = {},
        onDelete = {}
    )
}

