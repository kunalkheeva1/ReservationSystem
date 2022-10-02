import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ReservationSystem {
    public static void main(String[] args) {
        System.out.println("Reservation system begins....");

        BookingService bookingService = new BookingService();
        System.out.println("input file name is: " + args[0]);

        if(args[0] != null && (new File(args[0]).exists())){
            bookingService.readFromFile(args[0]);
        }

        try{
            BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservation"+
                    "\n Print seating: [A]vailability chart, [M]anifest, [Q]uit");

            String input =null;
            input = scanner.readLine();

            while(!input.equalsIgnoreCase("Q")){
                if(input.equalsIgnoreCase("P")){

                    System.out.printf("Name: ");
                    String name = scanner.readLine();
                    String[] pName = name.split(" ");
                    System.out.printf("Choose Service Class: [F]irst or [E]conomy : ");
                    String serviceClass = scanner.readLine();
                    System.out.printf("[W]indow, [A]isle, [C]enter: ");
                    String seatPreference = scanner.readLine();
                    ArrayList<Passenger> arrayList = new ArrayList<>();
                    arrayList.add(new Passenger(pName[0],pName[1]));
                    ReservationRequest reservationRequest = new ReservationRequest(BookingType.SINGLE, arrayList,
                            SeatPreference.valueOf(seatPreference.toUpperCase()), ServiceClass.valueOf(serviceClass.toUpperCase()), null);
                    bookingService.book(reservationRequest);
                } else if (input.equalsIgnoreCase("g")){
                    System.out.printf("GroupName: ");
                    String groupName = scanner.readLine();
                    System.out.printf("Enter Passenger Names(comma separated please): ");
                    String nameLine = scanner.readLine();

                    String[] nameList = nameLine.split(",");
                    ArrayList<Passenger> passengersArrayList = new ArrayList<>();
                    for(String name: nameList){
                        String[] pName = name.trim().split(" ");
                        passengersArrayList.add(new Passenger(pName[0],pName[1]));
                    }
                    System.out.printf("Service Class ([F]irst or [E]conomy: ");
                    String seatClass = scanner.readLine();
                    ReservationRequest reservationRequest = new ReservationRequest(BookingType.GROUP, passengersArrayList,
                            null, ServiceClass.valueOf(seatClass.toUpperCase()),groupName);
                    bookingService.book(reservationRequest);
                }
                else if(input.equalsIgnoreCase("c")){
                    System.out.printf("Cancel [G]roup or [I]ndividual");
                    input = scanner.readLine();
                    if(input.equalsIgnoreCase("i")){
                        System.out.printf("Name: ");
                        String name = scanner.readLine();
                        String[] pName = name.split(" ");
                        Passenger passenger = new Passenger(pName[0].trim(),pName[1].trim());
                        CancellationRequest cancellationRequest = new CancellationRequest(CancellationType.SINGLE, null, passenger);
                        bookingService.cancel(cancellationRequest);
                    }
                    else{
                        System.out.printf("Group Name: ");
                        String groupName = scanner.readLine();
                        CancellationRequest cancellationRequest = new CancellationRequest(CancellationType.GROUP, groupName.trim(),null);
                        bookingService.cancel(cancellationRequest);
                    }
                } else if(input.equalsIgnoreCase("a")){
                    System.out.printf("Service class [F]irst or [E]conomy: ");
                    String serviceClass = scanner.readLine();
                    bookingService.checkAvailability(ServiceClass.valueOf(serviceClass.toUpperCase()));
                }
                else if(input.equalsIgnoreCase("m")){
                    System.out.printf("Service class [F]irst or [E]conomy: ");
                    String serviceClass = scanner.readLine();
                    bookingService.checkAvailability(ServiceClass.valueOf(serviceClass.toUpperCase()));
                }
                System.out.printf("Add [P]assenger or [G]roup, [C]ancel Reservation, " +
                        "Print seat [A]vailability chart, [M]anifest, [Q]uit ");
                input = scanner.readLine();
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        bookingService.writeToFile(args[0]);
        System.out.println("Booking are written to file: " + args[0]);
        System.out.println("Reservation System Ended.........");
    }


}
