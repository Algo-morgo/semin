class Solution {
  public long maximumImportance(int n, int[][] roads) {
    long max = 0;
    long value = 1;
    int[] degree = new int[n];
    for (int[] road : roads) {
      degree[road[0]]++;
      degree[road[1]]++;
    }

    int[] frequency = new int[n];

    for (int d : degree) {
      frequency[d]++;
    }

    for (long i = 0; i < n; i++) {
      for (int j = 0; j < frequency[(int) i]; j++) {
        max += i * value++;
      }
    }

    return max;
  }
}