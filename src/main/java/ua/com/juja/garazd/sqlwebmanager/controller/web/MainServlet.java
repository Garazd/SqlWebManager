package ua.com.juja.garazd.sqlwebmanager.controller.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.com.juja.garazd.sqlwebmanager.service.Service;
import ua.com.juja.garazd.sqlwebmanager.service.ServiceImpl;

public class MainServlet extends HttpServlet {

    private Service service;

    @Override
    public void init() throws ServletException {
        super.init();

        service = new ServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        if (action.startsWith("/menu") || action.equals("/")) {
            request.setAttribute("items", service.commandsList());
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } else if (action.startsWith("/help")) {
            request.getRequestDispatcher("help.jsp").forward(request, response);
        } else if (action.startsWith("/connect")) {
            request.getRequestDispatcher("connect.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private String getAction(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String action = requestURI.substring(request.getContextPath().length(), requestURI.length());
        return action;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        if (action.startsWith("/connect")) {
            String databaseName = request.getParameter("databaseName");
            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                service.connect(databaseName, userName, password);
                response.sendRedirect(response.encodeRedirectURL("menu"));
            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
}