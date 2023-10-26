package railway;

import railway.strategies.CapacityPricingEnum;

import static railway.strategies.CapacityPricingEnum.*;

public class Booking {

    public static Booking getBookingInstance() {
//        if (instance == null) {
//            instance = new TrainSystem(); Singleton todo
//        }
        return new Booking();
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

    public void bookSeat(Seat seat, User user) {
        if (!seat.isBooked()) {
            seat.book(user);
        } else {
            throw new RuntimeException("Seat " + seat.getSeatNumber() + " is already booked.");
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