import java.util.*;

class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                
                if (countBits(h) + countBits(m) == turnedOn) {
                    // format minute to 2 digits
                    result.add(h + ":" + (m < 10 ? "0" + m : m));
                }
            }
        }
        
        return result;
    }
    
    private int countBits(int num) {
        int count = 0;
        while (num > 0) {
            count += num & 1;
            num >>= 1;
        }
        return count;
    }
}