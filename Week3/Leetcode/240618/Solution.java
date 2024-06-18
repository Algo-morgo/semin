import java.util.Arrays;

class Solution {
  public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
    int n = difficulty.length;
    int m = worker.length;
    int[][] pairs = new int[n][2];
    Arrays.sort(worker);

    for (int idx = 0; idx < m; idx++) {
      pairs[idx][0] = difficulty[idx];
      pairs[idx][1] = profit[idx];
    }

    int max = 0;

    for (int idx = 0; idx < m; idx++) {
      int tmp = 0;
      for (int j = 0; j < n; j++) {
        if (worker[idx] >= pairs[j][0]) {
          tmp = Math.max(tmp, pairs[j][1]);
        }
      }
      max += tmp;
    }

    return max;
  }
}