package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ9342 {
	static final String CORRECT = "Infected!";
	static final String INCORRECT = "Good";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		// 정규표현식 패턴
		// ^ : 시작
		// [A-F]? : A~F 중 하나가 있거나 없음
		// A+ : A가 1개 이상
		// F+ : F가 1개 이상
		// C+ : C가 1개 이상
		// [A-F]? : A~F 중 하나가 있거나 없음
		// $ : 끝
		String pattern = "^[A-F]?A+F+C+[A-F]?$";

		while (T-- > 0) {
			String line = br.readLine();

			if (line.matches(pattern)) {
				System.out.println(CORRECT);
			} else {
				System.out.println(INCORRECT);
			}
		}
	}
}