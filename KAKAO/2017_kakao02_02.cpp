/*
	작성자 : 박진현
	문제 : 카카오 1차 채용 2번문제
	작성일 : 17.10.05
*/

#include <stdio.h>
#include <iostream>
#include <vector>
#include <string>
#include <stack>

using namespace std;

int main(void) {
	string str;
	stack<int> s;

	int score = 0;
	int op1 = 0;
	int op2 = 0;

	int op = 0;

	cin >> str;

	for (int i = 0; i < str.length(); i++) {
		switch (str[i] - '0') {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			score = str[i] - '0';		

			if (score == 1 && str[i + 1] == '0') {
				score = 10;
				i++;
			}
			break;
		}

		switch (str[i]) {
		case 'S':
			s.push(score);
			break;
		case 'D':
			s.push(score * score);
			break;
		case 'T':
			s.push(score * score * score);
			break;

		case '*':
			op1 = s.top();
			s.pop();

			if (!s.empty()) {
				op2 = s.top();
				s.pop();
				op2 = op2 * 2;
				s.push(op2);
			}
			op1 = op1 * 2;

			s.push(op1);
			break;
		case '#':
			op = s.top();
			s.pop();

			op *= -1;
			s.push(op);
			break;
		}
	}

	int answer = 0;
	while (!s.empty()) {
		answer += s.top();
		s.pop();
	}
	printf("%d\n", answer);

	return 0;
}
