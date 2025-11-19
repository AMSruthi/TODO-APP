package com.example.flows.repository


import com.example.flows.data.Todo
import com.example.flows.data.TodoDao

class TodoRepository(
    private val dao: TodoDao
) {
    fun getTodos(category: String) =  dao.getTodos(category)
    suspend fun addTodo(title: String, category: String) {
        dao.insert(Todo(title = title, category = category))
    }

    suspend fun toggle(todo: Todo) {
        dao.update(todo.copy(isDone = !todo.isDone))
    }

    suspend fun delete(todo: Todo) {
        dao.delete(todo = todo)
    }
}