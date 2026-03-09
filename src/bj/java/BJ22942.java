package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ22942 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		final int OFFSET = 2_000_000;
		int[] check = new int[4_000_002];

		int N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			int left = x - r + OFFSET;
			int right = x + r + OFFSET;

			// 같은 위치에 점이 겹치면 바로 NO
			if (check[left] != 0 || check[right] != 0) {
				System.out.println("NO");
				return;
			}

			check[left] = i;
			check[right] = -i;
		}

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < check.length; i++) {
			if (check[i] > 0) {
				stack.push(check[i]);
			} else if (check[i] < 0) {
				if (stack.isEmpty()) {
					System.out.println("NO");
					return;
				}

				int num = stack.pop();
				if (num + check[i] != 0) {
					System.out.println("NO");
					return;
				}
			}
		}

		System.out.println("YES");
	}
}