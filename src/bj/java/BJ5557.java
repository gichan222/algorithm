package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ5557 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N-1]; // N-1개의 숫자를 저장

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N-1; i++){
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int target = Integer.parseInt(st.nextToken()); // 최종 목표 숫자

		long[][] answers = new long[N-1][21]; // 0 ~ 20 경우의 수 저장

		if(nums[0] - nums[1] >= 0){
			answers[1][nums[0] - nums[1]] += 1; // idx = 1 초기값
		}
		if(nums[0] + nums[1] <= 20){
			answers[1][nums[0] + nums[1]] += 1; // idx = 1 초기값
		}
		for(int i = 2; i < N-1; i++){
			int next = nums[i];
			for(int j = 0; j <= 20; j++){
				if(answers[i-1][j] == 0){ // 이전 값이 없다면 pass
					continue;
				}
				if(j - next < 0){ // 차가 0보다 작아지는 경우
					answers[i][j+next] += answers[i-1][j]; // 합만
					continue;
				}
				if(j + next > 20){ // 차가 20보다 커지는 경우
					answers[i][j-next] += answers[i-1][j]; // 차만
					continue;
				}
				// 아닌 경우 모두
				// 합 or 차만 했어도 되는 이유는
				// 문제에서 N의 범위가 0~9라고 한정되어있기 떄문.
				answers[i][j+next] += answers[i-1][j];
				answers[i][j-next] += answers[i-1][j];
			}
		}

		// idx = N-2에서 정답 출력
		System.out.println(answers[N-2][target]);

	}
}
