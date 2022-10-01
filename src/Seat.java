public class Seat {
    int row;
    int index;

    SeatStatus seatStatus;
    Passenger passenger;
    ServiceClass serviceClass;
    public Seat(int row, int index, SeatStatus seatStatus, ServiceClass serviceClass){
        this.row = row;
        this.index = index;
        this.seatStatus = seatStatus;
        this.serviceClass = serviceClass;
    }




}
