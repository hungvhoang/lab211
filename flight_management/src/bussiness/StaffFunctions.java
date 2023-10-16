package bussiness;

import data.FlightsData;
import models.Flight;
import models.Staff;
import tools.Input;
import tools.Validation;

import java.util.*;

public class StaffFunctions {
    private final String row=     "|-------------|--------------------|----------------------|----------------------|--------------------|-----------------|";
    private final String header = "|Flight number|   Departure city   |   Destination city   |    Departure time    |    Arrival Time    | Available Seats |";


    private HashMap<String, Staff> staffList = new HashMap<>();
    private FlightsData flightsData = new FlightsData();
    public StaffFunctions(){
        staffList.put("S001",new Staff("S001","Hung","abc"));
        staffList.put("S002",new Staff("S002","Thinh","abc"));
        staffList.put("S003",new Staff("S003","Duy","abc"));
        staffList.put("S004",new Staff("S004","Hoang","abc"));
    }

    public boolean staffLogin(){
        String staffID = Input.readString("Enter staff ID").trim().toUpperCase();
        if(!staffList.containsKey(staffID)){
            System.out.println("Staff id doesn't exit. Logging out staff mode...");
            return false;
        }
        else {
            Staff staff = staffList.get(staffID);
            String password = Input.readString("Welcome "+staff.getName()+".\nEnter password");
            while(!password.equals(staff.getPassword())){
                password = Input.readString("Wrong password. Enter again(\"exit\" to quit)");
                if(password.equalsIgnoreCase("exit")) return false;
            }
            return true;
        }
    }
    public void createFlight(){
        String flightID = Input.readByCondition("Enter flight number", Validation.NOTEMPTY).trim().toUpperCase();
        String departureCity = Input.readByCondition("Departure city",Validation.NOTEMPTY).trim().toUpperCase();
        String destinationCity = Input.readByCondition("Destination city",Validation.NOTEMPTY).trim().toUpperCase();
        Date departureTime = Input.readDate("Departure time(hh:mm dd/mm/yyyy)",Validation.TIMEFORMAT);
        Date arrivalTime = Input.readDate("Arrival time(hh:mm dd/mm/yyyy)",Validation.TIMEFORMAT);
        int totalSeats = Integer.parseInt(Input.readByCondition("Enter total seats",Validation.INTEGER));
        Flight flight = new Flight(flightID,departureCity,destinationCity,departureTime,arrivalTime,totalSeats);
        boolean confirm = Input.readBoolean("Add flight "+flightID+" to the system?(y/n)");
        if(confirm) {
            flightsData.addFlight(flight);
            System.out.println("Create success!");
        }
        else System.out.println("Cancelled!");
    }

    public void delay(){
        String flightID = Input.readByCondition("Enter flight need to be delayed",Validation.NOTEMPTY).trim().toUpperCase();
        while (!flightsData.checkFlight(flightID)){
            flightID = Input.readByCondition("Flight not exist. Enter again(or \"exit to quit\")",Validation.NOTEMPTY).trim().toUpperCase();
            if (flightID.equalsIgnoreCase("exit"))
                return;
        }
        Flight flight =flightsData.getFlight(flightID);
        Date newDepartureTime = Input.readDate("Enter new departure time",Validation.TIMEFORMAT);
        Date newArrivalTime = Input.readDate("Enter expected arrival time",Validation.TIMEFORMAT);
        boolean confirm = Input.readBoolean("Are you sure to update the time?(y/n)");
        if(confirm){
            flight.setDepartureTime(newDepartureTime);
            flight.setArrivalTime(newArrivalTime);
            System.out.println("Update success!");
        }
        else System.out.println("Cancelled.");
    }

    public void listAllFlights(){
        System.out.println("\tCurrent flights in the system:");
        System.out.println(header);
        System.out.println(row);
        for(Flight i: flightsData.toList()){
            System.out.println(i);
            System.out.println(row);
        }
    }

    public void deleteFlight(){
        String flightID= Input.readByCondition("Enter flight number to be deleted", Validation.NOTEMPTY).trim().toUpperCase();
        if(!flightsData.checkFlight(flightID)){
            System.out.println("Flight doesn't exist");
        }
        else{
            Flight flight = flightsData.getFlight(flightID);
            flight.showInformation();
            Boolean confirm = Input.readBoolean("Delete this flight?(y/n)");
            if(confirm) flightsData.delete(flightID);
            else System.out.println("Cancelled.");
        }
    }

    public void saveToFile(){
        try{
            flightsData.saveToFile();
        }catch(Exception e){

        }
    }

}
