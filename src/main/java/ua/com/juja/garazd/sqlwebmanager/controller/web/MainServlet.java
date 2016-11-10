package ua.com.juja.garazd.sqlwebmanager.controller.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        if (action.equals("/menu")) {
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } else if (action.equals("/help")) {
            request.getRequestDispatcher("help.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private String getAction(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String action = requestURI.substring(request.getContextPath().length(), requestURI.length());
        return action;
    }
}