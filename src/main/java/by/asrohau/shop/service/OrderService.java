package by.asrohau.shop.service;

import by.asrohau.shop.bean.Order;
import by.asrohau.shop.service.exception.ServiceException;

import java.util.LinkedList;

public interface OrderService {
    boolean validation(Order order);
    boolean reserveOrder(Order order) throws SecurityException;
    LinkedList<Integer> getAllReserved(int user_id, int row) throws ServiceException;
    int countReserved(int user_id) throws  ServiceException;

}
