import java.util.*;

class Solution {
    static Stack<Character> stack;
    
    public int solution(String s) {
        int answer = 0;
        
        for(int i = 0; i < s.length(); i++) {
            String rotated = s.substring(i) + s.substring(0, i);
            stack = new Stack<>();
            
            for(char c : rotated.toCharArray()) {
                if(stack.isEmpty()) {
                    stack.push(c);
                    continue;
                }
                
                if(c == ')' && stack.peek() == '(') {
                    stack.pop();
                    continue;
                } else if(c == ']' && stack.peek() == '[') {
                    stack.pop();
                    continue;
                } else if(c == '}' && stack.peek() == '{') {
                    stack.pop();
                    continue;
                }
                
                stack.push(c);
            }
            
            if(stack.size() == 0) {
                answer++;
            }
            
        }
        
        return answer;
    }
}