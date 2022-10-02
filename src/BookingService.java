import java.io.*;
import java.lang.reflect.Array;
import java.security.Provider;
import java.util.*;

public class BookingService {

    FlightDetails flightDetails;
    Map<String, Seat> passengerMap;
    Map<String, ArrayList<Passenger>> groupMap;

    public BookingService(){
        flightDetails = new FlightDetails(2,20);
        passengerMap = new HashMap<>();
        groupMap = new HashMap<>();
    }

    public boolean book(ReservationRequest reservationRequest){
        boolean result = false;
        //single
        if(reservationRequest.getBookingType().equals(BookingType.SINGLE)){
            if(reservationRequest.getBookingType().equals(ServiceClass.F)){
                result = bookSingleFirstClass(reservationRequest);
            }else{
                result = bookSingleEconomyClass(reservationRequest);
            }
        }
        //group
        else{
            if(reservationRequest.getServiceClass().equals(ServiceClass.F)){
                result = bookGroupFirstClass(reservationRequest);
            }else{
                result = bookGroupEconomyClass(reservationRequest);
            }
        }return result;
    }

    private boolean bookSingleFirstClass(ReservationRequest reservationRequest){
        int seatIndex =-1;
        int i=0;
        if(passengerMap.containsKey(reservationRequest.getPassengers().get(0).getFirstName()+ reservationRequest.getPassengers().get(0).getLastName())){
            Seat seat = passengerMap.get(reservationRequest.getPassengers().get(0).getFirstName() + reservationRequest.getPassengers().get(0).getLastName());
            System.out.println("Passenger's booking is present already!! "+ seat.toString());
            return false;
        }
        for(i=0; i<flightDetails.getFirstRows(); i++){
            if(reservationRequest.getSeatPreference().equals(SeatPreference.W)){
                if(flightDetails.getFirstClass()[0][i]==null){
                    seatIndex =0;
                }else if(flightDetails.getFirstClass()[3][i]== null){
                    seatIndex =3;
                }
            } else if(reservationRequest.getSeatPreference().equals(SeatPreference.A)){
                if(flightDetails.getFirstClass()[1][i] == null){
                    seatIndex =1;
                } else if (flightDetails.getFirstClass()[2][i]== null){
                    seatIndex = 2;
                }
            }if(seatIndex != -1){
                break;
            }
        }
        if(seatIndex != -1){
            Seat seat;
            seat = new Seat(i, seatIndex, SeatStatus.ALLOCATED, ServiceClass.F);
            seat.setPassenger(reservationRequest.getPassengers().get(0));
            flightDetails.getFirstClass()[seatIndex][i] = seat;
            passengerMap.put(reservationRequest.getPassengers().get(0).getFirstName() + reservationRequest.getPassengers().get(1).getLastName(), seat);
            System.out.println(i+1 +"" + getSeatIndex(seat)+ ":" + reservationRequest.getPassengers().get(0).getFirstName() +
                    reservationRequest.getPassengers().get(1).getLastName());
        }else{
            System.out.printf("Preferred seat is not available for booking.");
            return false;
        }return  true;
    }



    private boolean bookSingleEconomyClass(ReservationRequest reservationRequest) {
        int eSeatIndex = -1;
        int i = 0;
        if (passengerMap.containsKey(reservationRequest.getPassengers().get(0).getFirstName() + reservationRequest.getPassengers().get(0).getLastName())) {
            Seat seat = passengerMap.get(reservationRequest.getPassengers().get(0).getFirstName() + reservationRequest.getPassengers().get(0).getLastName());
            System.out.println("Passenger is booking present already ..." + seat.toString());
        }
        for (i = 0; i < flightDetails.getEconomyRows(); i ++) {
            if (reservationRequest.getSeatPreference().equals(SeatPreference.W)) {
                if (flightDetails.getEconomyClass()[0][i] == null) {
                    eSeatIndex = 0;
                } else if (flightDetails.getEconomyClass()[5][i] == null) {
                    eSeatIndex = 5;
                }
            } else if (reservationRequest.getSeatPreference().equals(SeatPreference.A)) {
                if (flightDetails.getEconomyClass()[2][i] == null) {
                    eSeatIndex = 2;
                } else if (flightDetails.getEconomyClass()[3][i] == null) {
                    eSeatIndex = 3;
                }
            } else {
                if (flightDetails.getEconomyClass()[1][i] == null) {
                    eSeatIndex = 1;
                } else if (flightDetails.getEconomyClass()[4][i] == null) {
                    eSeatIndex = 4;
                }
            }
            if (eSeatIndex != -1) {
                break;
            }
        }
        if (eSeatIndex != -1) {
            Seat seat = new Seat(i, eSeatIndex, SeatStatus.ALLOCATED, ServiceClass.E);
            seat.setPassenger(reservationRequest.getPassengers().get(0));
            flightDetails.getEconomyClass()[eSeatIndex][i] = seat;
            passengerMap.put(reservationRequest.getPassengers().get(0).getFirstName() + reservationRequest.getPassengers().get(0).getLastName(), seat);
            System.out.println(i+1 +""+ getSeatIndex(seat) +": "+ reservationRequest.getPassengers().get(0).getFirstName() +" "+ reservationRequest.getPassengers().get(0).getLastName());
        } else {
            System.out.println("Preferred seat is not available for booking");
            return false;
        }
        return true;
    }


}