import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class prob24 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		prob24 obj = new prob24();
		
//		Scanner s = new Scanner(System.in);
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		ArrayList<place> places = new ArrayList<place>();
		ArrayList<String> letters = new ArrayList<String>();
		ArrayList<String> words = new ArrayList<String>();
		
		//read in first section of word places and make objects
		while(true) {
			String in = f.readLine();
			if(in.equals("-------") || in.equals("------")) { break; }
			String[] arr = in.split(" ");
			boolean isVertical = false;
			if(arr[1].equals("V")) { isVertical = true; }
			place p = new place(Integer.parseInt(arr[0]), isVertical, Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
			places.add(p);
		}
		//read in second section of given letters
		while(true) {
			String in = f.readLine();
			if(in.equals("-------") || in.equals("------")) { break; }
			letters.add(in);
		}
		//read in third section of words
		while(true) {
			String in = f.readLine();
			if(in == null || in.equals("-------") || in.equals("------")) { break; }
			words.add(in);
		}
		//add letters
		for(place p : places) {
			for(String str : letters) {
				String[] arr = str.split(" ");
				int x = Integer.parseInt(arr[0]);
				int y = Integer.parseInt(arr[1]);
				char l = arr[2].charAt(0);
				p.fillInLetter(x, y, l);
			}
		}
		//check for words that fit
		for(place p : places) {
			for(String word : words) {
				p.addWordIfFits(word);
			}
		}
		
		for(place p : places) {
			String number = Integer.toString(p.wordNumber);
			if(number.length() == 1) { number = "0" + number; }
			System.out.println(number + " is " + p.word);
		}
		
		f.close();
		
	}
	
	public static class place {
		
		public int wordNumber;
		public char[] spot;
		public boolean isVertical;
		public int x;
		public int y;
		
		public String word;
		
		place(int wordNumber, boolean isVertical, int length, int x, int y){
			this.wordNumber = wordNumber;
			this.isVertical = isVertical;
			spot = new char[length];
			for(int i = 0; i < length; i++) {
				spot[i] = '.';
			}
			this.x = x;
			this.y = y;
		}
		
		public void fillInLetter(int x, int y, char l) {
			int xx = this.x;
			int yy = this.y;
			for(int i = 0; i < spot.length; i++) {
				if(xx == x && yy == y) {
					spot[i] = l;
				}
//				System.out.println("num: " + wordNumber + ", x: " + x + ", y: " + y + ", xx: " + xx + ", yy: " + yy + ", l: " + l);
				if(isVertical) { yy++; } else { xx++; }
			}
		}
		
		public void addWordIfFits(String str) {
			boolean match = true;
			if(str.length() == spot.length) {
				
				for(int i = 0; i < spot.length; i++) {
//					System.out.println()
					if(spot[i] == '.' || spot[i] != '.' && spot[i] == str.charAt(i)) {
						//It is good
					} else {
						match = false;
					}
				}
				
			} else { match = false; }
			if(match) {
				this.word = str;
			}
		}
	}

}
