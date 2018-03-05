package by.asrohau.shop.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		try {
			Map commandMap = CommandFactory.getInstance().getCommandMap();
			Command command = (Command) commandMap.get(request.getParameter("command"));
			command.execute(request, response);

		} catch (ControllerException e) {
			request.setAttribute("errorMessage", e.toString());
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}

	}

}
