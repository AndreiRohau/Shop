package by.asrohau.shop.service;

import by.asrohau.shop.bean.Order;
import by.asrohau.shop.bean.Reserve;
import by.asrohau.shop.bean.Product;
import by.asrohau.shop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.LinkedList;

public interface OrderService {
    boolean validation(Reserve reserve);
    boolean saveReserve(Reserve reserve) throws ServiceException;
    ArrayList<Product> getAllReserved(int user_id, int row) throws ServiceException;
    int countReserved(int user_id) throws ServiceException;
    boolean deleteReserved(int reserveId) throws ServiceException;

    LinkedList<Integer> getAllReservedIds(int user_id) throws ServiceException;

    boolean deleteAllReserved(int user_id) throws ServiceException;

    boolean saveNewOrder(Order order) throws ServiceException;

    int countNewOrders() throws ServiceException;

    ArrayList<Order> getAllNewOrders(int row) throws ServiceException;

    boolean deleteNewOrder(int orderId) throws ServiceException;

    boolean orderSetActive(int orderId) throws ServiceException;

    Order findOrderWithID(int orderId) throws ServiceException;

    boolean deleteProductFromOrder(Order order) throws ServiceException;
}
