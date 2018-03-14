package algo;

import java.io.IOException;
import java.util.Scanner;

class Task {
	private String N;
	private int K;
	private int answer;

	private final Scanner sc = new Scanner(System.in);
//	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	private StringTokenizer st = null;
//	private StringTokenizer getStringTokenizer() throws IOException {
//		return new StringTokenizer(br.readLine(), " ");
//	}
	
	private void input() throws IOException {
		N = sc.next();
		K = sc.nextInt();
		answer = -1;
	}
	
	public void solution(StringBuilder sb, int start, int cnt) {
		if(cnt == K) {
			int temp = Integer.parseInt(sb.toString());
			answer = answer > temp ? answer : temp;
			return;
		}
		
		int len = sb.length();
		for(int i=start; i<len; i++) {
			char temp = sb.charAt(i);
			for(int j=i+1; j<len; j++) {
				char next = sb.charAt(j);
				if(temp > next) continue;
				sb.setCharAt(i, next);
				sb.setCharAt(j, temp);
				solution(sb, i, cnt+1);
				sb.setCharAt(i, temp);
				sb.setCharAt(j, next);
			}
		}
	}
	
	public void run() throws IOException {
		int T = sc.nextInt();
		for(int i=1; i<=T; i++) {
			input();
			solution(new StringBuilder(N), 0, 0);
			System.out.println("#"+i+" " +answer);
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
