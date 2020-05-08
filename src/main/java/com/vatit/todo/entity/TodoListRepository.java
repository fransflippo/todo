package com.vatit.todo.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoListEntity, String> {
}
