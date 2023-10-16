package bussiness;

import data.*;
import models.Flight;
import models.Passenger;
import models.Reservation;
import tools.Input;
import tools.Validation;


import java.util.Date;
import java.util.List;

public class PassengerFunctions {
    private final String flightHeader = "|Flight number|   Departure city   |   Destination city   |    Departure time    |    Arrival Time    | Available Seats |";
    private final String flightRow=     "|-------------|--------------------|----------------------|----------------------|--------------------|-----------------|";
    private final String reservationHeader="|Reservation code|Passenger name|Flight number| Seat |    Departure time    |";
    private final String reservatioRow=    "|----------------|--------------|-------------|------|----------------------|";
    private PassengerData passengerData = new PassengerData();
    private ReservationData reservationData = new ReservationData();
    private FlightsData flightsData = new FlightsData();
    private Passenger createPassenger(String personalID){
        String name = Input.readString("Enter name");
        String contactNumber = Input.readByCondition("Enter contact number", Validation.PHONENUMBER);
        passengerData.addPassenger(new Passenger(personalID,name,contactNumber));
        System.out.println("Welcome "+name+" to the system.");
        return passengerData.getPassenger(personalID);
    }

    public Passenger createNewPassenger(){
        String personalID = Input.readByCondition("Enter your personal ID",Validation.NOTEMPTY);
        return createPassenger(personalID);
    }

     private String getSeat(Flight flight){
        int row = 6, column = flight.getTotalSeats()/row; //default of a plan have 6 seat in a row
        for(int i = 1;i<=column;i++){
            for(char j = 'A';j<'A'+row;j++){
                String seat = i+""+j;
                if(flight.checkBookedSeat(seat))
                    System.out.print("X\t");
                else System.out.print(seat+"\t");
            }
            System.out.println();
        }
        String seat = Input.readString("Enter your seat").toUpperCase();
        while(!seat.matches("^\\d+[A-F]$")||flight.checkBookedSeat(seat)){
            seat=Input.readString("Invalid. Enter again");
        }
        return seat;
    }

    public void listFlight(){
        String departure = Input.readString("Enter departure city");
        String destination = Input.readString("Enter destination city");
        Date departureTime = Input.readDate("Enter departure time",Validation.DATEFORMAT);
        System.out.println("\tList of available flight: ");
        System.out.println(flightHeader);
        System.out.println(flightRow);
        for(Flight i : flightsData.toList()){
            if(i.getDepartureCity().equalsIgnoreCase(departure) && i.getDestinationCity().equalsIgnoreCase(destination) && i.getDepartureTime().after(departureTime)){
                System.out.println(i);
                System.out.println(flightRow);
            }
        }
    }
    public void bookFlight(){
        listFlight();
        String flightNo = Input.readString("Enter flight code you want to book").trim().toUpperCase();
        while (!flightsData.checkFlight(flightNo)){
            flightNo = Input.readString("Flight doesn't exist. Enter again(or \"exit\" to cancel)");
            if (flightNo.equalsIgnoreCase("exit")) return;
        }
        Flight flight = flightsData.getFlight(flightNo);
        String reservationID = "R"+String.format("%03d",reservationData.getNumberOfReservation()+1);
        System.out.println("Reservation ID: "+reservationID);
        Passenger passenger;
        String personalID = Input.readByCondition("Enter your personal ID",Validation.NOTEMPTY);
        if(passengerData.checkPassenger(personalID)){
            passenger = passengerData.getPassenger(personalID);
        }
        else {
            passenger = createPassenger(personalID);
        }
        String seat = getSeat(flight);
        reservationData.addReservation(new Reservation(reservationID,passenger,flight,seat));
        flight.addBookedSeat(seat);
        flight.setAvailableSeats(flight.getAvailableSeats()-1);
        System.out.println("Reservation "+reservationID+" created successfully!");
    }

    public void passengerReservation(){
        Passenger passenger;
        String personalID = Input.readByCondition("Enter your personal ID",Validation.NOTEMPTY);
        if(passengerData.checkPassenger(personalID)){
            passenger = passengerData.getPassenger(personalID);
            List<Reservation> reservationList = reservationData.getReservationOfPassenger(passenger);
            System.out.println("\tYour reservation:");
            System.out.println(reservationHeader);
            System.out.println(reservatioRow);
            for(Reservation i: reservationList){
                System.out.println(i);
                System.out.println(reservatioRow);
            }
        }
        else {
            boolean check = Input.readBoolean("Your information currently not exist in system. Create passenger information?(y/n)");
            if(check)
                createPassenger(personalID);
            else return;
        }
    }

    public void saveToFile() {
        try {
            passengerData.saveToFile();
            reservationData.saveToFile();
            flightsData.saveToFile();
        }catch (Exception e){
            System.out.println("An error occur when saving data.");
        }
    }
}
