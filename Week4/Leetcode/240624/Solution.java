class Solution {
  public int minKBitFlips(int[] nums, int k) {
    int n = nums.length;
    int[] isFlipped = new int[n];
    int flipped = 0;
    int answer = 0;

    for (int idx = 0; idx < n; idx++) {
      if (idx >= k) {
        flipped ^= isFlipped[idx - k];
      }
      if (flipped == nums[idx]) {
        if (idx + k > n) {
          return -1;
        }
        isFlipped[idx] = 1;
        flipped ^= 1;
        answer++;
      }
    }

    return answer;
  }
}