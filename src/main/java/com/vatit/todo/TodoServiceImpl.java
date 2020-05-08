package com.vatit.todo;

import com.vatit.todo.entity.TodoItemEntity;
import com.vatit.todo.entity.TodoListEntity;
import com.vatit.todo.entity.TodoListRepository;
import com.vatit.todo.entity.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private TodoListRepository todoListRepository;

    @Override
    public List<TodoItem> getTodoItems(String listId) {
        var entities = todoItemRepository.findTodoItemsByListId(listId);
        return entities
                .stream()
                .map(entity -> new TodoItem(entity.getId(), entity.getText(), entity.getList().getId(), entity.getList().getName()))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String addItem(String listId, String text) {
        TodoListEntity list = todoListRepository.getOne(listId);
        TodoItemEntity todoItem = new TodoItemEntity();
        todoItem.setList(list);
        todoItem.setText(text);
        todoItem = todoItemRepository.save(todoItem);
        return todoItem.getId();
    }

    @Override
    public String createList(String name) {
        TodoListEntity list = new TodoListEntity();
        list.setName(name);
        list = todoListRepository.save(list);
        return list.getId();
    }

    @Override
    public List<TodoList> getTodoLists() {
        return todoListRepository.findAll()
                .stream()
                .map(entity -> new TodoList(entity.getId(), entity.getName()))
                .collect(Collectors.toUnmodifiableList());
    }
}
