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

                }
            }

        }
    }


}
