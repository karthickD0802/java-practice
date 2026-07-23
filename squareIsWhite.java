class Solution {
    public boolean squareIsWhite(String coordinates) {
        char column = coordinates.charAt(0);
        char row = coordinates.charAt(1);

        if ((column + row) % 2 == 1) {
            return true;
        } else {
            return false;
        }
    }
}