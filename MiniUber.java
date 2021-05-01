//https://www.lintcode.com/problem/525/
/**
 * Definition of Trip:
 * public class Trip {
 *     public int id; // trip's id, primary key
 *     public int driver_id, rider_id; // foreign key
 *     public double lat, lng; // pick up location
 *     public Trip(int rider_id, double lat, double lng);
 * }
 * Definition of Helper
 * class Helper {
 *     public static double get_distance(double lat1, double lng1,
                                         double lat2, double lng2) {
 *         // return distance between (lat1, lng1) and (lat2, lng2)
 *     }
 * };
 */
class  Location {
    double latitude;
    double longitude;
    public Location(double _latitude , double _longitude){
        latitude = _latitude;
        longitude = _longitude;
    }    
}

public class MiniUber {
    
    // stores all Trip in progress cabs rider ids(key) and Trip Information(value). 
    private Map<Integer,Trip> cabsTripInProgress; 
    // stores all available cab's rider ids(key) and location deatail(value)
    private Map<Integer,Location> availableCabsLocation; 

    public MiniUber() {
        cabsTripInProgress = new HashMap<>();
        availableCabsLocation = new HashMap<>();
    }
    // @param driver_id an integer
    // @param lat, lng driver's location
    // return matched trip information if there have matched rider or null
    public Trip report(int driver_id, double lat, double lng) {

        if(cabsTripInProgress.containsKey(driver_id)){
            return cabsTripInProgress.get(driver_id);
        }
        Location location = new Location(lat,lng);
        availableCabsLocation.put(driver_id , location);
        return null;
        
    }
    // @param rider_id an integer
    // @param lat, lng rider's location
    // return a trip
    public Trip request(int rider_id, double lat, double lng) {

        Trip trip = new Trip(rider_id, lat , lng);
        int matchedDriver = -1;
        double minDistance = Integer.MAX_VALUE;
        for(Integer driver_id : availableCabsLocation.keySet()){
            Location location = availableCabsLocation.get(driver_id);
            double distance = Helper.get_distance(lat,lng,location.latitude,location.longitude);
            if(distance < minDistance){
                minDistance = distance;
                matchedDriver = driver_id;
            }
        }

        if(matchedDriver!=-1){
            availableCabsLocation.remove(matchedDriver);
        }

        trip.driver_id = matchedDriver;
        cabsTripInProgress.put(matchedDriver,trip);

        return trip;
    }
}
