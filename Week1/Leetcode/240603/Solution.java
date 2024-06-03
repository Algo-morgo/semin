class Solution {
  public int appendCharacters(String s, String t) {
    int sIdx = 0;
    int tIdx = 0;

    while (sIdx < s.length() && tIdx < t.length()) {
      if (s.charAt(sIdx) == t.charAt(tIdx)) { // 동일한 문자인 경우...
        sIdx++;
        tIdx++;
      } else { // 동일하지 않은 문자인 경우...
        sIdx++;
      }
    }

    return t.length() - tIdx;
  }
}