public class Seat {
    int row;
    int index;

    SeatStatus seatStatus;
    Passenger passenger;
    ServiceClass serviceClass;

    /**
     * Seat detailing
     * @param row
     * row of the seat.
     * @param index
     * index count of the seat.
     * @param seatStatus
     * Whether it is free or allotted.
     * @param serviceClass
     * service of the class whether it is economy or first
     */
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

    @Override
    public String toString() {
        char seatIndex =(char)('A'+ this.index);

        return "Seat Number: " + (this.row +1) + " "+ (char)seatIndex;

    }
}
