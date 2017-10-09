
/*
작성자 : 박진현
문제 : 백준 1012번 문제, 유기농 배추
상태 : 틀렸습니다. 테스트 케이스 예시, 게시판 반례 다 맞게 나옴. 시간이 없으므로 반례를 천천히 찾아보자
*/
#include <stdio.h>
#include <vector>

using namespace std;

int M, N, K; // 가로길이(열), 세로길이(행), 배추개수
int row, col; // 이차원 배열에 나타나기 위한 행, 열
int map[50][50] = { 0, };
int visit[50][50] = { 0, };
int d[4][2] = { { 0, 1 },{ 0, -1 },{ 1, 0 },{ -1, 0 } }; // 동,서,남,북 

vector<int> worm;
int cnt = 0;

void DFS(int row, int col) {
	visit[row][col] = 1;

	// 사방향에 존재한다면,
	for (int i = 0; i < 4; i++) {
		int x = row + d[i][0];
		int y = col + d[i][1];

		if (x >= 0 && x < N && y >= 0 && y < M && !visit[x][y] && map[x][y]) {
			DFS(x, y);
		}
	}
}

int main(void) {
	int T;

	scanf("%d", &T);

	int temp = T;
	while (temp--) {
		scanf("%d %d %d", &M, &N, &K);

		for (int i = 0; i < K; i++) {
			scanf("%d %d", &col, &row);
			map[row][col] = 1;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] && !visit[i][j]) {
					DFS(i, j);
					cnt++;
				}
			}
		}

		//worm.push_back(cnt);
		printf("%d\n", cnt); // vector에 넣을 필요 없이 바로 printf로 출력해도 됨.

		// 다음 케이스를 위한 초기화
		cnt = 0;

		// 다음 케이스를 위한 초기화가 중요
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = 0;
				map[i][j] = 0;
			}
		}
	}

	//for (int i = 0; i < T; i++)
	//	printf("%d\n", worm[i]);

	return 0;
}

/*
1
10 6 14
0 0
1 0
1 1
2 4
3 4
4 2
4 3
4 5
7 4
8 4
9 4
7 5
8 5
9 5
*/
