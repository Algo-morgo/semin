import java.util.Stack;

class Solution {
  static StringBuilder sb;
  static Stack<Integer> stack;
  static int tableSize;

  public String solution(int n, int k, String[] cmd) {
    stack = new Stack<>();
    tableSize = n;
    sb = new StringBuilder();

    for (String c : cmd) {
      if (c.charAt(0) == 'U') { // 위에 있는 행 선택
        k -= Integer.parseInt(c.substring(2));
      } else if (c.charAt(0) == 'D') { // 아래 있는 행 선택
        k += Integer.parseInt(c.substring(2));
      } else if (c.charAt(0) == 'C') {
        stack.push(k); // 삭제된 행을 스택에 추가
        tableSize--; // 표의 크기 1 감소
        if (k == tableSize) { // 삭제된 행이 가장 마지막 행인 경우
          k--; // 바로 윗 행 선택
        }
      } else if (c.charAt(0) == 'Z') {
        if (stack.pop() <= k) { // 가장 최근에 삭제된 행이 선택된 행 이하인 경우
          k++; // 아래 행 선택
        }
        tableSize++; // 표의 크기 1 증가
      }
    }

    for (int idx = 0; idx < tableSize; idx++) { // 남아있는 표의 크기만큼 O 표시
      sb.append("O");
    }

    while (!stack.isEmpty()) { // 삭제된 행을 하나씩 꺼내 해당 위치에 X 표시
      sb.insert(stack.pop(), "X");
    }

    return sb.toString();
  }
}