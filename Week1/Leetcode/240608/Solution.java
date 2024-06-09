class Solution {
  public int subarraysDivByK(int[] nums, int k) {
      int answer = 0;
      int prefixSum = 0;
      Map<Integer, Integer> map = new HashMap<>();
      map.put(0, 1);

      for (int num : nums) {
          prefixSum += num;

          int mod = prefixSum % k;

          if (mod < 0) {
              mod += k;
          }

          if (map.containsKey(mod)) { // k로 나누었을 때 나머지가 0인 경우...
              answer += map.get(mod);
              map.put(mod, map.get(mod) + 1);
          } else {
              map.put(mod, 1);
          }
      }

      return answer;
  }
}