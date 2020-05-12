import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class prob29 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		prob29 obj = new prob29();
		
//		Scanner s = new Scanner(System.in);
		BufferedReader f = new BufferedReader(new FileReader("input.txt"));
		
		ArrayList<String> file = new ArrayList<String>();
		ArrayList<String> errors = new ArrayList<String>();
		
		while(true) {
			String in = f.readLine();
			if(in == null) { break; }
			file.add(in);
		}
		
		for(String str: file) {
//			System.out.println(str);
		}
		
		//For checking Indents
		int indexCntr = 0;
		for(String str : file) {
			//should I check for all of these?
			if(str.equals("") || str.equals(" ") || str.equals("  ") || str.equals("   ")) { continue; }
			
			String[] arr = str.split(" ");
			String it = str.trim();
			String[] arr2 = it.split(" ");
			
			if (arr2[0].equals("ENDIF") || arr2[0].equals("NEXT") || arr2[0].equals("ENDFUNC")) {
				indexCntr -= 4;
//				System.out.println("Subtracting: " + (file.indexOf(str) + 1));
			}
//			System.out.println(str);
			
			if(obj.leadingSpaces(str) != indexCntr) {
				String error = Integer.toString(file.indexOf(str) + 1) + ":20 Unexpected indentation";
				errors.add(error);
//				System.out.println("um: " + indexCntr + ", " + obj.leadingSpaces(str));
			}
			if(arr2[0].equals("IF") || arr2[0].equals("FOR") || arr2[0].equals("FUNC")){
				indexCntr += 4;
//				System.out.println(str);
			}
			
			//check for trailing whitespace
			if(str.charAt(str.length()-1) == ' ') {
				String error = Integer.toString(file.indexOf(str) + 1) + ":30 Trailing whitespace";
				errors.add(error);
			}
		}
		
		//for variable declared but not used
		HashMap<String, Integer> vars = new HashMap<String, Integer>();
		for(String str : file) {
			//should I check for all of these?
			if(str.equals("") || str.equals(" ") || str.equals("  ") || str.equals("   ")) { continue; }
			String[] arr = str.split(" ");
			if(arr[0].equals("#")) { continue; }
			
			
			if(arr[0].equals("VAR")) {
				vars.put(("$" + arr[1]), file.indexOf(str)+1);
//			}
//			else if(arr[0].equals("FUNC")) {
//				for(int i = 2; i < arr.length - 1; i++) {
//					if(i < arr.length - 2) {
//						vars.put(("$" + arr[i].substring(0, arr[i].length()-1)), file.indexOf(str));
//					} else {
//						vars.put(("$" + arr[i]), file.indexOf(str)+1);
//					}
//				}
			} else {
				for(String seg : arr) {
					if( vars.containsKey(seg)) {
						vars.remove(seg);
					}
				}
			}
		}
		//every variable left wasn't used
		Iterator iterator = vars.entrySet().iterator(); 
        while(iterator.hasNext()) { 
            Map.Entry mapElement = (Map.Entry)iterator.next(); 
            int line = ((int)mapElement.getValue()); 
            String error = Integer.toString(line) + ":10 Variable declared but not used";
            errors.add(error); 
        } 
		
		//check for function documentation
        for(int i = 0; i < file.size(); i++) {
        	if(file.get(i).trim().split(" ")[0].equals("FUNC")) {
        		if(i-1 < 0 || !file.get(i-1).split(" ")[0].equals("#")) {
        			String error = Integer.toString(i+1) + ":40 Func declaration without documentation";
                    errors.add(error); 
        		} else {
        			String[] temp = file.get(i-1).split(" ");
        			if(!(temp[1] + "(").equals(file.get(i).trim().split(" ")[1])) {
//        				System.out.println((temp[1] + "(") + " , " + file.get(i).trim().split(" ")[1]);
        				String error = Integer.toString(i+1) + ":40 Func declaration without documentation";
                        errors.add(error); 
        			}
//        			System.out.println((temp[1] + "(") + " , " + file.get(i).trim().split(" ")[1]);
        		} 
        	}
        }
        int currentLine = 1;
        int[] errorCodes = { 10, 20, 30, 40 };
        while(errors.size() != 0) {
        	for(int i = 0; i < errors.size(); i++) {
        		String str = errors.get(i);
        		for(int check : errorCodes) {
        			if(str.split(" ")[0].equals(currentLine + ":" + check)) {
            			System.out.println(str);
            			errors.remove(str);
            			i--;
            		}
        		}
        	}
        	currentLine++;
        }
		
        f.close();

	}
	
	public int leadingSpaces(String str) {
		int cntr = 0;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == ' '){
				cntr++;
			} else {
				break;
			}
		}
		return cntr;
	}
	

}
