package GUI;

import tools.Input;

public class Main {
    public static void main(String[] args) {
        final String[] mainMenu ={"Management system(Staff mode)","Passenger login and booking flight","Exit the program"};
        int choice =-1;
        do {
            System.out.println("\tWelcome to flight managing system");
            choice = Input.readMenuChoice(mainMenu);
            switch (choice){
                case 1:
                    StaffView.staffView(); //account to access: S001    password: abc
                    break;
                case 2:
                    PassengerView.passengerView();
                    break;
                case 3:
                    System.out.println("The program exited. Have a nice day");
                    break;
                default:
                    System.out.println("Enter from 1-3 please.");
                    break;
            }
        }while (choice!=3);
    }
}
