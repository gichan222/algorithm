package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1455 {

	// 세로 N, 가로 M
	// 앞면 0, 뒷면 1
	// 모든 면을 앞면으로
	// a,b칸 뒤집으면 (i,j) (1 <= i <= a, 1 <= j <= b) 모두 뒤집힘
	// 몇번 뒤집어야 될까?

	// 간단하게 생각하면 뒤집어야하는거 순서대로 다 뒤집으면 된다.
	// 최적화를 하자면.. 매번 뒤집는게 아니라 flag?같은 것ㅇ르 좌표마다 두어도 좋겠지만
	// 결국 비슷할 것 같아 다른 방법이 있다면 좋을 것 같다.
	static int[][] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int answer = 0;
		arr = new int[n][m];

		for(int i = 0; i < n; i++) {
			String[] tmp = br.readLine().split("");
			for(int j = 0; j < m ; j++){
				arr[i][j] = Integer.parseInt(tmp[j]);
			}
		}

		for(int i = m - 1; i >= 0; i--) {
			for(int j = n - 1; j >= 0; j--) {
				if(arr[j][i] == 1) {
					reverse(j, i);

					answer += 1;
				}

			}
		}

		System.out.println(answer);
	}

	static void reverse(int x, int y) {
		for(int i = 0; i <= x; i++) {
			for(int j = 0; j <= y; j++) {
				if(arr[i][j] == 1){
					arr[i][j] = 0;
				}else{
					arr[i][j] = 1;
				}
			}
		}
	}


}
