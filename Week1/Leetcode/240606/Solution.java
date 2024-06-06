import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean isNStraightHand(int[] hand, int groupSize) {
    if (hand.length % groupSize != 0) {
      return false;
    }

    Map<Integer, Integer> map = new HashMap<>(); // 각 카드의 개수를 저장하는 map
    for (int card : hand) {
      map.put(card, map.getOrDefault(card, 0) + 1);
    }

    for (int card : hand) {
      int startCard = card;

      while (map.getOrDefault(startCard - 1, 0) > 0) { // 연속되는 수열의 시작 카드 찾기
        startCard--;
      }

      while (startCard <= card) {
        while (map.getOrDefault(startCard, 0) > 0) {
          for (int nextCard = startCard; nextCard < startCard + groupSize; nextCard++) {
            if (map.getOrDefault(nextCard, 0) == 0) { // 카드가 없는 경우...
              return false;
            }
            map.put(nextCard, map.get(nextCard) - 1); // 카드 사용
          }
        }
        startCard++;
      }
    }

    return true;
  }
}