package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Task {
	private boolean[] prime;
	private int len;
	private char[] numbers;
	private boolean[] visited;
	private int answer = 0;
	private int MAX = 10000000;
	
	private HashSet<Integer> set;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void Eratos() {
		prime = new boolean[MAX+1];
		prime[0] = false;
		prime[1] = false;
		
		for(int i=2; i<=MAX; i++) {
			prime[i] = true;
		}
		for(int i=2; i<=MAX; i++) {
			for(int j=i*2; j<=MAX; j+=i) {
				if(prime[i]) {
					prime[j] = false;
				}
			}
		}
	}
	
	private void init() {
		visited = new boolean[len];
		set = new HashSet<>();
		answer = 0;
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		String str = st.nextToken();
		numbers = str.toCharArray(); // 입력받은 N을 char형 배열로 변환
		len = numbers.length;
		init();
	}
	
	private boolean isPrime(int num) {
		if(num<=1) return false;
		if(prime[num]) {
			return true;
		} else {
			return false;
		}
	}

	private void backtracking(String number) {
		int num = Integer.parseInt(number.toString());
		if(!set.contains(num) && isPrime(num)) {
			set.add(num);
			answer++;
		}
		
		for(int i=0; i<len; i++) {
			if(!visited[i]) {
				StringBuffer sb = new StringBuffer();
				sb.append(number);
				sb.append(numbers[i]);
				visited[i] = true;
				backtracking(sb.toString());
				visited[i] = false;				
			}
		}
	}
	
	public void run() throws IOException {
		Eratos();
		
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		while(T-- > 0) {
			input();
			for(int i=0; i<len; i++) {
				StringBuffer sb = new StringBuffer();
				sb.append(numbers[i]);
				visited[i] = true;
				backtracking(sb.toString());
				visited[i] = false;
			}
			System.out.println(answer);			
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
