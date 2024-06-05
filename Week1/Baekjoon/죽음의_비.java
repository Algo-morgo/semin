package Week1.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 죽음의_비
 */
public class 죽음의_비 {
  static BufferedReader br;
  static StringTokenizer st;

  static int size, health, durability; // 격자의 크기, 현재 체력, 우산의 내구도
  static char[][] map;
  static int[][] visited;
  static final int[] DELTA_ROW = { -1, 1, 0, 0 }; // 상하
  static final int[] DELTA_COL = { 0, 0, 1, -1 }; // 좌우
  static int startRow, startCol; // 시작 위치
  static int minCount = Integer.MAX_VALUE;

  static class Pos {
    int row;
    int col;
    int h; // 체력
    int d; // 우산 내구도
    int count; // 이동 횟수

    public Pos(int row, int col, int h, int d, int count) {
      this.row = row;
      this.col = col;
      this.h = h;
      this.d = d;
      this.count = count;
    }
  }

  static boolean isInRange(int r, int c) {
    return r >= 0 && r < size && c >= 0 && c < size;
  }

  static void bfs(int r, int c) {
    Queue<Pos> queue = new ArrayDeque<>();
    queue.add(new Pos(r, c, health, 0, 0));
    visited[r][c] = health;

    while (!queue.isEmpty()) {
      Pos cur = queue.poll();

      for (int dir = 0; dir < 4; dir++) {
        int h = cur.h;
        int d = cur.d;
        int count = cur.count;
        int nextRow = cur.row + DELTA_ROW[dir];
        int nextCol = cur.col + DELTA_COL[dir];

        if (!isInRange(nextRow, nextCol)) // 이동할 곳이 격자 밖인 경우...
          continue;
        if (map[nextRow][nextCol] == 'E') { // 안전지대인 경우...
          minCount = Math.min(minCount, count + 1); // 최소 이동 횟수 갱신
          continue;
        }

        if (map[nextRow][nextCol] == 'U') { // 우산인 경우...
          d = durability; // 새로운 우산으로 바꾸기
        }

        if (d > 0) { // 내구도가 남아 있는 경우...
          d--; // 내구도 감소
        } else {
          h--; // 체력 감소
        }

        if (h == 0) { // 체력이 0인 경우...
          continue;
        }

        if (visited[nextRow][nextCol] < h + d) { // 방문했던 위치보다 더 이동 가능한 경우...
          visited[nextRow][nextCol] = h + d;
          queue.add(new Pos(nextRow, nextCol, h, d, count + 1));
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine().trim());

    size = Integer.parseInt(st.nextToken());
    health = Integer.parseInt(st.nextToken());
    durability = Integer.parseInt(st.nextToken());

    map = new char[size][size];
    visited = new int[size][size];

    for (int row = 0; row < size; row++) {
      String input = br.readLine().trim();
      for (int col = 0; col < size; col++) {
        map[row][col] = input.charAt(col);
        if (map[row][col] == 'S') { // 시작 위치
          startRow = row;
          startCol = col;
        }
      }
    }

    bfs(startRow, startCol);

    minCount = minCount == Integer.MAX_VALUE ? -1 : minCount;
    System.out.println(minCount);

  }
}