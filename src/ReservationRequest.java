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

    public BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public SeatPreference getSeatPreference() {
        return seatPreference;
    }

    public void setSeatPreference(SeatPreference seatPreference) {
        this.seatPreference = seatPreference;
    }

    public ServiceClass getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(ServiceClass serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}