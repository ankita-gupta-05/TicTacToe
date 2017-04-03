package com.mmt.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Tic - Tac -Toe
 */
public class Application {

	private static List<User> users = new ArrayList<>(2);

	private static int[] filledBoard = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private static int[] emptyBoard = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

	private static Map<String, String> rulesMap = new HashMap<>();

	private static void fillRules() {
		rulesMap.put("1_2_3", "Win");
		rulesMap.put("2_5_8", "Win");
		rulesMap.put("4_5_6", "Win");
		rulesMap.put("1_4_7", "Win");
		rulesMap.put("3_6_9", "Win");
		rulesMap.put("7_8_9", "Win");
		rulesMap.put("1_5_9", "Win");
		rulesMap.put("3_5_7", "Win");
	}

	private static void getNames(int idx) throws IOException {

		System.out.println("Enter Player " + idx + " name:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String name = reader.readLine();
		User player = new User();
		player.setName(name);

		users.add(player);

	}

	public static void main(String[] args) {

		System.out.println("#########################");
		System.out.println("Let's Play Tic Tac Toe");
		System.out.println("#########################");
		fillRules();
		boolean gameOver = false;
		int playerIdx = 0;
		try {

			getNames(1);
			getNames(2);
			while (!gameOver) {

				gameOver = play(users.get(playerIdx));
				playerIdx++;
				if (playerIdx == 2) {
					playerIdx = 0;
				}

			}
		} catch (IOException e) {
			System.out.println("Something Bad happened : " + e);
		}

	}

	private static boolean play(User user) throws IOException {

		boolean done = false;
		int position;
		do {

			if (!printEmptyBoard(user)) {
				System.out.println("Game Over!!! ");
				return true;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			position = Integer.parseInt(reader.readLine());
			if (filledBoard[position] != 0) {
				System.out.println("Already Filled Position Entered, Pls. correct");
			} else {
				done = true;
			}
		} while (!done);

		user.addToList(position);
		filledBoard[position] = 1;
		emptyBoard[position] = 0;
		if (checkForWin(user)) {
			System.out.println("Game Over!!!" + user.getName() + " Congos!!! You won");
			return true;
		}

		return false;

	}

	private static boolean printEmptyBoard(User user) {
		boolean empty = false;
		StringBuilder sb = new StringBuilder(user.getName() + " Play your move from: ");
		int idx;
		for (idx = 1; idx < 9; idx++) {
			if (emptyBoard[idx] == 1) {
				empty = true;
				sb.append(idx + ",");
			}

		}
		if (emptyBoard[idx] == 1) {
			empty = true;
			sb.append(idx + ": ");
		}

		if (empty) {
			System.out.println(sb.toString());
		}

		return empty;

	}

	private static String generateKey(int idx1, int idx2, int idx3, User user) {

		StringBuilder key = new StringBuilder();
		key.append(String.valueOf((user.getBoard().get(idx1))));
		key.append("_");
		key.append(String.valueOf((user.getBoard().get(idx2))));
		key.append("_");
		key.append(String.valueOf((user.getBoard().get(idx3))));

		return key.toString();
	}

	private static boolean inRulesMap(String key) {
		if (rulesMap.containsKey(key)) {
			return true;
		}
		return false;
	}

	private static boolean checkForWin(User user) {
		boolean result = false;
		String key;
		if (user.getBoard().size() >= 3) {
			key = generateKey(0, 1, 2, user);
			result = inRulesMap(key);
			if (!result && user.getBoard().size() == 4) {
				key = generateKey(1, 2, 3, user);
				result = inRulesMap(key);
			}
			if (!result && user.getBoard().size() == 4) {
				key = generateKey(0, 1, 3, user);
				result = inRulesMap(key);
			}
			if (!result && user.getBoard().size() == 4) {
				key = generateKey(0, 2, 3, user);
				result = inRulesMap(key);
			}
		}

		return result;

	}

}
