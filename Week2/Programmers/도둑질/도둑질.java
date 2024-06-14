class Solution {
  static int[] dp; // idx 번째 집까지 털었을 때 돈의 최댓값

  public int solution(int[] money) {
    dp = new int[money.length];

    // 1. 첫 번째 집을 터는 경우
    dp[0] = money[0];
    dp[1] = money[0];

    for (int idx = 2; idx < money.length - 1; idx++) {
      // (전전 집까지 털었을 때 돈의 최댓값 + 현재 집에 있는 돈)
      // 현재(idx번째) 집을 털지 않는 경우 중 최댓값
      dp[idx] = Math.max(dp[idx - 2] + money[idx], dp[idx - 1]);
    }

    int answer = dp[money.length - 2];

    // 2. 첫 번째 집을 털지 않는 경우
    dp[0] = 0;
    dp[1] = money[1];

    for (int idx = 2; idx < money.length; idx++) {
      dp[idx] = Math.max(dp[idx - 2] + money[idx], dp[idx - 1]);
    }

    answer = Math.max(answer, dp[money.length - 1]);

    return answer;
  }
}