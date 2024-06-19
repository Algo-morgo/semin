class Solution {
  static final int ATTACK = 1;
  static final int HEAL = 2;
  static int[][] arr;

  static void attack(int r1, int c1, int r2, int c2, int degree, int[][] board) {
    arr[r1][c1] -= degree;
    arr[r2 + 1][c2 + 1] -= degree;
    arr[r1][c2 + 1] += degree;
    arr[r2 + 1][c1] += degree;
  }

  static void heal(int r1, int c1, int r2, int c2, int degree, int[][] board) {
    arr[r1][c1] += degree;
    arr[r2 + 1][c2 + 1] += degree;
    arr[r1][c2 + 1] -= degree;
    arr[r2 + 1][c1] -= degree;
  }

  public int solution(int[][] board, int[][] skill) {
    int answer = 0;
    arr = new int[board.length + 1][board[0].length + 1];

    for (int idx = 0; idx < skill.length; idx++) {
      int type = skill[idx][0];
      int r1 = skill[idx][1];
      int c1 = skill[idx][2];
      int r2 = skill[idx][3];
      int c2 = skill[idx][4];
      int degree = skill[idx][5];

      if (type == ATTACK) {
        attack(r1, c1, r2, c2, degree, board);
      } else if (type == HEAL) {
        heal(r1, c1, r2, c2, degree, board);
      }
    }

    // 위에서 아래로 누적합
    for (int i = 1; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        arr[i][j] += arr[i - 1][j];
      }
    }

    // 왼쪽에서 오른쪽으로 누적합
    for (int i = 0; i < arr.length; i++) {
      for (int j = 1; j < arr[0].length; j++) {
        arr[i][j] += arr[i][j - 1];
      }
    }

    // board와 합치기
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        board[r][c] += arr[r][c];
        if (board[r][c] > 0) {
          answer++;
        }
      }
    }

    return answer;
  }
}