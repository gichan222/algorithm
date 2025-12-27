package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BJ1560 {

	// BigInteger 자료형을 연습하는 문제
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger N = new BigInteger(br.readLine());

		if(N.equals(BigInteger.ONE)){
			System.out.println(1);
			return;
		}

		System.out.println(N.multiply(BigInteger.valueOf(2)).add(BigInteger.valueOf(-2)));
	}
}
