package control;



import bussiness.ProductManage;
import tools.Tools;

public class ProductMenu{
    static void productMenu(ProductManage productList){
        String productMenu[] ={"Add a product","Update information","Delete product","Show all product","Back to main menu"};
        int choice=0;
        do{
            System.out.println("\t\t1. Manage product:");
            choice = Tools.drawMenu(productMenu);
            switch(choice){
                case 1:                    
                    productList.addProducts();
                    break;
                case 2:
                    productList.updateProduct();
                    break;
                case 3:
                    productList.delete();
                    break;
                case 4:
                    productList.showAll();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please enter from 1-5 please.");
            }
        }while(choice != 5);
    }

    
}
