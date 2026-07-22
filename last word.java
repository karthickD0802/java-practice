class Solution {
    public int lengthOfLastWord(String s) {

        // Remove leading and trailing spaces
        s = s.trim();

        // Split the string into words
        String[] words = s.split(" ");

        // Return the length of the last word
        return words[words.length - 1].length();
    }
}