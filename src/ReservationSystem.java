import java.io.File;

public class ReservationSystem {
    public static void main(String[] args) {

        //file creation function
        try{
            File file = new File("file.txt");
            //if there is no file
            if(file.createNewFile()){
                System.out.println("File created Successfully");
            }
            else{
                System.out.println("File already exists! ");
            }
        }catch(Exception exception){
            System.err.println(exception);
        }

    }
    //seat class, passenger class,booking
}
