/*
  작성자 : 박진현
  문제 : 백준 2178번, 미로찾기 문제

  * 주의 : 정답 아님, 샘플 예제는 맞지만 정답은 아님
  -> 추측 : DFS는 시간 부담이 너무 많이 생김.
  혹은 다른 틀린 테스트 케이스가 있는 것 같지만, 시간 부족.
  추후 다시 풀어볼 것

*/
#include <stdio.h>
#include <stdlib.h>

using namespace std;

int ** map;
int ** visit;
int n, m;
int minCnt = 987654321; // 최소를 비교하기 위하여

// South : 0, North : 1, WEST: 2, EAST: 3
int rDiff[4] = { 1, -1, 0, 0 };
int cDiff[4] = { 0, 0, -1, 1 };

void DFS(int row, int col, int cnt) {
	visit[row][col] = 1;

	if (row == n - 1 && col == m - 1) {
		if (minCnt > cnt) {
			minCnt = cnt;
		}
		return;
	}

	for (int i = 0; i < 4; i++) {
		int r = row + rDiff[i];
		int c = col + cDiff[i];

		if (r < 0 || r >= n || c < 0 || c >= m || map[r][c] == 0) {
			continue;
		}

		if (map[r][c] == 1 && visit[r][c] == 0) {
			DFS(r, c, cnt + 1);
			visit[r][c] = 0;
		}
	}
}

int main(void) {
	scanf("%d", &n);
	scanf("%d", &m);

	map = (int **)malloc(sizeof(int *) * n);
	visit = (int **)malloc(sizeof(int *) * n);

	for (int i = 0; i < n; i++) {
		map[i] = (int *)malloc(sizeof(int) * m);
		visit[i] = (int *)malloc(sizeof(int) * m);
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			visit[i][j] = 0;
		}
	}

	// 맵 입력 
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	DFS(0, 0, 1);
	printf("%d\n", minCnt);

	return 0;
}
