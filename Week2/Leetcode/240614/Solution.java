class Solution {
    public int minIncrementForUnique(int[] nums) {
        int minIncrement = 0;
        Arrays.sort(nums);

        for (int idx = 1; idx < nums.length; idx++) {
            if (nums[idx] <= nums[idx - 1]) {
                minIncrement += nums[idx - 1] - nums[idx] + 1;
                nums[idx] = nums[idx - 1] + 1;
            }
        }

        return minIncrement;
    }
}