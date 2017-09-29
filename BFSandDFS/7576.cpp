/*
  작성자 : 박진현
  문제 : 백준 7576번, 토마토
  상태 : 완료. (3일 간의 투쟁..)
*/
#include <stdio.h>
#include <queue>
#include <utility>

using namespace std;

queue<pair<int, int>> Queue;

int M, N;

int map[1000][1000] = { 0, };
int answer_map[1000][1000] = { 0, };

// 동 서 남 북
int row_dir[4] = { 0,0,1,-1 };
int col_dir[4] = { 1,-1,0,0 };

void BFS() {
	while (!Queue.empty()) {
		int row = Queue.front().first;
		int col = Queue.front().second;
	
		Queue.pop();

		for (int i = 0; i < 4; i++) {
			if (row + row_dir[i] >= 0 && row + row_dir[i] < N && col + col_dir[i] >= 0 && col + col_dir[i] < M) {
				if (!map[row+row_dir[i]][col+col_dir[i]] && answer_map[row + row_dir[i]][col + col_dir[i]] == 0) {
					answer_map[row + row_dir[i]][col + col_dir[i]] = answer_map[row][col] + 1;
					Queue.push(make_pair(row + row_dir[i], col + col_dir[i]));
				}
			}
		}
	}
}

int main(void) {
	int day = 0;
	int max = -1;
	bool check = true;

	// row : N, col : M
	scanf("%d %d", &M, &N);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);

			if (map[i][j] == -1) {
				answer_map[i][j] = -1;
			}

			if (map[i][j] != 1) {
				check = false;
			}
		}
	}

	if (check) {
		printf("0\n");
		return 0;
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j]==1) {
				Queue.push(make_pair(i,j));
				answer_map[i][j] = 1;
			}
		}
	}

	BFS();

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (answer_map[i][j] == 0) {
				printf("-1\n");
				return 0;
			}
			else if (max < answer_map[i][j]) {
				max = answer_map[i][j];
			}
		}
	}

	printf("%d\n", max-1);
	
	return 0;
}

/*
6 4
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 1
*/
