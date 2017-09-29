/*
  
*/

#include <stdio.h>
#include <iostream>
#include <string>

using namespace std;

bool check[26] = { false, };

bool checker(string str) {
	for (int i = 0; i < str.length(); i++) {
		int index = str[i] - 'a';

		check[index] = true;
	}

	for (int i = 0; i < 26; i++) {
		if (!check[i])
			return false;
	}

	return true;
}

int main(void) {
	string str;

	getline(cin, str);

	if (checker(str))
		printf("YES\n");
	else
		printf("NO\n");

	return 0;
}
