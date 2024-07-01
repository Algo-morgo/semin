class Solution {
  public boolean threeConsecutiveOdds(int[] arr) {
    for (int idx = 2; idx < arr.length; idx++) {
      if (arr[idx] % 2 == 1 && arr[idx - 1] % 2 == 1 && arr[idx - 2] % 2 == 1) {
        return true;
      }
    }

    return false;
  }
}