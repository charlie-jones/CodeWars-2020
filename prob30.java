import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class prob30 {
	
	
	public static void main (String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		long time = System.currentTimeMillis();
		int n = Integer.parseInt(f.readLine());
		char[][] cake = new char[n][n];
		int count = 0;
		//fill Array
		for (int i = 0; i < n; i++) {
			String in = f.readLine();
			for (int j = 0; j < n; j++) {
				if(in.charAt(j) == '?') {
					count++;
				}
				cake[i][j] = in.charAt(j);
			}
		}
		//Mark positions of ?s
		int[] validSpots = new int[count];
		int spot = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(cake[i][j] == '?') {
					validSpots[spot] = i*n + j;
					spot++;
//					System.out.println("(" + i + ", " + j + ")");
				}
			}
		}
		//loop
		for(int val = 0; val < count; val++) {
			int index = validSpots[val];
//			System.out.println("-----");
//			System.out.println(index);
			int j = index % n;
			int i = (index-j)/n;
			if(cake[i][j] == '?') {
				//start with this guess
				cake[i][j] = '#';
			} else if (cake[i][j] == '#') {
				//try the other
				cake[i][j] = '.';
			} else if (cake[i][j] == '.') {
				cake[i][j] = '?';
				//Error: go back and fix
				if(val == 0) {
					System.out.println("Failed To Validate at Nothing");
					break;
				}
				val -= 2;
				continue;
			}
			//if not valid
//			print2D(cake, n);
//			System.out.println("grids: " + noGrids(cake, n));
//			System.out.println("connected: " + connectedCheck(cake));
			if(!(goodBorders(cake, n) && noGrids(cake, n) && connectedCheck(cake))) {
//				System.out.println("Try Again");
				//try again
				val -= 1;
			} // else, move on
		}
		print2D(cake, n);
		long timeEllapsed = System.currentTimeMillis() - time;
		System.out.println("Milliseconds: " + timeEllapsed);
		

	}
	
	private static boolean goodBorders(char[][] arr, int n) {
		
		int i = 0;
		int j = 0;
		char firstType = '?';
		char secondType = '?';
		boolean ontoSecond = false;
		boolean returnedToFirst = false;
		for(int loop = 0; loop < 4; loop++) {
//			System.out.println("-At: (" + i + ", " + j + ")");
			while(i < n && j < n && i >= 0 && j >= 0) {
				if(firstType == '?' && arr[i][j] != '?') {
					firstType = arr[i][j];
				} else {
					if(!ontoSecond) {
						if(arr[i][j] != firstType && arr[i][j] != '?') {
							ontoSecond = true;
//							System.out.println("Onto Second: (" + i + ", " + j + ")");
							secondType = arr[i][j];
						}
					} else {
						if(!returnedToFirst) {
							if(arr[i][j] != secondType && arr[i][j] != '?') {
								returnedToFirst = true;
//								System.out.println("Returned To First: (" + i + ", " + j + ")");
							}
						} else {
							if(arr[i][j] != firstType && arr[i][j] != '?') {
								return false;
							}
						}
					}
				}
				switch(loop) {
				case 0:
					i++;
					break;
				case 1:
					j++;
					break;
				case 2:
					i--;
					break;
				case 3:
					j--;
					break;
				}
			}
			switch(loop) {
			case 0:
				i--;
				break;
			case 1:
				j--;
				break;
			case 2:
				i++;
				break;
			}
			
		}

		return true;
	}
	
	private static boolean noGrids(char[][] arr, int n) {
		for(int i = 0; i < n-1; i++) {
			for(int j = 0; j < n-1; j++) {
				char checker = arr[i][j];
				if(checker != '?' && arr[i+1][j] == checker && arr[i][j+1] == checker && arr[i+1][j+1] == checker) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean connectedCheck(char[][] arr) {
//		print2D(arr, arr.length);
		int hashes = 0;
		int dots = 0;
		int[] dotStartPoint = new int[2];
		int[] hashStartPoint = new int[2];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] == '.') {
					dots++;
					dotStartPoint[0] = i;
					dotStartPoint[1] = j;
				} else if (arr[i][j] == '#') {
					hashes++;
					hashStartPoint[0] = i;
					hashStartPoint[1] = j;
				}
			}
		}
		if(expand(arr, '.', dotStartPoint) + 1 == dots) {
			pointsUsed.clear();
			if(expand(arr, '#', hashStartPoint) + 1 == hashes) {
				pointsUsed.clear();
				return true;
			} else {
				pointsUsed.clear();
			}
		} else {
			pointsUsed.clear();
		}
		
		return false;
	}
	//outside for global access and consistency
	public static ArrayList<String> pointsUsed = new ArrayList<String>();
	
	private static int expand(char[][] arr, char type, int[] point) {
		int answer = 0;
		int i = point[0];
		int j = point[1];
		pointsUsed.add(i + "-" + j);
//		System.out.println(type + " : " + "Current Spot: (" + i + ", " + j + ")");
//		System.out.println("________________________________Points" + pointsUsed.toString());
		if (i - 1 >= 0) {
			if ((arr[i - 1][j] == type ||  arr[i - 1][j] == '?') && pointsUsed.contains((i - 1) + "-" + j) == false ) {
//				System.out.println("Current Spot: (" + i + ", " + j + ")" + "UP");
//				pointsUsed.add((i - 1) + "" + j);
				if(!(arr[i - 1][j] == '?')) {
					answer++;
//					System.out.println("(" + (i-1) + ", " + (j) + ") - Plus");
				}
				answer = answer + expand(arr, type, new int[] {i - 1, j });
			}
		}
		
		if (j - 1 >= 0) {
			if ((arr[i][j - 1] == type || arr[i][j - 1] == '?') && pointsUsed.contains(i + "-" + (j - 1)) == false ) {
//				System.out.println("Current Spot: (" + i + ", " + j + ")" + "LEFT");
//				pointsUsed.add(i + "" + (j - 1));
				if(!(arr[i][j - 1] == '?')) {
					answer++;
//					System.out.println("(" + (i) + ", " + (j-1) + ") - Plus");
				}
				answer = answer + expand(arr, type, new int[] {i, j - 1});
			}
		}
		
		if (j + 1 < arr.length) {
			if ((arr[i][j + 1] == type || arr[i][j + 1] == '?') && pointsUsed.contains(i + "-" + (j + 1)) == false ) {
//				System.out.println("Current Spot: (" + i + ", " + j + ")" + "RIGHT");
//				pointsUsed.add(i + "" + (j + 1));
				if(!(arr[i][j + 1] == '?')) {
					answer++;
//					System.out.println("(" + (i) + ", " + (j+1) + ") - Plus");
				}
				answer = answer + expand(arr, type, new int[] {i, j + 1});
			}
		}
		
		if (i + 1 < arr.length) {
			if ((arr[i + 1][j] == type || arr[i + 1][j] == '?') && pointsUsed.contains((i + 1) + "-" + j) == false ) {
//				System.out.println("Current Spot: (" + i + ", " + j + ")" + "Down");
//				pointsUsed.add((i + 1) + "" + j);
				if(!(arr[i + 1][j] == '?')) {
					answer++;
//					System.out.println("(" + (i+1) + ", " + (j) + ") - Plus");
				}
				answer = answer + expand(arr, type, new int[] {i + 1, j});
			}
		}
		return answer;
	}
	
	private static void print2D(char[][] cake, int n) {
		for (int i = 0; i < n; i++) {
			String ln = "";
			for (int j = 0; j < n; j++) {
				ln += cake[i][j];
			}
			System.out.println(ln);
		}
	}
	
	
}
