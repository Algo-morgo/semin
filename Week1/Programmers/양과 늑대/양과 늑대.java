class Solution {
  static int answer;
  static boolean[] visited;

  public static void DFS(int[] info, int[][] edges, boolean[] visited, int vertex, int sheep, int wolf) {
    visited[vertex] = true; // 방문 처리

    if (info[vertex] == 0) { // 양인 경우...
      sheep++; // 양의 수 1 증가
      answer = Math.max(answer, sheep); // 모을 수 있는 양의 최댓값 갱신
    } else {
      wolf++; // 늑대의 수 1 증가
    }

    if (sheep <= wolf) { // 양의 수보다 늑대의 수가 같거나 많은 경우...
      return; // 중단
    }

    for (int[] edge : edges) {
      if (visited[edge[0]] && !visited[edge[1]]) { // 부모 노드는 방문한 적이 있고 자식 노드는 방문한 적이 없는 경우...
        boolean[] temp = visited.clone(); // 방문 배열 복사
        DFS(info, edges, temp, edge[1], sheep, wolf); // 다음 노드 탐색
      }
    }
  }

  public int solution(int[] info, int[][] edges) {
    visited = new boolean[info.length];

    DFS(info, edges, visited, 0, 0, 0);

    return answer;
  }
}