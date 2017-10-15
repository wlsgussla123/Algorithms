/*
	작성자: 박진현
	문제 : 백준 1051번, 숫자정사각형

	설명 : 문제를 꼼꼼히 읽자.
*/

#include <iostream>
#include <string>

using namespace std;

int N, M;
int map[50][50] = { 0, };
int visit[50][50] = { 0, };
int answer = 1;

void calculator(int row, int col, int cnt) {	
	int y = row + cnt;
	int x = col + cnt;

	while (y >= 0 && y < N && x >= 0 && x < M) {
		if (map[row][col] == map[y][col] && map[row][col] == map[row][x] && map[row][col] == map[y][x]) {
			if (cnt >= answer) {
				answer = cnt + 1;
			}
		}

		cnt++;

		y = row + cnt;
		x = col + cnt;
	}
}

int main(void) {
	int cnt = 1;
	string str[50];

	scanf("%d %d", &N, &M);
	getchar();
	for (int i = 0; i < N; i++) {
		getline(cin, str[i]);
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			map[i][j] = str[i][j] - '0';
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			calculator(i, j, cnt);
		}
	}

	cout << answer * answer << endl;

	return 0;
}
