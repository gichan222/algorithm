package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1730 {

	static int n;
	static char[][] board;
	static int x = 0, y = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String s = br.readLine();

		board = new char[n][n];
		for (char[] row : board) {
			Arrays.fill(row, '.');
		}

		for (char move : s.toCharArray()) {
			moveRobot(move);
		}

		printBoard();
	}

	static void moveRobot(char move) {
		int nx = x, ny = y;
		char line;

		switch (move) {
			case 'U': nx--; line = '|'; break;
			case 'D': nx++; line = '|'; break;
			case 'L': ny--; line = '-'; break;
			case 'R': ny++; line = '-'; break;
			default: return;
		}

		if (!isInRange(nx, ny)) return;

		draw(x, y, line);
		draw(nx, ny, line);

		x = nx;
		y = ny;
	}

	static void draw(int x, int y, char line) {
		if (board[x][y] == '.') {
			board[x][y] = line;
		} else if (board[x][y] != line) {
			board[x][y] = '+';
		}
	}

	static boolean isInRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	static void printBoard() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}
