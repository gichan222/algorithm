package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1963 {

	static List<Integer> primes = new ArrayList<>();
	static Map<Integer, Integer> primeIdxMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		isPrime();

		StringBuilder sb;

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			int result = bfs(start, end);
			System.out.println(result == -1 ? "Impossible" : result);
		}
	}

	// BFS: 한 자리씩 숫자를 바꿔서 모두 소수인 경로의 최소 횟수
	static int bfs(int start, int end) {
		Queue<Integer> queue = new ArrayDeque<>();
		Map<Integer, Integer> dist = new HashMap<>();

		queue.add(start);
		dist.put(start, 0);

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int curDist = dist.get(cur);

			if (cur == end) {
				return curDist;
			}

			// 네 자리 중 한 자리씩 바꿔보기
			char[] curArr = String.valueOf(cur).toCharArray();

			for (int i = 0; i < 4; i++) {
				char original = curArr[i];

				for (char d = '0'; d <= '9'; d++) {
					if (i == 0 && d == '0') continue; // 1000 이상 유지
					if (curArr[i] == d) continue;

					curArr[i] = d;
					int next = Integer.parseInt(new String(curArr));

					if (primeIdxMap.containsKey(next) && !dist.containsKey(next)) {
						dist.put(next, curDist + 1);
						queue.add(next);
					}
				}
				curArr[i] = original; // 복원
			}
		}

		return -1; // 경로 없음
	}

	// 1000~9999 소수 생성
	static void isPrime() {
		boolean[] isPrime = new boolean[10000];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;

		for (int i = 2; i <= Math.sqrt(10000); i++) {
			if (!isPrime[i]) continue;
			for (int j = i * i; j < 10000; j += i) {
				isPrime[j] = false;
			}
		}

		for (int i = 1000; i < 10000; i++) {
			if (isPrime[i]) {
				primes.add(i);
				primeIdxMap.put(i, primes.size() - 1);
			}
		}
	}