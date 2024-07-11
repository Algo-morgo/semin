class Solution {
  public String reverseParentheses(String s) {
    Stack<String> stack = new Stack<>();

    for (int idx = 0; idx < s.length(); idx++) {
      if (s.charAt(idx) == '(') {
        stack.push("(");
      } else if (s.charAt(idx) == ')') {
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty() && !stack.peek().equals("(")) {
          sb.append(new StringBuilder(stack.pop()).reverse());
        }
        stack.pop();
        stack.push(sb.toString());
      } else {
        stack.push(String.valueOf(s.charAt(idx)));
      }
    }

    StringBuilder answer = new StringBuilder();
    while (!stack.isEmpty()) {
      answer.insert(0, stack.pop());
    }

    return answer.toString();
  }
}