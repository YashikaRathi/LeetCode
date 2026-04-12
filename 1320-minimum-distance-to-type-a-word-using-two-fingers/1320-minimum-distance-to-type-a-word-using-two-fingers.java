class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        
        // dp array: 26 letters + 1 (for unused finger '#')
        int[][] dp = new int[n][27];
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        // initial state
        dp[0][26] = 0; // 26 means "no finger placed yet"
        
        for (int i = 1; i < n; i++) {
            char prev = word.charAt(i - 1);
            char curr = word.charAt(i);
            
            for (int f = 0; f <= 26; f++) {
                if (dp[i - 1][f] == Integer.MAX_VALUE) continue;
                
                // Case 1: same finger moves
                dp[i][f] = Math.min(
                    dp[i][f],
                    dp[i - 1][f] + dist(prev, curr)
                );
                
                // Case 2: other finger moves
                int prevIndex = prev - 'A';
                int moveCost = (f == 26) ? 0 : dist((char)(f + 'A'), curr);
                
                dp[i][prevIndex] = Math.min(
                    dp[i][prevIndex],
                    dp[i - 1][f] + moveCost
                );
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int f = 0; f <= 26; f++) {
            res = Math.min(res, dp[n - 1][f]);
        }
        
        return res;
    }
    
    private int dist(char a, char b) {
        int x1 = (a - 'A') / 6, y1 = (a - 'A') % 6;
        int x2 = (b - 'A') / 6, y2 = (b - 'A') % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}