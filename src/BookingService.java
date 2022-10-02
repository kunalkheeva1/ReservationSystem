import java.io.*;
import java.lang.reflect.Array;
import java.security.Provider;
import java.util.*;

public class BookingService {

    FlightDetails flightDetails;
    Map<String, Seat> passengerMap;
    Map<String, ArrayList<Passenger>> groupMap;

    public BookingService(){
        flightDetails = new FlightDetails(2,20);
        passengerMap = new HashMap<>();
        groupMap = new HashMap<>();
    }

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
            System.out.println("[Economy class] Available seats :" + availableEcoSeats + " and requested seats : " + reservationRequest.getPassenger().size());
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
            System.out.println("[First class] Available seats :" + availableSeats + " and requested seats : " + reservationRequest.getPassenger().size());
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


    public void seatManifest(SeatClass seatClass) {
        if (seatClass.equals(SeatClass.F)) {
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


}