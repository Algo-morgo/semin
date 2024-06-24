import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        int aIdx = 0;
        
        for(int idx = 0; idx < A.length; idx++) {
            if(A[aIdx] < B[idx]) {
                answer++;
                aIdx++;
            }
        }
        
        return answer;
    }
}