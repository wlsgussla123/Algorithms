package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[] cities;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		cities = new int[N];
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			cities[i] = Integer.parseInt(st.nextToken());
		}
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
	}
	
	private void solution() {
		long left = 1;
		long right = M;
		long mid = 0;
		long sum = 0;
		long answer = 0;
		long temp = 0;
		while(left<=right) {
			mid = (left+right)/2;
			sum = 0;
			for(int i=0; i<N; i++) {
				if(cities[i]<mid) {
					sum += cities[i];
					if(cities[i] > temp) temp = cities[i];
				} else {
					sum += mid;
					temp = mid;
				}
				
			}
			
			// 요청 합이 예산 한도 내에 해결
			// 상한선을 무조건 높여야 하는가..?
			if(sum <= M) {
				if(temp > answer) answer = temp;
				left = mid + 1;
			} else {
				// sum이 더 크다는 것은 상한선을 줄여야 하다는 뜻
				right = mid-1; 
			}
		}
		
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}
