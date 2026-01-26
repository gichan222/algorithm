package bj.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class BJ2733 {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T;
	static int[] byteArray;
	static int pointer;
	static String inst;
	static int index;
	static HashMap<Integer, Integer> bracket;
	static StringBuilder out = new StringBuilder();

	static void incPointer() {
		pointer = (pointer + 1) % 32768;
	}

	static void decPointer() {
		pointer = (pointer + 32767) % 32768;
	}

	static void addValue() {
		byteArray[pointer] = (byteArray[pointer] + 1) % 256;
	}

	static void subValue() {
		byteArray[pointer] = (byteArray[pointer] + 255) % 256;
	}

	static void print() {
		out.append((char) byteArray[pointer]);
	}

	static void frontBracket() {
		if (byteArray[pointer] == 0)
			index = bracket.get(index);
	}

	static void backBracket() {
		if (byteArray[pointer] != 0)
			index = bracket.get(index);
	}

	static boolean compile() {
		bracket = new HashMap<>();
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < inst.length(); i++) {
			char c = inst.charAt(i);
			if (c == '[') stack.push(i);
			else if (c == ']') {
				if (stack.isEmpty()) return false;
				int open = stack.pop();
				bracket.put(open, i);
				bracket.put(i, open);
			}
		}
		return stack.isEmpty();
	}

	static void comment() {
		while (index < inst.length() && inst.charAt(index) != 'N')
			index++;
	}

	static void brainFuck() throws IOException {
		StringBuilder instBuilder = new StringBuilder();
		String temp;

		while (!(temp = br.readLine()).equals("end")) {
			instBuilder.append(temp).append("N");
		}

		inst = instBuilder.toString();

		if (!compile()) {
			out.append("COMPILE ERROR");
			return;
		}

		for (index = 0; index < inst.length(); index++) {
			char c = inst.charAt(index);
			switch (c) {
				case '>': incPointer(); break;
				case '<': decPointer(); break;
				case '+': addValue(); break;
				case '-': subValue(); break;
				case '.': print(); break;
				case '[': frontBracket(); break;
				case ']': backBracket(); break;
				case '%': comment(); break;
				default: break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			byteArray = new int[32768];
			pointer = 0;

			out.append("PROGRAM #").append(i).append(":\n");
			brainFuck();
			out.append('\n');
		}

		System.out.print(out);
	}
}