package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2777 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		  int T = Integer.parseInt(br.readLine());
		  StringBuilder sb = new StringBuilder();
		  while(T-- > 0){
			  int N = Integer.parseInt(br.readLine());
			  int[] nums = new int[10];
			  boolean flag = false;
			  loop1:
			  while(N / 10 >= 1){
				  for(int i = 9; i > 1; i--){
					  if(N % i == 0){
						  nums[i]++;
						  N /= i;
						  continue loop1;
					  }
				  }
				  sb.append(-1).append("\n");
				  flag = true;
				  break ;
			  }
			  if(flag){
				  continue;
			  }

			  nums[N]++;
			  int cnt = 0;
			  if(N == 1){
				  cnt++;
			  }
			  for(int i = 2; i < 10; i++){
				  cnt += nums[i];
			  }
			  sb.append(cnt);
			  sb.append("\n");
		  }

		System.out.println(sb);
	}
}
