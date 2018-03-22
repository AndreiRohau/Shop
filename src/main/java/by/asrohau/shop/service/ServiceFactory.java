package by.asrohau.shop.service;

import by.asrohau.shop.service.impl.AdminServiceImpl;
import by.asrohau.shop.service.impl.OrderServiceImpl;
import by.asrohau.shop.service.impl.ProductServiceImpl;
import by.asrohau.shop.service.impl.UserServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory INSTANCE = new ServiceFactory();

	private final UserService userService = new UserServiceImpl();
	private final AdminService adminService = new AdminServiceImpl();
	private final ProductService productService = new ProductServiceImpl();
	private final OrderService orderService = new OrderServiceImpl();

	private ServiceFactory() {}

	public static ServiceFactory getInstance() {
		return INSTANCE;
	}

	public UserService getUserService() {
		return userService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public OrderService getOrderService() {
		return orderService;
	}
}
