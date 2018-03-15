package by.asrohau.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.UserService;
import by.asrohau.shop.service.exception.ServiceException;

public class DeleteUserCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		System.out.println("We got to delete User Command");

		User user = new User(request.getParameter("login").trim(), request.getParameter("password").trim());
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		boolean isChanged = false;

		try {
			boolean isUser = request.getSession().getAttribute("userName").equals(user.getLogin());
			boolean isAdmin = request.getSession().getAttribute("userName").equals("Admin");
			isChanged = isUser || isAdmin;
			isChanged = (isChanged && userService.deleteUser(user));
			String goToPage;
			if (isChanged && isUser) {
				request.getSession().invalidate();
				goToPage = "index.jsp";
			} else if (isChanged) {
				goToPage = "/jsp/admin/manageClients.jsp";
			} else {
				goToPage = "/jsp/user/profile.jsp";
				request.setAttribute("errorMessage", "cannot delete user");
			}
			request.getSession().setAttribute("address", goToPage);
			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);
			
		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}

	}

}
