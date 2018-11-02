#include"reg51.h"
#include<intrins.h>
#define uint unsigned int
#define uchar unsigned char

int delay(uint a)
{
	int b;
	for(a;a>0;a--)
	for(b=110;b>0;b--);
	return 0;
}

int main()
{
	P2=0x00;
	P2=0x01;
	while(1)
	{
		P2=_cror_(P2,1);
		delay(1000);
	}
	
}