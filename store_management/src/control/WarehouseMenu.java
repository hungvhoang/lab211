package control;

import bussiness.WarehouseManage;
import tools.Tools;

public class WarehouseMenu {
    public static void warehouseMenu(WarehouseManage warehouse){
        String warehouseMenu[] ={"Create an import receipt","Create an export receipt","Back to main menu"};
        int choice = 0;
        do {
            System.out.println("\t\t2. Manage Warehouse:");
            choice = Tools.drawMenu(warehouseMenu);
            switch(choice){
                case 1:
                    warehouse.createImport();
                    break;
                case 2:
                    warehouse.createExport();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Enter from 1-3 please.");
                    break;
            }
        } while (choice != 3);
    }
}
