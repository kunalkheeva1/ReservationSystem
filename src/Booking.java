public class Booking {
    //for group
    String [] passengerNameGroup ={};
    String [] serviceClassGroup ={};
    public void bookGroup(String[] serviceClass, String [] pName ){
        this.serviceClassGroup = serviceClass;
        for(int i = 0; i< passengerNameGroup.length; i++){
            passengerNameGroup[i] = pName[i];
        }
    }

    //cancel group
    public void cancelGroup(String[] pName){
    for(int i = 0; i< passengerNameGroup.length; i++){
        if(passengerNameGroup[i].contains(pName[i])){
            passengerNameGroup =null;  //if it contains either of the name nullify the whole array of holding
        }
    }
    }


    String [] serviceClassSingle = {};
    String [] seatPreference = {};
    Passenger passenger;

    public void bookSingle(Passenger passenger,String [] serviceClassSingle, String [] seatPreference ){

    }
    public void cancelSingle(){

    }


}
