#include"reg51.h"
#define uchar unsigned char
#define uint unsigned int
#define CLOCK 30    //闹钟阈值，单位为秒
sbit s=P3^0;
sbit b=P3^1;
sbit l1=P3^3;
sbit l2=P3^4;
sbit l3=P3^2;
sbit led=P3^5;    //端口重定义
uint num,t=0,t1=0,shi,ge,flag=0;
uchar code duma[]={0x3f,0x06,0x5b,0x4f,0x66,0x6d,0x7d,0x07,0x7f,0x6f,0x40};   //共阴极数码管数组
int delay(uint a)                //延时函数
{
	int c;
	for(a;a>0;a--)
	for(c=110;c>0;c--);
}
int main()     
{
	
	TMOD = 0x11;
    TH0 = (65536-45872)/256;
    TL0 = (65536-45872)%256;   //定时器中断0/1分别初始化
    EA = 1;                    //工作模式都是1
    ET0 = 1;
    TR0 = 1;
	  TH1 = 0xdc;
    TL1 = 0xdc;
    EA = 1;
    ET1 = 1;
    TR1 = 1;            
	                     
	while(1)
	{
		
		
		if(s == 0)     //暂停按键侦测1
    {
      flag=~flag;
      while(s == 0);   //检测按键是否放开，放开后代码向下执行
    }
			
		if(b == 0)    //清空按键侦测
    {
      t=0;
			
      while(b == 0);
    }
		
	}
}
	
	
	

void time() interrupt 1       //定时器中断0，计时中断
{
	TH0 = (65536-45872)/256;
    TL0 = (65536-45872)%256;   //中断重载，周期为50ms
	num=num+1;
	
	if(num==20)     //20个中断为1s
	{
		num=0;
		if(t<CLOCK){    //闹钟检测
		if(flag==0)     //判断定时器是否暂停，运行进行t++，暂停t1++
		{
		t++;
			t1=0;        //只记录单次暂停时间，每次继续后暂停时间数码管清零
		}
		else {t1++;}
		}
		else led=~led;    //闹钟激活时进行led取反
		
		
	}
}
void display() interrupt 3    //显示中断，原理为扫描显示
{
	TH1 = 0xdc;
    TL1 = 0xdc;
	shi=t/10;
		ge=t%10;
		l1=0;
		P2=duma[shi];
		delay(5);
		l1=1;
		P2=0xff;
		l2=0;
		P2=duma[ge];
		delay(5);
		l2=1;
		P2=0xff;
	l3=0;
		P2=duma[t1%10];
		delay(5);
		l3=1;
		P2=0xff;
}