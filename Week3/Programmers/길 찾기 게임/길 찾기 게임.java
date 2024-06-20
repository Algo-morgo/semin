import java.util.*;

class Solution {
    static class Node implements Comparable<Node>{
        int num;
        int x;
        int y;
        Node left;
        Node right;
        
        public Node(int num, int x, int y, Node left, Node right) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public int compareTo(Node o) {
            if(this.y == o.y) {
                return Integer.compare(this.x, o.x);
            }
            return Integer.compare(o.y, this.y);
        }
    }
    
    static List<Node> nodes;
    static int[][] answer;
    static int answerIdx;
    
    static void insertNode(Node parent, Node child) {
        if(parent.x > child.x) {
            if(parent.left == null) {
                parent.left = child;
            } else {
                insertNode(parent.left, child);
            }
        } else {
            if(parent.right == null) {
                parent.right = child;
            } else {
                insertNode(parent.right, child);
            }
        }
    }
    
    static void preorder(Node root) {
        if(root != null) {
            answer[0][answerIdx++] = root.num;
            preorder(root.left);
            preorder(root.right);
        }
    }
    
    static void postorder(Node root) {
        if(root != null) {
            postorder(root.left);
            postorder(root.right);
            answer[1][answerIdx++] = root.num;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        nodes = new ArrayList<>();
        
        for(int idx = 0; idx < nodeinfo.length; idx++) {
            nodes.add(new Node(idx + 1, nodeinfo[idx][0], nodeinfo[idx][1], null, null));
        }
        
        Collections.sort(nodes);
        
        Node root = nodes.get(0);
        for(int idx = 1; idx < nodes.size(); idx++) {
            insertNode(root, nodes.get(idx));
        }
        
        preorder(root);
        answerIdx = 0;
        postorder(root);
        
        return answer;
    }
}