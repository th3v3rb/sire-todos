package com.dantesoft.sire_todos.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dantesoft.sire_todos.entity.Todo;
import com.dantesoft.sire_todos.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
	final TodoRepository todoRepo;

	public Page<Todo> handleGetTodos(String search, Pageable pageable) {
		if (search != null && !search.isEmpty()) {
			return todoRepo.findByDescriptionContaining(search, pageable);
		}
		return todoRepo.findAll(pageable);
	}

	public Todo handleCreateTodo(Todo todo) {
		var dbTodo = todoRepo.save(todo);
		return dbTodo;
	}

	public Todo handleUpdateTodo(Todo todo, UUID id) {
		var dbTodo = todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));

		dbTodo.setDescription(todo.getDescription());
		dbTodo.setDate(todo.getDate());

		return todoRepo.save(dbTodo);
	}

	public void handleRemoveTodo(UUID id) {
		var dbTodo = todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
		todoRepo.delete(dbTodo);
	}

}
