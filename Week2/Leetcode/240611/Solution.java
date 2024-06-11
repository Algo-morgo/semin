import java.util.*;

class Solution {
  public int[] relativeSortArray(int[] arr1, int[] arr2) {
    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> remaining = new ArrayList<>();
    List<Integer> result = new ArrayList<>();

    for (int num : arr2) {
      map.put(num, 0);
    }

    for (int num : arr1) {
      if (map.containsKey(num)) { // arr2의 원소가 존재하는 경우...
        map.put(num, map.get(num) + 1); // 빈도 수 갱신
      } else {
        remaining.add(num); // arr2에 등장하지 않는 원소 목록에 원소 추가
      }
    }

    Collections.sort(remaining); // arr2에 등장하지 않는 원소 오름차순 정렬

    for (int num : arr2) {
      for (int idx = 0; idx < map.get(num); idx++) { // 등장횟수만큼 result 리스트에 담기
        result.add(num);
      }
    }

    result.addAll(remaining); // 결과 리스트에 arr2에 등장하지 않는 원소 목록 담기

    int[] answer = new int[result.size()];
    for (int idx = 0; idx < result.size(); idx++) {
      answer[idx] = result.get(idx);
    }

    return answer;
  }
}