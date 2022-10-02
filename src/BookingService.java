import java.io.*;
import java.lang.reflect.Array;
import java.security.Provider;
import java.util.*;

public class BookingService {

    FlightDetails flightDetails;
    Map<String, Seat> passengerMap;
    Map<String, ArrayList<Passenger>> groupMap;

    /**
     * Booking service constructor
     */
    public BookingService(){
        flightDetails = new FlightDetails(2,20);
        passengerMap = new HashMap<>();
        groupMap = new HashMap<>();
    }

    /**
     * Book method to receive the reservation request
     * @param reservationRequest
     * - reservation request to be used for the further detailing
     * @return
     * true or false with respect to the reservation request availability
     */
    public boolean book(ReservationRequest reservationRequest){
        boolean result = false;
        //single
        if(reservationRequest.getBookingType().equals(BookingType.SINGLE)){
            if(reservationRequest.getBookingType().equals(ServiceClass.F)){
                result = bookSingleFirstClass(reservationRequest);
            }else{
                result = bookSingleEconomyClass(reservationRequest);
            }
        }
        //group
        else{
            if(reservationRequest.getServiceClass().equals(ServiceClass.F)){
                result = bookGroupFirstClass(reservationRequest);
            }else{
                result = bookGroupEconomyClass(reservationRequest);
            }
        }return result;
    }

    /**
     * Booking for single passenger in first class
     * @param reservationRequest
     * - Type of the reservation reequest
     * @return
     * True or false with respect to the reservation request
     */
    private boolean bookSingleFirstClass(ReservationRequest reservationRequest){
        int seatIndex =-1;
        int i=0;
        if(passengerMap.containsKey(reservationRequest.getPassengers().get(0).getFirstName()+ reservationRequest.getPassengers().get(0).getLastName())){
            Seat seat = passengerMap.get(reservationRequest.getPassengers().get(0).getFirstName() + reservationRequest.getPassengers().get(0).getLastName());
            System.out.println("Passenger's booking is present already!! "+ seat.toString());
            return false;
        }
        for(i=0; i<flightDetails.getFirstRows(); i++){
            if(reservationRequest.getSeatPreference().equals(SeatPreference.W)){
                if(flightDetails.getFirstClass()[0][i]==null){
                    seatIndex =0;
                }else if(flightDetails.getFirstClass()[3][i]== null){
                    seatIndex =3;
                }
            } else if(reservationRequest.getSeatPreference().equals(SeatPreference.A)){
                if(flightDetails.getFirstClass()[1][i] == null){
                    seatIndex =1;
                } else if (flightDetails.getFirstClass()[2][i]== null){
                    seatIndex = 2;
                }
            }if(seatIndex != -1){
                break;
            }
        }
        if(seatIndex != -1){
            Seat seat;
            seat = new Seat(i, seatIndex, SeatStatus.ALLOCATED, ServiceClass.F);
            seat.setPassenger(reservationRequest.getPassengers().get(0));
            flightDetails.getFirstClass()[seatIndex][i] = seat;
            passengerMap.put(reservationRequest.getPassengers().get(0).getFirstName() + reservationRequest.getPassengers().get(1).getLastName(), seat);
            System.out.println(i+1 +"" + getSeatIndex(seat)+ ":" + reservationRequest.getPassengers().get(0).getFirstName() +
                    reservationRequest.getPassengers().get(1).getLastName());
        }else{
            System.out.printf("Preferred seat is not available for booking.");
            return false;
        }return  true;
    }


