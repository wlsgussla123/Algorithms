/*
	작성자: 박진현
	문제 : 10988번 팰린드롬
*/

#include <stdio.h>
#include <string>
#include <iostream>

using namespace std;

int main(void) {
	string str;
	int answer = 0;
	bool check = true;

	cin >> str;

	int len = str.size();
	for (int i = 0; i < str.size() / 2; i++, len--) {
		if (str[i] != str[len-1]) {
			check = false;
			break;
		}
	}

	if (check)
		printf("1\n");
	else
		printf("0\n");

	return 0;
}
