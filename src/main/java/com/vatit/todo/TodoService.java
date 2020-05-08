package com.vatit.todo;

import java.util.List;

public interface TodoService {
    List<TodoItem> getTodoItems(String listId);

    String addItem(String listId, String text);

    String createList(String name);

    List<TodoList> getTodoLists();
}
