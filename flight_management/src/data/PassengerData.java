package data;

import models.Passenger;

import java.io.*;
import java.util.*;

public class PassengerData {
    private final File file = new File("./passenger.dat");

    private HashMap<String, Passenger> list = new HashMap<>();
    public List<Passenger> toList() {
        return new ArrayList<>(list.values());
    }


    /**
     * Constructor. When initialized, load data from file
     */
    public PassengerData() {
        if(this.file.isFile()&& this.file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                boolean hasNext = true;
                while (hasNext) {
                    Passenger x = (Passenger) ois.readObject();
                    if (x != null) {
                        list.put(x.getPersonalID(), x);
                    } else hasNext = false;
                }
                ois.close();
            } catch (Exception e) {

            }
        }
    }

    public Passenger getPassenger(String passengerID){
        return list.get(passengerID);
    }

    /**
     * add a new passenger to list and save to file
      * @param passenger
     */
    public void addPassenger(Passenger passenger){
        list.putIfAbsent(passenger.getPersonalID(),passenger);
    }

    /**
     * save all passenger data to file
     * @throws IOException
     */
    public void saveToFile() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        for(Passenger x : toList()) {
            oos.writeObject(x);
        }
        oos.close();
    }

    public boolean checkPassenger(String ID){
        return list.containsKey(ID);
    }

}
