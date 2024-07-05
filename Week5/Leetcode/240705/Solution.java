/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
  public int[] nodesBetweenCriticalPoints(ListNode head) {
    int[] answer = { -1, -1 };
    ListNode prev = head;
    ListNode cur = prev.next;
    int prevPos = -1, curPos = -1, firstPos = -1, pos = 0;
    while (cur.next != null) {
      if ((cur.val < prev.val && cur.val < cur.next.val) || (cur.val > prev.val && cur.val > cur.next.val)) {
        prevPos = curPos;
        curPos = pos;
        if (firstPos == -1) {
          firstPos = pos;
        }
        if (prevPos != -1) {
          if (answer[0] == -1) {
            answer[0] = curPos - prevPos;
          } else {
            answer[0] = Math.min(answer[0], curPos - prevPos);
          }
          answer[1] = pos - firstPos;
        }
      }

      pos++;
      prev = cur;
      cur = cur.next;
    }

    return answer;
  }
}