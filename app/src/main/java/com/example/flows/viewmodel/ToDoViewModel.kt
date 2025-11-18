package com.example.flows.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flows.data.Todo
import com.example.flows.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repo: TodoRepository
) : ViewModel() {

    val todos: StateFlow<List<Todo>> =
        repo.todos.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun add(title: String) {
        viewModelScope.launch {
            repo.addTodo(title)
        }
    }

    fun toggle(todo: Todo) {
        viewModelScope.launch {
            repo.toggle(todo)
        }
    }

    fun delete(todo: Todo) {
        viewModelScope.launch {
            repo.delete(todo)
        }
    }
}
