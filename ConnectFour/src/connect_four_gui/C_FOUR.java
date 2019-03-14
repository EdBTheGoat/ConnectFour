package connect_four_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class C_FOUR extends JFrame{
	private JPanel jpMain, scoreBoard;
	private final int ROWS = 6;
	private final int COLS = 7;
	CFOUR jpBoard;
	private JLabel top;
	private JLabel bottom;
	private Player currPlayer;
	private Player player1;
	private Player player2;
	private int numWins1;
	private int numWins2;
	private int totalGames;
	
	public C_FOUR() {
		player1 = new Player("Green","R", Color.GREEN);
		player2 = new Player("Blue", "B", Color.BLUE);
		currPlayer = player1;
		
		jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());
		jpMain.setBackground(Color.CYAN);
		
		scoreBoard = new JPanel();
		scoreBoard.setLayout(new GridLayout(3,3));
		scoreBoard.setBackground(Color.CYAN);
		
		top = new JLabel("Hey! Welcome!");
		top.setFont(top.getFont().deriveFont(25f));
		
		bottom = new JLabel(player1.getName() + ": " + numWins1 + " " + player2.getName() + ": " + numWins2 + " Total games played: " + totalGames);
		bottom.setFont(bottom.getFont().deriveFont(25f));
		
		jpBoard = new CFOUR();
		scoreBoard.add(BorderLayout.NORTH, top);
		scoreBoard.add(BorderLayout.CENTER, bottom);
		jpMain.add(BorderLayout.CENTER, jpBoard);
		
		add(jpMain);
		setSize(700,600);
		setVisible(true);
		add(scoreBoard, BorderLayout.NORTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}	
	
	private class CFOUR extends JPanel implements GameBoardInterface, GamePlayerInterface, ActionListener {
		private JLabel[][] board;
		private JButton[] button;
		private String[] btnName = {"Click"};
		private int[] tracker = {5,5,5,5,5,5,5};
		
		
		public CFOUR() {
			button = new JButton[7];
			for(int i = 0; i < 7; i++) {
				button[i] = new JButton(btnName[0]);
				button[i].addActionListener(this);
				add(button[i]);
			}
			setLayout(new GridLayout(ROWS +1,COLS));
			board = new JLabel[ROWS][COLS];
			displayBoard();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = currPlayer.getWow();
			for(int i =0; i < 7;i++) {	
				if(button[i] == e.getSource()) {
					int rowToSet = tracker[i];
					board[rowToSet][i].setBackground(color);
					tracker[i]--;
					if(tracker[i] == -1) {
						button[i].setEnabled(false);
					}
					
				}
			}
			
			if(isWinner() == true){
				if(currPlayer == player1) {
					numWins1++;
					totalGames++;
				}else {
					numWins2++;
					totalGames++;
				}
				bottom.setText(player1.getName() + ": " + numWins1 + " " + player2.getName() + ": " + numWins2 + " Total games played: " + totalGames); 
				JOptionPane.showMessageDialog(null, "WINNER= "+currPlayer.getName());
				int reply = JOptionPane.showConfirmDialog(null, "Want to play again?", "Hm?", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION) {
					clearBoard();
				}else {
					System.exit(0);
				}
				
			}
			else if(isFull()){
				totalGames++;
				bottom.setText(player1.getName() + ": " + numWins1 + " " + player2.getName() + ": " + numWins2 + "Total games played: " + totalGames); 
				JOptionPane.showMessageDialog(null,"IS FULL... DRAW");
				int reply = JOptionPane.showConfirmDialog(null, "Want to play again?", "Hm?", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION) {
					clearBoard();
				}else {
					System.exit(0);
				}
			}
			takeTurn();
		}

		@Override
		public boolean isWinner() {
			if(isWinnerInRow() || isWinnerInCol() || isWinnerInDownwardDiag() || isWinnerInUpwardDiag()){
				return true;
			}
			return false;
		}
		
		public boolean isWinnerInRow(){
			Color wow = currPlayer.getWow();
			for(int row=0; row < board.length; row++){
				int matches = 0;
				for(int col=0; col< board[row].length; col++){
					if( board[row][col].getBackground().equals(wow)){
						matches++;
						if(matches == 4) {
							return true;
							}
						}else {
							matches = 0;
						}
					}
				}
			return false;
		}
		
		public boolean isWinnerInCol() {
			Color wow = currPlayer.getWow();
			for(int col=0; col < COLS; col++){
				int matches = 0;
				for(int row=0; row < ROWS; row++){
					if( board[row][col].getBackground().equals(wow)){
						matches++;
						if(matches == 4) {
							return true;
							}
						}else {
							matches = 0;
						}
					}
				}
			return false;
		}
		
		public boolean isWinnerInDownwardDiag() {
			Color wow = currPlayer.getWow();
			int row = 0;
			int col = 0;
			int matches = 0;
			for(int showRow = 0; showRow < board.length; showRow++) {
				row = showRow;
				col = 0;
				matches = 0;
				while(row < board.length && col < board[row].length) {
					if(board[row][col].getBackground().equals(wow)) {
						matches++;
					}else {
						matches = 0;
					}
					if(matches == 4) {
						return true;
					}
					row++;
					col++;
				}
			}
			row = 0;
			for(int showCol = 0; showCol < board[row].length; showCol++) {
				row = 0;
				col = showCol;
				matches = 0;
				while(row < board.length && col < board[row].length) {
					if(board[row][col].getBackground().equals(wow)) {
						matches++;
					}else {
						matches = 0;
					}
					if(matches == 4) {
						return true;
					}
					row++;
					col++;
					if(row > 5) {
						row = 5;
						break;
					}
				}
			}
			return false;
		}
		
		public boolean isWinnerInUpwardDiag() {
			Color wow = currPlayer.getWow();
			int row = 0;
			int col = 0;
			int matches = 0;
			for(int showRow = 3; showRow < 6 ; showRow++ ) {
				row = showRow;
				col = 0;
				matches = 0;
				while(row >= 0 && col < 7) {
					if(board[row][col].getBackground().equals(wow)){
						matches++;
						if(matches == 4) {
							return true;
							}
						}else {
							matches = 0;
						}
					row--;
					col++;
				}
			}
			row = 0;
			for(int showCol = 1; showCol < 4 ; showCol++ ) {
				row = 5;
				col = showCol;
				matches = 0;
				while(row >= 0 && col < 7) {
					if(board[row][col].getBackground().equals(wow)){
						matches++;
						if(matches == 4) {
							return true;
							}
						}else {
							matches = 0;
						}
					row--;
					col++;
				}
			}
			return false;
		}
		
		@Override
		public void takeTurn() {
			if(currPlayer.equals(player1)){
				currPlayer = player2;
				top.setText("The current player is: " + currPlayer.getName());
			}
			else{
				currPlayer = player1;
				top.setText("The current player is: " + currPlayer.getName());
			}
			
		}

		@Override
		public void displayBoard() {
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
					board[row][col] = new JLabel();
					board[row][col].setBorder(new LineBorder(Color.BLACK));
					board[row][col].setOpaque(true);
					add(board[row][col]);
				}
			}
			
		}

		@Override
		public void clearBoard() {
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
					board[row][col].setText(" ");
					board[row][col].setBackground(Color.WHITE);
					board[row][col].setEnabled(true);
				}
			}
			for(int i = 0; i < 7; i++) {
				tracker[i] = 5;
				button[i].setEnabled(true);
			}
			
			
		}

		@Override
		public boolean isEmpty() {
			
			return false;
		}

		@Override
		public boolean isFull() {
			for(int row = 0; row +1< 6; row++) {
				for(int col = 0; col < 7; col++) {
					String brick = board[row][col].getText().trim();
					if(brick.isEmpty()){
						return false;
					}
				}
			}
			return true;
		}
		
		
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() {
			
			@Override
			public void run() {
				C_FOUR yer = new C_FOUR();
			}
		});
	}
	
}
