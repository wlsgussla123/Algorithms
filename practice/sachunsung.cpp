/*
	작성자 : 박진현
	문제 : 사천성 문제 (약 한 달 전에 풀었던 문제)
*/

#include <iostream>

using namespace std;

int map[12][12] = { 0, };
int visit[12][12] = { 0, };
// 동, 서, 남, 북
int dirs[4][2] = { {0,1}, {0,-1}, {1,0}, {-1,0} };

bool answer = false;
int type = 0;

void initVisit() {
	for (int i = 1; i <= 10; i++) {
		for (int j = 1; j <= 10; j++) {
			visit[i][j] = 0;
		}
	}
}

void connection(int row1, int col1, int row2, int col2, int dir, int cnt) {
	if (row1 == row2 && col1 == col2) {
		answer = true;
	}
	else {
		for (int i = 0; i < 4; i++) {
			int x = row1 + dirs[i][0];
			int y = col1 + dirs[i][1];

			if (x > 0 && x <= 10 && y > 0 && y <= 10) {
				if (!map[x][y] || map[x][y] == type) {
					if (!visit[x][y]) {
						visit[x][y] = 1;
						if (dir == i) connection(x, y, row2, col2, i, cnt);
						else if (cnt + 1 <= 3) connection(x, y, row2, col2, i, cnt + 1);
						//visit[x][y] = 0;
					}
				}
			}
		}
	}
}

int main(void) {
	int cnt = 0;
	for (int i = 1; i <= 10; i++) {
		for (int j = 1; j <= 10; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	scanf("%d", &cnt);
	for (int i = 0; i < cnt; i++) {
		initVisit();
		answer = false;

		int row1, col1, row2, col2;
		scanf("%d %d %d %d", &row1, &col1, &row2, &col2);
		type = map[row1][col1];
		visit[row1][col1] = 1;
		connection(row1, col1, row2, col2, 0, 0);
		//visit[row1][col1] = 0;

		if (answer) cout << "YES" << endl;
		else cout << "NO" << endl;
	}

	return 0;
}
