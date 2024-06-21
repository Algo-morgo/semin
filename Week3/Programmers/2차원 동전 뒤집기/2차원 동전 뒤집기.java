class Solution {
  static int rowSize, colSize;
  static int min = 101; // 동전은 최대 100개

  static void flipRow(int[][] beginning, int row) {
    // 한 줄 뒤집기
    for (int i = 0; i < beginning[0].length; i++) {
      beginning[row][i] = 1 - beginning[row][i];
    }
  }

  static int countDiff(int[][] beginning, int[][] target, int col) {
    int diff = 0;
    // beginning 과 target 비교하여 다른 면 개수 세기
    for (int i = 0; i < beginning.length; i++) {
      if (beginning[i][col] != target[i][col]) {
        diff++;
      }
    }
    return diff;
  }

  static void dfs(int row, int count, int[][] beginning, int[][] target) {
    if (row == rowSize) {
      int result = count;
      for (int col = 0; col < colSize; col++) {
        int diff = countDiff(beginning, target, col);
        if (diff == rowSize) { // 목표 상태 와 정반대이므로 뒤집고 횟수 1 증가
          result++;
        } else if (diff != 0) { // 목표 상태를 만들수 없음
          return;
        }
      }
      min = Math.min(min, result); // 최솟갑 갱신
      return;
    }

    // 뒤집고 넘어가기
    flipRow(beginning, row);
    dfs(row + 1, count + 1, beginning, target);

    // 뒤집지 않고 넘어가기
    flipRow(beginning, row);
    dfs(row + 1, count, beginning, target);
  }

  public int solution(int[][] beginning, int[][] target) {
    rowSize = beginning.length;
    colSize = beginning[0].length;

    dfs(0, 0, beginning, target);

    return min == 101 ? -1 : min;
  }
}