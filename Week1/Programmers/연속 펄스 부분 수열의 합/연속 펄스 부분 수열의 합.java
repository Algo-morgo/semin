class Solution {
  long[][] dp; // dp[i][k] : i번째 인덱스를 마지막으로 갖는 부분 수열 합 최댓값 (k = 0인 경우, 1, -1, 1)

  public long solution(int[] sequence) {
    long answer = 0;
    dp = new long[sequence.length][2];
    dp[0][0] = sequence[0];
    dp[0][1] = -sequence[0];
    answer = Math.max(dp[0][0], dp[0][1]);

    for (int idx = 1; idx < sequence.length; idx++) {
      int seq = sequence[idx];
      dp[idx][0] = Math.max(seq, dp[idx - 1][1] + seq);
      dp[idx][1] = Math.max(-seq, dp[idx - 1][0] - seq);

      answer = Math.max(answer, Math.max(dp[idx][0], dp[idx][1]));
    }

    return answer;
  }
}