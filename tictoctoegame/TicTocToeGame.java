package com.capgemini.tictoctoegame;

import java.util.Scanner;

public class TicTocToeGame {
	public static char board[] = new char[10];

	/*
	 * UC1
	 */
	static public void createBoard() {

		for (int i = 0; i < 10; i++) {
			board[i] = ' ';
		}
	}

	/*
	 * UC2
	 */
	static public char chooseLetter() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your input (X or O): ");
		char input = sc.next().charAt(0);
		if (input == 'X')
			return 'X';
		if (input == 'O')
			return 'O';
		else {
			System.out.println("Invalid input");
			return 0;
		}
	}

	/*
	 * UC3
	 */
	static public void showBoard() {
		System.out.println("Valid cells to make move: ");
				System.out.println(board[1] +"_"+ board[2] +"_"+ board [3]+"_");
				System.out.println(board[4] +"_"+ board[5] +"_"+ board [6]+"_");
				System.out.println(board[7] +"_"+ board[8] +"_"+ board [9]+"_");
			}
	
	public static void main(String[] args) {
		createBoard();
		char userChoice = chooseLetter();
		char computerChoice;
		System.out.println("Player Choice: " + userChoice);
		if (userChoice == 'X')
			computerChoice = 'O';
		else
			computerChoice = 'X';
		System.out.println("Computer Choice: " + computerChoice);
		showBoard();
	}
}