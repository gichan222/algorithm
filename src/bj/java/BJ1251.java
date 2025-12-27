package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BJ1251 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// a b c d e f g h i j k l m n o p q r s t u v w x y z
		String s = br.readLine();

		List<String> list = new ArrayList<>();

		for (int i = 1; i < s.length() - 1; i++) {
			for (int j = i + 1; j < s.length(); j++) {

				StringBuilder sb = new StringBuilder();

				sb.append(new StringBuilder(s.substring(0, i)).reverse());
				sb.append(new StringBuilder(s.substring(i, j)).reverse());
				sb.append(new StringBuilder(s.substring(j)).reverse());

				list.add(sb.toString());
			}
		}

		Collections.sort(list);
		System.out.println(list.get(0));
	}
}
