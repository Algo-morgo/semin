import java.util.*;

class Solution {
  static int M, N;
  static int count;
  static final int[] DELTA_ROW = { -1, 0, 1, 0 };
  static final int[] DELTA_COL = { 0, 1, 0, -1 };

  public static boolean isInRange(int row, int col) {
    return row >= 0 && row < N && col >= 0 && col < N;
  }

  public static int[][] rotate(int[][] key) {
    int[][] rotated = new int[M][M];

    for (int i = 0; i < M; i++) {
      for (int j = 0; j < M; j++) {
        rotated[i][j] = key[M - 1 - j][i];
      }
    }

    return rotated;
  }

  public static boolean check(int[][] arr, int[][] lock) {
    List<int[]> list = new ArrayList<>();

    // key의 돌기 부분 찾기
    for (int row = 0; row < M; row++) {
      for (int col = 0; col < M; col++) {
        if (arr[row][col] == 1) {
          list.add(new int[] { row, col });
        }
      }
    }

    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        if (lock[row][col] == 1)
          continue; // lock의 돌기 부분인 경우... 무시
        for (int[] target : list) {
          boolean flag = true;
          int sum = 1;
          for (int[] e : list) { // key의 돌기 부분 탐색
            int nextRow = e[0] - target[0];
            int nextCol = e[1] - target[1];
            if (nextRow == 0 && nextCol == 0)
              continue;
            int checkRow = row + nextRow;
            int checkCol = col + nextCol;
            if (!isInRange(checkRow, checkCol))
              continue; // 범위 체크
            if (lock[checkRow][checkCol] == 1) { // lock의 돌기 부분
              flag = false;
              break;
            } else { // lock의 홈 부분
              sum++;
            }
          }

          if (flag && sum == count) { // 모든 부분이 일치하는 경우...
            return true;
          }
        }
      }
    }

    return false;
  }

  public boolean solution(int[][] key, int[][] lock) {
    M = key.length;
    N = lock.length;

    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        if (lock[row][col] == 0) { // 홈 부분 개수 세기
          count++;
        }
      }
    }

    if (count == 0) { // 홈이 없음
      return true;
    }

    int[][] copied = key.clone();

    for (int i = 0; i < 4; i++) {
      if (i != 0) {
        copied = rotate(copied);
      }

      if (check(copied, lock)) {
        return true;
      }
    }

    return false;
  }
}