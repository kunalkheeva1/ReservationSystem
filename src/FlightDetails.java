public class FlightDetails {
   Seat[][] firstClass;
   int firstRows;

   Seat[][] economyClass;
   int economyRows;

   public FlightDetails(int firstRows, int economyRows){
      this.firstClass = new Seat[4][firstRows];
      this.firstRows = firstRows;
      this.economyClass = new Seat[6][economyRows];
      this.economyRows = economyRows;
   }

   public Seat[][] getFirstClass() {
      return firstClass;
   }

   public void setFirstClass(Seat[][] firstClass) {
      this.firstClass = firstClass;
   }

   public int getFirstRows() {
      return firstRows;
   }

   public void setFirstRows(int firstRows) {
      this.firstRows = firstRows;
   }

   public Seat[][] getEconomyClass() {
      return economyClass;
   }

   public void setEconomyClass(Seat[][] economyClass) {
      this.economyClass = economyClass;
   }

   public int getEconomyRows() {
      return economyRows;
   }

   public void setEconomyRows(int economyRows) {
      this.economyRows = economyRows;
   }


}
