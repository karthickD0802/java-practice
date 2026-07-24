import java.util.HashMap;

class UndergroundSystem {

    // Stores passenger check-in information
    HashMap<Integer, CheckIn> checkInMap;

    // Stores total travel time and trip count
    HashMap<String, Trip> tripMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        tripMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {

        CheckIn check = checkInMap.get(id);

        String key = check.station + "-" + stationName;

        int travelTime = t - check.time;

        if (tripMap.containsKey(key)) {

            Trip trip = tripMap.get(key);
            trip.totalTime += travelTime;
            trip.count++;

        } else {

            tripMap.put(key, new Trip(travelTime, 1));

        }

        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {

        String key = startStation + "-" + endStation;

        Trip trip = tripMap.get(key);

        return (double) trip.totalTime / trip.count;
    }
}

class CheckIn {

    String station;
    int time;

    public CheckIn(String station, int time) {
        this.station = station;
        this.time = time;
    }
}

class Trip {

    int totalTime;
    int count;

    public Trip(int totalTime, int count) {
        this.totalTime = totalTime;
        this.count = count;
    }
}