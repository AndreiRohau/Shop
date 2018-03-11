package by.asrohau.shop.service.impl;

import by.asrohau.shop.bean.Product;
import by.asrohau.shop.dao.ProductDAO;
import by.asrohau.shop.dao.DAOFactory;
import by.asrohau.shop.dao.exception.DAOException;
import by.asrohau.shop.service.ProductService;
import by.asrohau.shop.service.exception.ServiceException;

public class ProductServiceImpl implements ProductService {

	private final ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	
	public ProductServiceImpl(){}

	@Override
	public boolean validation(Product product) {
		String toCompare = "";
		if (!toCompare.equals(product.getName()) &&
				!toCompare.equals(product.getType()) &&
				!toCompare.equals(product.getPrice())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Product findProduct(Product product) throws ServiceException {
		// validation!!! stub
		if (validation(product)) {
			try {
				return productDAO.findProduct(product);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return null;
	}

	@Override
	public Product findProductWithName(Product product) throws ServiceException {
		return null;
	}

	@Override
	public boolean addNewProduct(Product newProduct) throws ServiceException {
		// validation!!! stub
		if (validation(newProduct)) {

			try {
				if (productDAO.findProduct(newProduct) == null) {
					return productDAO.addProduct(newProduct);
				}
				Product product = productDAO.findProduct(newProduct); //for test
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		System.out.println("findProduct(newProduct) " + productDAO.toString());
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
