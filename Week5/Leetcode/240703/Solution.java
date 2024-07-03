class Solution {
  public int minDifference(int[] nums) {
    if (nums.length <= 4) {
      return 0;
    }

    Arrays.sort(nums);
    int answer = nums[nums.length - 1] - nums[0];
    for (int idx = 0; idx <= 3; idx++) {
      answer = Math.min(answer, nums[nums.length - (3 - idx) - 1] - nums[idx]);
    }

    return answer;
  }
}