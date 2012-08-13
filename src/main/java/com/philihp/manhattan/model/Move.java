package com.philihp.manhattan.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Move {

	private String command;
	private String workers;
	private String action;
	private int paramNum;
	private String paramChar;

	private enum Section {
		WORKERS, ACTION, PARAM_NUM, PARAM_CHAR;
	}

	private Move(String command) {
		Section section = Section.WORKERS;
		StringBuilder sectionString = new StringBuilder();
		for (int i = 0; i <= command.length(); i++) {
			char c = (i == command.length()) ? 0 : command.charAt(i);
			switch (section) {
			case WORKERS:
				if (false == Character.isUpperCase(c) || c == 0) {
					this.workers = sectionString.toString();
					sectionString.setLength(0);
					section = Section.ACTION;
				} else {
					sectionString.append(c);
					break;
				}
			case ACTION:
				if (Character.isDigit(c) || c == 0) {
					this.action = sectionString.toString();
					sectionString.setLength(0);
					section = Section.PARAM_NUM;
				} else {
					sectionString.append(c);
					break;
				}
			case PARAM_NUM:
				if (false == Character.isDigit(c) || c == 0) {
					this.paramNum = Integer.parseInt(sectionString.toString());
					sectionString.setLength(0);
					section = Section.PARAM_CHAR;
				} else {
					sectionString.append(c);
					break;
				}
			case PARAM_CHAR:
				if (c == 0) {
					this.paramChar = sectionString.toString();
				} else {
					sectionString.append(c);
					break;
				}
			}
		}
	}

	public static List<Move> parse(String commands) {
		StringTokenizer tok = new StringTokenizer(commands, " ");
		List<Move> moves = new ArrayList<Move>(tok.countTokens());
		while (tok.hasMoreElements()) {
			moves.add(new Move(tok.nextToken()));
		}
		return moves;
	}

	public String toString() {
		return "[" +
				"w=" + workers + ", " +
				"a=" + action + ", " +
				"n=" + paramNum + ", " +
				"c="+ paramChar
				+ "]";
	}
}
