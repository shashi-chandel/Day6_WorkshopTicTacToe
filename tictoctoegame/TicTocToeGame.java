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

	public static void main(String[] args) {
		createBoard();
		char userChoice = chooseLetter();
		char computerChoice;
		System.out.println("Player input: " + userChoice);
		if (userChoice == 'X')
			computerChoice = 'O';
		else
			computerChoice = 'X';
		System.out.println("Computer input: " + computerChoice);

	}
}