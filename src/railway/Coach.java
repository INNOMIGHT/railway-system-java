package railway;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Coach {
    private static int nextCoachId = 1;
    private int coachId;
    private Train train;
    private List<Seat> seats;


    public Coach(int coachId, Train train) {

        this.coachId = coachId; // create coach number separately
        this.train = train;
        this.seats = new ArrayList<>();
        UUID coachNumber = UUID.randomUUID();
        initializeSeats();
    }

    private void initializeSeats() {
        for (int i = 1; i <= 20; i++) {
            SeatType type = SeatType.LOWER;
            seats.add(new Seat(i, type, this));
        }
        for (int i = 1; i <= 20; i++) {
            SeatType type = SeatType.MIDDLE;
            seats.add(new Seat(i, type, this));
        }
        for (int i = 1; i <= 20; i++) {
            SeatType type = SeatType.UPPER;
            seats.add(new Seat(i, type, this));
        }
    }

    public int getCoachId() {
        return coachId;
    }

    public double capacity(){
        double totalSeats = seats.size();
        long occupiedSeats = seats.stream().filter(Seat::isBooked).count();
        return occupiedSeats / totalSeats;
    }

    public Train getTrain() {
        return train;
    }

    public List<Seat> getSeats() {
        return seats;
    }


    // Add other methods for managing seat allocation, pricing, and coach state
}