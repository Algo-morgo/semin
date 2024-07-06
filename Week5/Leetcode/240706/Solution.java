class Solution {
  int[] dir = { 1, -1 };

  public int passThePillow(int n, int time) {
    int index = 1;
    int d = 0;

    while (time-- > 0) {
      if (index == n) {
        d = 1;
      } else if (index == 1) {
        d = 0;
      }
      index += dir[d];
    }

    return index;
  }
}