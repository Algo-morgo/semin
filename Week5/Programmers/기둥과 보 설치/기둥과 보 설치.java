class Solution {
  static final int PILLAR = 0;
  static final int BEAM = 1;
  static final int DESTROY = 0;
  static final int BUILD = 1;

  static boolean[][] pillars;
  static boolean[][] beams;

  static boolean canBuildPillar(int x, int y) {
    return y == 1 || pillars[x][y - 1] || beams[x][y] || beams[x - 1][y];
  }

  static boolean canBuildBeam(int x, int y) {
    return pillars[x][y - 1] || pillars[x + 1][y - 1] || (beams[x - 1][y] && beams[x + 1][y]);
  }

  static boolean canDestroy(int x, int y, int type, int n) {
    for (int i = 1; i <= n + 1; i++) {
      for (int j = 1; j <= n + 1; j++) {
        if (pillars[i][j] && !canBuildPillar(i, j)) {
          return false;
        }
        if (beams[i][j] && !canBuildBeam(i, j)) {
          return false;
        }
      }
    }

    return true;
  }

  public int[][] solution(int n, int[][] build_frame) {
    int count = 0;

    pillars = new boolean[n + 3][n + 3];
    beams = new boolean[n + 3][n + 3];

    for (int[] frame : build_frame) {
      int x = frame[0] + 1;
      int y = frame[1] + 1;
      int a = frame[2];
      int b = frame[3];

      if (b == BUILD) {
        if (a == PILLAR && canBuildPillar(x, y)) {
          pillars[x][y] = true;
          count++;
        } else if (a == BEAM && canBuildBeam(x, y)) {
          beams[x][y] = true;
          count++;
        }
      } else if (b == DESTROY) {
        if (a == PILLAR) {
          pillars[x][y] = false;
        } else if (a == BEAM) {
          beams[x][y] = false;
        }

        if (canDestroy(x, y, a, n)) {
          count--;
          continue;
        }

        if (a == PILLAR) {
          pillars[x][y] = true;
        } else if (a == BEAM) {
          beams[x][y] = true;
        }
      }
    }

    int index = 0;
    int[][] answer = new int[count][3];
    for (int i = 1; i <= n + 1; i++) {
      for (int j = 1; j <= n + 1; j++) {
        if (pillars[i][j]) {
          answer[index++] = new int[] { i - 1, j - 1, PILLAR };
        }
        if (beams[i][j]) {
          answer[index++] = new int[] { i - 1, j - 1, BEAM };
        }
      }
    }

    return answer;
  }
}