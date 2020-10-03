import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Frequency {

	public void getLetterFreq(HashMap<Character, Integer> letterMap) {

		   List<Entry<Character, Integer>> entry = new ArrayList<Entry<Character, Integer>>(letterMap.entrySet());
	       entry.sort(new Comparator<Entry<Character, Integer>>() {

	           @Override
	           public int compare(Entry<Character, Integer> a1, Entry<Character, Integer> a2) {
	               return -1 * a1.getValue().compareTo(a2.getValue());
	           }
	       });
	       System.out.println("Top 10 letters");
	       for(int i=0;i<10;i++) {
	           System.out.println(entry.get(i).getKey()+"\t"+entry.get(i).getValue());
		}
	}

	public void getWordFreq(HashMap<String, Integer> wordMap) {
		System.out.println("\nTop ten words:");
		for(int i=0;i<10;i++)
		{
			String max="";
			for(String a :wordMap.keySet())
			{
				if(max.equals("")|| wordMap.get(max)< wordMap.get(a))
					max=a;
			}

			System.out.println("Word: " + max + " frequency: " + wordMap.get(max));
			wordMap.put(max,-wordMap.get(max));
		}
	}

	public void getStopWordFreq(HashMap<String, Integer> stopMap) {
		System.out.println("\nTop ten words without stop words:");
		for(int i=0;i<10;i++)
		{
			String max="";
			for(String a:stopMap.keySet())
			{
				if(max.equals("")|| stopMap.get(max)< stopMap.get(a))
					max=a;
			}

			System.out.println("Word: " + max + " frequency: " + stopMap.get(max));
			stopMap.put(max,-stopMap.get(max));
		}
	}

}
