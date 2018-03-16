/******************************************************************
** 文件名 :贪吃蛇
** Copyright (c) 2015 ********* kucece
** 创建人:kucece
** 日 期:2015.11.27
** 修改人:kucuce
** 日 期:
** 描 述:
**
** 版 本:0.1
**--------------------------------------------------------------------------
*/
#include <graphics.h>
#include <conio.h>
#include <queue>
#include <stdlib.h>
#include <stdio.h>
#include <iostream>
#include <time.h>
#include <math.h>
#include <string.h>


using namespace std;

#define R 10
#define C 10

typedef struct node
{
	int x;
	int y;
}snake;

int delay(long int delay);//延时函数
void inin();
void draw(int *arr);//成像 
void inin()
{	
	int max_x = R*20+60;
	int max_y = C*20+60;;
	initgraph(max_x, max_y);
}
void draw(int arr[][C+2])
{
	int r = 10;
	
	for(int i = 0 ; i < R + 2 ; i++)
	{
		for(int j = 0 ; j < C + 2 ; j++)
		{
			int x = -5+(i)*20;
			int y = -5+(j)*20;
			if(arr[i][j] == 3)
			{
				setlinecolor(YELLOW);
				setfillcolor(BLACK);	
			}
			else if(arr[i][j] == 2)
			{				
				setlinecolor(YELLOW);
				setfillcolor(RED);
			}
			else if(arr[i][j] == 1)
			{
				
				setlinecolor(YELLOW);
				setfillcolor(0xFFCCFF);
			}
			else if(arr[i][j] == 0)
			{	
				setlinecolor(YELLOW);
				setfillcolor(GREEN);
			}
			fillcircle(20+y, 20+x, r);
		}
	}
	moveto(20-10,20-12);
	outtext("ā");
	moveto(20-10,20+(C+1)*20-12);
	outtext("á");
	moveto(20+(R+1)*20-10,20-12);
	outtext("ǎ");
	moveto(20+(R+1)*20-10,20+(C+1)*20-12);
	outtext("à");
} 

int delay(long int time)
{
	clock_t begin,end;
	begin=clock();
	do
	{
		end=clock();
	}while(time > (end - begin));
    return 0; 
}

int main()
{
	//参数 
	int arr[R+2][C+2];
	queue<snake> Qu;
	char key='d',prekey='d';
	int prespeed=1000,speed;
	int count=0;
	memset(arr,0,sizeof(int)*(R+2)*(C+2));
	//set wall
	for(int w_i = 0 ; w_i < R + 2 ; w_i++)
	{
		for(int w_j = 0 ; w_j < C + 2 ; w_j++)
		{
			if(w_i==0 || w_i==R+1|| w_j==0 || w_j==C+1)
			{
				arr[w_i][w_j] = 3;
			}
		}
	}
	inin();
	snake t_snake;
	t_snake.x = 1;
	t_snake.y = 1;
	arr[t_snake.x][t_snake.y]=1;
	Qu.push(t_snake);
	arr[rand() % R + 2][rand() % C + 2]=2;	
	while(!Qu.empty() || key != 27)
	{
		if( (double)count/R *C > 0.5)
		{
			speed = prespeed/2;
		}
		else
		{
			speed = prespeed - (int)((double)count/R*C*prespeed);
		}
		delay(speed);
		// 获取按键
		if (kbhit())
			key = getch();
		t_snake = Qu.back();
		if(key == 'd' && prekey != 'a')	
		{
			t_snake.y+=1;
		}else if(key == 's' && prekey != 'w')
		{
			t_snake.x+=1;
		}else if(key == 'a' && prekey != 'd')
		{
			t_snake.y-=1;
		}else if(key == 'w' && prekey != 's')
		{
			t_snake.x-=1;
		}
		else
		{
			
			if(prekey == 'd' )	
			{
				t_snake.y+=1;
			}else if(prekey == 's')
			{
				t_snake.x+=1;
			}else if(prekey == 'a')
			{
				t_snake.y-=1;
			}else if(prekey == 'w')
			{
				t_snake.x-=1;
			}
		}
		prekey=key;
		if(arr[t_snake.x][t_snake.y]==1 || arr[t_snake.x][t_snake.y]==3)
		{
			cout << "Game Over!!!" << endl;
			exit(0);
		}else 
		{	
			if(arr[t_snake.x][t_snake.y]==0)
			{
				snake temp= Qu.front();
				arr[temp.x][temp.y]=0;
				Qu.pop();
			}
			else
			{
				int flag = 0;
				do
				{
					srand(time(NULL));
					int x = rand() % R + 2;
					int y = rand() % C + 2;
					if(arr[x][y]==0)
					{
						arr[x][y]=2;
						flag = 1;	
					}						
				}while(flag==0);
				count++;
			}
			arr[t_snake.x][t_snake.y]=1;				
			Qu.push(t_snake);
		}
		//show sence1
		draw(arr);
		//show sence1
		system("cls");
		for(int i = 0 ; i < R + 2 ; i++)
		{
			for(int j = 0 ; j < C + 2 ; j++)
			{
				cout<<arr[i][j];
			}
			cout << endl;
		}
	}
	closegraph(); 
		
	return 0;
}


