package railway;

import java.util.ArrayList;
import java.util.List;

public class TrainSystem {
    private static TrainSystem instance;
    private final ArrayList<Train> trains;
    private final Booking booking;


    private TrainSystem(Booking booking) { /* Constructor logic */
        this.trains = new ArrayList<>();
        this.booking = booking;
    }



    public static TrainSystem getInstance() {
        if (instance == null) {
            instance = new TrainSystem();
        }
        return instance;
    }

    public Train createTrain(String route, int numCoaches) {
        Train train = new Train(route, numCoaches);
        trains.add(train);
        return train;
    }

    public Train getTrainById(int trainId) {
        for (Train train : trains) {
            if (train.getTrainId() == trainId) {
                return train;
            }
        }
        return null;
    }

    public List<Train> getAllTrains() {
        return trains;
    }

    public Ticket bookTicket(User user){

        booking.bookSeat(user);
    }
}