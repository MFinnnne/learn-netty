//
// Created by MFine on 2021/11/7.
//

#include "test-jna.h"
#include <iostream>
#include <iomanip>
#include <cstring>

using namespace std;

void sayHello() {
  cout << "hello jna" << endl;
}

int testReturnInt() {
  return 1;
}

char *returnArray(double d[], int a[], const char *str, int length) {
  for (int i = 0; i < length; i++) {
	cout << "from c++ value is d" << a[i] << endl;
  }
  cout << "from C++ str value: " << str << endl;
  while (*str) {
	cout << *str++ << endl;
  }
  d[0] = 1.0;
  d[1] = 4.0;
  char *const return_str = "hello from c++";
  return return_str;
}

void sendString(const char* pszVal)
{
  // note: printfs called from C won't be
  // flushed to stdout until the Java
  // process completes
  printf("(C) '%s'\n", pszVal);
}

void getString(char** ppszVal)
{
  *ppszVal = (char*)malloc(sizeof(char) * 6);
  memset(*ppszVal, 0, sizeof(char) * 6);
  strcpy(*ppszVal, "hello");
}

void cleanup(char* pszVal)
{
  free(pszVal);
}