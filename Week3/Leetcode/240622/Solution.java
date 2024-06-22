class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        int[] prefixCount = new int[nums.length + 1];
        prefixCount[0] = 1;

        for (int idx = 0; idx < nums.length; idx++) {
            nums[idx] %= 2;
        }

        for (int num : nums) {
            sum += num;
            if (sum >= k) {
                count += prefixCount[sum - k];
            }
            prefixCount[sum]++;
        }

        return count;
    }
}