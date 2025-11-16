package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1826 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<GasStation> stations = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int loc = Integer.parseInt(st.nextToken());
			int fuel = Integer.parseInt(st.nextToken());
			stations.add(new GasStation(loc, fuel));
		}

		// 주유소 위치 기준 오름차순 정렬
		stations.sort(Comparator.comparingInt(s -> s.location));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken()); // 목표 지점
		int P = Integer.parseInt(st.nextToken()); // 시작 연료

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 연료 많은 주유소부터
		int idx = 0; // 지나간 주유소 인덱스
		int refuelCount = 0;
		int currFuel = P;

		while (currFuel < L) { // 목표 지점까지 도달할 때까지 반복
			// 현재 연료로 갈 수 있는 주유소 모두 heap에 추가
			while (idx < N && stations.get(idx).location <= currFuel) {
				maxHeap.add(stations.get(idx).fuel);
				idx++;
			}

			// 갈 수 있는 주유소가 없는데 연료 부족 → 실패
			if (maxHeap.isEmpty()) {
				System.out.println(-1);
				return;
			}

			// 연료가 모자라면 가장 많은 연료 주유
			currFuel += maxHeap.poll();
			refuelCount++;
		}

		System.out.println(refuelCount);
	}

	static class GasStation {
		int location;
		int fuel;

		public GasStation(int location, int fuel) {
			this.location = location;
			this.fuel = fuel;
		}
	}
}
