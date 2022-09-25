public class Seat {
    int seatRow;
    char seaLocation;
    SeatStatus status;

    public Seat(int seatRow, char seaLocation, SeatStatus status) {
        this.seatRow = seatRow;
        this.seaLocation = seaLocation;
        this.status = status;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public char getSeaLocation() {
        return seaLocation;
    }

    public void setSeaLocation(char seaLocation) {
        this.seaLocation = seaLocation;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
