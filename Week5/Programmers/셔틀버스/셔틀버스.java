import java.util.*;

class Solution {
  static List<Integer> crews;
  static int departure = 540; // 09:00
  static int crewIdx;
  static int time;

  public String solution(int n, int t, int m, String[] timetable) {
    String answer = "";
    crews = new ArrayList<>();

    for (String time : timetable) { // 크루 도착 시간을 분으로 변환
      int minute = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
      crews.add(minute);
    }

    Collections.sort(crews); // 크루가 도착하는 시각을 오름차순 정렬

    for (int idx = 1; idx <= n; idx++, departure += t) {
      int count = 0; // 셔틀에 타는 크루 수
      for (int j = crewIdx; j < crews.size(); j++) {
        if (crews.get(j) <= departure) { // 크루 도착 시간이 셔틀 출발 시각이하
          count++; // 셔틀에 탐
          crewIdx++; // 다음 크루
          if (count == m) { // 한 셔틀에 탈 수 있는 최대 크루 수
            break;
          }
        }
      }

      if (idx == n) { // 마지막 셔틀
        if (count == m) { // 한 셔틀에 탈 수 있는 최대 크루 수
          time = crews.get(crewIdx - 1) - 1;
        } else {
          time = departure;
        }
      }
    }

    int hour = time / 60;
    int min = time % 60;

    if (hour < 10) {
      answer += "0";
    }
    answer += hour + ":";
    if (min < 10) {
      answer += "0";
    }
    answer += min;

    return answer;
  }
}