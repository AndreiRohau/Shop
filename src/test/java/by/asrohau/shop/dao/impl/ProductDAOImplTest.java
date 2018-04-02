package by.asrohau.shop.dao.impl;

import by.asrohau.shop.bean.Product;
import by.asrohau.shop.dao.AbstractDAO;
import by.asrohau.shop.dao.DAOFactory;
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
	public void deleteProductTest() throws ServiceException {
		Product newProduct = new Product("Sony", "Vaio", "Laptop", "3000");
		Product existingProduct;


		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ProductService productService = serviceFactory.getProductService();

		productService.addNewProduct(newProduct); //didnt find cause there was no duplicate
		existingProduct = productService.findProduct(newProduct); // pull product to check its existance
		System.out.println("existingProduct" + existingProduct);

		try {
			boolean b = productService.deleteProduct(existingProduct);
			System.out.println("was deleted - " + b);
			System.out.println("try to find deleted product " + productService.findProduct(existingProduct));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void selectAllProductsTest() throws ServiceException {
		int row = 24;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ProductService productService = serviceFactory.getProductService();
		ArrayList<Product> productArrayList = new ArrayList<Product>();
		productArrayList = productService.getAllProducts(row);

		for(Product p : productArrayList){
			System.out.println(p);
		}
	}

	@Test
	public void countProductsTest() throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ProductService productService = serviceFactory.getProductService();

		int i = productService.countProducts();
		System.out.println(i);
	}

	@Test
	public void countProductsComprehensiveTest() throws DAOException {

		//comes with
		String company = "Panasonic";
		String name = "";
		String type = "TV";
		String price = "";

		//setting
		String check = "";
		company = company.trim();
		name = name.trim();
		type = type.trim();
		price = price.trim();

		Product product = new Product();
		if (!company.equals(check)) {
			product.setCompany(company);
		}
		if (!name.equals(check)) {
			product.setName(name);
		}
		if (!type.equals(check)) {
			product.setType(type);
		}
		if (!price.equals(check)) {
			product.setPrice(price);
		}

		//before
		DAOFactory daoFactory = DAOFactory.getInstance();
		ProductDAO productDAO = daoFactory.getProductDAO();

		int i = productDAO.countProductsComprehensive(product);
		System.out.println(i);
	}

	@Test
	public void selectProductsComprehensiveTest() throws DAOException {

		//comes with
		String company = "";
		String name = "";
		String type = "TV";
		String price = "";

		int row = 0;

		//setting
		String check = "";
		company = company.trim();
		name = name.trim();
		type = type.trim();
		price = price.trim();

		Product product = new Product();
		if (!company.equals(check)) {
			product.setCompany(company);
		}
		if (!name.equals(check)) {
			product.setName(name);
		}
		if (!type.equals(check)) {
			product.setType(type);
		}
		if (!price.equals(check)) {
			product.setPrice(price);
		}

		//before
		DAOFactory daoFactory = DAOFactory.getInstance();
		ProductDAO productDAO = daoFactory.getProductDAO();

		ArrayList<Product> i = productDAO.selectProductsComprehensive(product, row);
		for(Product p : i){
			System.out.println(p);
		}
	}



}
