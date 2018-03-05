package by.asrohau.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.AdminService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.UserService;
import by.asrohau.shop.service.exception.ServiceException;

public class LoginationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		System.out.println("We got to logination");

		User user = new User(request.getParameter("login"),  request.getParameter("password"));
		UserDTO userDTO;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		String goToPage;

		try {
			if(!request.getParameter("login").equals("Admin")){
				UserService userService = serviceFactory.getUserService();
				userDTO = userService.logination(user);
				goToPage = "/jsp/user/main.jsp";
			} else {
				AdminService adminService = serviceFactory.getAdminService();
				userDTO = adminService.logination(user);
				goToPage = "/jsp/admin/admin.jsp";
			}
			HttpSession session = request.getSession(true);
			if (userDTO != null) {
				//стартануть сессию юзера конкретного - first using request attributes, now trying to use session

				//request.setAttribute("userName", userDTO.getLogin());
				session.setAttribute("userName", userDTO.getLogin());


			} else {
				goToPage = "error.jsp";
				request.setAttribute("errorMessage", "no such user");
			}
			System.out.println(request.getSession().getAttribute("userName"));
			System.out.println(session.getAttribute("userName"));
			request.setAttribute("atr", "myatr");
			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);

		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}

	}

}
