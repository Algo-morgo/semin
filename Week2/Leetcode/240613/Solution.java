class Solution {
  public int minMovesToSeat(int[] seats, int[] students) {
    int answer = 0;
    Arrays.sort(seats);
    Arrays.sort(students);

    for (int idx = 0; idx < students.length; idx++) {
      answer += Math.abs(students[idx] - seats[idx]);
    }

    return answer;
  }
}