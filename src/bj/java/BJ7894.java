package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ7894 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		while(T-- > 0){
			int m = Integer.parseInt(br.readLine());
			double result = 0;
			for(int i = 1; i <= m; i++){
				result += Math.log10(i);
			}
			System.out.println((int)Math.floor(result) + 1);
		}
	}
}
