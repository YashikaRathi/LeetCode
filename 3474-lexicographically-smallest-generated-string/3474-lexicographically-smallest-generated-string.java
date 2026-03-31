class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        char[] word = new char[n + m - 1];
        boolean[] locked = new boolean[n + m - 1];

        // Step 1: fill with '?'
        for (int i = 0; i < word.length; i++) {
            word[i] = '?';
        }

        // Step 2: apply 'T' constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (word[i + j] == '?' || word[i + j] == str2.charAt(j)) {
                        word[i + j] = str2.charAt(j);
                        locked[i + j] = true; // mark as fixed
                    } else {
                        return ""; // conflict
                    }
                }
            }
        }

        // Step 3: fill remaining with 'a'
        for (int i = 0; i < word.length; i++) {
            if (word[i] == '?') {
                word[i] = 'a';
            }
        }

        // Step 4: handle 'F'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean match = true;

                for (int j = 0; j < m; j++) {
                    if (word[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    boolean changed = false;

                    // try to break match without touching locked positions
                    for (int j = m - 1; j >= 0; j--) {
                        if (locked[i + j]) continue;

                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c != str2.charAt(j)) {
                                word[i + j] = c;
                                changed = true;
                                break;
                            }
                        }

                        if (changed) break;
                    }

                    if (!changed) return "";
                }
            }
        }

        // Step 5: final validation
        for (int i = 0; i < n; i++) {
            boolean match = true;

            for (int j = 0; j < m; j++) {
                if (word[i + j] != str2.charAt(j)) {
                    match = false;
                    break;
                }
            }

            if (str1.charAt(i) == 'T' && !match) return "";
            if (str1.charAt(i) == 'F' && match) return "";
        }

        return new String(word);
    }
}