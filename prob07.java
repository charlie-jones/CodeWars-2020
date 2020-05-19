import java.io.BufferedReader;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.util.HashMap;



public class prob07 {

	public static void main (String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		String line = br.readLine();

		String[] tmp = new String[2];

		HashMap<String, String> hm = new HashMap<String, String>();

		

		hm.put("BLUE RED", "PURPLE");

		hm.put("RED BLUE", "PURPLE");

		hm.put("YELLOW BLUE", "GREEN");

		hm.put("BLUE YELLOW", "GREEN");

		hm.put("YELLOW RED", "ORANGE");

		hm.put("RED YELLOW", "ORANGE");

		hm.put("YELLOW WHITE", "LIGHT YELLOW");

		hm.put("WHITE YELLOW", "LIGHT YELLOW");

		hm.put("BLACK YELLOW", "DARK YELLOW");

		hm.put("YELLOW BLACK", "DARK YELLOW");

		hm.put("ORANGE WHITE", "LIGHT ORANGE");

		hm.put("WHITE ORANGE", "LIGHT ORANGE");

		hm.put("ORANGE BLACK", "DARK ORANGE");

		hm.put("BLACK ORANGE", "DARK ORANGE");

		hm.put("WHITE GREEN", "LIGHT GREEN");

		hm.put("GREEN WHITE", "LIGHT GREEN");

		hm.put("BLACK GREEN", "DARK GREEN");

		hm.put("GREEN BLACK", "DARK GREEN");

		hm.put("PURPLE WHITE", "LIGHT PURPLE");

		hm.put("WHITE PURPLE", "LIGHT PURPLE");

		hm.put("BLACK PURPLE", "DARK PURPLE");

		hm.put("PURPLE BLACK", "DARK PURPLE");

		hm.put("WHITE RED", "LIGHT RED");

		hm.put("RED WHITE", "LIGHT RED");

		hm.put("BLACK RED", "DARK RED");

		hm.put("RED BLACK", "DARK RED");

		hm.put("WHITE BLUE", "LIGHT BLUE");

		hm.put("BLUE WHITE", "LIGHT BLUE");

		hm.put("BLACK BLUE", "DARK BLUE");

		hm.put("BLUE BLACK", "DARK BLUE");

	

		while (line != null) {

			tmp = line.split(" ");

			if (tmp[0].equals(tmp[1])) {

				System.out.println(tmp[0] + "");

			}

			else {

				System.out.println(hm.get(line));

			}

			line = br.readLine();

			

		}

		

	}

}