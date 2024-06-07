class Solution {
  String solve(String word, Set<String> set) {
      for (int idx = 1; idx < word.length(); idx++) {
          String root = word.substring(0, idx);
          if (set.contains(root)) {
              return root;
          }
      }

      return word;
  }

  public String replaceWords(List<String> dictionary, String sentence) {
      String[] arr = sentence.split(" ");
      Set<String> set = new HashSet<>(dictionary);

      // 각 단어를 치환
      for (int idx = 0; idx < arr.length; idx++) {
          arr[idx] = solve(arr[idx], set);
      }

      return String.join(" ", arr);
  }
}