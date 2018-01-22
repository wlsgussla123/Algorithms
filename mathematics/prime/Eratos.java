package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Task {
	private int N=0;
	private ArrayList<Boolean> primeList;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		primeList = new ArrayList<Boolean>(N+1);
		// 0과 1은 소수가 아님
		primeList.add(false);
		primeList.add(false);
		// 2~N까지 소수로 설정
		for(int i=2; i<=N; i++) primeList.add(i, true);
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		// n<=1 일 때 종료
		if(N<=1) return;
		init();
	}
	
	private void Eratos() {
		// 소수가 N일때, 루트 n까지 나누어서 떨어지면 소수가 아니다. (i<=sqrt(N) 의 반대)
		for(int i=2; (i*i)<=N; i++) {
			if(primeList.get(i)) {
				for(int j=(i*i); j<=N; j+=i) {
					primeList.set(j, false);
				}
			}
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for(int i=2; i<=N; i++) {
			if(primeList.get(i)) {
				sb.append(i);
				sb.append(",");
			}
		}
		sb.setCharAt(sb.length()-1, '}');
		System.out.println(sb.toString());
	}
	
	public void run() throws IOException {
		input();
		Eratos();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
