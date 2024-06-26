class Solution {
  static int[] dp1, dp2;
  static int n;
  
  void solve (int sticker[]) {
      dp1[0] = sticker[0];
      dp1[1] = sticker[0];
      
      dp2[1] = sticker[1];
      
      for (int idx = 2; idx < n - 1; idx++) {
          dp1[idx] = Math.max(dp1[idx - 2] + sticker[idx], dp1[idx - 1]);
      }
      
      for (int idx = 2; idx < n; idx++) {
          dp2[idx] = Math.max(dp2[idx - 2] + sticker[idx], dp2[idx - 1]);
      }
  }
  
  public int solution(int sticker[]) {
      n = sticker.length;
      if(n == 1) {
          return sticker[0];
      } else if(n == 2) {
          return Math.max(sticker[0], sticker[1]);
      }
      
      dp1 = new int[n - 1];
      dp2 = new int[n];
      
      solve(sticker);

      return Math.max(dp1[dp1.length - 1], dp2[dp2.length - 1]);
  }
}