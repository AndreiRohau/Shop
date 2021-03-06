package by.asrohau.shop.dao.impl;

import by.asrohau.shop.bean.Order;
import by.asrohau.shop.bean.Reserve;
import by.asrohau.shop.bean.Product;
import by.asrohau.shop.dao.DAOFactory;
import by.asrohau.shop.dao.OrderDAO;
import by.asrohau.shop.dao.exception.DAOException;
import by.asrohau.shop.service.OrderService;
import by.asrohau.shop.service.ProductService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.exception.ServiceException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class OrderDAOImplTest {

    @Test
    public void selectAllReservedTest(){

        int user_id = 46; // login 1, psw 1
        int row = 12;
        ServiceFactory sf = ServiceFactory.getInstance();
        OrderService orderService = sf.getOrderService();

        try {
            ArrayList<Product> ids = orderService.getAllReserved(user_id, row);

            for(Product product : ids) {
                System.out.println(product);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void WselectAllReservedTest(){


        int user_id = 46; // login 1, psw 1
        int row = 12;
        ServiceFactory sf = ServiceFactory.getInstance();
        OrderService orderService = sf.getOrderService();
        ProductService productService = sf.getProductService();
        try {
            ArrayList<Product> reservedWithIdsList = orderService.getAllReserved(user_id, row);

            for(Product p : reservedWithIdsList) {
                System.out.println(p);
            }
            System.out.println();

            ArrayList<Product> productArray = new ArrayList<>();
            Product product;
            for(Product prod : reservedWithIdsList){
                product = productService.findProductWithId(prod);
                product.setReserve_id(prod.getReserve_id());
                productArray.add(product);
                product = new Product();
            }
            System.out.println();
            for (Product p : productArray){
                System.out.println(p);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void countReservedTest(){

        int user_id = 46; // login 1, psw 1

        ServiceFactory sf = ServiceFactory.getInstance();
        OrderService orderService = sf.getOrderService();

        try {
            int i = orderService.countReserved(user_id);
            System.out.println(i);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectAllReservedIdsTest(){

        int user_id = 46; // login 1, psw 1

        ServiceFactory sf = ServiceFactory.getInstance();
        OrderService orderService = sf.getOrderService();

        try {
            LinkedList<Integer> ids = orderService.getAllReservedIds(user_id);
            for(int id : ids){
                System.out.println(id);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void selectAllNewOrdersTest() throws DAOException{

        int user_id = 1; // login 1, psw 1
        String status = "new";
        DAOFactory daoFactory = DAOFactory.getInstance();
        OrderDAO orderDAO = daoFactory.getOrderDAO();

        ArrayList<Order> orders = orderDAO.selectAllOrders(user_id, status);
        for(Order order : orders){
            System.out.println(order);
        }

    }

    @Test
    public void deleteAllReservedTest() throws DAOException {

        int user_id = 36; // login 3, psw 3

        DAOFactory daoFactory = DAOFactory.getInstance();
        OrderDAO orderDAO = daoFactory.getOrderDAO();

        //before
        for (int i = 1; i <= 5; i++) {
            Reserve reserve = new Reserve(user_id, i);
            orderDAO.saveNewReservation(reserve);
        }

        int i = orderDAO.countReserved(user_id);
        System.out.println(i);

        LinkedList<Integer> listIds = orderDAO.selectAllReservedIds(user_id);
        for(int id : listIds){
            System.out.println(listIds);
        }

        //try
        boolean isDeleted = orderDAO.deleteAllReserved(user_id);
        System.out.println(isDeleted);

        //catch

        listIds = orderDAO.selectAllReservedIds(user_id);
        System.out.println("deleted " + listIds);
    }

    @Test
    public void insertNewOrderTest() throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        OrderDAO orderDAO = daoFactory.getOrderDAO();

        Order order = new Order(1, "1,2,2,3,3,4,", "minsk", "1234567");
        System.out.println(orderDAO.insertNewOrder(order));

    }

    @Test
    public void countAllNewOrdersTest() throws DAOException {

        int user_id = 46; // login 1, psw 1
        int row = 15;
        String status = "successful";

        DAOFactory daoFactory = DAOFactory.getInstance();
        OrderDAO orderDAO = daoFactory.getOrderDAO();


        int i = orderDAO.countOrders(status);
        System.out.println(i);
    }

    @Test
    public void deleteNewOrderTest() throws DAOException {

        int orderId = 2;

        DAOFactory daoFactory = DAOFactory.getInstance();
        OrderDAO orderDAO = daoFactory.getOrderDAO();


        boolean i = orderDAO.deleteOrder(orderId);
        System.out.println(i);
    }
    @Test
    public void updateOrderSetActiveTest() throws DAOException {

        int orderId = 1;
        String status = "active";
        DAOFactory daoFactory = DAOFactory.getInstance();
        OrderDAO orderDAO = daoFactory.getOrderDAO();


        boolean i = orderDAO.updateOrderSetStatus(orderId, status);
        System.out.println(i);
    }
    @Test
    public void selectOrderWithIDTest() throws DAOException {

        int orderId = 1;

        DAOFactory daoFactory = DAOFactory.getInstance();
        OrderDAO orderDAO = daoFactory.getOrderDAO();


        Order o = orderDAO.selectOrderWithID(orderId);
        //System.out.println(i);
        String[] ids = o.getProductIDs().split(",");
        for(String id : ids){
            System.out.println(id);
        }
    }

    @Test
    public void updateOrdersProductsTest() throws DAOException {

        int orderId = 1;
        String pIDs = "1,2,2,3,3,4,";
        //String pIDs = "1,2,3,4,";
        Order order = new Order();
        order.setId(orderId);
        order.setProductIDs(pIDs);
        DAOFactory daoFactory = DAOFactory.getInstance();
        OrderDAO orderDAO = daoFactory.getOrderDAO();


        boolean i = orderDAO.updateOrdersProducts(order);
        System.out.println(i);
    }
}
