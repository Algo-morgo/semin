class Solution {
  public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
    int satisfied = 0;

    for (int idx = 0; idx < customers.length; idx++) {
      if (grumpy[idx] == 0) {
        satisfied += customers[idx];
      }
    }

    int start = 0, bonus = 0;

    for (int idx = 0; idx < minutes; idx++) {
      if (grumpy[idx] == 1) {
        bonus += customers[idx];
      }
    }

    int curBonus = bonus;
    for (int end = minutes; end < customers.length; end++) {
      if (grumpy[start] == 1) {
        curBonus -= customers[start];
      }
      start++;
      if (grumpy[end] == 1) {
        curBonus += customers[end];
      }
      bonus = Math.max(bonus, curBonus);
    }

    return satisfied + bonus;
  }
}