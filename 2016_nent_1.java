
import java.util.Scanner;

class Main {
	static int[][] map = new int[8][8];
	static int[][] visit = new int[8][8];
	static boolean[] checks;
	static int cnt=0;

	public static void DFS(int row, int col) {
		visit[row][col] = 1;
		
		if(visit[7][7] == 1) {
			checks[cnt] = true;
			return ;
		}
		
		switch(map[row][col]) {
			case 1:
				if(col+1 < 8) {
					if(map[row][col+1] != 0 && visit[row][col+1] == 0) {
						DFS(row, col+1);
					}
				}
				break;
			case 2:
				if(row+1 < 8) {
					if(map[row+1][col] != 0 && visit[row+1][col] == 0) {
						DFS(row+1, col);
					}			
				}
				break;
			case 3:
				if(col+1 < 8) {
					if(map[row][col+1] != 0 && visit[row][col+1] == 0) {
						DFS(row, col+1);
					}
				}
				break;
			case 4:
				if(row+1 < 8) {
					if(map[row+1][col] != 0 && visit[row+1][col] == 0) {
						DFS(row+1, col);
					}
				}
				break;
		}
	}
	
  public static void main(String[] args) {
		int n;
		int temp;
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		temp = n;
		checks = new boolean[n];		
		
		for(int i=0; i<n; i++) {
			checks[i] = false;
		}

		while(temp!=0) {
			for(int i=0; i<8; i++) {
				for(int j=0; j<8; j++) {
					visit[i][j] = 0;
				}
			}
			
			for(int i=0; i<8; i++) {
				for(int j=0; j<8; j++) {
					map[i][j] = scan.nextInt();
				}
			}
			
			DFS(0,0);
			cnt++;
			temp--;
		}
		
		for(int i=0; i<n; i++) {
			if(checks[i]) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
  }
}
