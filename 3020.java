package algo;

import java.util.Scanner;

class Task {
	private int N;
	private int H;
	private final Scanner sc = new Scanner(System.in);
	
	private void input() {
		N = sc.nextInt();
		H = sc.nextInt();
		int up[] = new int[H+1];
		int down[] = new int[H+1];
		int totalUp[] = new int[H+1];
		int totalDown[] = new int[H+1];
		int total[] = new int[H+1];
		
		int count = 1;
		int min = 987654321;
		
		for(int i=0; i<=H; i++) {
			up[i] = down[i] = total[i] = totalUp[i] = totalDown[i] = 0;
		}
		
		for(int i=0; i<N; i++) {
			int value = sc.nextInt();
			if((i%2)==0) {
				up[value]++;
			}
			else {
				down[value]++;
			}
		}
		
		for(int i=H; i>=1; i--) {
			if(i==H) {
				totalUp[i] = up[i];
			} else {
				totalUp[i] = up[i] + totalUp[i+1];
			}
		}
		
		for(int i=H; i>=1; i--) {
			if(i==H) {
				totalDown[i] = down[i];
			} else {
				totalDown[i] = down[i] + totalDown[i+1];
			}
		}

		for(int i=1; i<=H; i++) {
			total[i] = totalDown[i] + totalUp[H-i+1];
			
			if(total[i] == min) {
				count++;
			} else if(total[i] < min) {
				min = total[i];
				count = 1;
			}
		}
		
		System.out.println(min + " " + count);	
	}
	
	public void run() {
		input();
	}
}

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.run();
	}
}
