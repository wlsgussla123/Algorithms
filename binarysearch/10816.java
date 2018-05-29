package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}

	static class Solution {
		private int N, M;
		private int[] cards;
		private int[] search;
		private int[] visited = new int[20000001];
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			cards = new int[N];
			st = getStringTokenizer();
			for(int i=0; i<N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(cards);
			
			st = getStringTokenizer();
			M = Integer.parseInt(st.nextToken());
			search = new int[M];
			Arrays.fill(visited, -1);
			st = getStringTokenizer();
			for(int i=0; i<M; i++) {
				search[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		private int binarySearch(int left, int right, int num) {
			int result = 0;
			int mid = 0;
			
			while(left <= right) {
				mid = (left + right) / 2;
				if(cards[mid] > num) {
					right = mid - 1;
				} else if(cards[mid] < num) {
					left = mid + 1;
				} else {
					++result;
					result += binarySearch(left, mid - 1, num);
					result += binarySearch(mid + 1, right, num);
					break;
				}
			}
			
			return result;
		}
		
		private void solution() throws IOException {
			for(int i=0; i<M; i++) {
				int key = search[i];
				if(visited[key + 10000000] != -1) {
					bw.write(String.valueOf(visited[key + 10000000]) + " " );
				} else {
					visited[key + 10000000] = binarySearch(0, N-1, key);
					bw.write(String.valueOf(visited[key + 10000000] + " "));
				}
			}
		}

		public void run() throws IOException {
			input();
			solution();
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
