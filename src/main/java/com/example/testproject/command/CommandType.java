package com.example.testproject.command;

import com.example.testproject.command.impl.*;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOAD_RESUMES(new LoadResumesCommand()),
    SAVE_RESUME(new SaveResumeCommand()),
    LOAD_RESUME(new LoadResumeCommand()),
    UPDATE_RESUME(new UpdateResumeCommand()),
    DELETE_RESUME(new DeleteResumeCommand());
    Command command;
    CommandType(Command command) {
        this.command = command;
    }
    public static Command define(String commandStr){
        CommandType current =  CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
