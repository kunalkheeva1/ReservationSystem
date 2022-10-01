public class Seat {
    int seatRow;
    char seatLocation;
    SeatStatus status;

    public Seat(int seatRow, char seaLocation, SeatStatus status) {
        this.seatRow = seatRow;
        this.seatLocation = seaLocation;
        this.status = status;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public char getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(char seaLocation) {
        this.seatLocation = seaLocation;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
