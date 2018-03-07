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

        try {
            String goToPage;
            if (request.getSession().getAttribute("userName") != null) {

                if(request.getSession().getAttribute("userName").equals("Admin") &&
                        request.getParameter("address").matches("/jsp/user/main.jsp")) {

                    goToPage = "/jsp/admin/main.jsp";
                    System.out.println("aaaaadmin" + request.getSession().getAttribute("userName"));
                } else {
                    goToPage = request.getParameter("address");
                    System.out.println("uuuuser" + request.getSession().getAttribute("userName"));
                }

            } else {
                goToPage = "index.jsp";
            }
            request.getSession().setAttribute("address", goToPage);
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);

            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            throw new ControllerException(e);
        }
    }
}
