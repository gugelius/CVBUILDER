package com.example.testproject.command.impl;

import com.example.testproject.command.Command;
import com.example.testproject.service.UserService;
import com.example.testproject.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //String login = request.getParameter("login");
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("user");
        String oldPassword = request.getParameter("oldPass");
        String newLogin = request.getParameter("newLogin");
        String newPassword = request.getParameter("newPass");
        UserService userService = UserServiceImpl.getInstance();
        String page;
        if(userService.validation(newPassword)){
            if(userService.updateProfile(login,oldPassword,newLogin,newPassword)){
                //request.setAttribute("user",newLogin);
                session.setAttribute("user",newLogin);//todo new
                request.setAttribute("chng_msg","UPDATED");
                page = "pages/main.jsp";
            } else {
                request.setAttribute("user",login);
                request.setAttribute("chng_msg","failed to update");
                page = "pages/main.jsp";
            }
        }else {
            request.setAttribute("user",login);
            request.setAttribute("chng_msg","incorrect new password");
            page ="pages/main.jsp";
        }

        return page;
    }
}
