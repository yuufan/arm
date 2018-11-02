#include"reg51.h"
#define uchar unsigned char
#define uint unsigned int
#define CLOCK 30    //������ֵ����λΪ��
sbit s=P3^0;
sbit b=P3^1;
sbit l1=P3^3;
sbit l2=P3^4;
sbit l3=P3^2;
sbit led=P3^5;    //�˿��ض���
uint num,t=0,t1=0,shi,ge,flag=0;
uchar code duma[]={0x3f,0x06,0x5b,0x4f,0x66,0x6d,0x7d,0x07,0x7f,0x6f,0x40};   //���������������
int delay(uint a)                //��ʱ����
{
	int c;
	for(a;a>0;a--)
	for(c=110;c>0;c--);
}
int main()     
{
	
	TMOD = 0x11;
    TH0 = (65536-45872)/256;
    TL0 = (65536-45872)%256;   //��ʱ���ж�0/1�ֱ��ʼ��
    EA = 1;                    //����ģʽ����1
    ET0 = 1;
    TR0 = 1;
	  TH1 = 0xdc;
    TL1 = 0xdc;
    EA = 1;
    ET1 = 1;
    TR1 = 1;            
	                     
	while(1)
	{
		
		
		if(s == 0)     //��ͣ�������1
    {
      flag=~flag;
      while(s == 0);   //��ⰴ���Ƿ�ſ����ſ����������ִ��
    }
			
		if(b == 0)    //��հ������
    {
      t=0;
			
      while(b == 0);
    }
		
	}
}
	
	
	

void time() interrupt 1       //��ʱ���ж�0����ʱ�ж�
{
	TH0 = (65536-45872)/256;
    TL0 = (65536-45872)%256;   //�ж����أ�����Ϊ50ms
	num=num+1;
	
	if(num==20)     //20���ж�Ϊ1s
	{
		num=0;
		if(t<CLOCK){    //���Ӽ��
		if(flag==0)     //�ж϶�ʱ���Ƿ���ͣ�����н���t++����ͣt1++
		{
		t++;
			t1=0;        //ֻ��¼������ͣʱ�䣬ÿ�μ�������ͣʱ�����������
		}
		else {t1++;}
		}
		else led=~led;    //���Ӽ���ʱ����ledȡ��
		
		
	}
}
void display() interrupt 3    //��ʾ�жϣ�ԭ��Ϊɨ����ʾ
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