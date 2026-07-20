class ParkingSystem {
   
    private int bigSlots;
    private int mediumSlots;
    private int smallSlots;

 
    public ParkingSystem(int big, int medium, int small) {
        this.bigSlots = big;
        this.mediumSlots = medium;
        this.smallSlots = small;
    }
    
    public boolean addCar(int carType) {
        if (carType == 1) { // Big car
            if (bigSlots > 0) {
                bigSlots--; // Decrement available slot
                return true;
            } else {
                return false;
            }
        } else if (carType == 2) { // Medium car
            if (mediumSlots > 0) {
                mediumSlots--; // Decrement available slot
                return true;
            } else {
                return false;
            }
        } else if (carType == 3) { // Small car
            if (smallSlots > 0) {
                smallSlots--; // Decrement available slot
                return true;
            } else {
                return false;
            }
        }
        
        return false;
    }
}