/*
	작성자 : 박진현
	문제 : 백준 2583번, 영역 구하기 문제
	날짜 : 17.09.27
*/

#include <stdio.h>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

struct position {
	int row;
	int col;
	int cnt;

	position(int r, int c, int d) : row(r), col(c), cnt(d) {}
};

queue<position> Queue;

vector<int> answer;

int visit[100][100] = { 0, };
int answer_map[100][100] = { 0, };
int map[100][100] = { 0, };
int M, N; // map size
int K; // 직사각형 개수

int lx, ly, rx, ry; // 좌표

// 동 서 남 북
int row_dir[4] = { 0, 0, 1, -1 };
int col_dir[4] = { 1, -1, 0, 0 };

// DFS와 BFS.. 뭘 선택해야 할까? BFS로 했다가 안 된다는 걸 느끼고 DFS로 바꿔서 풀었다.
void DFS(int row, int col, int cnt) {
	visit[row][col] = 1;
	answer_map[row][col] = cnt;

	for (int i = 0; i < 4; i++) {
		if (row + row_dir[i] >= 0 && row + row_dir[i] < M && col + col_dir[i] >= 0 && col + col_dir[i] < N) {
			if (!visit[row + row_dir[i]][col + col_dir[i]] && !map[row + row_dir[i]][col + col_dir[i]]) {
				DFS(row + row_dir[i], col + col_dir[i], cnt);
			}
		}
	}
}

int main(void) {
	int cnt = 1;

	// M : 행, N : 열
	scanf("%d %d", &M, &N);
	scanf("%d", &K);
	
	for (int i = 0; i < K; i++) {
		scanf("%d %d %d %d", &lx, &ly, &rx, &ry);

		// 180도로 돌려도 똑같기 때문에 굳이 input을 맞출 필요가 없다.
		for (int j = ly; j < ry; j++) {
			for (int k = lx; k < rx; k++) {
				map[j][k] = -1;
				answer_map[j][k] = -1;
			}
		}
	}

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (!map[i][j] && !visit[i][j]) {
				DFS(i, j, cnt++);
			}
		}
	}

	//putchar('\n');
	//for (int i = 0; i < M; i++) {
	//	for (int j = 0; j < N; j++) {
	//		printf("%d ", answer_map[i][j]);
	//	}
	//	putchar('\n');
	//}

	for (int k = 1; k <= N; k++) {
		int count = 0;

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (answer_map[i][j] == k)
					count++;
			}
		}
		answer.push_back(count);
	}

	
	sort(answer.begin(), answer.end());
	printf("%d\n", cnt - 1);

	for (int i = 0; i < (int)answer.size(); i++) {
		if (answer[i] != 0) {
			printf("%d ", answer[i]);
		}
	}
	putchar('\n');

	return 0;
} 

/*
5 7 3
0 2 4 4
1 1 2 5
4 0 6 2
*/
