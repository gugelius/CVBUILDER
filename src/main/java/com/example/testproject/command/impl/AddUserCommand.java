package com.example.testproject.command.impl;
import com.example.testproject.command.Command;
import com.example.testproject.service.UserService;
import com.example.testproject.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddUserCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        UserService userService = UserServiceImpl.getInstance();
        /*прошлая версия валидации*/
//        boolean result1 = userService.register(login,password);
//        String page;
//        if(result1){
//            request.setAttribute("user",login);
//            page = "pages/main.jsp";
//        } else {
//            request.setAttribute("login_msg","incorrect login or password");
//            page = "registration.jsp";
//        }
//
//        return page;
        String page;
        if(userService.validation(password)){
            if(userService.register(login,password)) {
                //request.setAttribute("user", login);
                HttpSession session = request.getSession();//todo new
                session.setAttribute("user",login);//todo new
                page = "pages/main.jsp";
            }else{
                request.setAttribute("pass",password);
                request.setAttribute("reg_msg","incorrect login");
                page = "registration.jsp";
            }
        }else {
            request.setAttribute("login_value",login);
            request.setAttribute("reg_msg","password must be 4 or more symbols");
            page="registration.jsp";
        }
        return page;
    }
}

