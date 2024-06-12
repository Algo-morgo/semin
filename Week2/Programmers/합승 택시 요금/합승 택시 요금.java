/**
 * 합승 택시 요금
 * 
 * 1. 각자 이동하는 경우의 예상 택시요금을 구한다.
 * 2. 플로이드 워셜 알고리즘을 이용해 각 지점에서 다른 지점까지의 최저 예상 택시 금을 구한다.
 * 3. 각 지점에 대하여 합승을 하는 경우와 1에서 구한 요금을 비교하여 최저 예상 택시요금을 갱신한다.
 */

class Solution {
    static int[][] costs;

    static void floyd(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (costs[j][i] != Integer.MAX_VALUE && costs[i][k] != Integer.MAX_VALUE) {
                        if (costs[j][k] == Integer.MAX_VALUE) {
                            costs[j][k] = costs[j][i] + costs[i][k];
                        } else {
                            costs[j][k] = Math.min(costs[j][k], costs[j][i] + costs[i][k]);
                        }
                    }
                }
            }
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        costs = new int[n][n];

        // 최저비용 초기화
        for (int from = 0; from < n; from++) {
            for (int to = 0; to < n; to++) {
                costs[from][to] = Integer.MAX_VALUE;
            }
        }

        for (int[] fare : fares) { // 이동 방향에 따라 택시요금은 달라지지 않음
            costs[fare[0] - 1][fare[1] - 1] = fare[2];
            costs[fare[1] - 1][fare[0] - 1] = fare[2];
        }

        floyd(n);

        for (int from = 0; from < n; from++) { // 자기 자신의 비용은 0
            costs[from][from] = 0;
        }

        answer = costs[s - 1][a - 1] + costs[s - 1][b - 1]; // 각각 택시를 타는 경우의 택시요금

        for (int i = 0; i < n; i++) {
            if (costs[s - 1][i] != Integer.MAX_VALUE && costs[i][a - 1] != Integer.MAX_VALUE
                    && costs[i][b - 1] != Integer.MAX_VALUE) { // 경로가 존재하는 경우
                answer = Math.min(answer, costs[s - 1][i] + costs[i][a - 1] + costs[i][b - 1]); // 합승 택시 요금 비교, 최저 요금 갱신
            }
        }

        return answer;
    }
}