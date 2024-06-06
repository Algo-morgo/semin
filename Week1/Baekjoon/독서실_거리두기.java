import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 독서실_거리두기
 */
public class 독서실_거리두기 {
  static BufferedReader br;
  static StringTokenizer st;

  static int N, T, P;
  static boolean[] visited;
  static int[] seat;
  static int answer;

  static class Reservation implements Comparable<Reservation> {
    int in;
    int out;

    public Reservation(int in, int out) { // 초 단위로 환산
      this.in = (in / 100) * 3600 + (in % 100) * 60;
      this.out = (out / 100) * 3600 + (out % 100) * 60;
    }

    @Override
    public int compareTo(Reservation o) {
      if (this.in == o.in) { // 입실 시간이 같은 경우...
        return (this.out - this.in) - (o.out - o.in); // 이용시간이 짧은 순으로
      }

      return this.in - o.in;
    }
  }

  static List<Reservation> reservations;

  static void remove(int time) {
    for (int idx = 1; idx <= N; idx++) {
      if (visited[idx] && reservations.get(seat[idx]).out <= reservations.get(time).in) {
        visited[idx] = false;
        seat[idx] = 0;
      }
    }
  }

  static int find() {
    List<int[]> distance = new ArrayList<>();

    for (int seat = 1; seat <= N; seat++) {
      if (!visited[seat]) { // 자리가 비어있는 경우... 가장 가까이에 앉아 있는 사람의 좌석번호와 거리 찾기
        int left = seat - 1;
        int right = seat + 1;
        int leftIdx = Integer.MAX_VALUE, rightIdx = Integer.MAX_VALUE;
        for (int idx = left; idx >= 1; idx--) {
          if (visited[idx]) { // 자리에 앉아 있는 사람 찾음
            leftIdx = idx;
            break;
          }
        }

        for (int idx = right; idx <= N; idx++) {
          if (visited[idx]) { // 자리에 앉아 있는 사람 찾음
            rightIdx = idx;
            break;
          }
        }

        int leftDistance = leftIdx == Integer.MAX_VALUE ? leftIdx : seat - leftIdx;
        int rightDistance = rightIdx == Integer.MAX_VALUE ? rightIdx : rightIdx - seat;
        distance.add(new int[] { seat, Math.min(leftDistance, rightDistance) });
      }
    }

    distance.sort((a, b) -> {
      if (a[1] == b[1]) { // 거리가 먼 좌석이 여러개인 경우
        return a[0] - b[0]; // 번호 순으로
      }

      return b[1] - a[1];
    });

    return distance.get(0)[0]; // 앉을 좌석 번호 반환
  }

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine().trim());
    N = Integer.parseInt(st.nextToken());
    T = Integer.parseInt(st.nextToken());
    P = Integer.parseInt(st.nextToken());

    visited = new boolean[N + 1];
    seat = new int[N + 1];
    reservations = new ArrayList<>();
    reservations.add(new Reservation(0, 0));

    for (int idx = 0; idx < T; idx++) {
      st = new StringTokenizer(br.readLine().trim());
      int in = Integer.parseInt(st.nextToken());
      int out = Integer.parseInt(st.nextToken());
      reservations.add(new Reservation(in, out));
    }

    Collections.sort(reservations);
    answer = 12 * 3600;

    for (int idx = 1; idx <= T; idx++) {
      remove(idx);
      int seatIndex = find();

      if (seatIndex == P) {
        answer -= reservations.get(idx).out - reservations.get(idx).in;
      }

      visited[seatIndex] = true;
      seat[seatIndex] = idx;
    }

    System.out.println(answer / 60);
  }
}