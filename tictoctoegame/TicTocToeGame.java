package com.capgemini.tictoctoegame;

import java.util.Scanner;

public class TicTocToeGame {
	public static char board[] = new char[10];
	public static Scanner sc = new Scanner(System.in);

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
		for (int i = 1; i < 10; i++) {
			if (board[i] == ' ')
				board[i] = '_';
		}
		System.out.println("Valid cells to make move: ");
		System.out.println(" " + board[1] + " | " + board[2] + " | " + board[3] + " \n");
		System.out.println(" " + board[4] + " | " + board[5] + " | " + board[6] + " \n");
		System.out.println(" " + board[7] + " | " + board[8] + " | " + board[9] + " \n");
	}

	/*
	 * UC4
	 */
	static public int desiredLocation() {
		boolean isSpaceAvailable = false;
		int location;
		do {
			System.out.println("Select the index from 1 to 9 to make the move");
			location = sc.nextInt();
			isSpaceAvailable = isSpaceFree(location);
		} while (false);
		return location;
	}

	public static boolean isSpaceFree(int location) {
		return (board[location] == ' ') ? true : false;
	}

	/*
	 * UC5
	 */
	static public void makeMove(int position, char userLetter) {
		board[position] = userLetter;
	}
	
	/**
	 * UC6
	 * @return
	 */
	static public String checkFirstMove() {
		int toss = (int) (Math.random() * 10) % 2;
		switch (toss) {
		case 0:
			System.out.println("Tail ! Player will move first.");
			break;
		case 1:
			System.out.println("Head ! Computer will move first.");
			break;
		}
		if (toss==0)
			return "UserTurn";
		else
			return "ComputerTurn";
	}

	/*
	 * main
	 */
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
		int position = desiredLocation();
		makeMove(position, userChoice);
		String whoseChanceToPlay = checkFirstMove();
	}
}