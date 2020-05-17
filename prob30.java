import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class prob30 {
	public static void main (String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		int n = Integer.parseInt(br.readLine());
		String cake[][] = new String[n][n];
		for (int i = 0; i < n; i++) {
			String in = br.readLine();
			for (int j = 0; j < n; j++) {
				cake[i][j] = String.valueOf(in.charAt(j));
			}
		}

		Node root = new Node(cake);
		String[][] answer = root.iterate(cake,0,0);
//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(cake[i]));
//		}
		
		// System.out.println(twoByTwoCheck(cake)); // PASS
		
		print2D(cake, n);
	}
	
	private static void print2D(String[][] cake, int n) {
		for (int i = 0; i < n; i++) {
			String ln = "";
			for (int j = 0; j < n; j++) {
				ln += cake[i][j];
			}
			System.out.println(ln);
		}
	}
	
	private static String[][] fillInGivens(String[][] arr) {
		String[][] answer = arr;
		
		for (int i = 1; i < arr.length - 1; i++) {
			for (int j = 1; j < arr.length - 1; j++) {
				//change to deal with corners, tops, sides, bottoms, and all 4 2x2 squares
				if (arr[i][j].equals("?") && arr[i][j - 1].equals(".") && arr[i - 1][j].equals(".") && arr[i - 1][j - 1].equals(".")) {
					arr[i][j] = "#";
				}
				
				if (arr[i][j].equals("?") && arr[i][j + 1].equals(".") && arr[i + 1][j].equals(".") && arr[i + 1][j + 1].equals(".")) {
					arr[i][j] = "#";
				}
				
				if (arr[i][j].equals("?") && arr[i][j - 1].equals("#") && arr[i - 1][j].equals("#") && arr[i - 1][j - 1].equals("#")) {
					arr[i][j] = ".";
				}
				
				if (arr[i][j].equals("?") && arr[i][j + 1].equals("#") && arr[i + 1][j].equals("#") && arr[i + 1][j + 1].equals("#")) {
					arr[i][j] = ".";
				}
				
				if (arr[i][j].equals("?") && arr[i][j - 1].equals("#") && arr[i + 1][j].equals("#") && arr[i + 1][j - 1].equals("#")) {
					arr[i][j] = ".";
				}
				
				if (arr[i][j].equals("?") && arr[i][j - 1].equals(".") && arr[i + 1][j].equals(".") && arr[i + 1][j - 1].equals(".")) {
					arr[i][j] = ".";
				}
				
				if (arr[i][j].equals("?") && arr[i][j - 1].equals("#") && arr[i - 1][j].equals("#") && arr[i - 1][j - 1].equals("#")) {
					arr[i][j] = "#";
				}
				
				if (arr[i][j].equals("?") && arr[i - 1][j].equals(".") && arr[i][j + 1].equals(".") && arr[i - 1][j + 1].equals(".")) {
					arr[i][j] = "#";
				}
			}
		}
		
		return answer;
	}
	
	private static boolean connectedCheck(String[][] arr) {
		int hashes = 0;
		int dots = 0;
		int[] dotStartPoint = new int[2];
		int[] hashStartPoint = new int[2];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j].equals(".")) {
					dots++;
					dotStartPoint[0] = i;
					dotStartPoint[1] = j;
				}
				
				if (arr[i][j].equals("#")) {
					hashes++;
					hashStartPoint[0] = i;
					hashStartPoint[1] = j;
				}
			}
		}
		
		if (expand(arr, new ArrayList<String>(), ".", dotStartPoint) == dots && expand(arr, new ArrayList<String>(), "#", hashStartPoint) == hashes) {
			return true;
		}
		
		return false;
	}
	
	private static int expand(String[][] arr, ArrayList<String> pointsUsed, String type, int[] point) {
		int answer = 0;
		int i = point[0];
		int j = point[1];
		if (i - 1 > 0) {
			if (arr[i - 1][j].equals(type) && pointsUsed.contains((i - 1) + "" + j) == false ) {
				pointsUsed.add((i - 1) + "" + j);
				answer++;
				answer = answer + expand(arr, pointsUsed, type, new int[] {i - 1, j });
			}
		}
		
		if (j - 1 > 0) {
			if (arr[i][j - 1].equals(type) && pointsUsed.contains(i + "" + (j - 1)) == false ) {
				pointsUsed.add(i + "" + (j - 1));
				answer++;
				answer = answer + expand(arr, pointsUsed, type, new int[] {i, j - 1});
			}
		}
		
		if (j + 1 < arr.length) {
			if (arr[i][j + 1].equals(type) && pointsUsed.contains(i + "" + (j + 1)) == false ) {
				pointsUsed.add(i + "" + (j + 1));
				answer++;
				answer = answer + expand(arr, pointsUsed, type, new int[] {i, j + 1});
			}
		}
		
		if (i + 1 > 0) {
			if (arr[i + 1][j].equals(type) && pointsUsed.contains((i + 1) + "" + j) == false ) {
				pointsUsed.add((i + 1) + "" + j);
				answer++;
				answer = answer + expand(arr, pointsUsed, type, new int[] {i + 1, j});
			}
		}
		
		return answer;
	}
	
	private static boolean loopCheck(String[][] arr) {
		//checks for 3x3 loops and for an outer loop
		
		
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[0][i].equals(arr[0][i + 1])) {
				return false;
			}
		}
		
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[arr.length - 1][i].equals(arr[arr.length - 1][i + 1])) {
				return false;
			}
		}
		
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i][0].equals(arr[i + 1][0])) {
				return false;
			}
		}
		
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i][arr.length - 1].equals(arr[i + 1][arr.length - 1])) {
				return false;
			}
		}
		
		//checking for 3x3 loops
		for (int i = 1; i < arr.length - 1; i++) {
			for (int j = 1; j < arr.length - 1; j++) {
				if (arr[i-1][j-1].equals("#") && arr[i-1][j].equals("#") && arr[i-1][j+1].equals("#") && arr[i][j-1].equals("#") && arr[i][j+1].equals("#") && arr[i+1][j-1].equals("#") && arr[i+1][j].equals("#") && arr[i+1][j+1].equals("#")) {
					return false;
				}
				
				if (arr[i-1][j-1].equals(".") && arr[i-1][j].equals(".") && arr[i-1][j+1].equals(".") && arr[i][j-1].equals(".") && arr[i][j+1].equals(".") && arr[i+1][j-1].equals(".") && arr[i+1][j].equals(".") && arr[i+1][j+1].equals(".")) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private static boolean twoByTwoCheck(String[][] grid) { // true = cake is free of 2x2
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (twoBytwoHelp(grid, i, j)) return false;
			}
		}
		return true;
	}
	static boolean twoBytwoHelp(String[][] grid, int i, int j) { // true = [i][j] is part of a 2x2
		// top left, top right, bottom right, bottom left
		String t = grid[i][j];
		try {
			if (grid[i][j-1] == t && grid[i-1][j-1] == t && grid[i-1][j] == t) return true;
		} catch (Exception e) {}
		try {
			if (grid[i][j+1] == t && grid[i-1][j+1] == t && grid[i-1][j] == t) return true;
		} catch (Exception e) {}
		try {
			if (grid[i][j+1] == t && grid[i+1][j+1] == t && grid[i+1][j] == t) return true;
		} catch (Exception e) {}
		try {
			if (grid[i][j-1] == t && grid[i+1][j-1] == t && grid[i+1][j] == t) return true;
		} catch (Exception e) {}
		return false; // not 2x2
	}
	
	public static int[] findNextQuestionMark(String[][] arr) {
		int[] answer = new int[2];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j].equals("?")) {
					answer[0] = i;
					answer[1] = j;
					return answer;
				}
			}
		}
		
		return null;
	}
	
	static class Node {
		private String[][] cake; 
	
		public Node (String[][] arr) {
			cake = arr.clone();
		}
		
		public String[][] iterate(String[][] arr, int i, int j) {
			if (!twoByTwoCheck(arr)) {
				System.out.println("branch ended");
				return null;
			}
			System.out.println("make branch");
			String[][] leftBranch = arr.clone();
			String[][] rightBranch = arr.clone();
			leftBranch[i][j] = ".";
			rightBranch[i][j] = "#";
			
//			int[] next = findNextQuestionMark(cake); // OLD code
//			int[] tmpLocation = next==null ? null : next.clone();
//			if (tmpLocation == null) {
//				return arr;
//			}
			
			// INSTEAD, find ALL available question marks
			// for loop here
			
			String[][] left = iterate(leftBranch, i,j); //  change from i,j to location of a question mark
			String[][] right = iterate(rightBranch,i,j); //  change to above
			
			if (left == null && right == null) {
				return null;
			}
			
			if (left == null && right != null) {
				return right;
			}
			
			return left;
		}
	}
}
