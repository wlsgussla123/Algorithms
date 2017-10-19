/*
	작성자 : 박진현
	문제 : 백준 14500, 테트로미노 문제

	설명 : dfs로 네칸을 갈 수 있는 경우는 테트리스 블록으로 나옴. 참고로 뻐큐모양은 dfs로 안 된다.
	그래서 다시 체크
*/
#include <iostream>
#include <algorithm>

using namespace std;

int N, M;
int map[501][501] = { 0, };
int visit[501][501] = { 0, };
// 동서남북
int dirs[4][2] = { {0,1}, {0,-1}, {1,0}, {-1,0} };
int answer = 0;

void dfs(int row, int col, int cnt, int sum) {
	if (cnt == 4) {
		answer = max(answer, sum);
	}
	else {
		for (int i = 0; i < 4; i++) {
			int x = row + dirs[i][0];
			int y = col + dirs[i][1];

			if (x >= 0 && x < N && y >= 0 && y < M) {
				if (!visit[x][y]) {
					visit[x][y] = 1;
					dfs(x, y, cnt + 1, sum + map[x][y]);
					visit[x][y] = 0;
				}
			}
		}
	}
}

void cross(int row, int col) {
	// ㅏ
	if (row - 1 >= 0 && row + 1 < N && col + 1 < M)
		answer = max(answer, map[row][col] + map[row - 1][col] + map[row + 1][col] + map[row][col + 1]);
	// ㅓ
	if (row - 1 >= 0  && row + 1 < N && col - 1 >= 0)
		answer = max(answer, map[row][col] + map[row - 1][col] + map[row + 1][col] + map[row][col - 1]);
	// ㅜ
	if (col - 1 >= 0 && col + 1 < M && row + 1 < N)
		answer = max(answer, map[row][col] + map[row + 1][col] + map[row][col - 1] + map[row][col + 1]);
	// ㅗ
	if (col - 1 >= 0 && col + 1 < M && row - 1 < N)
		answer = max(answer, map[row][col] + map[row - 1][col] + map[row][col - 1] + map[row][col + 1]);
}

int main(void) {
	scanf("%d %d", &N, &M);
	for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) scanf("%d", &map[i][j]);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			visit[i][j] = 1;
			dfs(i, j, 1, map[i][j]);
			visit[i][j] = 0;
			cross(i, j);
		}
	}

	cout << answer << endl;

	return 0;
}
