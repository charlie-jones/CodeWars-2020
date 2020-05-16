import java.util.*;
import java.io.*;
public class prob30 {
	static char[][] grid;
	static char[][] gridClone; // unused rn
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		int N = Integer.parseInt(f.readLine());
		grid = new char[N][N];
		for (int i = 0; i < N; i++) {
			grid[i] = f.readLine().toCharArray();
		}
		gridClone = grid.clone(); // unused rn
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(grid[i]));
//		} // PASS
		fill2x2();
		// pass top, right, bottom, left (outer --> inner)
		for (int i = 0; i < (N+1)/2; i++) {
			box(i, i, i, N-i-1);
			box(i, i, i, N-i-1);
		}
	}
	
	static void box(int i1, int i2, int j1, int j2) {
		String t = "";
		if (i1==i2) {
			t = grid[i1].toString().substring(j1,j2+1);
		} else {
			for (int c = i1; c <= i2; c++) {
				t += grid[c][j1];
			}
		}
		int fd = -1;
		int fh = -1;
		if (t.contains(".")) {
			fd = t.indexOf('.');
		}
		if (t.contains("#")) {
			fh = t.indexOf('#');
		}
	}
	
	static char is2x2(int i, int j) {
		// top left, top right, bottom right, bottom left
		Set<Character> types = new HashSet<Character>();
		types.add('#');
		types.add('.');
		for (char t : types) {
			try {
				if (grid[i][j-1] == t && grid[i-1][j-1] == t && grid[i-1][j] == t) return t;
			} catch (Exception e) {}
			try {
				if (grid[i][j+1] == t && grid[i-1][j+1] == t && grid[i-1][j] == t) return t;
			} catch (Exception e) {}
			try {
				if (grid[i][j+1] == t && grid[i+1][j+1] == t && grid[i+1][j] == t) return t;
			} catch (Exception e) {}
			try {
				if (grid[i][j-1] == t && grid[i+1][j-1] == t && grid[i+1][j] == t) return t;
			} catch (Exception e) {}
		}
		return 'c'; // not 2x2
	}
	
	static void fill2x2() {
		int count;
		do {
			count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					char t = is2x2(i,j);
					if (t != 'c') { 
						grid[i][j] = t == '#' ? '.' : '#';
						count++;
					}
				}
			}
		} while (count != 0);
	}
	
	static String stringFill(String str) {
		int firstDot = -1;
		int firstHash = -1;
		if (str.contains(".")) {
			firstDot = str.indexOf('.');
		}
		if (str.contains("#")) {
			firstHash = str.indexOf('#');
		}
		return "";
	}
	
}
