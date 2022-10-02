public class Passenger {
    String firstName, lastName;

    /**
     * Firs and last name of the passenger
     * @param firstName
     * - first name of the passenger
     * @param lastName
     * - last name of the passenger
     */
    public Passenger(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
