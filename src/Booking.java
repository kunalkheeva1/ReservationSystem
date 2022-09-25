import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Booking {

     FlightDetails flightDetails;
     Map<String, Seat> passengerMap;


    /**
     * Initialise flight
     */
    public Booking (){
         flightDetails = new FlightDetails();
         passengerMap = new HashMap<>();
     }

    /**
     *
     * @param reservationRequests
     */
    public void bookGroup(ReservationRequest [] reservationRequests){

    }

    //cancel group

    /**
     *
     * @param passengers
     */
    public void cancelGroup(Passenger [] passengers){

    }


    /**
     *
     * @param reservationRequest
     */
    public void bookSingle(ReservationRequest reservationRequest){
        /*
        * 1. booked already for given passnger
        * 2. if seatPref is available then book otherwise return error
        * 3. booking Status = Allocated
        * 4. Assign seat to Passenger -- make entry in hashmap
        * */
    }

    /**
     *
     * @param passenger
     */
    public void cancelSingle(Passenger passenger){

    }


}
