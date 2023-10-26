package railway;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private static int nextTrainId = 1;
    private int trainId;
    private String route;
    private int numCoaches;
    private List<Coach> coaches;

    public Train(String route, int numCoaches) {
        this.trainId = nextTrainId++;
        this.route = route;
        this.numCoaches = numCoaches;
        this.coaches = new ArrayList<>();
    }

    public int getTrainId() {
        return trainId;
    }

    public String getRoute() {
        return route;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public Coach getCoachById(int coachId){
        return (Coach) this.getCoaches().stream().filter((coach -> coach.getCoachId() == coachId));
    }

    public void createCoaches(int numCoaches) {
        for (int i=1; i<=10; i++){
            coaches.add(new Coach(i, this));
        }
    }

    // Add other methods for managing coaches and the train's state
}
