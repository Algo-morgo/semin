class Solution {
  public void sortColors(int[] nums) {
    int[] counts = new int[3];

    for (int num : nums) {
      counts[num]++;
    }

    int numIdx = 0;
    for (int idx = 0; idx < 3; idx++) {
      while (counts[idx]-- > 0) {
        nums[numIdx++] = idx;
      }
    }
  }
}