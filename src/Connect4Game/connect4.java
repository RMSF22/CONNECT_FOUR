package Connect4Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class connect4 extends JFrame implements ActionListener, connect4Interface{
//__________________________________________________________________________________________________________________
	private JPanel jpMain;
	private JPanel jpBoard;
	private JPanel jpIO;
	private JLabel displayOut;
	private JButton [][] board;
	private String currPlayer = "BLUE";
	private JButton Tokens1;
	private JButton Tokens2;
	private JButton Tokens3;
	private JButton Tokens4;
	private JButton Tokens5;
	private JButton Tokens6;
	private JButton Tokens7;
//___________________________________________________________________________________________________________________
	
	public connect4(){
	
		this.setTitle("CONNECT 4 GAME"); //Making name for the title
		
        jpBoard=new JPanel();
		jpMain = new JPanel();
		
		jpMain.setLayout(new BorderLayout());
		jpIO = new JPanel();
		displayOut = new JLabel();
	

		updateOut("Let's play ! \'"+ currPlayer +"\' goes first");
		
		jpIO.add(displayOut);
		jpBoard.setBackground(Color.BLACK);
		jpMain.add(jpIO, BorderLayout.NORTH);
		
		jpBoard = new JPanel();
		jpBoard.setLayout(new GridLayout(8,9));

		displayBoard();
		
		Tokens1 = new JButton("1");
		jpBoard.add(Tokens1);
		
		Tokens2 = new JButton("2");
		jpBoard.add(Tokens2);
		
		Tokens3 = new JButton("3");
		jpBoard.add(Tokens3);
		
		Tokens4 = new JButton("4");
		jpBoard.add(Tokens4);
		
		Tokens5 = new JButton("5");
		jpBoard.add(Tokens5);
		
		Tokens6 = new JButton("6");
		jpBoard.add(Tokens6);
		
		Tokens7 = new JButton("7");
		jpBoard.add(Tokens7);

		
		jpMain.add(jpBoard);
		add(jpMain);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setVisible(true);
	
	}
//________________________________________________________________________________________________________________	
	
	public void updateOut(String msg){
		if (currPlayer == "BLUE"){
	    displayOut.setText("<HTML><H1 color=blue>"+msg+"</H1></HTML>");
		}
		else{
		displayOut.setText("<HTML><H1 color=red>"+msg+"</H1></HTML>");
		}
	}
//___________________________________________________________________________________________________________________
	public void playAnotherGame(){
		JOptionPane.showMessageDialog(null, currPlayer + " is the Winner!");
		int yesNo = JOptionPane.showConfirmDialog(null, "Do you want to play another game?");
		if(yesNo == 0){
			clearBoard();
			updateOut(currPlayer+" goes first!");
		}
		else{
			updateOut("Thanks for playing");
			JOptionPane.showMessageDialog(null, "BYE");
			System.exit(EXIT_ON_CLOSE);
		}
		System.out.println(yesNo);
	}
//____________________________________________________________________________________________________________________
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton) e.getSource();
		btnClicked.setText(currPlayer);
		btnClicked.setEnabled(false);
		
		    if(currPlayer == "BLUE"){
				btnClicked.setBackground(Color.BLUE);
				btnClicked.setOpaque(true);
				
				
			    
		}
			if(currPlayer == "RED"){
				 btnClicked.setBackground(Color.RED);
				 btnClicked.setOpaque(true);
			}

		if(isWinner(currPlayer) || isFull()){
			displayWinner();
			playAnotherGame();
		}
		updateCurrPlayer();
		updateOut(currPlayer + " goes");
		
	}
//___________________________________________________________________________________________________________________
	@Override
	public void displayBoard() {
		board = new JButton[6][7];
		for(int row=0; row<board.length; row++){
			for(int col=0; col<board[row].length; col++){
				board[row][col] = new JButton();
				board[row][col].setFont(new Font(Font.SANS_SERIF,Font.BOLD,0));
				board[row][col].addActionListener(this);
				board[row][col].setEnabled(true);
				jpBoard.add(board[row][col]);
			}
		}
	}
//_________________________________________________________________________________________________________________
	@Override
	public void clearBoard() {
		for(int row=0; row<board.length; row++){
			for(int col=0; col<board[row].length; col++){
				board[row][col].setText("");
				board[row][col].setEnabled(true);
				board[row][col].setBackground(null);
				board[row][col].setIcon(null);
			}
		}
		
	}
//__________________________________________________________________________________________________________________________
	@Override
	public void displayWinner() {
		if(this.isWinner("BLUE")){
			updateOut("Blue is the winner");
		}
		else if(this.isWinner("RED")){
			updateOut("Red is the winner");
		}
		else{
			updateOut("DRAW!!!");
		}
	}
//____________________________________________________________________________________________________________________________
	@Override
	public void updateCurrPlayer() {
			if(currPlayer.equalsIgnoreCase("BLUE")){
				currPlayer = "RED";
			}
			else{
				currPlayer = "BLUE";
			}
	}   
