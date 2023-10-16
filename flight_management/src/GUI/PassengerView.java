package GUI;

import bussiness.PassengerFunctions;
import tools.Input;

public class PassengerView {
    public static void passengerView(){
        PassengerFunctions passengerFunctions = new PassengerFunctions();
        String[] menu = {"Create passenger account","Search flight","Book flight","My reservation","Return"};
        int choice = -1;
        do {
            System.out.println("\tPassenger and Flight service:");
            choice = Input.readMenuChoice(menu);
            switch (choice){
                case 1:
                    passengerFunctions.createNewPassenger();
                    break;
                case 2:
                    passengerFunctions.listFlight();
                    break;
                case 3:
                    passengerFunctions.bookFlight();
                    break;
                case 4:
                    passengerFunctions.passengerReservation();
                    break;
                case 5:
                    passengerFunctions.saveToFile();
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Please enter from 1-5.");
                    break;
            }
        } while (choice != 5);
    }
}
