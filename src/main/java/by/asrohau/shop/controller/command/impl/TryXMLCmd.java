package by.asrohau.shop.controller.command.impl;

import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rohau.andrei on 25-Feb-18.
 */
public class TryXMLCmd implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("try XML");
        request.getSession().setAttribute("address", "/jsp/tryxml.jsp");
        try {
            String goToPage = "/jsp/tryxml.jsp";

            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new ControllerException(e);
        } catch (IOException e) {
            throw new ControllerException(e);
        }

    }
}
