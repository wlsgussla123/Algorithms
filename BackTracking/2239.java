package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Position {
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	new Task().run();
    }
    
    static class Task {
    	private final int N=9;
    	private int[][] map = new int[N][N];
    	private ArrayList<Position> list = new ArrayList<>();
    	
    	private StringTokenizer st = null;
    	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	private StringTokenizer getStringTokenizer() throws IOException {
    		return new StringTokenizer(br.readLine(), " ");
    	}

    	private void input() throws IOException {
    		for(int i=0; i<N; i++) {
    			st = getStringTokenizer();
    			char[] input = st.nextToken().toCharArray();
    			for(int j=0; j<N; j++) {
    				map[i][j] = input[j] - '0';
    				if(map[i][j] == 0) {
    					list.add(new Position(i, j));
    				}
    			}
    		}
    	}
    	
    	private boolean checkRow(int row, int k) {
    		for(int col=0; col<N; col++) {
    			if(map[row][col] == k) return false;
    		}
    		
    		return true;
    	}
    	
    	private boolean checkCol(int col, int k) {
    		for(int row=0; row<N; row++) {
    			if(map[row][col] == k) return false;
    		}
    		
    		return true;
    	}
    	
    	private boolean checkRec(int row, int col, int k) {
    		int r = (row/3)*3;
    		int c = (col/3)*3;
    		
    		for(int i=r; i<r+3; i++) {
    			for(int j=c; j<c+3; j++) {
    				if(map[i][j] == k) return false;
    			}
    		}
    		
    		return true;
    	}
    	
    	private boolean isPromising(int row, int col, int k) {
    		if(checkRow(row, k) && checkCol(col, k) && checkRec(row, col, k))
    			return true;
    		else
    			return false;
    	}
    	
    	private void backtracking(int idx) {
    		if(idx == list.size()) {
    			for(int i=0; i<N; i++) {
    				for(int j=0; j<N; j++) {
    					System.out.print(map[i][j]);
    				}
    				System.out.println();
    			}
    			System.exit(0);
    		}
    		
    		Position p = list.get(idx);
    		for(int k=1; k<=9; k++) {
    			if(isPromising(p.x, p.y, k)) {
    				map[p.x][p.y] = k;
    				backtracking(idx+1);
    				map[p.x][p.y] = 0;
    			}
    		}
    	}
    	
		public void run() throws IOException {
			input();
			backtracking(0);
			close();
		}
    	
    	private void close() throws IOException {
    		bw.close();
    		br.close();
    	}    	
    }
}
