package by.asrohau.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.OrderService;
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
		OrderService orderService = serviceFactory.getOrderService();
		User userDTO = new User();


		boolean isLogedin; // = false

		try {
			//getting actual user's ID
			int user_id = userService.findIdWithLogin(user).getId();

			boolean isUser = request.getSession().getAttribute("userName").equals(user.getLogin());
			boolean isAdmin = request.getSession().getAttribute("userName").equals("Admin");
			isLogedin = isUser || isAdmin;
			isLogedin = (isLogedin && userService.deleteUser(user));
			String goToPage;
			String lastCMD;
			if (isLogedin && isUser) {
				orderService.deleteAllReserved(user_id);
				request.getSession().invalidate();
				goToPage = "index.jsp";
				lastCMD = "FrontController?command=goToPage&address=index.jsp";
			} else if (isLogedin) {
				orderService.deleteAllReserved(user_id);
				goToPage = "/jsp/admin/manageClients.jsp";
				lastCMD = "FrontController?command=goToPage&address=manageClients.jsp";
			} else if (!isAdmin){
				goToPage = "/jsp/user/profile.jsp";
				request.setAttribute("errorMessage", "cannot delete user");
				lastCMD = "FrontController?command=goToPage&address=profile.jsp";
			} else {
				goToPage = "error.jsp";
				request.setAttribute("errorMessage", "cannot delete user");
				lastCMD = "FrontController?command=goToPage&address=manageClients.jsp";
			}

			request.getSession().setAttribute("lastCMD", lastCMD);
			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);
			
		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}

	}

}
