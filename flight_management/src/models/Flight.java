package models;

import tools.Validation;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class Flight implements Serializable {
    private String flightID;
    private String departureCity;
    private String destinationCity;
    private Date departureTime;
    private Date arrivalTime;
    private int totalSeats;

    private int availableSeats;

    private HashSet<String> bookedSeats = new HashSet<>();

    public Flight() {
    }


    public Flight(String flightID, String departureCity, String destinationCity, Date departureTime, Date arrivalTime, int totalSeats) {
        this.flightID = flightID;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void addBookedSeat(String seat){
        bookedSeats.add(seat);
    }

    public boolean checkBookedSeat(String seat){
        return bookedSeats.contains(seat);
    }


    public void showInformation(){
        SimpleDateFormat timeFormat = new SimpleDateFormat(Validation.TIMEFORMAT);
        System.out.println("Flight no: "+flightID);
        System.out.println("Departure city: "+departureCity);
        System.out.println("Destination city: "+destinationCity);
        System.out.println("Departure time: "+timeFormat.format(departureTime));
        System.out.println("Arrival time: "+timeFormat.format(arrivalTime));
        System.out.println("Available seats: "+availableSeats);
    }

    @Override
    public String toString() {
        SimpleDateFormat timeFormat = new SimpleDateFormat(Validation.TIMEFORMAT);
        return String.format("|%-13s|%-20s|%-22s|%22s|%20s|%17d|",flightID,departureCity,destinationCity,timeFormat.format(departureTime),timeFormat.format(arrivalTime),availableSeats);
    }
}
