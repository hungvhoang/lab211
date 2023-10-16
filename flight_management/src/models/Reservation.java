package models;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import tools.Validation;

public class Reservation implements Serializable {
    private String reservationId;
    private Passenger passenger;
    private Flight flight;
    private String seat;
    private boolean checked;

    public Reservation() {
    }

    public Reservation(String reservationId, Passenger passenger, Flight flight, String seat) {
        this.reservationId = reservationId;
        this.passenger = passenger;
        this.flight = flight;
        this.seat = seat;
        this.flight.addBookedSeat(seat);
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public boolean isCheckedIn() {
        return checked;
    }

    public void checkin(){
        this.checked = true;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Validation.TIMEFORMAT);   
        return String.format("|%-16s|%-14s|%13s|%6s|%22s|",reservationId,passenger.getName(),flight.getFlightID(),seat,dateFormat.format(flight.getDepartureTime()));
    }
}
