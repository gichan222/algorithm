package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1036 {

	static List<String> inputs = new ArrayList<>();
	static final int MAX_LEN = 60;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Map<Character, int[]> maps = new HashMap<>();

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			inputs.add(s);
			for (int j = s.length() - 1; j >= 0; j--) {
				char cur = s.charAt(j);
				maps.putIfAbsent(cur, new int[MAX_LEN]);
				int idx = s.length() - 1 - j;
				maps.get(cur)[idx] += 1;
			}
		}

		// Z 치환 시 이득 계산
		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int i = 0; i < 36; i++) {
			char c = toChar(i);
			maps.putIfAbsent(c, new int[MAX_LEN]);

			long[] gain = new long[MAX_LEN];
			int[] arr = maps.get(c);

			long diff = 35 - i; // Z - 원래 문자 값
			long carry = 0;

			for (int j = 0; j < MAX_LEN; j++) {
				long v = (long) arr[j] * diff + carry;
				gain[j] = v % 36;
				carry = v / 36;
			}

			pq.add(new Node(i, gain));
		}

		// K개 문자 선택
		int K = Integer.parseInt(br.readLine());
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < K; i++) {
			Node cur = pq.poll();
			set.add(toChar(cur.idx));
		}

		// 최종 합 계산
		long[] result = new long[MAX_LEN];

		for (String s : inputs) {
			long carry = 0;
			for (int i = s.length() - 1; i >= 0; i--) {
				char c = s.charAt(i);
				int val = toNumber(c);
				if (set.contains(c)) val = 35; // Z로 치환

				int idx = s.length() - 1 - i;
				long sum = result[idx] + val + carry;
				result[idx] = sum % 36;
				carry = sum / 36;
			}

			// 자리 올림
			int pos = s.length();
			while (carry > 0) {
				long sum = result[pos] + carry;
				result[pos] = sum % 36;
				carry = sum / 36;
				pos++;
			}
		}

		// 출력 (뒤에서부터)
		StringBuilder sb = new StringBuilder();
		int start = MAX_LEN - 1;

		while (start > 0 && result[start] == 0) start--;
		for (int i = start; i >= 0; i--) {
			sb.append(toChar((int) result[i]));
		}

		System.out.println(sb);
	}

	static class Node implements Comparable<Node> {
		int idx;
		long[] gain;

		public Node(int idx, long[] gain) {
			this.idx = idx;
			this.gain = gain;
		}

		@Override
		public int compareTo(Node o) {
			for (int i = MAX_LEN - 1; i >= 0; i--) {
				if (this.gain[i] != o.gain[i]) {
					return Long.compare(o.gain[i], this.gain[i]); // 큰 이득 우선
				}
			}
			return 0;
		}
	}

	static int toNumber(char c) {
		if (Character.isDigit(c)) return c - '0';
		return c - 'A' + 10;
	}

	static char toChar(int num) {
		if (num >= 0 && num <= 9) return (char) ('0' + num);
		return (char) ('A' + (num - 10));
	}
}
