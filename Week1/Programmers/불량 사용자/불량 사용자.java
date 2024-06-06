import java.util.*;

class Solution {
  static Set<HashSet<String>> answer;

  static boolean check(String userId, String bannedId) {
    if (userId.length() != bannedId.length()) { // 두 문자열의 길이가 다르면 불량 사용자 아님
      return false;
    }

    for (int idx = 0; idx < userId.length(); idx++) {
      if (bannedId.charAt(idx) != '*' && userId.charAt(idx) != bannedId.charAt(idx)) {
        return false;
      }
    }

    return true;
  }

  static void dfs(HashSet<String> set, int curIdx, String[] user_id, String[] banned_id) {
    if (curIdx == banned_id.length) {
      answer.add(set);
      return;
    }

    for (int idx = 0; idx < user_id.length; idx++) {
      if (set.contains(user_id[idx]))
        continue; // 이미 목록에 포함된 경우... 무시

      if (check(user_id[idx], banned_id[curIdx])) { // 불량 사용자인 경우...
        set.add(user_id[idx]);
        dfs(new HashSet<>(set), curIdx + 1, user_id, banned_id);
        set.remove(user_id[idx]);
      }
    }
  }

  public int solution(String[] user_id, String[] banned_id) {
    answer = new HashSet<>();

    dfs(new HashSet<>(), 0, user_id, banned_id);

    return answer.size();
  }
}