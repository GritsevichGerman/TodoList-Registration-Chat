package com.germangric.todoapp.controllers;


import com.germangric.todoapp.model.TodoItem;
import com.germangric.todoapp.repositories.TodoItemRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController implements CommandLineRunner {

    private final TodoItemRepositories TodoItemRepositories;
    private final TodoItemRepositories todoItemRepositories;

    public TodoController( TodoItemRepositories todoItemRepositories) {
        TodoItemRepositories = todoItemRepositories;
        this.todoItemRepositories = todoItemRepositories;
    }

    @GetMapping
    public String index(Model model){

        List<TodoItem> allTodos = TodoItemRepositories.findAll();
        model.addAttribute("allTodos", allTodos);
        model.addAttribute("newTodo", new TodoItem());

        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute TodoItem todoItem){
        todoItemRepositories.save(todoItem);

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String removeAllItems(@PathVariable("id") Long id){
        todoItemRepositories.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/removeAll")
    public String deleteTodoItem(){
        todoItemRepositories.deleteAll();
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchTodoItems(@RequestParam("searchTerm") String searchTerm, Model model){
        List<TodoItem> allItems = todoItemRepositories.findAll();
        List<TodoItem> searchResults = new ArrayList<>();

        for (TodoItem item : allItems){
            if (item.getTitle().toLowerCase().contains(searchTerm.toLowerCase())){
                searchResults.add(item);
            }
        }
        model.addAttribute("allTodos", searchResults);
        model.addAttribute("newTodo", new TodoItem());
        model.addAttribute("searchTerm", searchTerm);

        return "index";
    }

    @Override
    public void run(String... args) throws Exception {
        TodoItemRepositories.save(new TodoItem("Задача 1"));
        TodoItemRepositories.save(new TodoItem("Задача 2"));
        TodoItemRepositories.save(new TodoItem("Задача 3"));
    }
}
