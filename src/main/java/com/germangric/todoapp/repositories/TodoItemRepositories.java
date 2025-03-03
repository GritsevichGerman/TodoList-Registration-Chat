package com.germangric.todoapp.repositories;

import com.germangric.todoapp.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepositories extends JpaRepository<TodoItem, Long> {
}
