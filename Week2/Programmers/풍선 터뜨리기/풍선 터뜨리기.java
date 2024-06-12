class Solution {
  static int[] left;
  static int[] right;
  static int leftMin;
  static int rightMin;

  public int solution(int[] a) {
    if (a.length == 1) { // 풍선이 1개인 경우...
      return 1;
    }

    int answer = 2; // 양 끝 풍선
    left = new int[a.length];
    right = new int[a.length];
    leftMin = a[0]; // 왼쪽 최솟값
    rightMin = a[a.length - 1]; // 오른쪽 최솟값

    for (int idx = 1; idx < a.length - 1; idx++) {
      if (leftMin > a[idx]) {
        leftMin = a[idx];
        left[idx] = leftMin;
      }
    }

    for (int idx = a.length - 2; idx > 0; idx--) {
      if (rightMin > a[idx]) {
        rightMin = a[idx];
        right[idx] = rightMin;
      }
    }

    for (int idx = 1; idx < a.length - 1; idx++) {
      if (a[idx] > left[idx] && a[idx] > right[idx]) // 터뜨려야 하는 풍선
        continue;
      answer++;
    }

    return answer;
  }
}