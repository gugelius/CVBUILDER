package com.example.testproject.command;

import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request, JsonObject jsonObject, HttpServletResponse response) throws IOException;
    default void refresh(){}
}
