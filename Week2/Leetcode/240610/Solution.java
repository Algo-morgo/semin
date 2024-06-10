class Solution {
  public int heightChecker(int[] heights) {
      int answer = 0;
      int[] expected = heights.clone();
      Arrays.sort(expected);

      for (int idx = 0; idx < heights.length; idx++) {
          if (heights[idx] != expected[idx]) {
              answer++;
          }
      }

      return answer;
  }
}