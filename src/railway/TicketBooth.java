package railway;

import railway.strategies.CapacityPricingEnum;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static railway.strategies.CapacityPricingEnum.*;

public class TicketBooth {

    public static TicketBooth getBookingInstance() {
//        if (instance == null) {
//            instance = new TrainSystem(); Singleton todo
//        }
        return new TicketBooth();
    }

    static CapacityPricingEnum selectStrategy(double capacity) {
        if (capacity >= 0.40) {
            return CALCULATE_PRICE_AT_FORTY_PERCENT;
        } else if (capacity >= 0.35) {
            return CALCULATE_PRICE_AT_THIRTY_FIVE_PERCENT;
        } else if (capacity >= 0.30) {
            return CALCULATE_PRICE_AT_THIRTY_PERCENT;
        } else if (capacity >= 0.20) {
            return CALCULATE_PRICE_AT_TWENTY_PERCENT;
        }
        return DEFAULT_PRICE;
    }
    public double calculatePrice(Seat seat) {
        return selectStrategy(seat.getCoach().capacity()).calculatePrice();
    }

    public Seat bookSeat(User user, Train train) {
        synchronized ("bookSeat") {
            // run first sequence
            List<Integer> firstSequence = IntStream.range(1, train.getCoaches().size() + 1).filter(num -> num % 2 != 0).boxed().toList();
            List<Integer> secondSequence = IntStream.range(2, train.getCoaches().size() + 1).filter(num -> num % 2 == 0).boxed().toList();
            List<Integer> allCoachSequence = IntStream.range(1, train.getCoaches().size() + 1).boxed().toList();

            Seat bookedSeat = null;
            boolean seatFound = false;
            for (Integer coachNum : firstSequence) {
                Coach coach = train.getCoachById(coachNum);
                if (coach.seatAvailable(user.getSeatTypePreference())) {
                    bookedSeat = coach.reserveSeat(user);
                    seatFound = true;
                    break;
                }
            }

            if (!seatFound) {
                for (Integer coachNum : secondSequence) {
                    Coach coach = train.getCoachById(coachNum);
                    if (coach.seatAvailable(user.getSeatTypePreference())) {
                        bookedSeat = coach.reserveSeat(user);
                        seatFound = true;
                        break;
                    }
                }
            }

            if (!seatFound) {
                for (Integer coachNum : allCoachSequence) {
                    Coach coach = train.getCoachById(coachNum);
                    if (coach.anySeatAvailable()) {
                        bookedSeat = coach.reserveAnySeat(user);
                        seatFound = true;
                        break;
                    }
                }
            }

            if (!seatFound) {
                throw new RuntimeException("No seat available!");
            }

            return bookedSeat;
        }

    }

    public void cancelBooking(Seat seat) {
        if (seat.isBooked()) {
            seat.cancelBooking();
        } else {
            throw new RuntimeException("Seat " + seat.getSeatNumber() + " is not booked.");
        }
    }
}
