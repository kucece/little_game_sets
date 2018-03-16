import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LianLianKan implements ActionListener {
	JFrame mainFrame; // �����
	Container thisContainer;
	JPanel centerPanel, southPanel, northPanel; // �����
	JButton diamondsButton[][] = new JButton[6][5];// ��Ϸ��ť����
	JButton exitButton, resetButton, remindButton, newlyButton; // �˳������У����¿�ʼ��ť
	int remindx0, remindy0, remindx, remindy, YNremind;
	JLabel NumLable = new JLabel("������"); // ������ǩ
	JLabel fractionLable = new JLabel("0"); // ������ǩ
	JLabel RestLable = new JLabel("ʣ�ಽ����"); // ʱ���ǩ
	JLabel RestNumLable = new JLabel("90");// ѡ���ǩ
	JButton firstButton, secondButton; // �ֱ��¼���α�ѡ�еİ�ť
	int grid[][] = new int[8][7];// ������Ϸ��ťλ��
	static boolean pressInformation = false; // �ж��Ƿ��а�ť��ѡ��
	int x0 = 0, y0 = 0, x = 0, y = 0, fristMsg = 0, secondMsg = 0, validateLV; // ��Ϸ��ť��λ������
	int i, j, k, n;// ������������
	int YNReload = 0;// �ж��Ƿ���Ҫ����

	public void init() { // ��ʼ����
		mainFrame = new JFrame("JKJ������"); // �ַ�
		/** ����һ���µġ���ʼ���ɼ��ġ�������ΪJKJ�������� Frame�� */
		thisContainer = mainFrame.getContentPane();
		/** [???] ���ش˴���� contentPane ���� */
		thisContainer.setLayout(new BorderLayout());
		/** ���ô������Ĳ��ֹ�������BorderLayout�������ı߿򲼾� */
		centerPanel = new JPanel();
		southPanel = new JPanel();
		northPanel = new JPanel();
		/** JPanelΪһ������������ */
		thisContainer.add(centerPanel, "Center");
		thisContainer.add(southPanel, "South");
		thisContainer.add(northPanel, "North");
		/** ��ָ���������ӵ���������β���� */
		centerPanel.setLayout(new GridLayout(6, 5));
		/** GridLayout ����һ�����ִ����������Ծ���������ʽ��������������в��á��������ֳɴ�С��ȵľ��Σ�һ�������з���һ������� */
		for (int cols = 0; cols < 6; cols++) {
			for (int rows = 0; rows < 5; rows++) {
				diamondsButton[cols][rows] = new JButton(
						String.valueOf(grid[cols + 1][rows + 1]));
				/** ����һ�����ı��İ�ť�� */
				diamondsButton[cols][rows].addActionListener(this);//
				centerPanel.add(diamondsButton[cols][rows]);
			}
		}
		exitButton = new JButton("�˳�"); // �˳���ť
		exitButton.addActionListener(this);
		resetButton = new JButton("����"); // ���а�ť
		resetButton.addActionListener(this);
		remindButton = new JButton("����"); // ���Ѱ�ť
		remindButton.addActionListener(this);
		newlyButton = new JButton("����һ��"); // ����һ�ְ�ť
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

	private void randomBuild() // ���������
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

	private void fraction()// ����
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
		pressInformation = false; // ����һ��Ҫ����ť�����Ϣ��Ϊ��ʼ
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
			JOptionPane.showMessageDialog(null, "����ɹ�����Ϸ����������һ�ְɣ�����");
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
			JOptionPane.showMessageDialog(null, "�޽⣬���аɣ�����");
			reload();
		}
	}

	private void Display() {
		diamondsButton[remindx0 - 1][remindy0 - 1].setBackground(Color.red);
		diamondsButton[remindx - 1][remindy - 1].setBackground(Color.red);
	}

	private void estimateEven(int placeX, int placeY, JButton bz) // �ж��������������Ƿ���ͬ
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

	private int xiao(int x0, int y0, int x, int y, int state) { // ��ͬ��������ܲ�����ȥ��
		if ((x0 == x && (y0 == y + 1 || y0 == y - 1))
				|| ((x0 == x + 1 || x0 == x - 1) && (y0 == y))) { // �ж��Ƿ�����
			return remove(state);
		} else {
			for (j = 0; j < 7; j++) {
				if (grid[x0][j] == 0) { // �жϵ�һ����ťͬ���ĸ���ťΪ��
					if (y > j) { // ����ڶ�����ť��Y������ڿհ�ť��Y����˵����һ��ť�ڵڶ���ť���
						for (i = y - 1; i >= j; i--) { // �жϵڶ���ť���ֱ����һ��ť�м���û�а�ť
							if (grid[x][i] != 0) {
								k = 0;
								break;
							} else {
								k = 1;
							} // K=1˵��ͨ���˵�һ����֤
						}
						if (k == 1) {
							linePassOne(x0, y0, x, y);
						}
					}
					if (y < j) { // ����ڶ�����ť��Y����С�ڿհ�ť��Y����˵����һ��ť�ڵڶ���ť�ұ�
						for (i = y + 1; i <= j; i++) { // �жϵڶ���ť���ֱ����һ��ť�м���û�а�ť
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
			for (i = 0; i < 8; i++) { // ��
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

	private void linePassOne(int x0, int y0, int x, int y)// �ж����Ƿ��������
	{
		if (y0 > j) { // ��һ��ťͬ�пհ�ť�����
			for (i = y0 - 1; i >= j; i--) { // �жϵ�һ��ťͬ���հ�ť֮����û��ť
				if (grid[x0][i] != 0) {
					k = 0;
					break;
				} else {
					k = 2;
				} // K=2˵��ͨ���˵ڶ�����֤
			}
		}
		if (y0 < j) { // ��һ��ťͬ�пհ�ť����ڶ���ť֮��
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

	private void rowPassOne(int x0, int y0, int x, int y) { // �ж����Ƿ��������
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

	private int remove(int state)// ������ѡ��ť
	{
		if (state == 1) {
			firstButton.setVisible(false); // ������ֻ��Ϊ2��1��
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

	public void actionPerformed(ActionEvent e) // �����¼�
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
			JOptionPane.showMessageDialog(null, "��Ϸ�������㣬��Ϸ����������һ�ְɣ�����");
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

	public static void main(String[] args) { // ��Ϸ���
		LianLianKan llk = new LianLianKan();
		llk.randomBuild();
		llk.init();
	}
}
