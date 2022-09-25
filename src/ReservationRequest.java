public class ReservationRequest {
     ServiceClass userServiceClass;
     SeatPreference userSeatPreference;
     Passenger passenger;

     public ReservationRequest(Passenger passenger, ServiceClass userServiceClass, SeatPreference userSeatPreference) {
        this.userSeatPreference = userSeatPreference;
        this.userServiceClass = userServiceClass;
        this.passenger = passenger;
    }


    public SeatPreference getUserSeatPreference() {
        return userSeatPreference;
    }

    public void setUserSeatPreference(SeatPreference userSeatPreference) {
        this.userSeatPreference = userSeatPreference;
    }

    public ServiceClass getUserServiceClass() {
        return userServiceClass;
    }

    public void setUserServiceClass(ServiceClass userServiceClass) {
        this.userServiceClass = userServiceClass;
    }


}
//create a new class Booking Service, booking methods, book single , book group, cancel single cancel group,


/*if book single send the reservation request to check(),
 book group  pass the list of the reservation request


 similarly in cancellation, if only one name then create a passenger object and pass to delete only one,
 if group then pass the list to delete it
 */