package by.asrohau.shop.dao;

import by.asrohau.shop.bean.Order;
import by.asrohau.shop.dao.exception.DAOException;

import java.util.LinkedList;

public interface OrderDAO {

    boolean saveNewReservation(Order order) throws DAOException;
    LinkedList<Integer> selectAllReserved(int user_id, int row) throws DAOException;
    int countReserved(int user_id) throws DAOException;
}
