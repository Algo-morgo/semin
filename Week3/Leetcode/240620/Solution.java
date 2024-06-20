class Solution {
  boolean solve(int[] position, int distance, int m) {
      // ball을 가장 첫번째 위치에 놓기
      int count = 1;
      int end = position[0];

      for (int idx = 1; idx < position.length; idx++) {
          if (position[idx] - end >= distance) {
              count++;
              end = position[idx];
          }
          if (count >= m) { // 모든 공을 놓을 수 있는 경우...
              return true;
          }
      }

      return false;
  }

  public int maxDistance(int[] position, int m) {
      int max = 1;

      int n = position.length;
      Arrays.sort(position);

      int lo = 1;
      int hi = (position[n - 1] - position[0]) / (m - 1);

      while (lo <= hi) {
          int mid = lo + (hi - lo) / 2;
          if (solve(position, mid, m)) {
              max = mid;
              lo = mid + 1;
          } else {
              hi = mid - 1;
          }
      }

      return max;
  }
}