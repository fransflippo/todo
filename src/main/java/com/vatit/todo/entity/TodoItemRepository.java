package com.vatit.todo.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoItemRepository extends CrudRepository<TodoItemEntity, Long> {

    List<TodoItemEntity> findTodoItemsByListId(String listId);
}
