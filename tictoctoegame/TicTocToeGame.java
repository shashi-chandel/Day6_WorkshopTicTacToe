package com.capgemini.tictoctoegame;

import java.util.Scanner;

/**
 * @author shashi7
 *
 */
public class TicTocToeGame {
	public static Scanner sc = new Scanner(System.in);

	public enum Players {
		COMPUTER, PLAYER
	}

	/**
	 * UC1
	 */
	static public char[] createBoard() {
		char[] board = new char[10];
		for (int i = 0; i < 10; i++) {
			board[i] = ' ';
		}
		return board;
	}

	/**
	 * UC2
	 * 
	 * @return
	 */
	static public char chooseLetter() {
		char input;
		while (true) {
			System.out.println("Enter your input (X or O): ");
			input = sc.next().charAt(0);
			if (input == 'X')
				return 'X';
			if (input == 'O')
				return 'O';
			else {
				System.out.println("Invalid input!");
			}
		}
	}

	/**
	 * UC3
	 * 
	 * @param board
	 */
	static public void showBoard(char[] board) {
		System.out.println("Valid cells to make move: ");
		System.out.println(" " + board[1] + " | " + board[2] + " | " + board[3]);
		System.out.println("---+---+---");
		System.out.println(" " + board[4] + " | " + board[5] + " | " + board[6]);
		System.out.println("---+---+---");
		System.out.println(" " + board[7] + " | " + board[8] + " | " + board[9]);
	}

	/**
	 * UC4
	 * 
	 * @param board
	 * @return
	 */
	static public int getMove(char[] board) {
		boolean isSpaceAvailable = false;
		int location = 0;
		while (true) {
			System.out.println("Select the index from 1 to 9 to make the move");
			location = sc.nextInt();
			isSpaceAvailable = isSpaceFree(board, location);
			if (isSpaceAvailable)
				break;
		}
		return location;
	}

	public static boolean isSpaceFree(char[] board, int location) {
		if (board[location] == ' ')
			return true;
		return false;
	}

	/**
	 * UC5
	 * 
	 * @param board
	 * @param location
	 * @param userLetter
	 * @return
	 */
	static public char[] makeMove(char[] board, int location, char userLetter) {
		board[location] = userLetter;
		return board;
	}

	/**
	 * UC6
	 * 
	 * @return
	 */
	static public Players checkWhoPlaysFirst() {
		int toss = (int) (Math.random() * 10) % 2;
		System.out.println("\n Coin is tossed.");
		switch (toss) {
		case 1:
			System.out.println("Head ! Player will move first.");
			break;
		case 0:
			System.out.println("Tail ! Computer will move first.");
			break;
		}
		if (toss == 1)
			return Players.PLAYER;
		else
			return Players.COMPUTER;
	}

	/**
	 * UC7
	 * 
	 * @param board
	 * @param letter
	 * @return
	 */
	static public String checkStatus(char[] board, char letter) {
		int index;
		if (((board[1] == board[2]) && (board[2] == board[3]) && (board[3] == letter))
				|| ((board[4] == board[5]) && (board[5] == board[6]) && (board[6] == letter))
				|| ((board[7] == board[8]) && (board[8] == board[9]) && (board[9] == letter))
				|| ((board[1] == board[4]) && (board[4] == board[7]) && (board[7] == letter))
				|| ((board[2] == board[5]) && (board[5] == board[8]) && (board[8] == letter))
				|| ((board[3] == board[6]) && (board[6] == board[9]) && (board[9] == letter))
				|| ((board[3] == board[5]) && (board[5] == board[7]) && (board[7] == letter))
				|| ((board[1] == board[5]) && (board[5] == board[9]) && (board[9] == letter)))
			return "win";
		for (index = 1; index <= 9; index++) {
			if (board[index] != ' ')
				continue;
			else
				break;
		}
		if (index == 9)
			return "tie";
		else
			return "turn";
	}

	/**
	 * UC8
	 * 
	 * @param board
	 * @param letter
	 * @return
	 */
	public static int successfulMoveIndex(char[] board, char letter) {
		int index;
		for (index = 1; index <= 9; index++) {
			char[] dummyBoard = board.clone();
			if (dummyBoard[index] == ' ') {
				dummyBoard[index] = letter;
				String status = checkStatus(dummyBoard, letter);
				if (status.equals("win"))
					return index;
			}
		}
		return 0;
	}

	/**
	 * UC9
	 * 
	 * @param letter
	 * @return
	 */
	public static int blockingMoveIndex(char[] board, char letter) {
		char dummyLetter = 'O';
		if (letter == dummyLetter)
			dummyLetter = 'X';
		return successfulMoveIndex(board, dummyLetter);
	}

	/**
	 * UC10
	 * 
	 * @return
	 */
	public static int availableCorner(char[] board) {
		if (board[1] == ' ')
			return 1;
		if (board[3] == ' ')
			return 3;
		if (board[7] == ' ')
			return 7;
		if (board[9] == ' ')
			return 9;
		else
			return 0;
	}

	/**
	 * UC11
	 * 
	 * @param board
	 * @param computerLetter
	 * @return
	 */
	public static char[] computerTurn(char[] board, char computerLetter) {
		int index = successfulMoveIndex(board, computerLetter);
		if (index != 0) {
			board = makeMove(board, index, computerLetter);
			return board;
		}
		index = blockingMoveIndex(board, computerLetter);
		if (index != 0) {
			board = makeMove(board, index, computerLetter);
			return board;
		}
		index = availableCorner(board);
		if (index != 0) {
			board = makeMove(board, index, computerLetter);
			return board;
		}
		if (isSpaceFree(board, 5)) {
			board = makeMove(board, index, computerLetter);
			return board;
		} else {
			for (int side = 2; side <= 8; side++) {
				if (isSpaceFree(board, side)) {
					board = makeMove(board, index, computerLetter);
					break;
				}
				side++;
			}
			return board;
		}
	}

	/**
	 * UC12
	 */
	public static void newGame() {
		char[] board = createBoard();
		char computerChoice = ' ';
		int movePosition = 0;
		int maxTurns = 9;
		char playerChoice = chooseLetter();
		if (playerChoice == 'X') {
			computerChoice = 'O';
			System.out.println("Player has chosen " + playerChoice + " and Computer has chosen O");
		} else {
			computerChoice = 'X';
			System.out.println("Player has chosen " + playerChoice + " and Computer has chosen X");
		}
		Players firstMove = checkWhoPlaysFirst();
		String winner = " ";

		do {
			maxTurns--;
			if (firstMove == Players.COMPUTER) {
				board = computerTurn(board, computerChoice);
				if (checkStatus(board, computerChoice).equals("win")) {
					winner = "computer";
					break;
				}
				firstMove = Players.PLAYER;
			} else {
				showBoard(board);
				movePosition = getMove(board);
				board = makeMove(board, movePosition, playerChoice);
				if (checkStatus(board, playerChoice).equals("win")) {
					winner = "player";
					break;
				}
				firstMove = Players.COMPUTER;
			}
		} while (maxTurns > 0);

		showBoard(board);
		if (winner == "computer")
			System.out.println("Computer won !!");
		else if (winner == "player")
			System.out.println("Player won !!");
		else
			System.out.println("Game Tied !!");
	}

	/**
	 * UC13
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		char choice;
		do {
			newGame();
			System.out.println("Want to play More !!  (y/n) ");
			choice = sc.next().charAt(0);
		} while (choice == 'y');

	}
}
