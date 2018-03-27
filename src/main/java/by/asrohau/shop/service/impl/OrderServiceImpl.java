package by.asrohau.shop.service.impl;

import by.asrohau.shop.bean.Order;
import by.asrohau.shop.bean.Reserve;
import by.asrohau.shop.bean.Product;
import by.asrohau.shop.dao.DAOFactory;
import by.asrohau.shop.dao.exception.DAOException;
import by.asrohau.shop.dao.OrderDAO;
import by.asrohau.shop.service.OrderService;
import by.asrohau.shop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.LinkedList;

public class OrderServiceImpl implements OrderService{

    private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

    public OrderServiceImpl() {
    }

    @Override
    public boolean validation(Reserve reserve) {
        return reserve.getUser_id() != 0 && reserve.getProduct_id() != 0;
    }

    @Override
    public boolean saveReserve(Reserve reserve) throws ServiceException {
        try {
            return validation(reserve) && orderDAO.saveNewReservation(reserve);
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

    @Override
    public LinkedList<Integer> getAllReservedIds(int user_id) throws ServiceException {
        try {
            return orderDAO.selectAllReservedIds(user_id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteAllReserved(int user_id) throws ServiceException {
        try {
            return orderDAO.deleteAllReserved(user_id);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean saveNewOrder(Order order) throws ServiceException {
        try {
            return orderDAO.insertNewOrder(order);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public int countNewOrders() throws ServiceException {
        try {
            return orderDAO.countNewOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Order> getAllNewOrders(int row)  throws ServiceException{
        try {
            return orderDAO.selectAllNewOrders(row);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public boolean deleteNewOrder(int orderId) throws ServiceException {
        try {
            return orderDAO.deleteNewOrder(orderId);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean orderSetActive(int orderId) throws ServiceException {
        try {
            return orderDAO.updateOrderSetActive(orderId);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Order findOrderWithID(int orderId)  throws ServiceException{
        try {
            return orderDAO.selectOrderWithID(orderId);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteProductFromOrder(Order order) throws ServiceException {
        try {
            return orderDAO.updateOrdersProducts(order);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }
}
