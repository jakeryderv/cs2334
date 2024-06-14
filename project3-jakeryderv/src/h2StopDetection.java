public static int h2StopDetection() {
		
        movingTrip = new ArrayList<TripPoint>();
        double d = 0.5;
        int count = 0;
        
        ArrayList<TripPoint> zone = new ArrayList<TripPoint>();
        // A nested loop to compute the check the stop zones
        for(TripPoint current: trip) {
            if(zone.size()==0) {
                zone.add(current);
                continue;
            }
            boolean inStopZone = false;
            for(TripPoint stop : zone) {
                if(haversineDistance(stop, current) < d) {
                    inStopZone = true;
                }
            }
            if(inStopZone) {
                zone.add(current);
            } else {
                if(zone.size()>=3) {
                    count += zone.size();
                }else {
                    for(TripPoint sp : zone) {
                        movingTrip.add(sp); 
                    }
                }
                zone.clear();
                zone.add(current);
            }
        }
        // calculate the stop zones one last time
        if(zone.size()>=3) {
            count += zone.size();
        }else {
            for(TripPoint stop : zone) {
                movingTrip.add(stop);
            }
        }
        return count;
  }