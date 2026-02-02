package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2251 {

	static int A_capacity, B_capacity, C_capacity;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A_capacity = Integer.parseInt(st.nextToken());
		B_capacity = Integer.parseInt(st.nextToken());
		C_capacity = Integer.parseInt(st.nextToken());

		Set<Water> visited = new HashSet<>();
		Set<Integer> result = new TreeSet<>(); // A가 0일 때 C의 물 양을 저장
		Queue<Water> queue = new ArrayDeque<>();

		Water start = new Water(0, 0, C_capacity); // 초기 상태: C는 가득, A와 B는 0
		visited.add(start);
		queue.add(start);

		while (!queue.isEmpty()) {
			Water cur = queue.poll();

			if (cur.A == 0) {
				result.add(cur.C);
			}

			// 가능한 모든 물 붓기
			for (Water next : getNextStates(cur)) {
				if (!visited.contains(next)) {
					visited.add(next);
					queue.add(next);
				}
			}
		}

		// 결과 출력
		for (int c : result) {
			System.out.print(c + " ");
		}
	}

	// 현재 상태에서 가능한 모든 이동을 생성
	static List<Water> getNextStates(Water w) {
		List<Water> nextStates = new ArrayList<>();

		// A->B
		nextStates.add(pour(w.A, w.B, A_capacity, B_capacity, 'A', 'B', w));
		// A->C
		nextStates.add(pour(w.A, w.C, A_capacity, C_capacity, 'A', 'C', w));
		// B->A
		nextStates.add(pour(w.B, w.A, B_capacity, A_capacity, 'B', 'A', w));
		// B->C
		nextStates.add(pour(w.B, w.C, B_capacity, C_capacity, 'B', 'C', w));
		// C->A
		nextStates.add(pour(w.C, w.A, C_capacity, A_capacity, 'C', 'A', w));
		// C->B
		nextStates.add(pour(w.C, w.B, C_capacity, B_capacity, 'C', 'B', w));

		// null 제거
		nextStates.removeIf(Objects::isNull);
		return nextStates;
	}

	// from -> to로 물을 붓는 함수
	static Water pour(int fromAmount, int toAmount, int fromCap, int toCap, char from, char to, Water w) {
		if (fromAmount == 0) return null; // 붓을 물이 없음
		int move = Math.min(fromAmount, toCap - toAmount); // 실제 옮길 수 있는 물 양

		int A = w.A, B = w.B, C = w.C;
		switch (from + "" + to) {
			case "AB": A -= move; B += move; break;
			case "AC": A -= move; C += move; break;
			case "BA": B -= move; A += move; break;
			case "BC": B -= move; C += move; break;
			case "CA": C -= move; A += move; break;
			case "CB": C -= move; B += move; break;
		}
		return new Water(A, B, C);
	}

	static class Water {
		int A, B, C;

		public Water(int A, int B, int C) {
			this.A = A;
			this.B = B;
			this.C = C;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Water water = (Water) o;
			return A == water.A && B == water.B && C == water.C;
		}

		@Override
		public int hashCode() {
			return Objects.hash(A, B, C);
		}
	}
}
