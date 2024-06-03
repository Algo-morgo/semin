package Week1.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 물대기
 * 
 * 1. i번째 논에 우물을 팔 때 드는 비용을 가상의 0번째 논에서 i번째 논을 연결하는데 드는 비용으로 계산
 * 2. MST
 * 
 * @author semin.kim
 * 
 */
public class 물대기 {
  static BufferedReader br;
  static StringTokenizer st;
  static int N; // 논의 수
  static int[] parent;
  static List<Edge> edges; // 논과 논을 연결하는 간선
  static int minCost; // 최소비용

  static class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
      return Integer.compare(this.weight, o.weight);
    }
  }

  static void make() {
    parent = new int[N + 1];
    for (int idx = 0; idx <= N; idx++) { // 모든 원소가 자기 자신을 가리키도록 초기화
      parent[idx] = idx;
    }
  }

  static int find(int a) {
    if (parent[a] == a) { // 부모 노드가 자기 자신인 노드인 경우...
      return a; // 그 번호를 반환
    }

    return parent[a] = find(parent[a]); // 경로 압축
  }

  static boolean union(int a, int b) {
    int aRoot = find(a);
    int bRoot = find(b);

    if (aRoot == bRoot) { // 두 원소의 집합 번호가 같으면 같은 집합
      return false;
    }
    // 집합 번호가 더 작은 집합을 큰 집합에 연결
    else if (aRoot < bRoot) {
      parent[aRoot] = bRoot;
      return true;
    } else {
      parent[bRoot] = aRoot;
      return true;
    }
  }

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine().trim());

    edges = new ArrayList<>();

    for (int idx = 1; idx <= N; idx++) {
      int weight = Integer.parseInt(br.readLine().trim());
      edges.add(new Edge(0, idx, weight)); // 가상의 논을 만들어 우물을 팔 때 드는 비용으로 계산
    }

    for (int from = 1; from <= N; from++) {
      st = new StringTokenizer(br.readLine().trim());
      for (int to = 1; to <= N; to++) {
        int weight = Integer.parseInt(st.nextToken());
        if (from == to) // 자기 자신 제외
          continue;
        edges.add(new Edge(from, to, weight));
      }
    }

    Collections.sort(edges); // 비용 기준 오름차순 정렬

    make();

    for (Edge edge : edges) {
      if (union(edge.from, edge.to)) { // 서로 다른 집합인 경우...
        minCost += edge.weight; // 최소비용 갱신
      }
    }

    System.out.println(minCost);
  }
}