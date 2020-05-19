import java.io.BufferedReader;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.util.*;



public class prob30 {
	static String[][] out;
	static int states = 0;
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

		root.iterate(0,0); // change from 0,0 to a location found to have a question mark

//		for (int i = 0; i < n; i++) {

//			System.out.println(Arrays.toString(cake[i]));

//		}

		

		// System.out.println(twoByTwoCheck(cake)); // PASS

		

		print2D(root.outCake.clone(), n);

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
	
	static boolean inBounds(int x, int y, int N) {
		if (x < 0 || x >= N || y < 0 || y >= N) {// Boundaries for *possible replacement
	        return false; 
		}
		return true;
	}
	static void fillRoom(int x, int y, int group, String[][] arr, int[][] groups, String type) {
		if (!(arr[x][y].equals(type) || arr[x][y].equals("?"))) return;
		groups[x][y] = group;
	    if (inBounds(x+1,y,arr.length) && groups[x+1][y] == -1) fillRoom(x+1, y, group,arr,groups,type); 
	    if (inBounds(x-1,y,arr.length) && groups[x-1][y] == -1) fillRoom(x-1, y, group,arr,groups,type); 
	    if (inBounds(x,y+1,arr.length) && groups[x][y+1] == -1) fillRoom(x, y+1, group,arr,groups,type); 
	    if (inBounds(x,y-1,arr.length) && groups[x][y-1] == -1) fillRoom(x, y-1, group,arr,groups,type);  
	}
	private static int connectedCheck(String[][] arr, int[][] groups) {
		int gr = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j <  arr.length; j++) {
				// reset
				ArrayList<Integer>[] qMarks = findAvailableQuestionMarks(arr); 
				int len = qMarks[0].size();
				for (int a = 0; a < len; a++) {
					//System.out.println("new branch");
					int iPos = qMarks[0].get(a);
					int jPos = qMarks[1].get(a);
					groups[iPos][jPos] = -1;
				}
				if (groups[i][j] == -1 && !arr[i][j].equals("?")) {
					fillRoom(i,j,++gr,arr,groups,arr[i][j]); // gr starts at 1
				}
			}
		}
		return gr;
	}

	private static boolean loopCheck(String[][] arr) { // true = no loop problem

		//checks for 3x3 loops and for an outer loop

		

		

		for (int i = 0; i < arr.length - 1; i++) {

			if (!(arr[0][i].equals(arr[0][i + 1]))) {

				return true;

			}

		}

		

		for (int i = 0; i < arr.length - 1; i++) {

			if (!(arr[arr.length - 1][i].equals(arr[arr.length - 1][i + 1]))) {

				return true;

			}

		}

		

		for (int i = 0; i < arr.length - 1; i++) {

			if (!(arr[i][0].equals(arr[i + 1][0]))) {

				return true;

			}

		}

		

		for (int i = 0; i < arr.length - 1; i++) {

			if (!(arr[i][arr.length - 1].equals(arr[i + 1][arr.length - 1]))) {

				return true;

			}

		}

		

		return false;

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
		private String[][] outCake; 
	
		static boolean end = false;
		public Node (String[][] arr) {

			cake = arr.clone();
			outCake=cake;
		}
		
		public void iterate(int i, int j) {
			if (end) return;
			ArrayList<Integer>[] qMarks = findAvailableQuestionMarks(cake);
			int len = qMarks[0].size();
			//print2D(arr, arr.length);
			if (len == 0) {
				
				outCake = cake;
//				print2D(outCake,cake.length);
//				System.out.println("--------------");
			}
			print2D(outCake,cake.length);
			System.out.println("--------------");
			cake[i][j] = ".";
			int[][] Gs = new int[cake.length][cake.length];
			for (int[] b : Gs) {
				Arrays.fill(b, -1);
			}
			boolean dotsOk = (twoByTwoCheck(cake) && loopCheck(cake) && connectedCheck(cake, Gs) <= 2); // check if "loopCheck works"
//			int[] blah = new int[100000];
//			for (int a = 0; a < 100000; a++) { // simulates a connected check
//				blah[a] += 5;
//			}
			cake[i][j] = "#";
			Gs = new int[cake.length][cake.length];
			for (int[] b : Gs) {
				Arrays.fill(b, -1);
			}
			boolean hashesOk = (twoByTwoCheck(cake) && loopCheck(cake) && connectedCheck(cake, Gs) <= 2);
//			for (int a = 0; a < 100000; a++) { // simulates a connected check
//				blah[a] += 5;
//			}
			cake[i][j] = "?";
			if (dotsOk || hashesOk) {
				for (int a = 0; a < len; a++) {
					//System.out.println("new branch");
					
					int iPos = qMarks[0].get(a);
					int jPos = qMarks[1].get(a);
					if (dotsOk) {
						cake[i][j] = ".";
						iterate(iPos,jPos);
					}
					if (hashesOk) {
						cake[i][j] = "#";
						iterate(iPos,jPos);
					}
				}
			}
			
			//System.out.println("branch finished");
			//System.out.println(++states); // # program calls on iterate -- then find the bigO of 1 pass thru iterate 

		}

	}

}