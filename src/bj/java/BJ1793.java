package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BJ1793 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 숫자범위가 매우 크기에 BigInteger 사용
		// dp로 진행
		BigInteger[] dp = new BigInteger[251];
		dp[2] = BigInteger.valueOf(3);
		dp[1] = BigInteger.ONE;
		// 0C0 = 1임을 꼭 기억
		dp[0] = BigInteger.ONE;
		for(int i = 3; i < 251; i++){
			dp[i] = dp[i-2].multiply(BigInteger.TWO).add(dp[i-1]);
		}
		String s = null;
		while((s = br.readLine()) != null){
			System.out.println(dp[Integer.parseInt(s)]);
		}
	}
}

