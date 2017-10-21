/*
	작성자 : 박진현
	문제 : 백준 14503번, 삼성 기출 로봇청소기
*/

#include <iostream>

using namespace std;

int map[51][51] = { 0, };
int answer = 0;

// 북 동 남 서
int dirs[4][2] = { { -1, 0 }, { 0, 1 }, { 1,0 },{ 0,-1 } };

int checkDir(int d, bool reverse) {
	if (reverse) {
		if (d == 0) {
			return 2;
		}
		else if (d == 1) {
			return 3;
		}
		else if (d == 2) {
			return 0;
		}
		else if (d == 3) {
			return 1;
		}
	}
	else {
		if (d == 0) {
			return 3;
		}
		else if (d == 1) {
			return 0;
		}
		else if (d == 2) {
			return 1;
		}
		else if (d == 3) {
			return 2;
		}
	}
}

int main(void) {
	int N, M;
	int x, y, d;
	int cnt = 0; // 네방향 청소 체크용

	scanf("%d %d", &N, &M);
	scanf("%d %d %d", &x, &y, &d);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	map[x][y] = 2;
	answer++;

	while (true) {
		int nx;
		int ny;
		
		if (cnt == 4) {
			d = checkDir(d, true); // 네방향이 다 청소됐으면 후진 해야함, true
			nx = x + dirs[d][0];
			ny = y + dirs[d][1];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 1) {
				break;
			}

			x = nx;
			y = ny;
			d = checkDir(d, 1); // 방향을 유지한채로 후진을 해야하기 때문에 후진을 하고 다시 방향을 돌리기 위하여
			cnt = 0; // 다시 청소한 칸이 0이 되도록.
		}

		d = checkDir(d, false); // 아직 네방향 청소할 곳이 꽉 안 찼으므로..
		nx = x + dirs[d][0];
		ny = y + dirs[d][1];
		cnt++;

		// 현재 위치의 왼쪽 방향으로 청소할 곳이 있다면 전진 후 청소
		if (nx >= 0 && nx < N && ny >= 0 && ny < M && !map[nx][ny]) {
			x = nx; 
			y = ny;  

			map[x][y] = 2; // 청소
			answer++;
			cnt = 0; // 전진했으니 다시 네방향 체크해야함.
		}
	}

	cout << answer << endl;
	
	return 0;
}
