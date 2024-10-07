package com.dantesoft.sire_todos.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dantesoft.sire_todos.entity.Todo;
import com.dantesoft.sire_todos.services.TodoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TodosController {
	final TodoService todoService;

	@GetMapping("/")
	public String listTodos(Model model,
			@RequestParam(defaultValue = "") String search,
	        @PageableDefault(page = 0, direction = Direction.ASC, size = 10) Pageable pageable) {
	    
		  var todos = todoService.handleGetTodos(search, pageable);
	        model.addAttribute("todos", todos);
	        model.addAttribute("newTodo", new Todo());  
	        model.addAttribute("search", search);
	        return "todos"; // Nombre de la vista Thymeleaf
	}
	
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String storeTodo(Model model, @ModelAttribute Todo newTodo) {
	    this.todoService.handleCreateTodo(newTodo);
	    return "redirect:/";
	}
}
