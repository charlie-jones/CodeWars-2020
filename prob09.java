import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;



public class prob09 {

	public static void main (String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		String line = br.readLine();

		String[] tmp2 = new String[2];

		int[] tmp = new int[2];

		tmp2 = line.split(" ");

		tmp[0] = Integer.parseInt(tmp2[0]);

		tmp[1] = Integer.parseInt(tmp2[1]);

		while(tmp[0] != 0 || tmp[1] != 0) {

			int t = (tmp[0] * 60 + tmp[1]);

			if ((t) <= 1500) {

				int min = (3000 - t) / 60;

				int secs = 60 - tmp[1];

				System.out.println("Time remaining " + min + " minutes and " + secs + " seconds");

			}

			

			if (t > 1500 && t <= 3000) {

				int min = (3000 - t) / 60;

				int secs = 60 - tmp[1];

				System.out.println("Time remaining " + min + " minutes and " + secs + " seconds (we'll need both sides)");

			}

			

			if (t > 3000) {

				int min = tmp[0] - 50;

				System.out.println("Time remaining -" + min + " minutes and -" + tmp[1] + " seconds (we're gonna need a bigger record)");

			}

			

			line = br.readLine();

			tmp2 = line.split(" ");

			tmp[0] = Integer.parseInt(tmp2[0]);

			tmp[1] = Integer.parseInt(tmp2[1]);

		}

	}

}