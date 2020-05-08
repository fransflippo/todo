package com.vatit.todo.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
record TodoListResource(String name, String url, String itemsUrl) {
}
