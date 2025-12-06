package bj.java;

import java.io.*;
import java.util.*;

public class BJ22866 {

	static final int INF = 100_001;

	static Building[] buildings;
	static int[] visibleCount;
	static int[][] nearest;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");

		buildings = new Building[n + 1];
		visibleCount = new int[n + 1];
		nearest = new int[n + 1][2];

		for (int i = 1; i <= n; i++) {
			buildings[i] = new Building(i, Integer.parseInt(input[i - 1]));
			nearest[i][0] = 0;
			nearest[i][1] = INF;
		}

		// 왼쪽 → 오른쪽
		scanLeftToRight();

		// 오른쪽 → 왼쪽
		scanRightToLeft();

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			if (visibleCount[i] == 0) {
				sb.append(0).append("\n");
			} else {
				sb.append(visibleCount[i]).append(" ").append(nearest[i][0]).append("\n");
			}
		}
		System.out.print(sb);
	}

	static void scanLeftToRight() {
		Stack<Building> stack = new Stack<>();

		for (int i = 1; i <= n; i++) {
			Building current = buildings[i];

			while (!stack.isEmpty() && stack.peek().height <= current.height) {
				stack.pop();
			}

			visibleCount[i] += stack.size();

			if (!stack.isEmpty()) {
				updateNearest(nearest[i], stack.peek().index, i);
			}

			stack.push(current);
		}
	}

	static void scanRightToLeft() {
		Stack<Building> stack = new Stack<>();

		for (int i = n; i >= 1; i--) {
			Building current = buildings[i];

			while (!stack.isEmpty() && stack.peek().height <= current.height) {
				stack.pop();
			}

			visibleCount[i] += stack.size();

			if (!stack.isEmpty()) {
				updateNearest(nearest[i], stack.peek().index, i);
			}

			stack.push(current);
		}
	}

	static void updateNearest(int[] nearestInfo, int otherIndex, int currentIndex) {
		int gap = Math.abs(otherIndex - currentIndex);

		if (gap < nearestInfo[1]) {
			nearestInfo[0] = otherIndex;
			nearestInfo[1] = gap;
		} else if (gap == nearestInfo[1] && otherIndex < nearestInfo[0]) {
			nearestInfo[0] = otherIndex;
		}
	}

	static class Building {
		int index;
		int height;

		public Building(int index, int height) {
			this.index = index;
			this.height = height;
		}
	}
}