//_____________________________________________________________________________________________________________________________
	public boolean isWinner(String player) {
		        //check rows
		    for(int row=0; row<board.length;row++){
					int rowCount=0;
					for(int col=0; col<board[row].length; col++){
						if(board[row][col].getText().contains(player)){
							rowCount++;
							if(rowCount == 4){
									return true;
									
							}}}}				
//______________________________________________________________________________________________________				
				//check columns
				for(int col=0; col<7; col++){
					int colCount=0;
					for(int row=0; row<6; row++){
						if(board[row][col].getText().contains(player)){
							colCount++;
							if(colCount == 4){
	
								return true;   
								}}}}
//_________________________________________________________________________________________________________			
				//main diagonal [0][0] [1][1] [2][2] [3][3] [4][4] [5][5]
				int diagCount = 0;
				int row = 0;
				int col = 0;
				while(row<=5 && col<=5){
					if(board[row][col].getText().contains(player)){
						diagCount++;
						if(diagCount == 4){
							return true;// found 4 in a row 
						}
						
					}
					row++;
					col++;
				}
//_________________________________________________________________________________________________________					
				//check secondary diagonal [5][0],[4][1],[3][2],[2][3],[1][4],[0][5]
				int diag2Count=0;
				int row1=5;
				int col1=0;
				while(row1 >=0 && col1<=5){
					if(board[row1][col1].getText().contains(player)){
						diag2Count++;
						if(diag2Count == 4){
							return true;//found 4 same across secondary diagonal
						    }}
					row1--;
					col1++;  }
//____________________________________________________________________________________________________________________
				//new code for the bottom right diagonal [0][1],[1]2],[2][3],[3][4],[4][5],[5][6]
				int diag3count = 0;
				row1 = 0;
				col1 = 1;		
				while(row1 <= 5 && col1<=6){
					
					if(board[row1][col1].getText().contains(player)){
						
						diag3count++;
					if(diag3count ==4){
						return true;//found 4 same across secondary diagonal
						}}
				row1++;
				col1++;  }
//_________________________________________________________________________________________________________________________				
				 //[0][2],[1][3],[2][4],[3][5],[4][6]
				int diag4count = 0;
				row1 = 0;
				col1 = 2;		
				while(row1 <=5 && col1<=6){
					
					if(board[row1][col1].getText().contains(player)){
						
						diag4count++;
					if(diag4count ==4){
						return true;//found 4 same across secondary diagonal
						}}
				row1++;
				col1++;  }
//______________________________________________________________________________________________________________________				
				//[3][6],[2][5],[1][4],[0][3]
				int diag5count = 0;
				row1 = 0;
				col1 = 3;		
				while(row1 <=3 && col1<=6){
					
					if(board[row1][col1].getText().contains(player)){
						
						diag5count++;
					if(diag5count ==4){
						return true;//found 4 same across secondary diagonal
						}}
				row1++;
				col1++;  }
//______________________________________________________________________________________________________________________				
				//[5][2],[4][3],[3][4],[2][5],[1][6]
				int diag6count = 0;
				row1 = 5;
				col1 = 2;		
				while(row1 >=1 && col1<=6){
					
					if(board[row1][col1].getText().contains(player)){
						
						diag6count++;
					if(diag6count ==4){
						return true;//found 4 same across secondary diagonal
						}}
				row1--;
				col1++;  }
				
//_____________________________________________________________________________________________________________________	
				//[5][3] [4][4] [3][5] [2][6] 
				int diag7count = 0;
				row1 = 5;
				col1 = 3;		
				while(row1 >=2 && col1 <=6){
					
					if(board[row1][col1].getText().contains(player)){
						
						diag7count++;
					if(diag7count ==4){
						return true;//found 4 same across secondary diagonal
						}}
				row1--;
				col1++;  }
//_____________________________________________________________________________________________________________________
				//[3][0] [2][1] [1][2] [0][3] 
				int diag8count = 0;
				row1 = board.length-3;
				col1 = 0;		
				while(row1 >=0 && col1<=3){
					
					if(board[row1][col1].getText().contains(player)){
						
						diag8count++;
					if(diag8count ==4){
						return true;//found 4 same across secondary diagonal
						}}
				row1--;
				col1++;  }
//________________________________________________________________________________________________________________________	
				//[4][0] [3][1] [2][2] [1][3] [0][4]
				int diag9count = 0;
				row1 = board.length-2;
				col1 = 0;		
				while(row1 >=0 && col1<=4){
					
					if(board[row1][col1].getText().contains(player)){
						
						diag9count++;
					if(diag9count ==4){
						return true;//found 4 same across secondary diagonal
						}}
				row1--;
				col1++;}
//_________________________________________________________________________________________________________________________					
				//[5][1] [4][2] [3][3] [2][4] [1][5] [0][6]
				int diag10count = 0;
				row1 = board.length-1;
				col1 = 1;		
				while(row1 >=0 && col1<=6){
					
					if(board[row1][col1].getText().contains(player)){
						
						diag10count++;
					if(diag10count ==4){
						return true;//found 4 same across secondary diagonal
						}}
				row1--;
				col1++;  }	
//______________________________________________________________________________________________________________________
				//[1][0] [2][1] [3][2] [4][3] [5][4] 
				int diag11count = 0;
				row1 = 1;
				col1 = 0;		
				while(row1 <=5 && col1<=4){
					
					if(board[row1][col1].getText().contains(player) ){
						
						diag11count++;
					if(diag11count ==4){
						return true;//found 4 same across secondary diagonal
						}}
				row1++;
				col1++;  }
//________________________________________________________________________________________________________________________	
				//[2][0] [3][1] [4][2] [5][3]
				int diag13count = 0;
				row1 = board.length-4;
				col1 = 0;		
				while(row1 <=5 && col1<=3){
					
					if(board[row1][col1].getText().contains(player)){
						
						diag13count++;
					if(diag13count ==4){
						return true;//found 4 same across secondary diagonal
						}}
				row1++;
				col1++;  }
				return false; }
//______________________________________________________________________________________________________________________
	@Override
	public boolean isFull() {
		for(int row=0; row<board.length; row++){
			for(int col=0; col<board[row].length; col++){
				String text = board[row][col].getText();
				if( !(text.contains("BLUE")) && !(text.contains("RED")) ){
					return false; }}}
		            return true; }
        
        }

