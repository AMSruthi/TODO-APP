package com.example.flows.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.flows.data.Todo

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