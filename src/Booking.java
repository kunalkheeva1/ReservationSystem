import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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







    //just writing the codes for the required things will put them on their places afterwards

   //list of passenger names, now going to check if it exists in the list or not,

    public boolean checkIfBookedSingle(Passenger passenger) {
        if (passengerMap.containsKey(passenger.firstName + passenger.lastName)) {
            System.out.println("There is already a booking for this passenger");
            return true;
        }return false;
    }


    // check the seat availability through
    public static void checkSeatPreference(Seat seat){
     try{
         if(bookingStatus(seat)){
           if(seat.seatRow == seat.getSeatRow() || seat.seatLocation == seat.getSeatLocation()){
               System.out.println("This choice of seat is unavailable, Please modify your choice");
           }
           else{

           }
       }
     }
    }


    public static boolean bookingStatus(Seat seatStatus){
        if(seatStatus.status == SeatStatus.ALLOCATED){
            System.out.println("This seat has already been allocated!");
            return false;
        }
        else{
            System.out.println("This seat is vacant!");
            return true;
        }

    }



}
