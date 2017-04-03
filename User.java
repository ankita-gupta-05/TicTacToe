package com.mmt.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

	private String name;

	private List<Integer> board = new ArrayList<>(4);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getBoard() {
		return board;
	}

	public void setBoard(List<Integer> board) {
		this.board = board;
	}

	public void addToList(int pos) {

		board.add(pos);
		Collections.sort(board);
	}

}
