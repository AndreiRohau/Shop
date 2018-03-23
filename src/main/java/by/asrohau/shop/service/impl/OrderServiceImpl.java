package by.asrohau.shop.service.impl;

import by.asrohau.shop.bean.Order;
import by.asrohau.shop.bean.Product;
import by.asrohau.shop.dao.DAOFactory;
import by.asrohau.shop.dao.exception.DAOException;
import by.asrohau.shop.dao.OrderDAO;
import by.asrohau.shop.service.OrderService;
import by.asrohau.shop.service.exception.ServiceException;

import java.util.ArrayList;

public class OrderServiceImpl implements OrderService{

    private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

    public OrderServiceImpl() {
    }

    @Override
    public boolean validation(Order order) {
        return order.getUser_id() != 0 && order.getProduct_id() != 0;
    }

    @Override
    public boolean reserveOrder(Order order) throws ServiceException {
        try {
            return validation(order) && orderDAO.saveNewReservation(order);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Product> getAllReserved(int user_id, int row) throws ServiceException {
        try {
            return orderDAO.selectAllReserved(user_id, row);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countReserved(int user_id) throws ServiceException {
        try {
            return orderDAO.countReserved(user_id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteReserved(int reserveId) throws ServiceException {
        try {
            return orderDAO.deleteReserved(reserveId);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }
}
