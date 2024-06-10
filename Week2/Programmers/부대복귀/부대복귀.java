import java.util.*;

class Solution {
  static class Node {
    int next;
    int weight;

    public Node(int next, int weight) {
      this.next = next;
      this.weight = weight;
    }
  }

  static List<Node>[] nodes;
  static int[] distance;
  static boolean[] visited;
  static final int INF = 987654321;

  static void dijkstra(int n, int destination) {
    Arrays.fill(distance, INF);
    distance[destination] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return Integer.compare(o1[1], o2[1]);
      }
    });
    pq.offer(new int[] { destination, 0 });

    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      if (visited[cur[0]])
        continue;

      visited[cur[0]] = true;
      for (Node node : nodes[cur[0]]) {
        if (distance[node.next] > distance[cur[0]] + 1) {
          distance[node.next] = distance[cur[0]] + 1;
          pq.offer(new int[] { node.next, distance[node.next] });
        }
      }
    }
  }

  public int[] solution(int n, int[][] roads, int[] sources, int destination) {
    int[] answer = new int[sources.length];

    nodes = new ArrayList[n + 1];
    distance = new int[n + 1];
    visited = new boolean[n + 1];

    for (int idx = 0; idx <= n; idx++) {
      nodes[idx] = new ArrayList<>();
    }

    for (int idx = 0; idx < roads.length; idx++) {
      nodes[roads[idx][0]].add(new Node(roads[idx][1], 1));
      nodes[roads[idx][1]].add(new Node(roads[idx][0], 1));
    }

    dijkstra(n, destination);

    for (int idx = 0; idx < sources.length; idx++) {
      answer[idx] = distance[sources[idx]] == INF ? -1 : distance[sources[idx]];
    }

    return answer;
  }
}