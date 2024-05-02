package com.example.testproject.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response);
    default void refresh(){}
}
