class Solution {
  public double averageWaitingTime(int[][] customers) {
    double sum = 0.0;
    int time = 0;
    for (int[] customer : customers) {
      if (time < customer[0]) {
        time = customer[0];
      }
      sum += time - (customer[0] - customer[1]);
      time += customer[1];
    }

    return sum / customers.length;
  }
}