    /**
     * Booking for ht single passenger in economy class
     * @param reservationRequest
     * -contains the reservation details with respect to passenger data as well
     * @return
     * True or false with respect to the reservation request
     */
    private boolean bookSingleEconomyClass(ReservationRequest reservationRequest) {
        int eSeatIndex = -1;
        int i = 0;
        if (passengerMap.containsKey(reservationRequest.getPassengers().get(0).getFirstName() + reservationRequest.getPassengers().get(0).getLastName())) {
            Seat seat = passengerMap.get(reservationRequest.getPassengers().get(0).getFirstName() + reservationRequest.getPassengers().get(0).getLastName());
            System.out.println("Passenger is booking present already ..." + seat.toString());
        }
        for (i = 0; i < flightDetails.getEconomyRows(); i ++) {
            if (reservationRequest.getSeatPreference().equals(SeatPreference.W)) {
                if (flightDetails.getEconomyClass()[0][i] == null) {
                    eSeatIndex = 0;
                } else if (flightDetails.getEconomyClass()[5][i] == null) {
                    eSeatIndex = 5;
                }
            } else if (reservationRequest.getSeatPreference().equals(SeatPreference.A)) {
                if (flightDetails.getEconomyClass()[2][i] == null) {
                    eSeatIndex = 2;
                } else if (flightDetails.getEconomyClass()[3][i] == null) {
                    eSeatIndex = 3;
                }
            } else {
                if (flightDetails.getEconomyClass()[1][i] == null) {
                    eSeatIndex = 1;
                } else if (flightDetails.getEconomyClass()[4][i] == null) {
                    eSeatIndex = 4;
                }
            }
            if (eSeatIndex != -1) {
                break;
            }
        }
        if (eSeatIndex != -1) {
            Seat seat = new Seat(i, eSeatIndex, SeatStatus.ALLOCATED, ServiceClass.E);
            seat.setPassenger(reservationRequest.getPassengers().get(0));
            flightDetails.getEconomyClass()[eSeatIndex][i] = seat;
            passengerMap.put(reservationRequest.getPassengers().get(0).getFirstName() + reservationRequest.getPassengers().get(0).getLastName(), seat);
            System.out.println(i+1 +""+ getSeatIndex(seat) +": "+ reservationRequest.getPassengers().get(0).getFirstName() +" "+ reservationRequest.getPassengers().get(0).getLastName());
        } else {
            System.out.println("Preferred seat is not available for booking");
            return false;
        }
        return true;
    }

