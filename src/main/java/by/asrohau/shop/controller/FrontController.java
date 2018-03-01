package by.asrohau.shop.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.command.CommandFactory;
import by.asrohau.shop.controller.exception.ControllerException;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final CommandFactory commandFactory = CommandFactory.getInstance();

	public FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("in servlet : command : " + request.getParameter("command"));

		try {
			Map commandMap = CommandFactory.getInstance().getCommandMap();
			Command command = (Command) commandMap.get(request.getParameter("command"));

			HttpSession session = request.getSession(true);
			session.getId(); //to dev!!!

			command.execute(request, response);

		} catch (ControllerException e) {
			request.setAttribute("errorMessage", e.toString());
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}

	}

}
