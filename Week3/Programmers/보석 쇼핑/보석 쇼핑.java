import java.util.*;

class Solution {
  static Map<String, Integer> map;
  static Set<String> set;

  public int[] solution(String[] gems) {
    int[] answer = new int[2];

    map = new HashMap<>();
    set = new HashSet<>(List.of(gems));
    int len = Integer.MAX_VALUE, start = 0;

    for (int end = 0; end < gems.length; end++) {
      map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);

      while (map.get(gems[start]) > 1) {
        map.put(gems[start], map.get(gems[start]) - 1);
        start++;
      }

      if (map.size() == set.size() && len > (end - start)) {
        len = end - start;
        answer[0] = start + 1;
        answer[1] = end + 1;
      }
    }

    return answer;
  }
}