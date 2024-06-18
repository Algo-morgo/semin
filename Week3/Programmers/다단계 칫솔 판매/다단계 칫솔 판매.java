import java.util.*;

class Solution {
  static class Node {
    String name;
    Node parent;
    int profit;

    public Node(String name, Node parent, int profit) {
      this.name = name;
      this.parent = parent;
      this.profit = profit;
    }

    public void calculateProfit(int profit) {
      int parentProfit = profit / 10; // 추천인에게 가는 금액
      this.profit += profit - parentProfit;
      if (this.parent != null && parentProfit >= 1) {
        this.parent.calculateProfit(parentProfit);
      }
    }
  }

  static Map<String, Node> map;

  public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
    int[] answer = new int[enroll.length];
    map = new HashMap<>();

    for (String name : enroll) {
      map.put(name, new Node(name, null, 0));
    }

    for (int idx = 0; idx < enroll.length; idx++) {
      if (referral[idx].equals("-"))
        continue; // 추천인이 없는 경우...
      map.get(enroll[idx]).parent = map.get(referral[idx]);
    }

    for (int idx = 0; idx < seller.length; idx++) {
      map.get(seller[idx]).calculateProfit(amount[idx] * 100);
    }

    for (int idx = 0; idx < answer.length; idx++) {
      answer[idx] = map.get(enroll[idx]).profit;
    }

    return answer;
  }
}