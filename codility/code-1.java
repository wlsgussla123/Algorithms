package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}

	static class Solution {
		private Stack<Integer> s = new Stack();

		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		public boolean dup() {
			if (s.isEmpty()) {
				return false;
			}
			int num = s.pop();
			s.push(num);
			s.push(num);

			return true;
		}

		public boolean pop() {
			if (s.isEmpty()) {
				return false;
			}
			s.pop();
			return true;
		}

		public boolean add(String str) {
			if (s.size() < 2) {
				return false;
			}

			int num1 = s.pop();
			int num2 = s.pop();
			int res = num1 + num2;
			if (res > ((1 << 20)) - 1) {
				return false;
			}
			s.push(res);
			return true;
		}

		public boolean subtract(String str) {
			if (s.size() < 2) {
				return false;
			}

			int num1 = s.pop();
			int num2 = s.pop();
			int res = num1 - num2;

			if (res < 0) {
				return false;
			}
			s.push(res);

			return true;
		}

		public int solution(String S) {
			String[] input = S.split("\\s");

			for (String str : input) {
				switch (str) {
				case "DUP":
					if (!dup()) {
						return -1;
					}
					break;
				case "POP":
					if (!pop()) {
						return -1;
					}
					break;
				case "+":
					if (!add(str)) {
						return -1;
					}
					break;
				case "-":
					if (!subtract(str)) {
						return -1;
					}
					break;
				default:
					int num = Integer.parseInt(str);
					s.push(num);
					break;
				}
			}

			if (s.isEmpty()) {
				return -1;
			}
			return s.pop();
		}

		public void run() throws IOException {
			bw.write(String.valueOf(solution("3 DUP 5 - -")));
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
