package by.asrohau.shop.controller.command.impl;

import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to GoToPageCommand");

        System.out.println("request.getContextPath(): " + request.getContextPath());
        System.out.println("request.getPathInfo(): " + request.getPathInfo());
        System.out.println("request.getRequestURI(): " + request.getRequestURI());
        System.out.println("request.getRequestURL(): " + request.getRequestURL());

        try {
            String goToPage = request.getParameter("address");
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);

            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            throw new ControllerException(e);
        }
    }
}