    /**
     * Booking for the group in the economy class
     * @param reservationRequest
     * - Type of the reservation request.
     * @return
     * True or false with respect to the reservation request
     */
    public boolean bookGroupEconomyClass(ReservationRequest reservationRequest) {
        int availableEcoSeats = 0;

        // calculate available seats in first class
        for(int i = 0; i < flightDetails.getEconomyRows(); i ++) {
            for (int j = 0; j < 6; j++) {
                if (flightDetails.getEconomyClass()[j][i] == null) {
                    availableEcoSeats ++;
                }
            }
        }
        // check if seat are available for booking
        if (availableEcoSeats < reservationRequest.getPassengers().size()) {
            System.out.println("Seat are not available for group : " + reservationRequest.getGroupId());
            System.out.println("[Economy class] Available seats :" + availableEcoSeats + " and requested seats : " + reservationRequest.getPassengers().size());
            return false;
        }
        // now block seats
        // allocate seat
        int count = 0;
        boolean flag = false;
        for( int i = 0; i < flightDetails.getEconomyRows(); i ++) {
            for (int j = 0; j < 6; j++) {
                if (flightDetails.getEconomyClass()[j][i] == null) {
                    Seat seat = new Seat(i, j, SeatStatus.ALLOCATED, ServiceClass.E);
                    Passenger passenger = reservationRequest.getPassengers().get(count);
                    seat.setPassenger(passenger);
                    passengerMap.put(passenger.getFirstName()+passenger.getLastName(), seat);
                    flightDetails.getEconomyClass()[j][i] = seat;
                    count++;
                }
                if (count == reservationRequest.getPassengers().size()) {
                    groupMap.put(reservationRequest.getGroupId(), reservationRequest.getPassengers());
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        return true;
    }


    /**
     * booking for the group in first class
     * @param reservationRequest
     * - Type of the reservation request.
     * @return
     * True or false with respect to the reservation request
     */
    public boolean bookGroupFirstClass(ReservationRequest reservationRequest) {
        int availableSeats = 0;

        // calculate available seats in first class
        for(int i = 0; i < 4; i ++) {
            for (int j = 0; j < flightDetails.getFirstRows(); j++) {
                if (flightDetails.getFirstClass()[i][j] == null) {
                    availableSeats ++;
                }
            }
        }
        if (availableSeats < reservationRequest.getPassengers().size()) {
            System.out.println("Seat are not available for group : " + reservationRequest.getGroupId());
            System.out.println("[First class] Available seats :" + availableSeats + " and requested seats : " + reservationRequest.getPassengers().size());
            return false;
        }

        int count = 0;
        boolean flag = false;
        // allocate seat
        for( int i = 0; i < flightDetails.getFirstRows(); i ++) {
            for (int j = 0; j < 4; j++) {
                if (flightDetails.getFirstClass()[j][i] == null) {
                    Seat seat = new Seat(i, j, SeatStatus.ALLOCATED, ServiceClass.F);
                    Passenger passenger = reservationRequest.getPassengers().get(count);
                    seat.setPassenger(passenger);
                    passengerMap.put(passenger.getFirstName()+passenger.getLastName(), seat);
                    flightDetails.getFirstClass()[j][i] = seat;
                    count++;
                }

                if (count == reservationRequest.getPassengers().size()) {
                    groupMap.put(reservationRequest.getGroupId(), reservationRequest.getPassengers());
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        return true;
    }

    /**
     * cancel the request
     * @param cancellationRequest
     * - cancellation request as a parameter
     * @return
     * True of false with respect to the type of the cancellation request
     */
    public boolean cancel(CancellationRequest cancellationRequest) {
        boolean result = false;
        if (cancellationRequest.getCancellationType().equals(CancellationType.SINGLE)) {
            result = cancelSingle(cancellationRequest);
            System.out.println("Single cancellation is done");
        } else if (cancellationRequest.getCancellationType().equals(CancellationType.GROUP)) {
            result= cancelGroup(cancellationRequest);
            System.out.println("Group cancellation is done");
        } else {
            System.out.println("Cancellation type is not correct ");
        }
        return result;
    }


    /**
     * Cancellation of the single passenger
     * @param cancellationRequest
     * - managing the cancellation request of the single passenger
     * @return
     * True of false with respect to the type of the cancellation request
     */
    private boolean cancelSingle(CancellationRequest cancellationRequest) {
        if (cancellationRequest.getCancellationType().equals(CancellationType.SINGLE)) {
            if (passengerMap.containsKey(cancellationRequest.getPassengerName().getFirstName() + cancellationRequest.getPassengerName().getLastName())) {
                Seat seat = passengerMap.get(cancellationRequest.getPassengerName().getFirstName() + cancellationRequest.getPassengerName().getLastName());
                if (seat.getServiceClass() == ServiceClass.F) {
                    flightDetails.getFirstClass()[seat.getIndex()][seat.getRow()] = null;
                } else {
                    flightDetails.getEconomyClass()[seat.getIndex()][seat.getRow()] = null;
                }
                passengerMap.remove(cancellationRequest.getPassengerName().getFirstName() + cancellationRequest.getPassengerName().getLastName());
            } else {
                System.out.println("Passenger booking doesn't exist");
                return false;
            }
        } else  {
            System.out.println("Cancellation type is not single in this case");
            return false;
        }
        return true;
    }


    /**
     * Cancellation of whole group from the system.
     * @param cancellationRequest
     * anaging the cancellation request of the group
     * @return
     * True of false with respect to the type of the cancellation request
     */
    private boolean cancelGroup(CancellationRequest cancellationRequest) {
        if (cancellationRequest.getCancellationType().equals(CancellationType.GROUP)) {
            if (groupMap.containsKey(cancellationRequest.getGroupId())) {
                ArrayList<Passenger> passengerArrayList = groupMap.get(cancellationRequest.getGroupId());
                for (Passenger passenger : passengerArrayList) {
                    Seat seat = passengerMap.get(passenger.getFirstName() + passenger.getLastName());
                    if (seat.getServiceClass() == ServiceClass.F) {
                        flightDetails.getFirstClass()[seat.getIndex()][seat.getRow()] = null;
                    } else {
                        flightDetails.getEconomyClass()[seat.getIndex()][seat.getRow()] = null;
                    }
                    passengerMap.remove(passenger.getFirstName() + passenger.getLastName());
                }
                groupMap.remove(cancellationRequest.getGroupId());
            } else {
                System.out.println("Passenger booking doesn't exist");
                return false;
            }
        }
        return true;
    }

    /**
     * checking the seat availability at certain point
     * @param seatClass
     * - taking the paramter of seat class, which is service of first or economy.
     */
    public void checkAvailability(ServiceClass seatClass) {
        if (seatClass.equals(ServiceClass.F)) {
            // availability for first class
            System.out.println("=====================================");
            System.out.println("First Class Availability :");
            System.out.println("=====================================");
            for (int i = 0; i < flightDetails.getFirstRows(); i++) {
                ArrayList<Character> seatList = new ArrayList<>();
                if (flightDetails.getFirstClass()[0][i] == null) {
                    seatList.add('A');
                }
                if (flightDetails.getFirstClass()[1][i] == null) {
                    seatList.add('B');
                }
                if (flightDetails.getFirstClass()[2][i] == null) {
                    seatList.add('C');
                }
                if (flightDetails.getFirstClass()[3][i] == null) {
                    seatList.add('D');
                }
                printSeat(i + 1, seatList);
            }
        } else {
            // availability for economy class
            System.out.println();
            System.out.println("=====================================");
            System.out.println("Economy Class Availability :");
            System.out.println("=====================================");
            for (int i = 0; i < flightDetails.getEconomyRows(); i++) {
                ArrayList<Character> seatList = new ArrayList<>();
                if (flightDetails.getEconomyClass()[0][i] == null) {
                    seatList.add('A');
                }
                if (flightDetails.getEconomyClass()[1][i] == null) {
                    seatList.add('B');
                }
                if (flightDetails.getEconomyClass()[2][i] == null) {
                    seatList.add('C');
                }
                if (flightDetails.getEconomyClass()[3][i] == null) {
                    seatList.add('D');
                }
                if (flightDetails.getEconomyClass()[4][i] == null) {
                    seatList.add('E');
                }
                if (flightDetails.getEconomyClass()[5][i] == null) {
                    seatList.add('F');
                }
                printSeat(i + 1, seatList);
            }
        }
    }


    /**
     * Records of seat Manifest
     * @param seatClass
     * - service of the class as the parameter.
     */

    public void seatManifest(ServiceClass seatClass) {
        if (seatClass.equals(ServiceClass.F)) {
            // availability for first class
            System.out.println("=====================================");
            System.out.println("First Class Availability :");
            System.out.println("=====================================");
            for (int i = 0; i < 4; i ++) {
                for (int j = 0; j < flightDetails.getFirstRows(); j++) {
                    if (flightDetails.getFirstClass()[i][j] != null) {
                        Seat seat = flightDetails.getFirstClass()[i][j];
                        System.out.println(j+1 + " "+ getSeatIndex(seat) + " : " + seat.getPassenger().getFirstName() +" "+ seat.getPassenger().getLastName());
                    }
                }
            }
        } else {
            // availability for economy class
            System.out.println();
            System.out.println("=====================================");
            System.out.println("Economy Class Availability :");
            System.out.println("=====================================");
            for (int i = 0; i < 6; i ++) {
                for (int j = 0; j < flightDetails.getEconomyRows(); j++) {
                    if (flightDetails.getEconomyClass()[i][j] != null) {
                        Seat seat = flightDetails.getEconomyClass()[i][j];
                        System.out.println(j+1 + ""+ getSeatIndex(seat) + " : " + seat.getPassenger().getFirstName() +" "+ seat.getPassenger().getLastName());
                    }
                }
            }
        }
    }

    /**
     * Printing the seat arrangements
     * @param row
     * -row numbers
     * @param seats
     * list of the seat.
     */
    private void printSeat(int row, ArrayList<Character> seats) {
        System.out.printf(String.valueOf(row) + ": ");
        for (int i = 0; i < seats.size(); i++) {
            if (i < seats.size() - 1) {
                System.out.printf(seats.get(i) + ", ");
            } else {
                System.out.printf(seats.get(i) + "");
            }
        }
        System.out.println();
    }

    /**
     * writing to the file for the detailing
     * @param fileName
     * - name of the existing or created file
     */
    public void writeToFile(String fileName) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            // writing group data
            for (Map.Entry<String, ArrayList<Passenger>> entry : groupMap.entrySet()) {
                StringBuffer groupHeaderLine = new StringBuffer();
                groupHeaderLine.append("G, " + entry.getKey() + ", ");
                groupHeaderLine.append(entry.getValue().size() +" \n");
                fileWriter.write(groupHeaderLine.toString());

                for (Passenger passenger : entry.getValue()) {
                    StringBuffer singleLine = new StringBuffer();
                    singleLine.append(passenger.getFirstName() + " " + passenger.getLastName() + ", ");
                    Seat seat = passengerMap.get(passenger.getFirstName() + passenger.getLastName());
                    singleLine.append(seat.getServiceClass().toString() + ", ");
                    singleLine.append(seat.getRow() + 1 +", ");
                    singleLine.append(getSeatIndex(seat) + " \n");
                    // removed entry from passenger map
                    passengerMap.remove(passenger.getFirstName() + passenger.getLastName());

                    fileWriter.write(singleLine.toString());
                }
            }
            // write single booking
            for (Map.Entry<String, Seat> entry : passengerMap.entrySet()) {
                StringBuffer singleLine = new StringBuffer("S, ");
                Seat seat = entry.getValue();
                singleLine.append(seat.getPassenger().getFirstName()+ " " + seat.getPassenger().getLastName() + ", ");
                singleLine.append(seat.getServiceClass().toString() + ", ");
                singleLine.append(seat.getRow() + 1 + ", ");
                singleLine.append(getSeatIndex(seat) + "\n");
                fileWriter.write(singleLine.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Reading from file
     * @param fileName
     * - file name as the parameter.
     */
    public void readFromFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.charAt(0) == 'G') { // group booking
                    String []groupHeaderLine = line.split(",");
                    bookGroupFromFile(groupHeaderLine[1].trim(), Integer.parseInt(groupHeaderLine[2].trim()), bufferedReader);
                } else {
                    bookSingleFromFile(line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Book for the single from the files
     * @param line
     * - string as the parameter.
     */
    private void bookSingleFromFile(String line) {
        String [] singleBookingLine = line.split(",");
        String []pasName = singleBookingLine[1].trim().split(" ");
        Passenger passenger = new Passenger(pasName[0].trim(), pasName[1].trim());
        ServiceClass seatClass = ServiceClass.valueOf(singleBookingLine[2].trim());
        int row = Integer.parseInt(singleBookingLine[3].trim()) -1;
        int seatIndex = getSeatIndex(singleBookingLine[4].trim().charAt(0));
        Seat seat = new Seat(row, seatIndex, SeatStatus.ALLOCATED, seatClass);
        seat.setPassenger(passenger);
        if (seat.getServiceClass().equals(ServiceClass.F)) {
            flightDetails.getFirstClass()[seatIndex][row] = seat;
            passengerMap.put(passenger.getFirstName()+passenger.getLastName(), seat);
        } else {
            flightDetails.getEconomyClass()[seatIndex][row] = seat;
            passengerMap.put(passenger.getFirstName()+passenger.getLastName(), seat);
        }
    }

    /**
     * Book group from file
     * @param groupName
     * - name of the group
     * @param number
     * - number as the paramter
     * @param bufferedReader
     * - reader for the reading files
     * @throws IOException
     * if does not go through then throws exception
     */
    private void bookGroupFromFile(String groupName, int number, BufferedReader bufferedReader) throws IOException {
        ArrayList<Passenger> passengerArrayList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            String line = bufferedReader.readLine();
            String []singleLine = line.split(",");
            String []pname = singleLine[0].split(" ");
            Passenger passenger = new Passenger(pname[0].trim(), pname[1].trim());
            ServiceClass seatClass = ServiceClass.valueOf(singleLine[1].trim());
            int row = Integer.parseInt(singleLine[2].trim()) -1;
            int seatIndex = getSeatIndex(singleLine[3].trim().charAt(0));
            Seat seat = new Seat(row, seatIndex, SeatStatus.ALLOCATED, seatClass);
            seat.setPassenger(passenger);
            if (seat.getServiceClass().equals(ServiceClass.F)) {
                flightDetails.getFirstClass()[seatIndex][row] = seat;
                passengerMap.put(passenger.getFirstName()+passenger.getLastName(), seat);
            } else {
                flightDetails.getEconomyClass()[seatIndex][row] = seat;
                passengerMap.put(passenger.getFirstName()+passenger.getLastName(), seat);
            }
            passengerArrayList.add(passenger);
        }
        groupMap.put(groupName, passengerArrayList);
    }

    /**
     * Seat Index
     * @param ch
     * - Alphabetical arrangement of seat
     * @return
     * number of the arrangement.
     */
    private int getSeatIndex(char ch) {
        if (ch == 'A') {
            return 0;
        } else if (ch == 'B') {
            return 1;
        } else if (ch == 'C') {
            return 2;
        } else if (ch == 'D') {
            return 3;
        } else if (ch == 'E') {
            return 4;
        } else if (ch == 'F') {
            return 5;
        }
        return -1;
    }


    /**
     * Index of the seat
     * @param seat
     * - takes seat details as the parameters
     * @return
     * - alphabetical number of the seat.
     */
    private char getSeatIndex(Seat seat) {
        if (seat.getServiceClass().equals(ServiceClass.E)) {
            if (seat.getIndex() == 0) {
                return 'A';
            } else if (seat.getIndex() == 1) {
                return 'B';
            } else if (seat.getIndex() == 2) {
                return 'C';
            } else if (seat.getIndex() == 3) {
                return 'D';
            } else if (seat.getIndex() == 4) {
                return 'E';
            } else if (seat.getIndex() == 5) {
                return 'F';
            }
        } else if (seat.getServiceClass().equals(ServiceClass.F)) {
            if (seat.getIndex() == 0) {
                return 'A';
            } else if (seat.getIndex() == 1) {
                return 'B';
            } else if (seat.getIndex() == 2) {
                return 'C';
            } else if (seat.getIndex() == 3) {
                return 'D';
            }
        }
        return 'N';
    }



}