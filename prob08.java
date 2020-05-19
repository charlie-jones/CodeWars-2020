import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class prob08 {
	public static void main (String[] args) throws IOException {
		Scanner s = new Scanner(new FileReader("input.txt"));
		while (s.hasNextLine()) {
			solve(s.nextLine());
			
		}
		s.close();
	}
	
	static void solve(String line) {
		if (line.isEmpty()) return;
		String[] in = line.split(" ");
		int tot_chars = 0;
		int i;
		for (i = 0; i < in.length; i++) {
			if (tot_chars+in[i].length()<=80) {
				tot_chars+=in[i].length();
				if (tot_chars+1 > 80) {
					i++;
					break;
				} else {
					tot_chars++;
				}
			} else {
				break;
			}
		}
		for (int j = 0; j < i-1; j++) {
			System.out.print(in[j] + " ");
		}
		System.out.println(in[i-1]);
		String st = "";
		for (int j = i; j < in.length; j++) {
			st += in[j] + " ";
		}
		
		solve(st);
	}

}

