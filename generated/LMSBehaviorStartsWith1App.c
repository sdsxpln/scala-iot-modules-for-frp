/*****************************************
  Emitting C Generated Code                  
*******************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
bool x0;
int x1;
bool x29;
int x30;
int x41;
void x28(uint8_t* x2,int x3) {
int x6 = 0;
int x7 = 0;
int x5 = x3;
uint8_t* x4 = x2;
for (;;) {
int x8 = x7;
bool x9 = x8 < x5;
if (!x9) break;
int x11 = x7;
uint8_t x12 = x4[x11];
int x16 = x6;
int x13 = (int ) x12;
int x14 = x11 * 8;
int x15 = x13 << x14;
int x17 = x16 + x15;
x6 = x17;
int x19 = x11 + 1;
x7 = x19;
}
x0 = true;
int x24 = x6;
int x25 = (int ) x24;
x1 = x25;
};
void x40() {
bool x31 = x0;
if (x31) {
x29 = true;
int x33 = x1;
x30 = 5;
} else {
x29 = false;
}
};
void x48() {
bool x42 = x29;
if (x42) {
int x43 = x30;
x41 = x43;
} else {
}
};
void top1(uint8_t* x49,int x50) {
uint8_t* x51 = x49;
int x52 = x50;
x28(x51,x52);
x40();
x48();
};
void init_module1() {
x41 = 1;
};
/*****************************************
  End of C Generated Code                  
*******************************************/
