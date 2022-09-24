import java.io.File;
import java.io.IOException;
import java.util.*;

public class ReservationSystem {
    public static void main(String[] args) {

        ArrayList<ReservationRequest> data = new ArrayList<>();


        // checks the file name and throws exception if it does not exist
        String fName = "//file path here";
        File file = new File(fName);
        if(file.exists()){
            System.out.println("File exists!!");
        }
        else{
            System.out.println("File does not exit!");
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }







    }
    //seat class, passenger class,booking

}
