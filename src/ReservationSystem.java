import java.io.File;
import java.io.IOException;
import java.util.*;

public class ReservationSystem {
    public static void main(String[] args) {

        ArrayList<ReservationRequest> data = new ArrayList<>();


        // checks the file name and throws exception if it does not exist
        String fName = args[0];
        String fName1;
       try {
           File file = new File(fName);
           if (file.exists() == false) {
               file.createNewFile();
           }
           fName1 = mainMenu;
       }catch (Exception e){
           e.printStackTrace();;
       }








    }
    //seat class, passenger class,booking

}
