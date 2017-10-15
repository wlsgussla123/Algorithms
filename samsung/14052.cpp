/*
	작성자 : 박진현
	문제 : 삼성 기출, 14502 연구소
	
	설명 : 사실 그 전에 모의고사로 시간을 재면서 풀었는데 실패했다. 
	내가 시도했던 접근법은 바이러스로부터 경로를 모두 체크하고 각 경로마다
	퍼뜨리는 바이러스 개수를 구하였다. 그 중에서 가장 큰 세 개를 뽑아서
	벽을 두는 것으로 구현을 했으나, 문제는 같은 바이러스가 3개를 넘어가면 문제였다.

	검색을 해서 코드를 보니 아예 벽을 모든 좌표에 넣어봄으로써 시도하는 것이 맞는 방향인가보다.

	물론 이것은 MAX_SIZE가 작기 때문에 가능한 방법이다.
*/

#include <iostream>
#include <vector>
#include <queue>

#define MAX_SIZE 8

using namespace std;

// input
int M, N;
int map[MAX_SIZE][MAX_SIZE] = { 0, };
int answer_map[MAX_SIZE][MAX_SIZE] = { 0, };

int visit[MAX_SIZE][MAX_SIZE] = { 0, };
// 동서남북
int dir[4][2] = { {0,1}, {0,-1}, {1,0}, {-1,0} };
int answer = -1;
vector<pair<int, int>> virus;

int max(int a, int b) {
	return a > b ? a : b;
}

int countSafe() {
	int cnt = 0;

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (!answer_map[i][j]) cnt++;
		}
	}

	return cnt;
}

// 기둥 세우고 다시 원래로 복원.
void recoveryMap(int (*map)[MAX_SIZE], int(*temp)[MAX_SIZE]) {
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			map[i][j] = temp[i][j];
		}
	}
}

// 임시 복사
void copyMap(int (*a)[MAX_SIZE], int (*b)[MAX_SIZE]) {
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			a[i][j] = b[i][j];
		}
	}
}

// 기둥을 세워둔 것에 기반하여 바이러스를 퍼뜨려 본다.
void bfs() {
	queue<pair<int, int>> q;
	for (int i = 0; i < virus.size(); i++) q.push(virus[i]);

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];

			if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
				if (!answer_map[nx][ny]) {
					answer_map[nx][ny] = 2;
					q.push(make_pair(nx, ny));
				}
			}
		}
	}
}

// cnt = 벽의 개수, 벽놓기 함수
void dfs(int row, int col, int cnt) {
	answer_map[row][col] = 1;
	visit[row][col] = 1;

	if (cnt == 3) {
		//// 복사 맵이 필요 (기둥을 놓고 다시 복원을 해야하므로)
		int temp[MAX_SIZE][MAX_SIZE];
		copyMap(temp, answer_map);

		bfs(); // bfs를 하면 바이러스를 다 퍼뜨리므로, 바이러스를 퍼뜨리기 전의 맵을 저장하는 temp 맵이 필요
	
		answer = max(answer, countSafe());
		recoveryMap(answer_map, temp);
	}
	else {
		for (int i = row; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j] && !answer_map[i][j]) {
					dfs(i, j, cnt + 1);
				}
			}
		}
	}

	answer_map[row][col] = 0;
	visit[row][col] = 0;
}

int main(void) {
	scanf("%d %d", &M, &N);

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &map[i][j]);

			answer_map[i][j] = map[i][j];
			if (map[i][j] == 2) virus.push_back(make_pair(i,j));
		}
	}

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (!answer_map[i][j]) {
				dfs(i, j, 1);
			}
		}
	}

	printf("%d\n", answer);

	return 0;
}

/*
7 7
2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0

4 4
2 0 0 1
0 0 2 0
0 1 0 0
0 0 1 1
*/
