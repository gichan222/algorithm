package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2258 {

	/*
	문제의 주요 포인트는 자기 자신보다 싼 가격의 것들은 모두 취득할 수 있다는 것이다.
	여기서 첫번째 생각한 아이디어는 그럼 구간합이겠네? 였다.
	1. 가격 기준으로 오름차순 정렬한다.
	2. 가격이 같다면 무게 기준으로 내림차순 정렬한다.
	3. 최초로 찾은 값을 반환한 뒤 마무리한다.
	였으나 문제 풀이 과정에서 오류가 발생한 것이었다.

	원인은 다음과 같다.
	1. 같은 가격의 물건이 존재한다면 무게가 더 가벼우니 바로 건너뛰는 것이 아닌, 일단 더해본다.
	2. 최초 만족을 찾았다 하더라도 다음 값들을 계속 찾아본다.
	3. 최대가 Integer.MAX_VALUE이니 최초 최솟값을 long타입으로 가져가야한다.

	예를 들면,
	3 2
	1 1
	1 1
	2 3 같은 반례가 있다.
	이는 2짜리를 3원 주고 사서 총 4의 무게를 가져가는 것이 아닌,
	1짜리를 1원주고 2개 사는 것이 더 적합하기 때문이다.
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Meat> meats = new ArrayList<>();

		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			meats.add(new Meat(weight, cost));
		}

		Collections.sort(meats);

		int sum = 0;
		int lastCost = 0;
		int totalCost = 0;
		long min = Integer.MAX_VALUE + 1L;
		for(Meat m : meats){
			if(m.cost == lastCost){
				sum += m.weight;
				totalCost += m.cost;
				if(sum >= M){
					min = Math.min(totalCost, min);
				}
				continue;
			}
			sum += m.weight;
			lastCost = m.cost;
			totalCost = m.cost;
			if(sum >= M){
				min = Math.min(totalCost, min);
			}
		}

		if(min != Integer.MAX_VALUE + 1L){
			System.out.println(min);
			return;
		}
		System.out.println(-1);
	}

	static class Meat implements Comparable<Meat>{
		int weight;
		int cost;

		public Meat(int weight, int cost){
			this.weight = weight;
			this.cost = cost;
		}

		@Override
		public int compareTo(Meat meat){
			if(this.cost != meat.cost){
				return this.cost - meat.cost;
			}
			return meat.weight - this.weight;
		}
	}
}
