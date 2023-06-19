// 수정전

package ch13;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class E05 extends JFrame {
	private static final long serialVersionUID = 1L;
	JLabel title = new JLabel("Tic Tac Toe");
	JLabel disCurrentPlayer = new JLabel("레벨 1");
	int score1 = 0;
	int score2 = 0;
	JLabel scoreLabel = new JLabel(" | " + score1 + " : " + score2 + " |");

	JButton startNewGame = new JButton("Start New Game");

	JPanel titleBar = new JPanel();
	JPanel nineRoom = new JPanel();

	private final int START_PLAYER = 1;
	private boolean isGameEnd = false;

	E04 ttt = new E04(START_PLAYER);

	public E05() {
		this.setSize(400, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.drawWindow();
		this.setVisible(true);
	}

	public void drawWindow() {
		titleBar.add(title);
		titleBar.add(disCurrentPlayer);
		titleBar.add(scoreLabel);
		titleBar.add(startNewGame);

		disCurrentPlayer.setText("Player " + START_PLAYER);

		add(titleBar, BorderLayout.NORTH);

		nineRoom.setLayout(new GridLayout(3, 3));

		for (int i = 0; i < 9; i++) {
			JButton tempButton = new JButton("");

			tempButton.setFont(new Font("Impact", Font.PLAIN, 22));
			nineRoom.add(tempButton);
		}

		add(nineRoom, BorderLayout.CENTER);

		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JButton tempButton = (JButton) e.getComponent();
				if (isGameEnd) {
					return;
				}
				if (tempButton.getText().equals("o") || tempButton.getText().equals("x")) {
					return;
				}

				if (ttt.getCurrentPlayerNum() == 1) {
					tempButton.setText("o");
					disCurrentPlayer.setText("Player " + 2);
				} else {
					tempButton.setText("x");
					disCurrentPlayer.setText("Player " + 1);
				}

				ttt.changeTurn();

				int[][] ticArr = new int[3][3];

				for (int i = 0; i < ticArr.length; i++) {
					for (int j = 0; j < ticArr[i].length; j++) {
						String text = ((JButton) nineRoom.getComponent(j + i * 3)).getText();
						if (text.equals("o")) {
							ticArr[i][j] = 1;
						} else if (text.equals("x")) {
							ticArr[i][j] = 2;
						} else {
							ticArr[i][j] = 0;
						}
					}
				}

				int result = ttt.inputCurrentStage(ticArr);
				System.out.println("result: [" +result + "]");

				
				if (result == 1 || result == 2) {
					JOptionPane.showMessageDialog(nineRoom, "플레이어 " + result + "의 승리입니다.");
					if (result == 1) {
						score1++;
					} else {
						score2++;
					}
					scoreLabel.setText("|" + score1 + " : " + score2 + " |");
					isGameEnd = true;
					} else if (result == 99) {
					JOptionPane.showMessageDialog(nineRoom, "비겼습니다.");
					isGameEnd = true;
					}
					}
					};

					for (Component c : nineRoom.getComponents()) {
					c.addMouseListener(mouseAdapter);
					}

					startNewGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					ttt.resetGame(START_PLAYER);
					isGameEnd = false;
					for (Component c : nineRoom.getComponents()) {
					((JButton) c).setText("");
					}
					}
					});

					add(titleBar, BorderLayout.NORTH);
					add(nineRoom, BorderLayout.CENTER);
					}

					public static void main(String[] args) {
					SwingUtilities.invokeLater(new Runnable() {
					public void run() {
					new E05();
					}
					});
					}
					}
