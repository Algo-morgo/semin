class Solution {
  static final int[] DELTA_ROW = { 1, 0, 0, -1 }; // d u
  static final int[] DELTA_COL = { 0, -1, 1, 0 }; // l r
  static final Character[] pos = { 'd', 'l', 'r', 'u' };

  static String answer = "impossible";
  static int startX, startY;
  static int endX, endY;
  static boolean flag;

  static int calculateDistance(int x1, int y1, int x2, int y2) {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
  }

  static boolean isValidMove(int x, int y, int k) {
    int distance = calculateDistance(x, y, endX, endY);
    if (distance > k || (k - distance) % 2 == 1) {
      return false;
    }

    return true;
  }

  static void move(int x, int y, int count, String s, int n, int m) {
    if (flag || count < 0)
      return;

    for (int dir = 0; dir < 4; dir++) {
      if (flag) {
        return;
      }

      int nextRow = x + DELTA_ROW[dir];
      int nextCol = y + DELTA_COL[dir];

      if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m || !isValidMove(nextRow, nextCol, count)) {
        continue;
      }
      if (count > 0) {
        move(nextRow, nextCol, count - 1, s + pos[dir], n, m);
      }

      if (nextRow == endX && nextCol == endY && count == 0) {
        flag = true;
        answer = s + pos[dir];
        return;
      }
    }
  }

  public String solution(int n, int m, int x, int y, int r, int c, int k) {
    startX = x - 1;
    startY = y - 1;
    endX = r - 1;
    endY = c - 1;

    if (startX == endX && startY == endY) {
      return "";
    }

    if (!isValidMove(startX, startY, k)) {
      return answer;
    }

    move(startX, startY, k - 1, "", n, m);

    return answer;
  }
}