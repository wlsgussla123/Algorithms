/*
	작성자 : 박진현
	문제 : 삼성 기출,연구소
*/
#include <iostream>
#include <queue>

using namespace std;

int N, M;

int map[8][8] = { 0, };
int visit[8][8] = { 0, };
int answer = 0;

int dirs[4][2] = { {0,1}, {0,-1}, {1,0}, {-1,0} };

queue<pair<int, int>> q;

void copy_map(int a[8][8], int b[8][8]) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			a[i][j] = b[i][j];
		}
	}
}

int count_virus(int a[8][8]) {
	int count = 0;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (!a[i][j]) {
				count++;
			}
		}
	}

	return count;
}

// 바이러스 퍼뜨리기.
void bfs() {
	// 바이러스 좌표 일단 넣어두고.
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j] == 2) {
				q.push({ i, j });
			}
		}
	}

	int temp[8][8]; 
	copy_map(temp, map);

	while (!q.empty()) {
		int row = q.front().first;
		int col = q.front().second;
		q.pop();
		
		for (int i = 0; i < 4; i++) {
			int x = row + dirs[i][0];
			int y = col + dirs[i][1];

			if (x >= 0 && x < N && y >= 0 && y < M) {
				if (!temp[x][y]) {
					temp[x][y] = 2;
					q.push({ x,y });
				}
			}
		}
	}

	int count = count_virus(temp);
	answer = max(answer, count);
}

// 백트랙킹으로 세 개씩 세울 때마다 bfs 바이러스 퍼뜨려보기
void dfs(int row, int col, int cnt) {
	if (cnt == 3) {
		bfs();
	}
	else {
		for (int i = row; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j] && !map[i][j]) {
					map[i][j] = 1;
					visit[i][j] = 1;
					dfs(i, j, cnt + 1);
					map[i][j] = 0;
					visit[i][j] = 0;
				}
			}
		}
	}
}

int main(void) {
	scanf("%d %d", &N, &M);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (!visit[i][j] && !map[i][j]) {
				map[i][j] = 1;
				visit[i][j] = 1;
				dfs(i, j, 1);
				visit[i][j] = 0;
				map[i][j] = 0;
			}
		}
	}

	cout << answer << endl;

	return 0;
}
