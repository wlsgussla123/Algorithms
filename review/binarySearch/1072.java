package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Task {
	private long X,Y,Z;
	private long answer;
	private final Scanner sc = new Scanner(System.in);
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		X = sc.nextLong();
		Y = sc.nextLong();
		Z = (Y*100)/X;
	}
	
	public void run() throws IOException {
		while(sc.hasNext()) {
			input();
			long l = 1;
			long r = 2000000000;
			answer = r;
			while(l<=r) {
				long mid = (l+r)/2;
				long temp = ((Y+mid)*100)/(X+mid);
				// temp가 크면 answer 저장하고 다른 범위 찾아봄
				if(temp > Z) {
					answer = answer < mid ? answer : mid;
					r = mid-1;
				} else {
					// temp가 작거나 같으면 아직 변한게 아니므로 승리 범위를 넓혀주자
					l = mid+1;
				}
			}
			System.out.println(answer == r ? "-1" : answer);
		}
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}
