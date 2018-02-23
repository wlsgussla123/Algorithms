package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private boolean flag = false;
	// input
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
	}
	
	// true : 노중복, false : 중복
	private boolean isPromising(StringBuilder sb) {
		boolean isOverlap = true;
		boolean isPass = false;
		
		if(sb.length() == 1) return true;
		for(int i=0; i<sb.length(); i++) {
			for(int j=i; j<sb.length(); j++) {
				String comp1 = sb.substring(i,j+1);
				String comp2 = sb.substring(j+1, sb.length());
				int k=0;
				for(k=0; k<comp1.length(); k++) {
					if(k >= comp2.length()) {
						isPass = true;
						break;
					}
					if(comp1.charAt(k) != comp2.charAt(k)) {
						isOverlap = false;
						break;
					}
				}
				if(isPass) {
					isPass = false;
					continue;
				}
				
				if(isOverlap) {
					return false;
				}
				isOverlap = true;
			}
		}
		
		return true;
	}
	
	private void backtracking(StringBuilder sb, int cnt) {
		if(cnt == N) {
			System.out.println(sb.toString());
			flag = true;
			return;
		} else {
			for(int i=1; i<=3; i++) {
				if(flag) return;
				StringBuilder temp = new StringBuilder(sb.toString());
				temp.append(String.valueOf(i));
				if(!isPromising(temp)) continue;
				backtracking(new StringBuilder(temp.toString()), cnt+1);
			}
		}
	}

	private void solution() {
		for(int i=1; i<=3; i++) {
			if(flag) return;
			backtracking(new StringBuilder(String.valueOf(i)), 1);
		}
	}

	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
