public class CancellationRequest {
    CancellationType cancellationType;
    Passenger passengerName;
    String groupId;

    /**
     * Cancellation Request
     * @param cancellationType
     * - type of the cancellation whether Individual or group
     * @param groupId
     * - name of the group
     * @param passengerName
     * - name of the passenger
     */
    public CancellationRequest(CancellationType cancellationType, String groupId, Passenger passengerName){
        this.cancellationType = cancellationType;
        this.passengerName = passengerName;
        this.groupId = groupId;
    }

    public CancellationType getCancellationType() {
        return cancellationType;
    }

    public void setCancellationType(CancellationType cancellationType) {
        this.cancellationType = cancellationType;
    }

    public Passenger getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(Passenger passengerName) {
        this.passengerName = passengerName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
