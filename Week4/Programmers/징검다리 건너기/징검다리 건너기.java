import java.util.*;

class Solution {
  boolean isPossible(int[] stones, int k, int d) {
    int count = 0; // 건너지 못하는 연속 횟수
    for (int idx = 0; idx < stones.length; idx++) {
      if (stones[idx] <= d) {
        count++;
        if (count >= k) { // 건너지 못하는 연속 횟수가 k명 이상인 경우...
          return false;
        }
      } else { // 연속 횟수 초기화
        count = 0;
      }
    }

    return true;
  }

  public int solution(int[] stones, int k) {
    int answer = 0;
    int n = stones.length;
    int[] copy = stones.clone();
    Arrays.sort(copy);
    int lo = 1;
    int hi = copy[n - 1];

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (isPossible(stones, k, mid)) {
        lo = mid + 1;
        answer = lo;
      } else {
        hi = mid - 1;
      }
    }

    return answer;
  }
}