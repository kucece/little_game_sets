import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LianLianKan implements ActionListener {
	JFrame mainFrame; // 主面板
	Container thisContainer;
	JPanel centerPanel, southPanel, northPanel; // 子面板
	JButton diamondsButton[][] = new JButton[6][5];// 游戏按钮数组
	JButton exitButton, resetButton, remindButton, newlyButton; // 退出，重列，重新开始按钮
	int remindx0, remindy0, remindx, remindy, YNremind;
	JLabel NumLable = new JLabel("分数："); // 分数标签
	JLabel fractionLable = new JLabel("0"); // 分数标签
	JLabel RestLable = new JLabel("剩余步数："); // 时间标签
	JLabel RestNumLable = new JLabel("90");// 选择标签
	JButton firstButton, secondButton; // 分别记录两次被选中的按钮
	int grid[][] = new int[8][7];// 储存游戏按钮位置
	static boolean pressInformation = false; // 判断是否有按钮被选中
	int x0 = 0, y0 = 0, x = 0, y = 0, fristMsg = 0, secondMsg = 0, validateLV; // 游戏按钮的位置坐标
	int i, j, k, n;// 消除方法控制
	int YNReload = 0;// 判断是否需要重列

	public void init() { // 开始方法
		mainFrame = new JFrame("JKJ连连看"); // 字符
		/** 创建一个新的、初始不可见的、标题名为JKJ连连看的 Frame。 */
		thisContainer = mainFrame.getContentPane();
		/** [???] 返回此窗体的 contentPane 对象 */
		thisContainer.setLayout(new BorderLayout());
		/** 设置此容器的布局管理器。BorderLayout即容器的边框布局 */
		centerPanel = new JPanel();
		southPanel = new JPanel();
		northPanel = new JPanel();
		/** JPanel为一般轻量级容器 */
		thisContainer.add(centerPanel, "Center");
		thisContainer.add(southPanel, "South");
		thisContainer.add(northPanel, "North");
		/** 将指定的组件添加到此容器的尾部。 */
		centerPanel.setLayout(new GridLayout(6, 5));
		/** GridLayout 类是一个布局处理器，它以矩形网格形式对容器的组件进行布置。容器被分成大小相等的矩形，一个矩形中放置一个组件。 */
		for (int cols = 0; cols < 6; cols++) {
			for (int rows = 0; rows < 5; rows++) {
				diamondsButton[cols][rows] = new JButton(
						String.valueOf(grid[cols + 1][rows + 1]));
				/** 创建一个带文本的按钮。 */
				diamondsButton[cols][rows].addActionListener(this);//
				centerPanel.add(diamondsButton[cols][rows]);
			}
		}
		exitButton = new JButton("退出"); // 退出按钮
		exitButton.addActionListener(this);
		resetButton = new JButton("重列"); // 重列按钮
		resetButton.addActionListener(this);
		remindButton = new JButton("提醒"); // 提醒按钮
		remindButton.addActionListener(this);
		newlyButton = new JButton("再来一局"); // 再来一局按钮
		newlyButton.addActionListener(this);
		southPanel.add(exitButton);
		southPanel.add(resetButton);
		southPanel.add(remindButton);
		southPanel.add(newlyButton);
		northPanel.add(NumLable);
		fractionLable.setText(String.valueOf(Integer.parseInt(fractionLable
				.getText())));
		northPanel.add(fractionLable);
		northPanel.add(RestLable);
		RestNumLable.setText(String.valueOf(Integer.parseInt(RestNumLable
				.getText())));
		northPanel.add(RestNumLable);
		mainFrame.setBounds(280, 100, 500, 450);
		mainFrame.setVisible(true);
	}

	private void randomBuild() // 产生随机数
	{
		int randoms, cols, rows;
		for (int twins = 1; twins <= 15; twins++) {
			randoms = (int) (Math.random() * 25 + 1);
			for (int alike = 1; alike <= 2; alike++) {
				cols = (int) (Math.random() * 6 + 1);
				rows = (int) (Math.random() * 5 + 1);
				while (grid[cols][rows] != 0) {
					cols = (int) (Math.random() * 6 + 1);
					rows = (int) (Math.random() * 5 + 1);
				}
				this.grid[cols][rows] = randoms;
			}
		}
	}

	private void fraction()// 计数
	{
		fractionLable.setText(String.valueOf(Integer.parseInt(fractionLable
				.getText()) + 100));
	}

	private void newly() {
		int grid[][] = new int[8][7];
		this.grid = grid;
		randomBuild();
		mainFrame.setVisible(false);
		pressInformation = false;
		init();
		fractionLable.setText("0");
		RestNumLable.setText("90");
	}

	private void reload() {
		int save[] = new int[30];
		int n = 0, cols, rows;
		int grid[][] = new int[8][7];
		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j <= 5; j++) {
				if (this.grid[i][j] != 0) {
					save[n] = this.grid[i][j];
					n++;
				}
			}
		}
		n = n - 1;
		this.grid = grid;
		while (n >= 0) {
			cols = (int) (Math.random() * 6 + 1);
			rows = (int) (Math.random() * 5 + 1);
			while (grid[cols][rows] != 0) {
				cols = (int) (Math.random() * 6 + 1);
				rows = (int) (Math.random() * 5 + 1);
			}
			this.grid[cols][rows] = save[n];
			n--;
		}
		mainFrame.setVisible(false);
		pressInformation = false; // 这里一定要将按钮点击信息归为初始
		init();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (grid[i + 1][j + 1] == 0)
					diamondsButton[i][j].setVisible(false);
			}
		}
	}

	private void check() {
		int save[][] = new int[30][2];
		int n = 0;
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 5; j++) {
				if (this.grid[i][j] != 0) {
					save[n][0] = i;
					save[n][1] = j;
					n++;
				}
			}
		}
		if (n == 0) {
			JOptionPane.showMessageDialog(null, "清除成功，游戏结束。再来一局吧！！！");
			newly();
			return;
		}
		YNReload = 0;
		for (int i = 0; i < n; i++) {
			int tf = 0;
			for (int j = i + 1; j < n; j++) {
				if (this.grid[save[i][0]][save[i][1]] == this.grid[save[j][0]][save[j][1]]) {
					if (xiao(save[i][0], save[i][1], save[j][0], save[j][1], 0) == 0) {
						remindx0 = save[i][0];
						remindy0 = save[i][1];
						remindx = save[j][0];
						remindy = save[j][1];
						YNremind = 1;
						tf = 1;
						System.out.println(remindx0 + "," + "," + remindy0);
						System.out.println(remindx + "," + "," + remindy);
						break;
					}
				}
			}
			if (tf == 1)
				break;
		}
		if (YNReload == 0) {
			JOptionPane.showMessageDialog(null, "无解，重列吧！！！");
			reload();
		}
	}

	private void Display() {
		diamondsButton[remindx0 - 1][remindy0 - 1].setBackground(Color.red);
		diamondsButton[remindx - 1][remindy - 1].setBackground(Color.red);
	}

	private void estimateEven(int placeX, int placeY, JButton bz) // 判断所单击的两次是否相同
	{
		if (pressInformation == false) {
			x = placeX;
			y = placeY;
			secondMsg = grid[x][y];
			secondButton = bz;
			pressInformation = true;
		} else {
			x0 = x;
			y0 = y;
			fristMsg = secondMsg;
			firstButton = secondButton;
			x = placeX;
			y = placeY;
			secondMsg = grid[x][y];
			secondButton = bz;
			if (fristMsg == secondMsg && secondButton != firstButton) {
				xiao(x0, y0, x, y, 1);
			}
		}
	}

	private int xiao(int x0, int y0, int x, int y, int state) { // 相同的情况下能不能消去。
		if ((x0 == x && (y0 == y + 1 || y0 == y - 1))
				|| ((x0 == x + 1 || x0 == x - 1) && (y0 == y))) { // 判断是否相邻
			return remove(state);
		} else {
			for (j = 0; j < 7; j++) {
				if (grid[x0][j] == 0) { // 判断第一个按钮同行哪个按钮为空
					if (y > j) { // 如果第二个按钮的Y坐标大于空按钮的Y坐标说明第一按钮在第二按钮左边
						for (i = y - 1; i >= j; i--) { // 判断第二按钮左侧直到第一按钮中间有没有按钮
							if (grid[x][i] != 0) {
								k = 0;
								break;
							} else {
								k = 1;
							} // K=1说明通过了第一次验证
						}
						if (k == 1) {
							linePassOne(x0, y0, x, y);
						}
					}
					if (y < j) { // 如果第二个按钮的Y坐标小于空按钮的Y坐标说明第一按钮在第二按钮右边
						for (i = y + 1; i <= j; i++) { // 判断第二按钮左侧直到第一按钮中间有没有按钮
							if (grid[x][i] != 0) {
								k = 0;
								break;
							} else {
								k = 1;
							}
						}
						if (k == 1) {
							linePassOne(x0, y0, x, y);
						}
					}
					if (y == j) {
						linePassOne(x0, y0, x, y);
					}
				}
				if (k == 2) {
					if (x0 == x) {
						return remove(state);
					}
					if (x0 < x) {
						for (n = x0; n <= x - 1; n++) {
							if (grid[n][j] != 0) {
								k = 0;
								break;
							}
							if (grid[n][j] == 0 && n == x - 1) {
								return remove(state);
							}
						}
					}
					if (x0 > x) {
						for (n = x0; n >= x + 1; n--) {
							if (grid[n][j] != 0) {
								k = 0;
								break;
							}
							if (grid[n][j] == 0 && n == x + 1) {
								return remove(state);
							}
						}
					}
				}
			}
			for (i = 0; i < 8; i++) { // 列
				if (grid[i][y0] == 0) {
					if (x > i) {
						for (j = x - 1; j >= i; j--) {
							if (grid[j][y] != 0) {
								k = 0;
								break;
							} else {
								k = 1;
							}
						}
						if (k == 1) {
							rowPassOne(x0, y0, x, y);
						}
					}
					if (x < i) {
						for (j = x + 1; j <= i; j++) {
							if (grid[j][y] != 0) {
								k = 0;
								break;
							} else {
								k = 1;
							}
						}
						if (k == 1) {
							rowPassOne(x0, y0, x, y);
						}
					}
					if (x == i) {
						rowPassOne(x0, y0, x, y);
					}
				}
				if (k == 2) {
					if (y0 == y) {
						return remove(state);
					}
					if (y0 < y) {
						for (n = y0; n <= y - 1; n++) {
							if (grid[i][n] != 0) {
								k = 0;
								break;
							}
							if (grid[i][n] == 0 && n == y - 1) {
								return remove(state);
							}
						}
					}
					if (y0 > y) {
						for (n = y0; n >= y + 1; n--) {
							if (grid[i][n] != 0) {
								k = 0;
								break;
							}
							if (grid[i][n] == 0 && n == y + 1) {
								return remove(state);
							}
						}
					}
				}
			}
		}
		return -1;
	}

	private void linePassOne(int x0, int y0, int x, int y)// 判断行是否可以消除
	{
		if (y0 > j) { // 第一按钮同行空按钮在左边
			for (i = y0 - 1; i >= j; i--) { // 判断第一按钮同左侧空按钮之间有没按钮
				if (grid[x0][i] != 0) {
					k = 0;
					break;
				} else {
					k = 2;
				} // K=2说明通过了第二次验证
			}
		}
		if (y0 < j) { // 第一按钮同行空按钮在与第二按钮之间
			for (i = y0 + 1; i <= j; i++) {
				if (grid[x0][i] != 0) {
					k = 0;
					break;
				} else {
					k = 2;
				}
			}
		}
	}

	private void rowPassOne(int x0, int y0, int x, int y) { // 判断列是否可以消除
		if (x0 > i) {
			for (j = x0 - 1; j >= i; j--) {
				if (grid[j][y0] != 0) {
					k = 0;
					break;
				} else {
					k = 2;
				}
			}
		}
		if (x0 < i) {
			for (j = x0 + 1; j <= i; j++) {
				if (grid[j][y0] != 0) {
					k = 0;
					break;
				} else {
					k = 2;
				}
			}
		}
	}

	private int remove(int state)// 消除所选按钮
	{
		if (state == 1) {
			firstButton.setVisible(false); // 最大距离只能为2行1列
			secondButton.setVisible(false);
			fraction();
			pressInformation = false;
			k = 0;
			grid[x0][y0] = 0;
			grid[x][y] = 0;
			return 1;
		} else {
			YNReload++;
			return 0;
		}
	}

	public void actionPerformed(ActionEvent e) // 监听事件
	{

		if (e.getSource() == exitButton) {
			System.exit(0);
		}
		if (e.getSource() == resetButton) {
			reload();
			RestNumLable.setText(String.valueOf(Integer.parseInt(RestNumLable
					.getText()) - 9));
		}
		RestNumLable.setText(String.valueOf(Integer.parseInt(RestNumLable
				.getText()) - 1));
		if (Integer.parseInt(RestNumLable.getText()) <= 0) {
			JOptionPane.showMessageDialog(null, "游戏步数归零，游戏结束。再来一局吧！！！");
			newly();
		}
		if (e.getSource() == newlyButton) {
			newly();
		}
		for (int cols = 0; cols < 6; cols++) {
			for (int rows = 0; rows < 5; rows++) {
				if (e.getSource() == diamondsButton[cols][rows])
					estimateEven(cols + 1, rows + 1, diamondsButton[cols][rows]);
			}
		}
		check();
		if (e.getSource() == remindButton) {
			RestNumLable.setText(String.valueOf(Integer.parseInt(RestNumLable
					.getText()) - 3));
			Display();
		}
	}

	public static void main(String[] args) { // 游戏入口
		LianLianKan llk = new LianLianKan();
		llk.randomBuild();
		llk.init();
	}
}
