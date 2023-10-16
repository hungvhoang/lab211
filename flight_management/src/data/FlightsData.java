package data;

import models.Flight;

import java.io.*;
import java.util.*;

public class FlightsData {
    private final File file = new File("./flights.dat");
    private HashMap<String, Flight> list = new HashMap<>();

    /**
     * Constructor. When initialized, load data from file.
     */
    public FlightsData(){
        if(this.file.isFile()&& this.file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                boolean hasNext = true;
                while (hasNext) {
                    Flight x = (Flight) ois.readObject();
                    if (x != null) {
                        list.put(x.getFlightID(), x);
                    } else hasNext = false;
                }
                ois.close();
            } catch (Exception ignored) {

            }
        }
    }
    public Flight getFlight(String flightID){
        return list.get(flightID);
    }
    public List<Flight> toList(){
        return new ArrayList<>(list.values());
    }

    /**
     * add a new flight to list
     * @param flight
     */
    public void addFlight(Flight flight) {
        list.putIfAbsent(flight.getFlightID(),flight);
    }

    public boolean saveToFile() throws FileNotFoundException, IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        for(Flight x : toList()) {
            oos.writeObject(x);
        }
        oos.close();
        return true;
    }

    public void delete(String flightID){
        list.remove(flightID);
    }

    public boolean checkFlight(String flightID){return list.containsKey(flightID);
    }
}
