package data;

import models.Flight;
import models.Passenger;
import models.Reservation;

import java.io.*;
import java.util.*;

public class ReservationData {

    private HashMap<String, Reservation> list = new HashMap<>();
    private static int numberOfReservation=0;
    private final File file = new File("./reservation");

    /**
     * Constructor. When initialized, load all data from file.
     */
    public ReservationData(){
        if(this.file.isFile()&& this.file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                boolean hasNext = true;
                while (hasNext) {
                    Reservation x = (Reservation) ois.readObject();
                    if (x != null) {
                        list.put(x.getReservationId(), x);
                        numberOfReservation++;
                    } else hasNext = false;
                }
                ois.close();
            } catch (Exception ignored) {

            }
        }
    }

    /**
     *
     * @return an array list of reservation
     */
    public List<Reservation> toList(){
        return new ArrayList<>(list.values());
    }

    /**
     * get a reservation from list
     * @param id of the reservation
     * @return reservation
     */
    public Reservation getReservation(String id){
        return list.get(id);
    }

    public List<Reservation> getReservationOfPassenger(Passenger passenger){
        List<Reservation> reservationList = new ArrayList<>();
        for(Reservation i : toList()){
            if(i.getPassenger().getPersonalID().equalsIgnoreCase(passenger.getPersonalID()))
                reservationList.add(i);
        }
        return reservationList;
    }

    /**
     * add a new reservation to list
     * @param reservation
     */
    public void addReservation(Reservation reservation){
        list.putIfAbsent(reservation.getReservationId(),reservation);
        numberOfReservation++;
    }

    /**
     * save all data of reservation
     * @throws IOException
     */
    public void saveToFile() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        for(Reservation x : toList()) {
            oos.writeObject(x);
        }
        oos.close();
    }
    public int getNumberOfReservation(){
        return numberOfReservation;
    }
}
