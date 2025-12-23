package bj.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2290 {
	static int size;               // LCD 세그먼트 크기(s)
	static char[][] screen;         // 출력 화면 버퍼

	// 각 숫자가 사용하는 7세그먼트 정보
	// 순서: 위, 좌상, 우상, 중간, 좌하, 우하, 아래
	static final boolean[][] DIGIT_SEGMENTS = {
		{ true,  true,  true,  false, true,  true,  true  }, // 0
		{ false, false, true,  false, false, true,  false }, // 1
		{ true,  false, true,  true,  true,  false, true  }, // 2
		{ true,  false, true,  true,  false, true,  true  }, // 3
		{ false, true,  true,  true,  false, true,  false }, // 4
		{ true,  true,  false, true,  false, true,  true  }, // 5
		{ true,  true,  false, true,  true,  true,  true  }, // 6
		{ true,  false, true,  false, false, true,  false }, // 7
		{ true,  true,  true,  true,  true,  true,  true  }, // 8
		{ true,  true,  true,  true,  false, true,  true  }  // 9
	};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		String numbers = st.nextToken();

		int height = 2 * size + 3;                 // LCD 전체 높이
		int width = (size + 3) * numbers.length(); // LCD 전체 너비

		screen = new char[height][width];
		for (char[] row : screen) {
			Arrays.fill(row, ' ');
		}

		// 각 숫자를 LCD 세그먼트로 변환
		for (int i = 0; i < numbers.length(); i++) {
			int digit = numbers.charAt(i) - '0';
			drawDigit(digit, i);
		}

		// 결과 출력
		for (char[] row : screen) {
			bw.write(row);
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	/**
	 * 하나의 숫자를 LCD 세그먼트 형태로 그린다
	 */
	static void drawDigit(int digit, int index) {
		boolean[] seg = DIGIT_SEGMENTS[digit];

		if (seg[0]) drawHorizontal(index, 0);          // 위
		if (seg[1]) drawVertical(index, 1, 1);         // 좌상
		if (seg[2]) drawVertical(index, 1, 2);         // 우상
		if (seg[3]) drawHorizontal(index, size + 1);   // 중간
		if (seg[4]) drawVertical(index, size + 2, 1);  // 좌하
		if (seg[5]) drawVertical(index, size + 2, 2);  // 우하
		if (seg[6]) drawHorizontal(index, 2 * size + 2); // 아래
	}

	/**
	 * 수평 세그먼트('-')를 그린다
	 */
	static void drawHorizontal(int index, int row) {
		int startCol = index * (size + 3) + 1;
		for (int i = 0; i < size; i++) {
			screen[row][startCol + i] = '-';
		}
	}

	/**
	 * 수직 세그먼트('|')를 그린다
	 * side: 1 = 왼쪽, 2 = 오른쪽
	 */
	static void drawVertical(int index, int startRow, int side) {
		int col = index * (size + 3) + (side == 1 ? 0 : size + 1);
		for (int i = 0; i < size; i++) {
			screen[startRow + i][col] = '|';
		}
	}
}
