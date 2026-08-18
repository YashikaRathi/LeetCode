class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // Convert empty string to word2[0...j]
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // Characters already match
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Insert, delete, or replace
                    dp[i][j] = 1 + Math.min(
                        dp[i][j - 1],                 // Insert
                        Math.min(
                            dp[i - 1][j],             // Delete
                            dp[i - 1][j - 1]          // Replace
                        )
                    );
                }
            }
        }

        return dp[m][n];
    }
}