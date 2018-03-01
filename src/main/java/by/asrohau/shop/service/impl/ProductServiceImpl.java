package by.asrohau.shop.service.impl;

import by.asrohau.shop.bean.Product;
import by.asrohau.shop.dao.ProductDAO;
import by.asrohau.shop.dao.DAOFactory;
import by.asrohau.shop.service.ProductService;
import by.asrohau.shop.service.exception.ServiceException;

public class ProductServiceImpl implements ProductService {

	private final ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	
	public ProductServiceImpl(){}

	@Override
	public boolean findProduct(Product product) throws ServiceException {
		return false;
	}

	@Override
	public boolean addNewProduct(Product newProduct) throws ServiceException {
		return false;
	}

	@Override
	public boolean deleteProduct(Product product) throws ServiceException {
		return false;
	}

	@Override
	public boolean updateProduct(Product product, String[] productInfo) throws ServiceException {
		return false;
	}

	@Override
	public boolean reserveProduct(Product product) throws ServiceException {
		return false;
	}
}
