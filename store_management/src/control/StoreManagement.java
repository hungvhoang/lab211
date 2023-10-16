package control;

import bussiness.ProductManage;
import bussiness.WarehouseManage;
import tools.Tools;

public class StoreManagement {
    public static void main(String[] args) throws Exception {
        ProductManage productList = new ProductManage();
        WarehouseManage warehouse = new WarehouseManage(productList);
        productList.loadFromFile();
        warehouse.loadFromFile();
        String mainMenu[] = {"Manage Product","Manage Warehouse","Report","Store data to file","Close the application"};
        int choice =0;
        do {
            System.out.println("\t\tStore Management at Convenience Store");
            choice = Tools.drawMenu(mainMenu);
            switch(choice){
                case 1: 
                    ProductMenu.productMenu(productList);
                    break;
                case 2:
                    WarehouseMenu.warehouseMenu(warehouse);
                    break;
                case 3:
                    ReportMenu.repotMenu(productList, warehouse);
                    break;
                case 4:
                    productList.saveToFile();
                    warehouse.saveToFile();
                    System.out.println("Data have been saved.");
                    break;
                case 5:
                    productList.saveToFile();
                    warehouse.saveToFile();
                    System.out.println("Data have been saved. Program Ended.\nHave a nice day!");
                    break;
                default:
                    System.out.println("Enter from 1-5 please.");
                    break;
            }
        } while (choice != 5);
    }
}
