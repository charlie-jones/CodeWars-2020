import java.io.*;
import java.util.*;

public class prob23 {
	static int N;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		int N = Integer.parseInt(f.readLine());
		HashMap<Character, Character> flip = new HashMap<Character, Character>();
		flip.put('a', 'e');
		flip.put('e', 'a');
		
		flip.put('b', 'q');
		flip.put('q', 'b');
		
		flip.put('d', 'p');
		flip.put('p', 'd');
		
		flip.put('h', 'y');
		flip.put('y', 'h');
		
		flip.put('m', 'w');
		flip.put('w', 'm');
		
		flip.put('n', 'u');
		flip.put('u', 'n');
		
		for (int i = 0; i < N; i++) {
	        char[] cArr = f.readLine().toLowerCase().replaceAll("\\p{Punct}","").toCharArray(); 
	        char[] flipped = new char[cArr.length];
	        int siz = cArr.length-1;
	        for (int j = 0; j < cArr.length; j++) {
	        	flipped[j] = cArr[siz-j];
	        	if (flip.containsKey(cArr[siz-j])) {
	        		flipped[j] = flip.get(cArr[siz-j]);
	        	}
	        }
	        String condensedF = String.valueOf(flipped).replaceAll("\\s","");
	        String condensedA = String.valueOf(cArr).replaceAll("\\s","");
	        String correct = " (not) ";
	        if (condensedF.equals(condensedA)) {
	        	correct = " (is) ";
	        }
	        System.out.println(String.valueOf(cArr) + correct + String.valueOf(flipped));
		}
		
	}


}
