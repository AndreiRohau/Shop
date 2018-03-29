package by.asrohau.shop.dao.impl;

import by.asrohau.shop.service.OrderService;
import by.asrohau.shop.service.ServiceFactory;
import org.junit.Test;

public class FastTest {

    @Test
    public void getStringIdsFromOrderTest() {
//      @Before
        int currentPage = 3;

        String orderProdIDsEx = "1,2,3,4,5,6,7,8,9,10,11,12,12,12,13,14,15,112,1,2,3,4,5,6,7,8,9,5,21,21,32,55,45,";
        //String orderProdIDsEx = "1,2,3,4,5,6,7,8,9,10,11,12,12,12,13,14,15,112,1,2,3,4,5,6,7,8,9,5,21,21,";
        //String orderProdIDsEx = "1,2,3,4,5,6,7,8,9,10,11,12,12,12,13,14,15,112,1,2,3,4,5,6,";

//      @Test

        //currentPage is 1||2||3||4||5.........
        String[] productIDsArray = orderProdIDsEx.split(",");
        int maxPage = (int) Math.ceil(((double) productIDsArray.length) / 15);
        int row = (currentPage - 1) * 15; //

        int finArrlength = currentPage < maxPage ? 15 : (productIDsArray.length % 15 == 0 ? 15 : productIDsArray.length % 15);

        String[] finalArray = new String[finArrlength];
        //доставать из productIDsArray промежуток if(1)[1-15] - if(2)[16-30] - if(3)[31-45]......
        for (int i = 0; i < finArrlength; i++) {
            System.out.println(i);
            finalArray[i] = productIDsArray[i + row];
        }

        int k = 0;
        for (String x : finalArray) {
            System.out.println(k + " : " + x);
            k++;
        }


    }

    @Test
    public void changingStringIdsFromOrderTest() {
//      @Before
        int currentPage = 3;

        String orderProdIDsEx = "1,2,3,4,5,6,7,8,9,10,11,12,12,12,13,14,15,112,1,2,3,4,5,6,7,8,9,5,21,21,32,55,45,";
        //String orderProdIDsEx = "1,2,3,4,5,6,7,8,9,10,11,12,12,12,13,14,15,112,1,2,3,4,5,6,7,8,9,5,21,21,";
        //String orderProdIDsEx = "1,2,3,4,5,6,7,8,9,10,11,12,12,12,13,14,15,112,1,2,3,4,5,6,";

//      @Test

        //currentPage is 1||2||3||4||5.........
        String[] productIDsArray = orderProdIDsEx.split(",");
        int maxPage = (int) Math.ceil(((double) productIDsArray.length) / 15);
        int row = (currentPage - 1) * 15; //

        int finArrlength = currentPage < maxPage ? 15 : (productIDsArray.length % 15 == 0 ? 15 : productIDsArray.length % 15);

        String[] finalArray = new String[finArrlength];
        //доставать из productIDsArray промежуток if(1)[1-15] - if(2)[16-30] - if(3)[31-45]......
        for (int i = 0; i < finArrlength; i++) {
            System.out.println(i);
            finalArray[i] = productIDsArray[i + row];
        }

        int k = 0;
        for (String x : finalArray) {
            System.out.println(k + " : " + x);
            k++;
        }
    }


    @Test
    public void deleteFromOrderTest() {
//      @Before
        int currentPage = 2;
        int counter = 15; // from [1,15]
        String productIDsStringEx = "1,2,3,4,5,6,7,8,9,10,11,12,12,12,13,14,15,112,1,2,3,4,5,6,7,8,9,5,21,21,32,55,45,";
        //String productIDsStringEx = "1,2,3,4,5,6,7,8,9,10,11,12,12,12,13,14,15,112,1,2,3,4,5,6,7,8,9,5,21,21,";
        //String productIDsStringEx = "1,2,3,4,5,6,7,8,9,10,11,12,12,12,13,14,15,112,1,2,3,4,5,6,";
        System.out.println(productIDsStringEx);
//      @Test

        String[] productIDsArray = productIDsStringEx.split(",");

        int indexOfdeletingProd = (currentPage - 1) * 15 + counter;

        String finalIDs = "";
        counter = 1;
        for(String id : productIDsArray){
            if(counter != indexOfdeletingProd) {
                finalIDs = finalIDs + id + ",";
            }
            counter++;
        }
        System.out.println(finalIDs);


    }

    @Test
    public void stringSplitTest(){
        String s = "orderSetSuccessful";
        String[]sa = s.split("Set");
        for(String x : sa){
            System.out.println("E [" + x + "]");
        }

        System.out.println("[" + s.split("Set")[1].toLowerCase() + "]");
    }
}