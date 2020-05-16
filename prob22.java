import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class prob22 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		
		char[] E = { 'E', 'F', 'G', 'A', 'B', 'C', 'D', 'E', 'E' };
		int[] Ef = { 0, 1, 3, 5, 7, 8, 10, 12 };
		char[] A = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'A', 'A' };
		int[] Af = { 0, 2, 3, 5, 7, 8, 10, 12 };
		
				
				
				HashMap<Character, String> spots = new HashMap<Character, String>();
		spots.put('A', "5 E 0 A 12 A");
		spots.put('B', "7 E 2 A");
		spots.put('C', "8 E 3 A");
		spots.put('D', "10 E 5 A");
		spots.put('E', "0 E 12 E 7 A");
		spots.put('F', "1 E 8 A");
		spots.put('G', "3 E 10 A");
		
		while(true) {
			String str = f.readLine();
			if(str == null) { break; }
			
			if(str.length() == 1) {
				//use hashmap to find all places this note can be played
				System.out.println(spots.get(str.charAt(0)));
			} else {
				//find next note on string
				String[] arr = str.split(" ");
				if(arr[1].charAt(0) == 'E') {
					//E String
					int fret = Integer.parseInt(arr[0]);
					for(int i = 0; i < Ef.length; i++) {
						int val = Ef[i];
						if(val == fret) {
							System.out.println(E[i+1]);
							break;
						}
					}
				} else {
					//A String
					int fret = Integer.parseInt(arr[0]);
					for(int i = 0; i < Af.length; i++) {
						int val = Af[i];
						if(val == fret) {
							System.out.println(A[i+1]);
							break;
						}
					}
				}
			}

		}
		f.close();
		
		
		
	}

}
