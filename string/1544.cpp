/*
	작성자 : 박진현
	문제 : 백준 1544번, 사이클 단어
*/
#include <stdio.h>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

bool isSubstring(string str1, string str2) {
	if (str1.find(str2) != string::npos) {
		return true;
	}

	return false;
}

bool isRotation(string str1, string str2) {
	if (isSubstring(str1 + str1, str2))
		return true;
	else
		return false;
}

int main(void) {
	int N;
	vector<string> v;
	string temp;
	int answer = 0;

	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		cin >> temp;
		v.push_back(temp);
	}

	// 만약 i!=j이고 사이클 단어라면, 벡터에서 그것을 제거한다. 그렇다면 사이클 단어를 제외하고 단어의 원형만이 남을 것. 그것의 개수가 답
	for (int i = 0; i < v.size(); i++) {
		for (int j = 0; j < v.size(); j++) {
			if (v[i].size() != v[j].size()) continue; // 길이가 다르면, 삭제할 대상이 아니므로 넘어감
			if (i != j && isRotation(v[i], v[j])) {
				v.erase(v.begin() + j);
				j--;
			}
		}
	}
	printf("%d\n", v.size());

	return 0;
}

/*
5
picture
turepic
icturep
word
ordw
*/
