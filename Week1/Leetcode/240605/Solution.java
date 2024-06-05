import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> commonChars(String[] words) {
        List<String> answer = new ArrayList<>();

        int[] minCount = new int[26];
        for (int idx = 0; idx < minCount.length; idx++) {
            minCount[idx] = Integer.MAX_VALUE;
        }

        for (String word : words) {
            int[] count = new int[26];
            // 각 단어에서 알파벳 등장 횟수 세기
            for (int idx = 0; idx < word.length(); idx++) {
                count[word.charAt(idx) - 'a']++;
            }
            // 모든 단어에서 등장하는 알파벳에 대하여 최소 등장 횟수 세기
            for (int idx = 0; idx < minCount.length; idx++) {
                minCount[idx] = Math.min(minCount[idx], count[idx]);
            }
        }

        for (int idx = 0; idx < minCount.length; idx++) {
            while (minCount[idx]-- > 0) {
                answer.add(String.valueOf((char) ('a' + idx)));
            }
        }

        return answer;
    }
}