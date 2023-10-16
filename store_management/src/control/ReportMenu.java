package control;

import bussiness.ProductManage;
import bussiness.WarehouseManage;
import tools.Tools;

public class ReportMenu {
    public static  void repotMenu(ProductManage productsList, WarehouseManage warehouseList){
        String reportMenu[]= {"Products that have expired","The products that the store is selling","Products that are running out of stock (sorted in ascending order)","Import/export receipt of a product","Back to main menu"};
        int choice =0;
        do {
            System.out.println("\t\t3. Report");
            choice = Tools.drawMenu(reportMenu);
            switch(choice){
                case 1:
                    productsList.expired();
                    break;
                case 2:
                    productsList.selling();
                    break;
                case 3: 
                    productsList.outOfStock();
                    break;
                case 4:
                    warehouseList.displayByProduct();
                    break;
                case 5:
                    break;
                default: 
                    System.out.println("Enter from 1 to 5 please");
            }
        } while (choice != 5);
    }
}
