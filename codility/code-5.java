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
		private int ans = 0;

		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		public int getLeftMax(int[] A, int idx, int L) {
			if(idx-L < 0) return 0;
			
			int j,k=0;
			int max = 0;
			int sum = 0;
			for(int i=0; i<idx; i++) {
				sum = 0;
				k = 0;
				j=i;
				while(k<L && j<idx) {
					sum += A[j];
					j++;
					k++;
				}
				
				max = max > sum ? max : sum;
			}
			
			return max;
		}
		
		public int getRightMax(int[] A, int idx, int L) {
			int len = A.length;
			if(idx+L >= len) return 0;

			int j,k=0;
			int max = 0;
			int sum = 0;
			for(int i=idx+1; i<len; i++) {
				sum = 0;
				k = 0;
				j=i;
				while(k<L && j<len) {
					sum += A[j];
					j++;
					k++;
				}
				
				max = max > sum ? max : sum;
			}
			
			return max;
		}
		
		public int solution(int[] A, int K, int L) {
			int len = A.length;
			if(K+L > len) {
				return -1;
			}
			
			int idx = 0;
			int alice_sum = 0;
			int bob_sum = 0;
			while(idx < len) {
				int a = idx+K;
				alice_sum = 0;
				bob_sum = 0;
				
				if(a >= len) {
					break;
				}
				
				for(int i=idx; i<a; i++) {
					alice_sum += A[i];
				}
				int left = getLeftMax(A, idx, L);
				int right = getRightMax(A, idx, L);
				bob_sum = left > right ? left : right;
				ans = ans > alice_sum + bob_sum ? ans : alice_sum + bob_sum;
				
				idx++;
			}
			
			return ans;
		}

		public void run() throws IOException {
			int[] a = {10,19,15};
			int k = 2;
			int l = 2;
			bw.write(String.valueOf(solution(a, k, l)));
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
