package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}

	static class Solution {
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		private boolean[] visited = new boolean[4];
		private int[] res = new int[4];
		private int max = 0;
		
		private int getDist(int x1, int y1, int x2, int y2) {
			return (int)(Math.pow(Math.abs(x1-x2), 2) + Math.pow(Math.abs(y1-y2), 2));
		}
		
		private void shuffle(int[] arr, int idx) {
			int len = arr.length;
			if(idx == len) {
				int num = getDist(res[0], res[1], res[2], res[3]);
				max = max > num ? max : num;
				return;
			}
			
			for(int i=0; i<len; i++) {
				if(visited[i]) continue;
				visited[i] = true;
				res[idx] = arr[i];
				shuffle(arr, idx+1);
				res[idx] = arr[i];
				visited[i] = false;
			}
		}
		
		public int solution(int A, int B, int C, int D) {
			int[] arr = new int[4];
			arr[0] = A;
			arr[1] = B;
			arr[2] = C;
			arr[3] = D;
			
			int len = arr.length;
			for(int i=0; i<len; i++) {
				visited[i] = true;
				res[0] = arr[i];
				shuffle(arr, 1);
				visited[i] = false;
			}
			
			return max;
		}

		public void run() throws IOException {
			bw.write(String.valueOf(solution(2,4,2,4)));
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
