#include<stdio.h>
#include<iostream>
#include<stdlib.h>
#include<string.h>
#include<time.h>
#include <conio.h>
using namespace std;

int main(){
	int arr[5][4];
	cout<<"��Ȱ׿��"<<endl;
	cout<<"��Ϸ˵��:"<<endl;
	cout<<"����a��s��d��f�����ҷֱ��Ӧ����1������2������3������4"<<endl;
	cout<<"����0����׿飬����1����ǰ׿顣"<<endl;
	cout<<"��ʼ";
renew:
	cout << "��Ȱ׿��" << endl;
	system("pause");
	system("cls");
	srand(time(NULL));
	//init
	memset(arr,0,sizeof(int)*5*4);
	cout << "������0" << endl;
	cout << "��ǰ�ٶȣ�ÿ��0.00����"<< endl;
	for(int i = 0 ; i < 5 ; i++)
	{
		arr[i][rand()%4] = 1;
	}
	for(int j = 0 ; j < 5 ; j++)
	{
		for(int k = 0 ; k < 4 ; k++)
		{
			cout<<arr[j][k];
		}
		cout << endl;
	}
	char key;
	int key_num;
	int flag_up=0,flag_change=0;
	int now_pos=4;
	int count = 0;
	clock_t begin=clock();
	while(1)
	{

		if (kbhit())
		{
			char t = getch();
			if(t == 'a' ||t == 's' ||t == 'd' ||t == 'f'||t == 'A' ||t == 'S' ||t == 'D' ||t == 'F'  )
			{
				key = t;
				flag_up = 1;
			}
		}
		if(flag_up)
		{
			flag_up=0;
			switch(key)
			{
				case 'a': 
				case 'A': key_num = 0;break;
				case 's': 
				case 'S': key_num = 1;break;
				case 'd': 
				case 'D': key_num = 2;break;
				case 'f':
				case 'F':  key_num = 3;break;
			}
			if(arr[now_pos][key_num]==1)
			{
				memset(&arr[now_pos],0,sizeof(int)*4);
				arr[now_pos][rand()%4] = 1;
				now_pos = (now_pos + 5 -1)%5;
				count++;
				flag_change=1;
			}
			else
			{
				cout << "Game Over" << endl;
				cout << "���裬�˳���رմ���" << endl << "����һ��";
				goto renew;
			}

		}
		if(flag_change == 1)
		{
			flag_change = 0;
			system("cls");
			cout << "������" << count << endl;
			double time = clock()/1000.0;
			//cout << "��ǰ�ٶȣ�" << (double)count/time << endl;
			printf("��ǰ�ٶȣ�ÿ��%.2lf��\n",(double)count/time);
			for(int j = 0 ; j < 5 ; j++)
			{
				for(int k = 0 ; k < 4 ; k++)
				{
					cout<<arr[(now_pos + 1 + j)%5][k];
				}
				cout << endl;
			}
		}
			


	}
	return 0;
}
