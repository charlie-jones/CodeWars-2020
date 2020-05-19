import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

import java.util.HashMap;



public class prob06 {

	public static void main (String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		

		String line = br.readLine();

		

		while (line != null) {

			

			solve(line);

			line = br.readLine();

		}

		

		

	}

	

	public static void solve(String line) {

		HashMap<Integer, String> hm = new HashMap<Integer, String>();

		hm.put(1, "B");

		hm.put(2, "BB");

		hm.put(3, "BBB");

		hm.put(4, "BW");

		hm.put(5, "W");

		hm.put(6, "WB");

		hm.put(7, "WBB");

		hm.put(8, "WBBB");

		hm.put(9, "BK");

		hm.put(10, "Z");

		hm.put(20, "ZZ");

		hm.put(30, "ZZZ");

		hm.put(40, "ZP");

		hm.put(50, "P");

		hm.put(60, "PZ");

		hm.put(70, "PZZ");

		hm.put(80, "PZZZ");

		hm.put(90, "ZB");

		hm.put(100, "B");

		hm.put(200, "BB");

		hm.put(300, "BBB");

		hm.put(400, "BG");

		hm.put(500, "G");

		hm.put(600, "GB");

		hm.put(700, "GBB");

		hm.put(800, "GBBB");

		hm.put(900, "BR");

		hm.put(1000, "R");

		

		String answer = "";

		

		int num = Integer.parseInt(line);

		int count = line.length() - 1;

		while (num > 0) {

			if (hm.get(num - (num % (int) Math.pow(10, count))) != null) {

				answer = answer + hm.get((num - (num % (int) Math.pow(10, count))));

			}

			

			num = num % (int) Math.pow(10, count);

			count--;

		}

		

		System.out.println(answer);

	}

}