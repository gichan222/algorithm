package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class BJ5021 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		String founder = br.readLine();

		// 혈통 값
		Map<String, BigDecimal> blood = new HashMap<>();
		blood.put(founder, BigDecimal.ONE);

		// 관계 저장
		List<String[]> relations = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent1 = st.nextToken();
			String parent2 = st.nextToken();
			relations.add(new String[]{child, parent1, parent2});
		}

		// 여러 번 반복해서 혈통 전파
		// 부모값이 아직 없을 뿐 추후 값이 저장될 수 있기 때문이다
		for (int round = 0; round < N; round++) {
			for (String[] rel : relations) {
				String child = rel[0];
				String p1 = rel[1];
				String p2 = rel[2];

				BigDecimal v1 = blood.getOrDefault(p1, BigDecimal.ZERO);
				BigDecimal v2 = blood.getOrDefault(p2, BigDecimal.ZERO);

				BigDecimal childValue = v1.add(v2)
					.divide(BigDecimal.valueOf(2));

				if (childValue.compareTo(BigDecimal.ZERO) > 0) {
					blood.put(child, childValue);
				}
			}
		}

		// 왕 후보 중 최대 혈통 찾기
		BigDecimal max = BigDecimal.ZERO;
		String answer = null;

		for (int i = 0; i < M; i++) {
			String candidate = br.readLine();
			BigDecimal value = blood.getOrDefault(candidate, BigDecimal.ZERO);

			if (value.compareTo(max) > 0) {
				max = value;
				answer = candidate;
			}
		}

		System.out.println(answer);
	}
}
