import railway.*;


import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        TrainSystem trainSystem = TrainSystem.getInstance();

        Scanner scanner = new Scanner(System.in);

        // Create a train and coaches
        Train trainA = trainSystem.createTrain("Route A", 2);
        trainA.createCoaches(10);


        System.out.println("1. User 2. Admin");
        int firstInput = scanner.nextInt();


        if (firstInput == 1){
            User newUser = new User();

            System.out.println("Enter Your Name: ");
            newUser.setName(scanner.next());
            System.out.println("Enter Your Age: ");
            newUser.setAge(scanner.nextInt());
            System.out.println("Enter Your Meal Preference - 1. Veg 2. NonVeg (Select a Number)" );
            Integer mealPreferenceOption = scanner.nextInt();
            if (mealPreferenceOption.equals(1)){
                newUser.setMealPreference(MealPreference.VEG);
            } else {
                newUser.setMealPreference(MealPreference.NON_VEG);
            }

            System.out.println("Enter Your Seat Preference - 1. Lower 2. Middle 3.Upper (Select a Number)" );
            Integer seatPreferenceOption = scanner.nextInt();
            if (seatPreferenceOption.equals(1)){
                newUser.setSeatTypePreference(SeatType.LOWER);
            } else if (seatPreferenceOption.equals(2)){
                newUser.setSeatTypePreference(SeatType.MIDDLE);
            }
            else {
                newUser.setSeatTypePreference(SeatType.UPPER);
            }

            // Book a seat
            Coach coachA1 = trainA.getCoaches().get(0); // Example: Get the first coach
            Seat seat = coachA1.getSeats().get(0); // Example: Get the first seat
            Booking booking = new Booking();
            booking.bookSeat(seat, newUser);

            // Display seat details, user details, time details, and price after a delay
            int delayInSeconds = 5; // Delay in seconds
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // Display details
                    System.out.println("Seat details: " + seat.getSeatNumber() + " in Coach " + coachA1.getCoachId());
                    System.out.println("Seat Type: " + newUser.getSeatTypePreference());
                    System.out.println("User details: " + newUser.getName() + " (Age: " + newUser.getAge() + ")");
                    System.out.println("Time details: Current time");
                    System.out.println("Price: " + booking.calculatePrice(seat) + " Rs");
                }
            }, delayInSeconds * 1000);
        } else {
            System.out.println("Select which train you want to view: 1-10");
            List<Train> allTrains = TrainSystem.getInstance().getAllTrains();
            System.out.println(allTrains.stream().map((Train::getTrainId)).toList());
            System.out.println("Enter the id of train: ");
            int adminTrainSelectOption = scanner.nextInt();
            Train selectedTrain = TrainSystem.getInstance().getTrainById(adminTrainSelectOption);
            System.out.println("Select which coach you want to view: 1-10");
            int adminCoachSelectOption = scanner.nextInt();
            System.out.println(selectedTrain.getCoachById(adminCoachSelectOption));

        }
        }

}