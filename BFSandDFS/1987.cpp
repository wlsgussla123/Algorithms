/*
	작성자: 박진현
	문제 : 백준 1987, 알파벳 문제
*/

#include <iostream>
#include <string>

using namespace std;

// input
int R, C;
string temp;

char map[20][20] = { 0, };
int visit[20][20] = { 0, };

int alphabet[26] = { 0 };
// 동서남북
int dir[4][2] = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
int answer = 0;

void DFS(int row, int col, int cnt) {
	visit[row][col] = 1;
	int index = map[row][col] - 'A';
	alphabet[index] = 1;

	for (int i = 0; i < 4; i++) {
		int x = row + dir[i][0];
		int y = col + dir[i][1];

		// 범위 검사
		if (x >= 0 && x < R && y >= 0 && y < C) {
			int nIndex = map[x][y] - 'A'; // index 나오고
			// 방문했는지 혹은 나왔던 알파벳인지 검사
			if (!alphabet[nIndex] && !visit[x][y]) {
				DFS(x, y, cnt + 1);
			}
		}
	}

	if (answer < cnt) {
		answer = cnt;
	}
	visit[row][col] = 0;
	alphabet[index] = 0;
}

int main(void) {
	scanf("%d %d", &R, &C);
	getchar();
	for (int i = 0; i < R; i++) {
		getline(cin, temp);

		for (int j = 0; j < C; j++) {
			map[i][j] = temp[j];
		}
	}

	DFS(0, 0, 1);
	cout << answer << endl;

	return 0;
}
