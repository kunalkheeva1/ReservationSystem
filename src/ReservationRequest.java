import java.awt.print.Book;
import java.util.ArrayList;

public class ReservationRequest {
    //arrangement for the passenger
    BookingType bookingType;
    ArrayList<Passenger> passengers;
    SeatPreference seatPreference;
    ServiceClass serviceClass;

    //groupId
    String groupId;

    public ReservationRequest(BookingType bookingType, ArrayList<Passenger> passengers, SeatPreference seatPreference, ServiceClass serviceClass, String groupId){
        this.bookingType = bookingType;
        this.passengers = passengers;
        this.serviceClass = serviceClass;

        if(bookingType.equals(BookingType.SINGLE)){
            this.seatPreference = seatPreference;
        }else{
            this.seatPreference = seatPreference;
            this.groupId = groupId;
        }
    }


}