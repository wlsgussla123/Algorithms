package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M;
		private int[] ans;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ans = new int[N];
		}

		private void dfs(int depth, ArrayList<Integer> list) {
			if(depth == M) {
				for(int i=0; i<M; i++) {
					System.out.print(ans[i] + " ");
				}
				System.out.println();
				return;
			}
			
			for(Iterator<Integer> iter = list.iterator(); iter.hasNext();) {
				ArrayList<Integer> temp = (ArrayList<Integer>) list.clone();
				ans[depth] = iter.next();
				temp.remove((Integer)ans[depth]);
				dfs(depth+1, temp);
			}
		}
		
		public void run() throws IOException {
			input();
			ArrayList<Integer> list = new ArrayList();
			for(int i=1; i<=N; i++) {
				list.add(i);
			}
			dfs(0, list);
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
