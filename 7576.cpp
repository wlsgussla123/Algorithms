#include <stdio.h>
#include <queue>

using namespace std;

struct position {
	int row;
	int col;

	position(int r, int c) : row(r), col(c) {}
};

queue<position> Queue;

int M, N;
int cnt = 0; // 토마토의 개수
int day = 0; // 일수
int endNumber;

int map[1000][1000] = { 0, };
int answer_map[1000][1000] = { 0, };
int visit[1000][1000] = { 0, };

// 동 서 남 북
int row_dir[4] = { 0,0,1,-1 };
int col_dir[4] = { 1,-1,0,0 };

void BFS(int row, int col) {
	visit[row][col] = 1;

	for (int i = 0; i < 4; i++) {
		// 범위 안에 있으면 queue에 넣자.
		if (row + row_dir[i] >= 0 && row + row_dir[i] < N && col + col_dir[i] >= 0 && col + col_dir[i] < M) {
			Queue.push(position(row + row_dir[i], col + col_dir[i]));
		}
	}

	while (!Queue.empty()) {		
		position temp = Queue.front();
		Queue.pop();
		
		if (!visit[temp.row][temp.col]) {
			BFS(temp.row, temp.col);
		}
	}
	if (cnt == endNumber) {
		return;
	}

	cnt++;
}

int main(void) {
	// row : N, col : M

	scanf("%d %d", &M, &N);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	endNumber = M * N;

	// 본 적 없는 토마토면 탐색
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j] && !visit[i][j]) {
				BFS(i, j);
			}
		}
	}

	printf("%d\n", day);

	return 0;
}
