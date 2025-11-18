package com.example.flows.repository


import com.example.flows.data.Todo
import com.example.flows.data.TodoDao
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val dao: TodoDao
) {
    val todos: Flow<List<Todo>> = dao.getTodos()

    suspend fun addTodo(title: String) {
        dao.insert(Todo(title = title))
    }

    suspend fun toggle(todo: Todo) {
        dao.update(todo.copy(isDone = !todo.isDone))
    }

    suspend fun delete(todo: Todo) {
        dao.delete(todo = todo)
    }
}