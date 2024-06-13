import java.util.*;

class Solution {
  static int n;
  static int A, B;

  public int solution(int[][] scores) {
    int answer = 1;
    n = scores.length;
    A = scores[0][0];
    B = scores[0][1];

    Arrays.sort(scores, (o1, o2) -> {
      if (o1[0] == o2[0]) {
        return o1[1] - o2[1];
      }
      return o2[0] - o1[0];
    });

    int max = scores[0][1];

    for (int idx = 1; idx < n; idx++) {
      if (scores[idx][1] < max) {
        if (scores[idx][0] == A && scores[idx][1] == B) {
          return -1;
        }
        scores[idx][0] = -1;
        scores[idx][1] = -1;
      } else {
        max = scores[idx][1];
      }
    }

    Arrays.sort(scores, (o1, o2) -> (o2[0] + o2[1]) - (o1[0] + o1[1]));

    for (int idx = 0; idx < n; idx++) {
      if (scores[idx][0] + scores[idx][1] > A + B) {
        answer++;
      } else {
        break;
      }
    }

    return answer;
  }
}