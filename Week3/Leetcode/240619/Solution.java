class Solution {
  public int solve(int[] bloomDay, int mid, int k) {
    int num = 0;
    int count = 0;

    for (int idx = 0; idx < bloomDay.length; idx++) {
      if (bloomDay[idx] <= mid) {
        count++;
      } else {
        count = 0;
      }

      if (count == k) {
        num++;
        count = 0;
      }
    }

    return num;
  }

  public int minDays(int[] bloomDay, int m, int k) {
    int lo = 0;
    int hi = 0;
    int minDays = -1;

    for (int day : bloomDay) {
      hi = Math.max(hi, day);
    }

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (solve(bloomDay, mid, k) >= m) {
        minDays = mid;
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }

    return minDays;
  }
}