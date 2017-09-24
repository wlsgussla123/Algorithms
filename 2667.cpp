/*
  작성자 : 박진현
  문제 : 단지번호 붙이기, 백준 문제
*/

#include <stdio.h>
#include <stdlib.h>
#include <algorithm>
#include <vector>

using namespace std;

int N;
vector<int> answer;
int ** map;
int ** answer_map;
int ** visit;

// NORTH : 0, SOUTH : 1, EAST: 2, WEST: 3 
int r_dir[4] = { 1, -1, 0, 0 };
int c_dir[4] = { 0, 0, 1, -1 };

void DFS(int row, int col, int cnt) {
	for (int i = 0; i < 4; i++) {
		if (row + r_dir[i] >= 0 && row + r_dir[i] < N && col + c_dir[i] >= 0 && col + c_dir[i] < N) {
			if (!visit[row + r_dir[i]][col + c_dir[i]] && map[row + r_dir[i]][col + c_dir[i]]) {
				visit[row + r_dir[i]][col + c_dir[i]] = 1;
				answer_map[row + r_dir[i]][col + c_dir[i]] = cnt;
				DFS(row + r_dir[i], col + c_dir[i], cnt);
			}
		}
	}
}

int main(int argc, const char * argv[]) {
	int cnt = 1;

	scanf("%d", &N);
	map = (int **)malloc(sizeof(int *) * N);
	answer_map = (int **)malloc(sizeof(int *) * N);
	visit = (int **)malloc(sizeof(int *) * N);

	for (int i = 0; i < N; i++) {
		map[i] = (int *)malloc(sizeof(int) * N);
		answer_map[i] = (int *)malloc(sizeof(int) * N);
		visit[i] = (int *)malloc(sizeof(int) * N);
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			visit[i][j] = 0;
			answer_map[i][j] = 0;
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%1d", &map[i][j]);
		}
	}
	
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (map[i][j] && !visit[i][j]) {
				visit[i][j] = 1;
				answer_map[i][j] = cnt;
				DFS(i, j, cnt++);
			}
		}
	}

	for (int i = 1; i <= cnt - 1; i++) {
		int count = 0;
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < N; k++) {
				if (answer_map[j][k] == i)
					count++;
			}
		}
		answer.push_back(count);
	}
	sort(answer.begin(), answer.end());
	printf("%d\n", cnt - 1);

	if (cnt - 1) {
		for (int i = 0; i < (int)answer.size(); i++) {
			printf("%d\n", answer[i]);
		}
	}

	return 0;
}

/*
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000
*/
