package com.example.testproject.command;

import com.example.testproject.command.impl.*;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOAD_RESUMES(new LoadResumesCommand()),
    SAVE_RESUME(new SaveResumeCommand());
//    LOGOUT(new LogoutCommand() ),
//    DEFAULT(new DefaultCommand()),
//    CHANGE_PROFILE(new ChangeProfileCommand()),
//    SETLOCALE(new SetLocaleCommand()),
//    FILE_UPLOAD(new FileUploadCommand()),
//    FILE_DOWNLOAD(new FileDownloadCommand());
    Command command;
    CommandType(Command command) {
        this.command = command;
    }
    public static Command define(String commandStr){
        CommandType current =  CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
