package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M;
		private int[] arr;
		private boolean[] visited;
		private HashSet<ArrayList<Integer>> set = new HashSet();
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			arr = new int[N];
			visited = new boolean[N];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			st = getStringTokenizer();
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
		}
		
		private void solve(int depth, int prev, ArrayList<Integer> list) throws IOException {
			if(depth == M) {
				for(int n : list) {
					bw.write(String.valueOf(n) + " ");
				}
				bw.write("\n");
				return;
			}
			
			ArrayList<Integer> temp = new ArrayList();
			if(depth > 0) {
				temp = (ArrayList<Integer>) list.clone();
			}
			
			for(int i=0; i<N; i++) {
				if(prev > arr[i] || visited[i]) continue;
				visited[i] = true;
				temp.add(arr[i]);
				if(set.contains(temp)) {
					temp.remove(temp.size()-1);
					visited[i] = false;
					continue;
				}
				set.add(temp);
				solve(depth+1, arr[i], temp);
				temp.remove(temp.size()-1);
				visited[i] = false;
			}
		}
		
		public void run() throws IOException {
			input();
			solve(0, 0, null);
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
