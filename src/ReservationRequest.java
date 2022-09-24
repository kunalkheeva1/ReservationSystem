public class ReservationRequest {
     String[] serviceClass = {"First", "Economy"}

     String[] seatPreference = {"Window","Center","Aisle"} ;

     Passenger passenger;

     public ReservationRequest(Passenger passenger, String[] ServiceClass, String[] userSeatPreference) {
        this.seatPreference = seatPreference;
        this.serviceClass = serviceClass;
        this.passenger = passenger;
    }
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }


    public String[] getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String[] serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String[] getSeatPreference() {
        return seatPreference;
    }

    public void setSeatPreference(String[] seatPreference) {
        this.seatPreference = seatPreference;
    }
}
//create a new class Booking Service, booking methods, book single , book group, cancel single cancel group,


/*if book single send the reservation request to check(),
 book group  pass the list of the reservation request


 similarly in cancellation, if only one name then create a passenger object and pass to delete only one,
 if group then pass the list to delete it
 */