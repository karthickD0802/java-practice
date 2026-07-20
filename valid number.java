class Solution {
    public boolean isNumber(String s) {

        boolean hasDigit = false;
        boolean hasDecimal = false;
        boolean hasExponent = false;
        boolean digitAfterExponent = true;

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (Character.isDigit(current)) {
                hasDigit = true;
                digitAfterExponent = true;
            }
            else if (current == '.') {
                // Decimal point can appear only once and before exponent
                if (hasDecimal || hasExponent) {
                    return false;
                }
                hasDecimal = true;
            }
            else if (current == 'e' || current == 'E') {
                // Exponent must come after a number and only once
                if (hasExponent || !hasDigit) {
                    return false;
                }
                hasExponent = true;
                digitAfterExponent = false;
            }
            else if (current == '+' || current == '-') {
                if (i != 0) {
                    char previous = s.charAt(i - 1);
                    if (previous != 'e' && previous != 'E') {
                        return false;
                    }
                }
            }
            else {
                return false;
            }
        }

        return hasDigit && digitAfterExponent;
    }
}