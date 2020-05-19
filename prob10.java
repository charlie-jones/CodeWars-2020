import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;



public class prob10 {

	public static void main (String[] args) throws IOException {

		//MIGHT THIS CODE NOTE WORK -- replacing any random V or smthn

		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		String sentence = br.readLine();

		String sentence2 = sentence;

		String line = br.readLine();

		ArrayList<String> nouns = new ArrayList<String>();

		ArrayList<String> adverbs = new ArrayList<String>();

		ArrayList<String> verbs = new ArrayList<String>();

		ArrayList<String> adjectives = new ArrayList<String>();

		line = br.readLine();

		while (line.equals("ADVERBS") == false) {

			nouns.add(line);

			line = br.readLine();

		}

		line = br.readLine();

		while (line.equals("VERBS") == false) {

			adverbs.add(line);

			line = br.readLine();

		}

		line = br.readLine();

		while (line.equals("ADJECTIVES") == false) {

			verbs.add(line);

			line = br.readLine();

		}

		line = br.readLine();

		while (line.equals("END") == false) {

			adjectives.add(line);

			line = br.readLine();

		}

		int count = 0;

		

		while (sentence.contains("AJ")) {

			String v = adjectives.get(count);

			sentence = sentence.replaceFirst("AJ", v);

			

			adjectives.remove(v);

		}



		while (sentence.contains("AV")) {

			String v = adverbs.get(count);

			sentence = sentence.replaceFirst("AV", v);

		

			adverbs.remove(v);

		}

		count = 0;

		while (sentence.contains("N")) {

			String v = nouns.get(count);

			

			sentence = sentence.replaceFirst("N", v);

	

			nouns.remove(v);

		}



		while (sentence.contains("V")) {

			String v = verbs.get(count);

			sentence = sentence.replaceFirst("V", v);

	

			verbs.remove(v);

		}

	

		sentence = sentence.replaceAll("\\[", "").replaceAll("\\]","");

		System.out.println(sentence);

		

		while (sentence2.contains("AJ")) {

			String v = adjectives.get(count);

			sentence2 = sentence2.replaceFirst("AJ", v);

			

			adjectives.remove(v);

		}

		

		while (sentence2.contains("AV")) {

			String v = adverbs.get(count);

			sentence2 = sentence2.replaceFirst("AV", v);

			

			adverbs.remove(v);

		}

		

		while (sentence2.contains("N")) {

			

			String v = nouns.get(count);

			

			sentence2 = sentence2.replaceFirst("N", v);

			

			nouns.remove(v);

		}

		

		while (sentence2.contains("V")) {

			

			String v = verbs.get(count);

			sentence2 = sentence2.replaceFirst("V ", v);

			

			verbs.remove(v);

		}

		sentence2 = sentence2.replaceAll("\\[", "").replaceAll("\\]","");

		System.out.println(sentence2);

	}

	                         

}

