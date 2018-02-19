package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Task {
	private long X,Y,Z;
	private final int MAX = 2000000001;
	private Scanner sc = new Scanner(System.in);
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		while(sc.hasNextLong()) {
			X = sc.nextLong();
			Y = sc.nextLong();
			long temp = (Y*100)/X;
			Z = temp;
			if(Z>=99) System.out.println("-1");
			else {
				process();
			}
		}
	}

	private void process() {
		long left=0, right=2000000001;
		long answer = MAX;
		while(left<=right) {
			long x = X;
			long y = Y;
			long mid = (left+right)/2;
			x += mid;
			y += mid;
			long temp = (y*100)/x;
			long z = temp;
			
			if(z<=Z) {
				left = mid+1;
			} else {
				right = mid-1;
				if(answer > mid) {
					answer = mid;
				}
			}
		}
		
		if(answer == MAX) System.out.println("-1");
		else System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
