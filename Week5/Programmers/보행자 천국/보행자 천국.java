class Solution {
  int MOD = 20170805;
  static int[][][] dp;
  static final int RIGHT = 0;
  static final int DOWN = 1;

  public int solution(int m, int n, int[][] cityMap) {
    if (m == 1 && n == 1) {
      return 1;
    }

    dp = new int[m][n][2];

    for (int i = 1; i < m; i++) {
      if (cityMap[0][i] == 1) {
        break;
      }
      dp[0][i][RIGHT] = 1;
    }

    for (int i = 1; i < n; i++) {
      if (cityMap[i][0] == 1) {
        break;
      }
      dp[i][0][DOWN] = 1;
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (cityMap[i - 1][j] == 0) {
          dp[i][j][DOWN] = (dp[i - 1][j][DOWN] + dp[i - 1][j][RIGHT]) % MOD;
        }
        if (cityMap[i - 1][j] == 1) {
          dp[i][j][DOWN] = 0;
        }
        if (cityMap[i - 1][j] == 2) {
          dp[i][j][DOWN] = dp[i - 1][j][DOWN];
        }

        if (cityMap[i][j - 1] == 0) {
          dp[i][j][RIGHT] = (dp[i][j - 1][DOWN] + dp[i][j - 1][RIGHT]) % MOD;
        }
        if (cityMap[i][j - 1] == 1) {
          dp[i][j][RIGHT] = 0;
        }
        if (cityMap[i][j - 1] == 2) {
          dp[i][j][RIGHT] = dp[i][j - 1][RIGHT];
        }
      }
    }

    return (dp[m - 1][n - 1][DOWN] + dp[m - 1][n - 1][RIGHT]) % MOD;
  }
}