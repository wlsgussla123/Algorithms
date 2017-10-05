/*
	작성자 : 박진현
	문제 : 카카오 1차 1번
	작성일 : 17.10.05
*/

#include <stdio.h>
#include <vector>
#include <iostream>
#include <string>

using namespace std;

int answer_map[16][16] = {};
int N;

vector<string> v;
vector<char> temp;

// 이진수로 변환
void decimalToBinary(int index, int map[]) {
	string str = "";

	// 이진수 변환하는 반목문
	while (map[index] > 0) {
		temp.push_back(map[index] % 2 + '0');
		map[index] = map[index] / 2;
	}

	str = "";
	reverse(temp.begin(), temp.end());
	for (int i = 0; i < temp.size(); i++) {
		str += temp[i];
	}

	for (int i = temp.size(); i < N; i++)
		str = '0' + str;

	temp.clear();
	v.push_back(str);
}

int main(void) {
	int map1[16] = {0,};
	int map2[16] = {0,};

	scanf("%d", &N);

	// 두 개의 map 입력
	for (int i = 0; i < N; i++)
		scanf("%d", &map1[i]);
	for (int i = 0; i < N; i++)
		scanf("%d", &map2[i]);

	for (int i = 0; i < N; i++) {
		decimalToBinary(i, map1);
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			answer_map[i][j] = v[i][j] - '0';
		}
	}
	
	v.clear();
	for (int i = 0; i < N; i++) {
		decimalToBinary(i, map2);
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (v[i][j] - '0' == 1) {
				answer_map[i][j] = v[i][j] -'0';
			}
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (answer_map[i][j])
				printf("#");
			else
				printf(" ");
		}
		putchar('\n');
	}

	return 0;
}
