package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}

	static class Solution {
		private int N,M;
		private int[] arr;
		private HashSet<ArrayList<Integer>> set = new HashSet();
		
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
			arr = new int[N];
			st = getStringTokenizer();
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
		}

		private void permutation(ArrayList<Integer> list) {
			if(list.size() == M) {
				if(!set.contains(list)) {
					set.add(list);
					for(Integer num : list) {
						System.out.print(num + " ");
					}
					System.out.println();
				}
				return;
			}
			
			for(int i=0; i<N; i++) {
				list.add(arr[i]);
				permutation(list);
				list.remove(list.size()-1);
			}
		}
		
		public void run() throws IOException {
			input();
			permutation(new ArrayList<>());
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
