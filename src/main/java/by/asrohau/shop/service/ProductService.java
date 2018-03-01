package by.asrohau.shop.service;

import by.asrohau.shop.bean.Product;
import by.asrohau.shop.service.exception.ServiceException;

public interface ProductService {

	boolean findProduct(Product product) throws ServiceException;
	boolean addNewProduct(Product newProduct) throws ServiceException;
	boolean deleteProduct(Product product) throws ServiceException;
	boolean updateProduct(Product product, String[] productInfo) throws ServiceException;
	boolean reserveProduct(Product product) throws ServiceException;
	
}
