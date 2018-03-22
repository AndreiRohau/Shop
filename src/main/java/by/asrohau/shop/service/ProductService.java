package by.asrohau.shop.service;

import by.asrohau.shop.bean.Product;
import by.asrohau.shop.service.exception.ServiceException;

import java.util.ArrayList;

public interface ProductService {

	boolean validation(Product product);
	boolean addNewProduct(Product newProduct) throws ServiceException;
	ArrayList<Product> getAllProducts(int row) throws ServiceException;
	Product findProductWithId(Product product) throws ServiceException;
	boolean updateProduct(Product product) throws ServiceException;

	Product findProduct(Product product) throws ServiceException;
	boolean deleteProduct(Product product) throws ServiceException;
	int countProducts() throws  ServiceException;
	
}
