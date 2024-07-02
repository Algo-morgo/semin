class Solution {
  public int[] intersect(int[] nums1, int[] nums2) {
    HashMap<Integer, Integer> map = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    for (int num : nums1) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    for (int num : nums2) {
      if (map.containsKey(num)) {
        list.add(num);
        map.put(num, map.get(num) - 1);
        if (map.get(num) == 0) {
          map.remove(num);
        }
      }

    }

    int[] answer = new int[list.size()];
    for (int idx = 0; idx < list.size(); idx++) {
      answer[idx] = list.get(idx);
    }

    return answer;
  }
}