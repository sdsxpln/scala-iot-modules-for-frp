/*****************************************
  Emitting C Generated Code                  
*******************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <math.h>
#include <stdbool.h>

bool x1;
int x2;
bool x30;
int x31;
int x42;
int x50;
void x60() {
int x51 = x50;
bool x52 = x51 == 0;
if (x52) {
x42 = 1;
x50 = 1;
} else {
}
x30 = false;
x1 = false;
};
void x29(uint8_t* x3,int x4) {
int x7 = 0;
int x8 = 0;
int x6 = x4;
uint8_t* x5 = x3;
for (;;) {
int x9 = x8;
bool x10 = x9 < x6;
if (!x10) break;
int x12 = x8;
uint8_t x13 = x5[x12];
int x17 = x7;
int x14 = (int ) x13;
int x15 = x12 * 8;
int x16 = x14 << x15;
int x18 = x17 + x16;
x7 = x18;
int x20 = x12 + 1;
x8 = x20;
}
x1 = true;
int x25 = x7;
int x26 = (int ) x25;
x2 = x26;
};
void x41() {
bool x32 = x1;
if (x32) {
x30 = true;
int x34 = x2;
x31 = 5;
} else {
x30 = false;
}
};
void x49() {
bool x43 = x30;
if (x43) {
int x44 = x31;
x42 = x44;
} else {
}
};
void top1(uint8_t* x61,int x62) {
x60();
uint8_t* x63 = x61;
int x64 = x62;
x29(x63,x64);
x41();
x49();
};
/*****************************************
  End of C Generated Code                  
*******************************************/
