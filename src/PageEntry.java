import java.util.*;
import java.io.*;

public class PageEntry {
	String name_of_page;
	int i=1;
	int l=1;
	PageIndex webpageid;
	String[] stop_words = { "a", "an", "the", "they", "these", "this", "for", "is", "are", "was", "of", "or", "and", "does", "will", "whose"};
	String[] punctuation = { "{","}","[","]","<",">","=","(",")",".",",",";", "'", String.valueOf('"'),"?","#","!","-",":" };
	PageEntry(String pageName){
		try {
			name_of_page = pageName;
			webpageid = new PageIndex();
			FileInputStream fstream = new FileInputStream(pageName);
			Scanner s = new Scanner(fstream);
			while(s.hasNextLine()) {
				String line = s.nextLine();
				if(line.equals("")) {}
				else {
				for(int j=0;j<punctuation.length;j++) {
					   if (line.contains(punctuation[j])) {
						   line = line.replace(punctuation[j]," ");
					   }
			    }
				
				String word[] = line.split("\\s+");
				for(int k=0; k<word.length;k++) {
					if (!Arrays.stream(stop_words).anyMatch(word[k]::equals)) {
						word[k] = word[k].toLowerCase();
						if(word[k].equals("stacks")||word[k].equals("structures")||word[k].equals("applications")) {
							   word[k] = word[k].substring(0,word[k].length()-1);
						}
						   Position p = new Position(this,i,l);
						   webpageid.addPositionForWord(word[k], p);

						   webpageid.webpage_indices.temp2 = webpageid.webpage_indices.head;
						   while(webpageid.webpage_indices.temp2!=null) {
								if(webpageid.webpage_indices.temp2.element.search_word.equals(word[k])) {
									webpageid.webpage_indices.temp2.element.t.Insert(p);
								}
								webpageid.webpage_indices.temp2 = webpageid.webpage_indices.temp2.next;			
							}
						   l++;
					}
					i = i+1;
				}
				
				}
					
		   }
			s.close();
			
		   
		}	
		
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	PageIndex getPageIndex() {
		return webpageid;
	}
	int total_words() {
		return l;
	}
	float getTermFrequency(String word) {
		int word_count = 0;
		webpageid.webpage_indices.temp2 = webpageid.webpage_indices.head;
		while(webpageid.webpage_indices.temp2 != null){
			if(webpageid.webpage_indices.temp2.element.search_word.equals(word)){
				word_count = webpageid.webpage_indices.temp2.element.word_indices.size;
				break;
		    }
		    webpageid.webpage_indices.temp2 = webpageid.webpage_indices.temp2.next;
		}
		return (float) word_count/this.total_words(); 
	}
	
}
