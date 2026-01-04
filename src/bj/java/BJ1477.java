package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1477 {

	static int N, M, L;
	static List<Integer> edges = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			edges.add(Integer.parseInt(st.nextToken()));
		}

		edges.add(0); // 시작
		edges.add(L); // 끝

		Collections.sort(edges);
		binarySearch();
	}

	static void binarySearch() {
		int s = 1;
		int e = L - 1;

		while (s <= e) {
			int mid = (s + e) / 2;
			int cnt = 0;

			for (int i = 1; i < edges.size(); i++) {
				int dist = edges.get(i) - edges.get(i - 1);

				// 나눗셈 결과가 딱 떨어지는 경우(예: 거리 200, mid 100 -> 휴게소 1개만 지으면 됨)를 위해 -1을 함
				cnt += (dist - 1) / mid;
			}

			if (cnt > M) {
				// 지어야 할 휴게소가 M보다 많으면 간격(mid)을 너무 좁게 잡은 것 -> 간격을 늘려야 함
				s = mid + 1;
			} else {
				// 지어야 할 휴게소가 M보다 적거나 같으면 간격(mid)이 충분하거나 넓은 것 -> 최솟값을 찾기 위해 간격을 좁혀봄
				e = mid - 1;
			}
		}
		System.out.println(s);
	}
}