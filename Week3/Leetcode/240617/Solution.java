class Solution {
  public boolean judgeSquareSum(int c) {
    long lo = 0;
    long hi = (long) Math.sqrt(c);

    while (lo <= hi) {
      long sum = lo * lo + hi * hi;
      if (sum < c) {
        lo++;
      } else if (sum > c) {
        hi--;
      } else {
        return true;
      }
    }

    return false;
  }
}