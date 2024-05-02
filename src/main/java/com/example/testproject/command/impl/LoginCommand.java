package com.example.testproject.command.impl;
import com.example.testproject.command.Command;
import com.example.testproject.service.UserService;
import com.example.testproject.service.impl.UserServiceImpl;
import com.mysql.cj.Session;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        UserService userService = UserServiceImpl.getInstance();
        String page;
        if(userService.authentificate(login,password)){
            HttpSession session = request.getSession();//todo new
            session.setAttribute("user",login);//todo new
            //request.setAttribute("user",login);
            page = "pages/main.jsp";
        } else {
           request.setAttribute("login_msg","incorrect login or password");
           page = "index.jsp";
        }

        return page;
    }
}
