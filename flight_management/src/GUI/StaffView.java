package GUI;

import bussiness.StaffFunctions;
import tools.Input;

public class StaffView { //account for access staff mode:       ID: S001    password: abc
    public static void staffView(){
        StaffFunctions staffFunctions = new StaffFunctions();
        boolean login = staffFunctions.staffLogin();
        if(login){
            String[] menu = {"List all current flights","Create a flight","Delay a flight","Delete a flight","Log out staff mode"};
            int choice = -1;
            do {
                System.out.println("\tFlight managing system:");
                choice = Input.readMenuChoice(menu);
                switch (choice){
                    case 1:
                        staffFunctions.listAllFlights();
                        break;
                    case 2:
                        staffFunctions.createFlight();
                        break;
                    case 3:
                        staffFunctions.delay();
                        break;
                    case 4:
                        staffFunctions.deleteFlight();
                        break;
                    case 5:
                        staffFunctions.saveToFile();
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Enter from 1-4 please.");
                }
            }while(choice!=5);
        }
    }
}
