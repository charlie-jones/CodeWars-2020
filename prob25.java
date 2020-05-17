import java.io.*;
import java.util.*;

public class prob25 {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		HashMap<String, String> systems = new HashMap<String, String>();
		String[] in = f.readLine().split(" ");
		int numSys = Integer.parseInt(in[0]);
		int numQ = Integer.parseInt(in[1]);
		for (int i = 0; i < numSys; i++) {
			String[] sys = f.readLine().split(" ");
			systems.put(sys[1], sys[0]);
		}
		for (int i = 0; i < numQ; i++) {
			String ln = f.readLine();
			String[] group = ln.split(" ");
			int x1 = getX(systems.get(group[0]));
			int x2 = getX(systems.get(group[1]));
			int y1 = getY(systems.get(group[0]));
			int y2 = getY(systems.get(group[1]));
			
			int xDiff = Math.abs(x1-x2);
			int yDiff = Math.abs(y1-y2);
			int tot = 0;
			int topX = x2;
			if (y1 < y2) {
				topX = x1;
			}
			if (xDiff >= 2*yDiff) {
				tot = xDiff;
			} else {
				if (topX % 2 != 0 && xDiff %2 != 0) {
					tot++;
				} 
				tot += (xDiff)/2 + yDiff;
			}
			System.out.println(ln + " " + tot);
		}
	}
	static int getX(String str) {
		return Integer.parseInt(str.substring(0,2));
	}
	static int getY(String str) {
		return Integer.parseInt(str.substring(2));
	}
}
