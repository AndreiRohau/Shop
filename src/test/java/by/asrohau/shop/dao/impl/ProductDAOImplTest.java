package by.asrohau.shop.dao.impl;

import by.asrohau.shop.bean.Product;
import by.asrohau.shop.dao.AbstractDAO;
import by.asrohau.shop.dao.ProductDAO;
import by.asrohau.shop.dao.exception.DAOException;
import by.asrohau.shop.service.ProductService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.exception.ServiceException;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImplTest {

	@Test
	public void findProductWithIdTest(){

		Product product = new Product();
		product.setId(7);

		ServiceFactory sf = ServiceFactory.getInstance();
		ProductService ps = sf.getProductService();

		Product product1 = null;
		try {
			product1 = ps.findProductWithId(product);
			System.out.println(product1.toString());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteProductTest(){
		Product product = new Product();
		product.setId(9);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ProductService productService = serviceFactory.getProductService();

		try {
			boolean b = productService.deleteProduct(product);
			System.out.println(b);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
