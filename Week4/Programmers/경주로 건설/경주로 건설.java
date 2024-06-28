import java.util.*;

class Solution {
    static class Pos {
        int row;
        int col;
        int dir;
        int cost;
        
        public Pos(int row, int col, int dir, int cost) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.cost = cost;
        }
    }
    
    static final int[] DELTA_ROW = {-1, 0, 1, 0};
    static final int[] DELTA_COL = {0, 1, 0, -1};
    
    static boolean[][][] visited;
    static int N;
    
    static boolean isInRange(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }
    
    static int bfs(int[][] board) {
        Queue<Pos> queue = new ArrayDeque<>();
        
        queue.offer(new Pos(0, 0, -1, 0));
        
        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Pos cur = queue.poll();
            
            if(cur.row == N - 1 && cur.col == N - 1) {
                min = Math.min(min, cur.cost);
            }
            
            for(int dir = 0; dir < 4; dir++) {
                int nextRow = cur.row + DELTA_ROW[dir];
                int nextCol = cur.col + DELTA_COL[dir];
                
                if(!isInRange(nextRow, nextCol)) continue;
                if(board[nextRow][nextCol] == 1) continue;
                
                int cost = cur.cost;
                if(cur.dir == -1 || cur.dir == dir) {
                    cost += 100;
                } else {
                    cost += 600;
                }
                
                if(!visited[nextRow][nextCol][dir] || board[nextRow][nextCol] >= cost) {
                    board[nextRow][nextCol] = cost;
                    visited[nextRow][nextCol][dir] = true;
                    queue.offer(new Pos(nextRow, nextCol, dir, cost));
                }
            }
        }
        
        return min;
    }
    
    public int solution(int[][] board) {
        N = board.length;
        visited = new boolean[N][N][4];
        
        return bfs(board);
    }
}