package Week1.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 휴게소_세우기
 */
public class 휴게소_세우기 {
  static BufferedReader br;
  static StringTokenizer st;

  static int N; // 휴게소의 개수
  static int M; // 더 지으려고 하는 휴게소의 개수
  static int L; // 고속도로의 길이

  static List<Integer> pos;
  static int answer;

  static boolean decide(int mid) {
    int count = 0;
    for (int idx = 1; idx < pos.size(); idx++) {
      int distance = pos.get(idx) - pos.get(idx - 1);
      count += distance / mid;
      if (distance % mid == 0) { // 나누어 떨어지는 경우
        count--; // 중복 제거
      }
    }

    return count > M;
  }

  static int lowerBound() {
    int lo = 1;
    int hi = L;

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (decide(mid)) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }

    return lo;
  }

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine().trim());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());

    pos = new ArrayList<>();
    pos.add(0); // 시작
    pos.add(L); // 끝

    st = new StringTokenizer(br.readLine().trim());
    for (int idx = 0; idx < N; idx++) {
      pos.add(Integer.parseInt(st.nextToken()));
    }

    Collections.sort(pos); // 오름차순 정렬

    System.out.println(lowerBound());
  }
}