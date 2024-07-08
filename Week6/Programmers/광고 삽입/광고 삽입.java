class Solution {
  static int playTime, advTime;
  static long[] counts;
  static long max;
  static int maxTime;

  static int timeToSeconds(String time) {
    String[] times = time.split(":");
    return Integer.parseInt(times[0]) * 3600 + Integer.parseInt(times[1]) * 60 + Integer.parseInt(times[2]);
  }

  public String solution(String play_time, String adv_time, String[] logs) {
    String answer = "";

    playTime = timeToSeconds(play_time);
    advTime = timeToSeconds(adv_time);

    counts = new long[playTime + 1];
    for (String log : logs) {
      String[] l = log.split("-");

      int startTime = timeToSeconds(l[0]);
      int endTime = timeToSeconds(l[1]);
      counts[startTime]++;
      counts[endTime]--;
    }

    for (int i = 1; i <= playTime; i++) {
      counts[i] += counts[i - 1];
    }
    for (int i = 1; i <= playTime; i++) {
      counts[i] += counts[i - 1];
    }

    max = counts[advTime - 1];

    for (int i = 0; i + advTime <= playTime; i++) {
      long count = counts[i + advTime] - counts[i];
      if (count <= max) {
        continue;
      }
      max = count;
      maxTime = i + 1;
    }

    int h = maxTime / 3600;
    int m = (maxTime % 3600) / 60;
    int s = maxTime % 60;

    answer = String.format("%02d:%02d:%02d", h, m, s);

    return answer;
  }
}