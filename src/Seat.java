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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public ServiceClass getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(ServiceClass serviceClass) {
        this.serviceClass = serviceClass;
    }
}
