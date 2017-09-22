/*
  경세가 도와준 사천성..
  아직은 DFS활용에 약한 것 같다.

  코드 분석하고 다시 풀어보아ㅑ겠다
*/
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <vector>
#include <string>

using namespace std;

int type = 0;
int check = 0;
int map[12][12] = { 0, };
int visit[12][12] = { 0, };
int row_dir[4] = { 1, -1, 0, 0 };
int col_dir[4] = { 0, 0, 1, -1 };

//dir South 0 North 1 East 2 West 3
void connection(int row1, int col1, int row2, int col2, int dir, int cnt) {
	if (row1 == row2 && col1 == col2) check = 1;
	else {
		for (int i = 0; i<4; i++) {
			if (row1 + row_dir[i]>0 && row1 + row_dir[i] <= 10 && col1 + col_dir[i]>0 && col1 + col_dir[i] <= 10) {
				if (!map[row1 + row_dir[i]][col1 + col_dir[i]] || map[row1 + row_dir[i]][col1 + col_dir[i]] == type) {
					if (!visit[row1 + row_dir[i]][col1 + col_dir[i]]) {
						visit[row1 + row_dir[i]][col1 + col_dir[i]] = 1;
						if (dir == i) connection(row1 + row_dir[i], col1 + col_dir[i], row2, col2, i, cnt);
						else if (cnt + 1 <= 3) connection(row1 + row_dir[i], col1 + col_dir[i], row2, col2, i, cnt + 1);
						visit[row1 + row_dir[i]][col1 + col_dir[i]] = 0;
					}
				}
			}
		}
	}
}

int main(int argc, const char * argv[]) {
	int cnt = 0;
	for (int i = 1; i <= 10; i++) for (int j = 1; j <= 10; j++) scanf("%d", &map[i][j]);
	scanf("%d", &cnt);
	for (int k = 0; k<cnt; k++) {
		for (int i = 1; i <= 10; i++) for (int j = 1; j <= 10; j++) visit[i][j] = 0;
		int row1, row2, col1, col2;
		scanf("%d %d %d %d", &row1, &col1, &row2, &col2);
		check = 0;
		type = map[row1][col1];
		visit[row1][col1] = 1;
		connection(row1, col1, row2, col2, 0, 0);
		visit[row1][col1] = 0;
		if (check)  printf("YES\n");
		else printf("NO\n");
	}
	return 0;
}

/*
0 0 0 0 0 0 0 0 0 0
0 1 2 3 0 0 0 0 0 0
0 2 3 4 0 0 0 0 0 0
0 1 0 4 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
4
2 2 2 3
2 2 4 2
2 3 3 2
2 2 4 2

->

NO
YES
YES
NO
*/
