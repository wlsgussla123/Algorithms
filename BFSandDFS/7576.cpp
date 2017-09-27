/*
  작성자 : 박진현
  문제 : 백준 7576번, 토마토
  상태 : 미완
*/
#include <stdio.h>
#include <queue>

using namespace std;

struct position {
	int row;
	int col;
	int day;

	position(int r, int c, int d) : row(r), col(c), day(d) {}
};

queue<position> Queue;

int M, N;

int map[1000][1000] = { 0, };
int answer_map[1000][1000] = { -1, };
int visit[1000][1000] = { 0, };

// 동 서 남 북
int row_dir[4] = { 0,0,1,-1 };
int col_dir[4] = { 1,-1,0,0 };

void BFS(int row, int col, int day) {
	Queue.push(position(row, col, day));

	while (!Queue.empty()) {
		position temp = Queue.front();
		Queue.pop();

		answer_map[temp.row][temp.col] = temp.day;

		int nextDay = temp.day + 1;

		for (int i = 0; i < 4; i++) {
			if (temp.row + row_dir[i] >= 0 && temp.row + row_dir[i] < N && temp.col + col_dir[i] >= 0 && temp.col + col_dir[i] < M) {
				if (answer_map[temp.row + row_dir[i]][temp.col + col_dir[i]] == -1) {
					Queue.push(position(temp.row + row_dir[i], temp.col + col_dir[i], nextDay));
				}
			}
		}
	}
}

int main(void) {
	int day = 0;
	int max = -1;

	// row : N, col : M
	scanf("%d %d", &M, &N);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
			answer_map[i][j] = -1;
		}
	}

	// 본 적 없는 토마토면 탐색
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j] && answer_map[i][j] == -1) {
				BFS(i, j, day);
			}
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (max < answer_map[i][j]) {
				max = answer_map[i][j];
			}
		}
	}

	if (max) {
		printf("%d\n", max);
	}
	else {
		printf("-1\n");
	}

	return 0;
}

/*
6 4
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 1
*/
