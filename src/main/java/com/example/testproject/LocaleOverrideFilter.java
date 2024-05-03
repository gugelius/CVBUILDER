package com.example.testproject;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
@WebFilter(urlPatterns = {"/controller","*.jsp"})
public class LocaleOverrideFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        if (session != null) {
            String sessionLocale = (String) session.getAttribute("jlocale");
            if (sessionLocale == null) {
                Locale locale = new Locale("ru");
//                httpRequest.setAttribute("org.apache.catalina.core.DISPATCHER_REQUEST_LOCALE", locale);
                session.setAttribute("jlocale","ru");
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
