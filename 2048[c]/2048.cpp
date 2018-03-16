#include<stdio.h>
#include<iostream>
#include<time.h>
#include<string.h>
#include<stdlib.h>
#include<conio.h>
#include <graphics.h> 
using namespace std;

class game_2048
{
	public:
		game_2048()
		{
			arr[4][4];
			memset(arr,0,sizeof(int)*4*4);
			num_rand();
			num_rand();
			count=0;
			score=0;
			//initgraph(640, 480,SHOWCONSOLE); 
			initgraph(550, 400); 
			game_print();
		}

		void num_rand()
		{
			srand(time(NULL));
			int num = rand()%16;
			int ok_set=0;
			while(!ok_set)
			{
				if(arr[num/4][num%4]==0)
				{
					arr[num/4][num%4]=2;
					ok_set = 1;
				}
				else
				{
					num++;
				}
			}			
		}
		void a_word()
		{
			ij_change();
			ii_change();
			s_word();
			ii_change();
			ij_change();
		}
		void s_word()
		{
			if(word())
			{
				num_rand();
			}
			else
			{
				//check();
			}
		}
		void d_word()
		{
			ij_change();	
			s_word();
			ij_change();
		}
		void w_word()
		{
			ii_change();
			s_word();
			ii_change();
		}
		int  count_num()
		{	
			int t=0;
			for(int i = 0 ; i < 40 ; i++)
			{
				if(arr[i/4][i%4]!=0) t++;
			}
			return t;
		}
		int word()
		{
			int mov = 0;
			int clr = 0;
			for(int i = 0 ; i < 4 ; i++)
			{
				int pos=3;
				int num_count=0;
				for(int j = 3 ; j >=0 ; j--)
				{
					if(arr[j][i]!=0)
					{
						int temp;
						if(pos!=j)
						{					
							temp = arr[j][i];
							arr[j][i] = arr[pos][i];
							arr[pos][i] = temp;
							mov = 1;
						}						
						pos--;
						num_count++;
					}
				}
				switch(num_count)
				{
				case 0:
				case 1:
					break;
				case 2:
					if(arr[3][i]==arr[2][i])
					{
						arr[3][i]=2 * arr[2][i];
						arr[2][i]=0;
						score+=arr[3][i];
						clr=1;
					}
					break;
				case 3:
					if(arr[3][i]==arr[2][i])
					{
						arr[3][i]=2 * arr[2][i];
						arr[2][i]=arr[1][i];
						arr[1][i]=0;
						score+=arr[3][i];
						clr=1;
					}
					else if(arr[2][i]==arr[1][i])
					{
						arr[2][i]=2 * arr[1][i];
						arr[1][i]=0;
						score+=arr[2][i];
						clr=1;
					}
					break;
				case 4:
					if(arr[3][i]==arr[2][i]&&arr[1][i]==arr[0][i])
					{
						arr[3][i]=2 * arr[2][i];
						arr[2][i]=0;
						score+=arr[3][i];
						arr[2][i]=2* arr[1][i];
						arr[1][i]=0;
						arr[0][i]=0;
						score+=arr[2][i];
						clr=1;
					}
					else if(arr[3][i]==arr[2][i])
					{
						arr[3][i]=2 * arr[2][i];
						arr[2][i]=arr[1][i];
						arr[1][i]=arr[0][i];
						arr[0][i]=0;
						score+=arr[3][i];						
						clr=1;
					}
					else if(arr[2][i]==arr[1][i])
					{
						arr[2][i]=2 * arr[1][i];
						arr[1][i]=arr[0][i];
						arr[0][i]=0;
						score+=arr[2][i];	
						clr=1;
					}
					else if(arr[1][i]==arr[0][i])
					{
						arr[1][i]=2 * arr[0][i];
						arr[0][i]=0;
						score+=arr[1][i];						
						clr=1;
					}
					break;
				}
			} 
			return mov||clr;
		}
		void game_print()
		{
		/*	system("cls");
			for(int i = 0 ; i < 4 ; i++)
			{
				for(int j = 0 ; j < 4 ; j++)
				{
					cout << arr[i][j] << "\t";
				}
				cout << endl;
			}
		*/
			
			for(int i = 0 ; i < 4 ; i++)
			{
				for(int j = 0 ; j < 4 ; j++)
				{
					char str[10];
					int index;
					switch(arr[i][j])
					{
					case 0: index=0;break;
					case 2: index=1;break;
					case 4: index=2;break;
					case 8: index=3;break;
					case 16: index=4;break;
					case 32: index=5;break;
					case 64: index=6;break;
					case 128: index=7;break;
					case 256: index=8;break;
					case 512: index=9;break;
					case 1024: index=10;break;
					case 2048: index=11;break;
					case 4096: index=12;break;
					case 8192: index=13;break;
					case 16384: index=14;break;
					default: index=15;
					}
					setfillcolor(RGB(255*index/15, 0, 255*index/15.0));
					fillrectangle( j*80+0,i*80+0,j*80+80,i*80+80);
					moveto(j*80+40,i*80+40);
					if(arr[i][j]!=0)
					{
						sprintf(str,"%d",arr[i][j]);
						outtext(str);
					}
				}
			}
		}
		
		int score;
	private:
		int arr[4][4];
		int count;
		void ij_change()
		{
			for(int i = 0 ; i < 4 ; i++)
			{
				for(int j = 0 ; j < i ; j++)
				{
					int temp;
					temp = arr[i][j];
					arr[i][j] = arr[j][i];
					arr[j][i] = temp;
				}
			}
		}
		void ii_change()
		{
			for(int i = 0 ; i < 4/2 ; i++)
			{
				for(int j = 0 ; j < 4 ; j++)
				{
					int temp;
					temp = arr[i][j];
					arr[i][j] = arr[3-i][j];
					arr[3-i][j] = temp;
				}
			}
		}

};


int main()
{
	game_2048 demo;
	char str[10];
	moveto(4*80+40,0*80+40);
	sprintf(str,"步数:0");
	outtext(str);
	moveto(4*80+40,1*80+40);
	sprintf(str,"分数：%d",demo.score);
	outtext(str);
	moveto(4*80+40,2*80+40);
	outtext("上下左右wsad，esc退出");
	char key; 
	int flag_up;
	int count=0;
	while(1)
	{
		
		if (kbhit())
		{
			char t = getch();
			if(t == 'a' ||t == 's' ||t == 'd' ||t == 'w'||t == 'A' ||t == 'S' ||t == 'D' ||t == 'W' )
			{
				key = t;
				flag_up = 1;
			}
			else if(t == 27)
			{
				exit(0);
			}
			if(flag_up)
			{
				flag_up = 0;
				switch(key)
				{
					case 'a': 
					case 'A': demo.a_word();break;
					case 's': 
					case 'S': demo.s_word();break;
					case 'd': 
					case 'D': demo.d_word();break;
					case 'w':
					case 'W': demo.w_word();break;
				}
				char str[10];
				demo.game_print();
				cout <<"步数："<<count++ << endl;
				cout << "分数:"<<demo.score << endl;
				moveto(4*80+40,0*80+40);
				sprintf(str,"步数：%d",count);
				outtext(str);
				moveto(4*80+40,1*80+40);
				sprintf(str,"分数：%d",demo.score);
				outtext(str);
				moveto(4*80+40,2*80+40);
				outtext("上下左右wsad，esc退出");
			}
			
		}
	}
	return 0;
}
