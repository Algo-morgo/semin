class Solution {
  public int solution(int n, int[] stations, int w) {
      int answer = 0;
      
      int cur = 1; // 현재 위치
      int stationIdx = 0; // 기지국 index
      
      while(cur <= n) {
          // 현재 위치가 모든 기지국의 범위를 넘는 경우 또는 기지국의 범위보다 작은 경우
          if(stationIdx >= stations.length || cur < stations[stationIdx] - w) {
              answer++; // 기지국 설치
              cur += 2 * w + 1; // 현재 위치를 설치한 기지국 바로 다음으로 이동
          } else {
              cur = stations[stationIdx] + w + 1; // 현재 위치를 기지국 바로 다음으로 이동
              stationIdx++; // 다음 기지국 탐색
          }
      }

      return answer;
  }
}