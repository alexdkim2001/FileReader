import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Project1 {

	public static void main(String[] args) throws Exception {

		String fname = "alice-in-wonderland.txt";
		FileReader fin = null;

		try {
			fin = new FileReader(fname);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner info = new Scanner(fin);	    	   

		ArrayList<String> fileLines = new ArrayList<>();
		while (info.hasNextLine()) {
			fileLines.add(info.nextLine().toLowerCase());
		}

		Frequency topTenLetters;
		topTenLetters = new Frequency();

		Frequency topTenWords;
		topTenWords = new Frequency();

		Frequency topTenWordsStop;
		topTenWordsStop = new Frequency();

		topTenLetters.getLetterFreq(letterMap(fileLines));

		topTenWords.getWordFreq(wordMap(fileLines, info));

		topTenWordsStop.getStopWordFreq(stopCount(fileLines, info));

		System.out.println(wordFind(fileLines, info));

	}
	
	/*Method that will return a HashMap of the letters and their frequencies in the text file*/
	
	public static HashMap<Character, Integer> letterMap(ArrayList<String> file) {

		HashMap<Character,Integer> letterMap = new HashMap<Character,Integer>();

		for(String str:file) {
			for(int i=0;i<str.length();i++) {
				if(letterMap.containsKey(str.charAt(i))) {
					letterMap.put(str.charAt(i), letterMap.get(str.charAt(i))+1);
				}
				else {
					letterMap.put(str.charAt(i),1);
				} 
			}
		}
		return letterMap;
	}
	
	/*Method that will return a HashMap of the words and their frequencies in the text file*/
	
	public static HashMap<String, Integer> wordMap(ArrayList<String> file, Scanner info) {

		HashMap<String, Integer> wordMap = new HashMap<String, Integer>();

		for(String str : file) {
			info = new Scanner(str);
			while(info.hasNext()) {
				info.useDelimiter("[^a-zA-Z']");
				String word = info.next();
				word = word.toLowerCase();
				word = word.replaceAll("^'+", "");
				word = word.replaceAll("'+$", "");
				if(!wordMap.containsKey(word)) {
					wordMap.put(word, 1);
				}
				else
					wordMap.put(word, wordMap.get(word)+1);
			}
		}
		return wordMap;
	}

	/*Method that will read the stop words file, recreate the text file, and then removes all stop words
	 and returns the new HashMap containing the words and their frequencies*/
	
	public static HashMap<String, Integer> stopCount(ArrayList<String> file, Scanner info) throws FileNotFoundException {

		File stopList=new File("stop-list.txt");

		Set<String> stopWords = new HashSet<String>();
		Scanner stop = new Scanner(stopList);

		while(stop.hasNext()) {
			stopWords.add(stop.nextLine().toLowerCase());
		}

		HashMap<String,Integer> stopMap = new HashMap<String,Integer>();

		for(String str : file) {
			info = new Scanner(str);
			while(info.hasNext()) {
				info.useDelimiter("[^a-zA-Z']");
				String word = info.next();
				word = word.toLowerCase();
				word = word.replaceAll("^'+", "");
				word = word.replaceAll("'+$", "");
				if(!stopMap.containsKey(word)) {
					stopMap.put(word, 1);
				}
				else
					stopMap.put(word, stopMap.get(word)+1);
			}
			stopMap.keySet().removeAll(stopWords);
			stop.close();
		}
		return stopMap;
	}
	
	/*Method that will ask the user to input a word. If word exists in text file,
	will return a message saying how many times it appears. If not, will send a message saying it does not exist.*/

	public static String wordFind(ArrayList<String> file, Scanner info) throws FileNotFoundException {

		HashMap<String,Integer> findMap = new HashMap<String,Integer>();

		for(String str : file) {
			info = new Scanner(str);
			while(info.hasNext()) {
				info.useDelimiter("[^a-zA-Z']");
				String word = info.next();
				word = word.toLowerCase();
				word = word.replaceAll("^'+", "");
				word = word.replaceAll("'+$", "");
				if(!findMap.containsKey(word)) {
					findMap.put(word, 1);
				}
				else
					findMap.put(word,findMap.get(word)+1);
			}
		}

		Scanner input = new Scanner(System.in);
		System.out.println("\nEnter word you want to find: ");

		String word = input.nextLine(); 

		if(findMap.containsKey(word)) {
			return "Found " + word +  " " + findMap.get(word) + " times!";
		}
		return "Not in file.";
	}

}

