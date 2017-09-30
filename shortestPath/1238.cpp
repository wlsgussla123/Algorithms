/*
	작성자 : 박진현
	문제 : 파티 문제, 백준 1283번
	풀이법 : 플로이드-워셜
	작성일 : 17.09.29
*/

#include <stdio.h>
#include <algorithm>

using namespace std;

int map[1000][1000];

int main(void) {
	int N, M, X; 
	int row, col, val;

	scanf("%d %d %d", &N, &M, &X);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			map[i][j] = 987654321;
		}
		map[i][i] = 0; // 자기 자신에 대한 경로는 0으로 하자...
	}

	for (int i = 0; i < M; i++) {
		scanf("%d %d %d", &row, &col, &val);
		map[row - 1][col - 1] = val;
	}

	// x->y로 갈 때, 
	// 한점을 경유하여
	for (int k = 0; k < N; k++) {
		// x -> 한점
		for (int i = 0; i < N; i++) {
			// 한점 -> y
			for (int j = 0; j < N; j++) {
				if (map[i][j] > map[i][k] + map[k][j]) {
					map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
	}

	int answer = -1;
	for (int i = 0; i < N; i++) {
		answer = max(answer, map[i][X-1] + map[X-1][i]);
	}

	printf("%d\n", answer);

	return 0;
}

/*
4 8 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3
*/
