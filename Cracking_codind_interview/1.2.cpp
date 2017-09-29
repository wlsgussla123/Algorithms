#include <stdio.h>
#include <iostream>
#include <string>

using namespace std;

string reverse(string str) {
	string result;

	int j = str.length() - 1;
	char temp;

	for (int i = 0; i < str.length() / 2; i++) {
		temp = str[i];
		str[i] = str[j];
		str[j] = temp;

		j--;
	}
	result = str;

	return result;
}


int main(void) {
	string str;

	getline(cin, str);
	cout << reverse(str) << endl;

	return 0;
}
