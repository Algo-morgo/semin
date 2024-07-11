import java.util.*;

class Solution {
  static Set<Integer> tuple;

  public int[] solution(String s) {
    tuple = new HashSet<>();

    String[] input = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");

    Arrays.sort(input, (s1, s2) -> s1.length() - s2.length());

    int[] answer = new int[input.length];
    int idx = 0;
    for (String str : input) {
      for (String e : str.split(",")) {
        int num = Integer.parseInt(e);
        if (tuple.add(num)) {
          answer[idx++] = num;
        }
      }
    }

    return answer;
  }
}
