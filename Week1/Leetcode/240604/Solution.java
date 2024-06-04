import java.util.HashMap;
import java.util.Map;

class Solution {
  public int longestPalindrome(String s) {
    int oddFrequencyCount = 0; // 홀수번 등장하는 알파벳의 개수
    Map<Character, Integer> map = new HashMap<>();

    for (int idx = 0; idx < s.length(); idx++) {
      char ch = s.charAt(idx);

      map.put(ch, map.getOrDefault(ch, 0) + 1);
      if (map.get(ch) % 2 == 1) { // 홀수번 등장하는 경우...
        oddFrequencyCount++;
      } else {
        oddFrequencyCount--;
      }
    }

    if (oddFrequencyCount > 1) { // 홀수번 등장하는 알파벳의 개수가 2개 이상인 경우...
      return s.length() - oddFrequencyCount + 1; // 홀수번 등장하는 알파벳 중 하나의 문자는 가운데에 배치 가능
    }

    return s.length();
  }
}