package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private boolean solution(String str1, String str2) throws IOException {
		if(str1.length() - str2.length() >= 2) return false;
		if(str1.length() - str2.length() == 1) {
			boolean flag = false;
			int idx1 = 0;
			int idx2 = 0;
			
			while(idx1 < str1.length() && idx2 < str2.length()) {
				if(str1.charAt(idx1) == str2.charAt(idx2)) {
					idx1++;
					idx2++;
				} else {
					if(flag) return false;
					idx1++;
					flag = true;
				}
			}
		} else {
			boolean flag = false;
			int idx1 = 0;
			int idx2 = 0;

			while(idx1 < str1.length() && idx2 < str2.length()) {
				if(str1.charAt(idx1) == str2.charAt(idx2)) {
					idx1++;
					idx2++;
				} else {
					if(flag) return false;
					idx1++;
					idx2++;
					flag = true;
				}
			}
		}
		return true;
	}

	public void run() throws IOException {
		st = getStringTokenizer();
		String str1 = st.nextToken();
		String str2 = st.nextToken();
		if(str1.length() >= str2.length()) {
			System.out.println(solution(str1, str2));
		} else {
			System.out.println(solution(str2, str1));
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
