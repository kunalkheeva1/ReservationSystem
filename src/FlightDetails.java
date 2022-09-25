public class FlightDetails {
    /**
     *   X X--> 0th W
     *   X X --> Aisle
     *   X X --> A
     *   X X --> W
     */
    Seat [][] firstClass;//= new Seat[4][2];

    /**
     *   X X--> 0th W
     *   X X --> C
     *   X X --> Aisle
     *
     *   X X --> A
     *   X X --> C
     *   X X --> W
     */
    Seat [][] secondClass;// = new Seat [6][20];

    public FlightDetails() {
        firstClass = new Seat[4][2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                char location;
                if (i == 0 || i == 3) {
                    location = 'W';
                } else {
                    location = 'A';
                }
                Seat seat = new Seat(j + 1, location, SeatStatus.FREE);
                firstClass[i][j] = seat;
            }
        }
        secondClass = new Seat [6][20];
        // initialise second class seats
    }

    public Seat[][] getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(Seat[][] firstClass) {
        this.firstClass = firstClass;
    }

    public Seat[][] getSecondClass() {
        return secondClass;
    }

    public void setSecondClass(Seat[][] secondClass) {
        this.secondClass = secondClass;
    }
}
