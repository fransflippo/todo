package com.vatit.todo.rest;

import com.vatit.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TodoRestController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/lists")
    public List<TodoListResource> getTodoLists(HttpServletRequest request) {
        return todoService.getTodoLists()
                .stream()
                .map(it -> {
                    StringBuffer listUrl = request.getRequestURL().append('/').append(it.id());
                    return new TodoListResource(it.name(), listUrl.toString(), listUrl.append("/items").toString());
                })
                .collect(Collectors.toUnmodifiableList());
    }

    @PostMapping("/lists")
    public ResponseEntity<Void> addItem(@RequestBody NewTodoList newList, HttpServletRequest request) {
        String id = todoService.createList(newList.name());
        StringBuffer location = request.getRequestURL().append("/").append(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, location.toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/lists/{listId}/items")
    public List<TodoItemResource> getTodoItems(@PathVariable("listId") String listId, HttpServletRequest request) {
        return todoService.getTodoItems(listId)
                .stream()
                .map(it -> {
                    StringBuffer url = request.getRequestURL().append('/').append(it.id());
                    return new TodoItemResource(it.text(), url.toString());
                })
                .collect(Collectors.toUnmodifiableList());
    }

    @PostMapping("/lists/{listId}/items")
    public ResponseEntity<Void> addItem(@PathVariable("listId") String listId, @RequestBody NewTodoItem newTodoItem, HttpServletRequest request) {
        String id = todoService.addItem(listId, newTodoItem.text());
        StringBuffer location = request.getRequestURL().append("/").append(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, location.toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

}
