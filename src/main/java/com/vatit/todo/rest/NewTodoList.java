package com.vatit.todo.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

record NewTodoList(@JsonProperty("name") String name) {
    @JsonCreator
    public NewTodoList {}
}
