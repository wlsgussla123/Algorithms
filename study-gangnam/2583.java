package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Task {
	private int N, M, K;
	private int[][] map;
	private boolean[][] visited;
	private int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	private int[] answer;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void init() {
		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		init();
		for (int i = 1; i <= K; i++) {
			st = getStringTokenizer();
			int lx, ly, rx, ry;
			lx = Integer.parseInt(st.nextToken());
			ly = Integer.parseInt(st.nextToken());
			rx = Integer.parseInt(st.nextToken());
			ry = Integer.parseInt(st.nextToken());
			draw(lx, ly, rx, ry);
		}
	}

	private void draw(int lx, int ly, int rx, int ry) {
		for (int i = N - ly; i > N - ry; i--) {
			for (int j = lx + 1; j <= rx; j++) {
				map[i][j] = 1;
			}
		}
	}

	private void dfs(int x, int y, int c) {
		map[x][y] = c;
		for (int i = 0; i < 4; i++) {
			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];

			if (nx >= 1 && nx <= N && ny >= 1 && ny <= M && !visited[nx][ny] && map[nx][ny] == 0) {
				visited[nx][ny] = true;
				dfs(nx, ny, c);
			}
		}
	}

	private void count() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] >= 101) {
					answer[map[i][j] - 101]++;
				}
			}
		}
		Arrays.sort(answer);
		for (Integer num : answer)
			System.out.print(num + " ");
	}

	private void process() {
		int c = 100;
		int len = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (!visited[i][j] && map[i][j] == 0) {
					len++;
					dfs(i, j, ++c);
				}
			}
		}
		answer = new int[len];
		System.out.println(len);
		count();
	}

	public void run() throws IOException {
		input();
		process();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}
