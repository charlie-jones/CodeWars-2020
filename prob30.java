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

		boolean[][] isVisited = new boolean[n][n];

		for (boolean[] b : isVisited) {

			Arrays.fill(b, false);

		}

		root.iterate(cake,0,0, isVisited); // change from 0,0 to a location found to have a question mark

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
		if (t.equals("?")) return false;
		try {

			if (grid[i][j-1].equals(t) && grid[i-1][j-1].equals(t) && grid[i-1][j].equals(t)) return true;

		} catch (Exception e) {}

		try {

			if (grid[i][j+1].equals(t) && grid[i-1][j+1].equals(t) && grid[i-1][j].equals(t)) return true;

		} catch (Exception e) {}

		try {

			if (grid[i][j+1].equals(t) && grid[i+1][j+1].equals(t) && grid[i+1][j].equals(t)) return true;

		} catch (Exception e) {}

		try {

			if (grid[i][j-1].equals(t) && grid[i+1][j-1].equals(t) && grid[i+1][j].equals(t)) return true;

		} catch (Exception e) {}

		return false; // not 2x2

	}

	

	public static ArrayList<Integer>[] findAvailableQuestionMarks(String[][] arr) {

		ArrayList<Integer>[] answer = new ArrayList[2];

		answer[0] = new ArrayList<Integer>();

		answer[1] = new ArrayList<Integer>();

		for (int i = 0; i < arr.length; i++) {



			for (int j = 0; j < arr.length; j++) {



				if (arr[i][j].equals("?")) {

					

					answer[0].add(i);



					answer[1].add(j);



				}



			}



		}



		

		

		return answer;



	}

	

	static class Node {

		private String[][] cake; 

	

		public Node (String[][] arr) {

			cake = arr.clone();

		}

		

		public String[][] iterate(String[][] arr, int i, int j, boolean[][] visited) {

			if (!twoByTwoCheck(arr)) {

				System.out.println("branch ended");

				return null;

			}

			visited[i][j] = true;

			String[][] leftBranch = arr.clone();

			String[][] rightBranch = arr.clone();

			leftBranch[i][j] = ".";

			rightBranch[i][j] = "#";

			

//			int[] next = findNextQuestionMark(cake); // OLD code

//			int[] tmpLocation = next==null ? null : next.clone();

//			if (tmpLocation == null) {

//				return arr;

//			}

			ArrayList<Integer>[] qMarks = findAvailableQuestionMarks(arr).clone();

			// System.out.println(Arrays.toString(qMarks)); // PASS

			int siz = arr.length;

			String[][] left = new String[siz][siz];

			String[][] right = new String[siz][siz];

			int len = qMarks[0].size();
			System.out.println(len);
			System.out.println("reenter");
			for (int a = 0; a < len; a++) {

				System.out.println("new branch");

				int iPos = qMarks[0].get(a);

				int jPos = qMarks[1].get(a);

				if (visited[iPos][jPos]) continue;

				visited[iPos][jPos] = true;

				try {

					left = iterate(leftBranch, iPos, jPos, visited).clone(); 

				} catch (Exception e) {}

				try {

					right = iterate(rightBranch, iPos, jPos, visited).clone(); 

				} catch (Exception e) {}

			}

			

			visited[i][j] = false;

			

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