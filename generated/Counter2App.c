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
bool x59;
int x60;
bool x76;
int x77;
bool x93;
int x94;
bool x126;
int x127;
int x138;
void x146() {
int x139 = x138;
bool x140 = x139 == 0;
if (x140) {
x94 = 0;
x138 = 1;
} else {
}
};
void x154() {
x1 = false;
x126 = false;
x30 = false;
x59 = false;
x76 = false;
x93 = false;
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
void x75() {
bool x61 = x1;
if (x61) {
int x62 = x2;
bool x63 = x62 < 10;
if (x63) {
x59 = true;
x60 = x62;
} else {
x59 = false;
}
} else {
x59 = false;
}
};
void x125() {
bool x95 = x59;
bool x96 = x76;
bool x97 = x95 && x96;
if (x97) {
int x98 = x60;
int x99 = x77;
int x100 = x94;
int x101 = x100 + x98;
int x102 = x101 - x99;
x94 = x102;
} else {
if (x95) {
x93 = true;
int x106 = x60;
int x107 = x94;
int x108 = x107 + x106;
x94 = x108;
} else {
if (x96) {
x93 = true;
int x112 = x77;
int x113 = x94;
int x114 = x113 - x112;
x94 = x114;
} else {
x93 = false;
}
}
}
};
void x137() {
bool x128 = x93;
if (x128) {
x126 = true;
int x130 = x94;
x127 = x130;
} else {
x126 = false;
}
};
void x174(uint8_t* x167,int x168) {
uint8_t* x169 = x167;
uint8_t x171 = *x169;
printf("%u\n",x171);
};
void x180() {
bool x166 = x126;
if (x166) {
int x175 = x127;
x174((uint8_t*)&x175, sizeof(x175));
} else {
}
};
void x183(uint8_t* x155,int x156) { //top1
x146();
x154();
uint8_t* x157 = x155;
int x158 = x156;
x29(x157,x158);
x75();
x125();
x137();
x180();
};
void x58(uint8_t* x32,int x33) {
int x36 = 0;
int x37 = 0;
int x35 = x33;
uint8_t* x34 = x32;
for (;;) {
int x38 = x37;
bool x39 = x38 < x35;
if (!x39) break;
int x41 = x37;
uint8_t x42 = x34[x41];
int x46 = x36;
int x43 = (int ) x42;
int x44 = x41 * 8;
int x45 = x43 << x44;
int x47 = x46 + x45;
x36 = x47;
int x49 = x41 + 1;
x37 = x49;
}
x30 = true;
int x54 = x36;
int x55 = (int ) x54;
x31 = x55;
};
void x92() {
bool x78 = x30;
if (x78) {
int x79 = x31;
bool x80 = x79 < 10;
if (x80) {
x76 = true;
x77 = x79;
} else {
x76 = false;
}
} else {
x76 = false;
}
};
void x198(uint8_t* x185,int x186) { //top2
x146();
x154();
uint8_t* x187 = x185;
int x188 = x186;
x58(x187,x188);
x92();
x125();
x137();
x180();
};
/*****************************************
  End of C Generated Code                  
*******************************************/
