/*
	작성자 : 박진현
	문제 : 백준 1120, 문자열 문제
*/

#include <stdio.h>
#include <string>
#include <iostream>

using namespace std;

int min(int a, int b) {
	return a < b ? a : b;
}

int main(void) {
	string str1;
	string str2;
	int cnt = 0;
	int answer = 987654321;
	int index = 0;

	cin >> str1;
	cin >> str2;

	int diff = str2.size() - str1.size();
	for (int i = 0; i <= diff; i++) {
		for (int j = 0; j < str1.size(); j++) {
			if (str1[j] != str2[j + i]) {
				cnt++;
			}
		}
		
		answer = min(answer, cnt);
		cnt = 0;
	}

	cout << answer << endl;
	

	return 0;
}
