import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TripPoint {
    private int time;
    private double lat;
    private double lon;
    private static ArrayList<TripPoint> trip = new ArrayList<>();

    public TripPoint(int time, double lat, double lon) {
        this.time = time;
        this.lat = lat;
        this.lon = lon;
    }

    public int getTime() {
        return time;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public static ArrayList<TripPoint> getTrip() {
        return new ArrayList<>(trip); 
    }

    public static void readFile(String filename) throws IOException {
        trip.clear();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty() || !line.matches("\\d+,[-+]?\\d*\\.?\\d+,[-+]?\\d*\\.?\\d+")) {
                continue;
            }
            String[] values = line.split(",");
            try {
                int time = Integer.parseInt(values[0]);
                double lat = Double.parseDouble(values[1]);
                double lon = Double.parseDouble(values[2]);
                trip.add(new TripPoint(time, lat, lon));
            } catch (NumberFormatException e) {
                System.err.println("Skipping line with invalid data: " + line);
                continue;
            }
        }
        br.close();
    }

    public static double totalTime() {
        if (trip.isEmpty()) {
            return 0;
        }
        int totalMinutes = trip.get(trip.size() - 1).getTime() - trip.get(0).getTime();
        return totalMinutes / 60.0;
    }

    public static double haversineDistance(TripPoint a, TripPoint b) {
        final int R = 6371; 
        double latDistance = Math.toRadians(b.lat - a.lat);
        double lonDistance = Math.toRadians(b.lon - a.lon);
        double h = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(a.lat)) * Math.cos(Math.toRadians(b.lat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1 - h));
        return R * c;
    }

    public static double totalDistance() {
        double totalDistance = 0.0;
        for (int i = 0; i < trip.size() - 1; i++) {
            totalDistance += haversineDistance(trip.get(i), trip.get(i + 1));
        }
        return totalDistance;
    }

    public static double avgSpeed(TripPoint a, TripPoint b) {
        double distance = haversineDistance(a, b);
        double timeDifference = Math.abs(a.getTime() - b.getTime()) / 60.0;
        if (timeDifference == 0) return 0;
        return distance / timeDifference;
    }
}
