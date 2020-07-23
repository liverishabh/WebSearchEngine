import java.lang.Math;

public class InvertedPageIndex {
	MySet<PageEntry> pages = new MySet<>();
	MyHashTable table = new MyHashTable();
	
	void addPage(PageEntry p) {
		pages.Insert(p);
		MyLinkedList<WordEntry> list_of_wordEntries = p.webpageid.getWordEntries();
		list_of_wordEntries.temp2 = list_of_wordEntries.head;
		while(list_of_wordEntries.temp2 != null) {
			WordEntry w = list_of_wordEntries.temp2.element;
			table.addPositionsForWord(w);
			list_of_wordEntries.temp2 = list_of_wordEntries.temp2.next;
		}		
	}
	MySet<PageEntry> getPagesWhichContainWord(String str){
		MySet<PageEntry> result = new MySet<>() ;
		int index = table.getHashIndex(str);
		table.temp = table.Bucketarray[index];
		while(table.temp != null) {
			if(table.temp.data.search_word.equals(str)) {
				MyLinkedList<Position> p = table.temp.data.getAllPositionsForThisWord();
				p.temp2 = p.head;
				while(p.temp2 != null) {
					if(!result.IsMember(p.temp2.element.page_entry)) {
						result.Insert(p.temp2.element.page_entry);
					}
					p.temp2 = p.temp2.next;
				}
				
			}
			table.temp = table.temp.next;
		}
		return result;
	}
	MySet<PageEntry> getPagesWhichContainPhrase(String str[]){
		AVLTree[] arr = new AVLTree[str.length];
		Boolean flag = false;
		MySet<PageEntry> myset = new MySet<>();
		MySet<PageEntry> myset2 = new MySet<>();
		for(int i=0;i<str.length;i++) {
			if(i == 0)
				myset = this.getPagesWhichContainWord(str[0]);
			else myset = myset.Intersection(this.getPagesWhichContainWord(str[i]));				
		}
		Node<WordEntry> node = new Node<>(null);
		myset.l.temp2 = myset.l.head;
		while(myset.l.temp2 != null) {
			for(int i=0;i<str.length;i++) {
				Node<WordEntry> temp = myset.l.temp2.element.webpageid.webpage_indices.head;
				while(temp != null) {
					if(temp.element.search_word.equals(str[0])) {
						node = temp;
					}
					if(temp.element.search_word.equals(str[i])) {
						arr[i] = temp.element.getTree();
						break;
					  }
					temp = temp.next;
					}
								
			}		
			node.element.word_indices.temp2 = node.element.word_indices.head;
		    while(node.element.word_indices.temp2 != null) {
		    	for(int j=1; j<arr.length;j++) {
		    		if(!arr[j].find(node.element.word_indices.temp2.element.word_id_withoutstopwords + j)) {
		    			break;
		    		}
		    		if(j == arr.length-1) {
			    		flag = true;
			    	}
		    	}
		    	if(flag == true) {
		    		myset2.Insert(myset.l.temp2.element);
		    		flag = false;
		    		break;
		    	}
		    	node.element.word_indices.temp2 = node.element.word_indices.temp2.next;
		    }
			myset.l.temp2 = myset.l.temp2.next;
		}
		return myset2;
	}
	String getPositionsOfWordInAPage(String str, String name_of_page) {
		
		pages.l.temp2 = pages.l.head;
		while(pages.l.temp2 != null) {
			if(pages.l.temp2.element.name_of_page.equals(name_of_page)) {
				break;				
			}
			pages.l.temp2 = pages.l.temp2.next;
		}
		if(pages.l.temp2==null) {
			System.out.println("No webpage "+name_of_page+" found");
			return "";
		}
		
		MySet<PageEntry> check = getPagesWhichContainWord(str);
		check.l.temp2 = check.l.head;
		while(check.l.temp2 != null) {
			if(check.l.temp2.element.name_of_page.equals(name_of_page)) {
				break;				
			}
			check.l.temp2 = check.l.temp2.next;
		}
		if(check.l.temp2==null) {
			System.out.println("Webpage "+name_of_page+" does not contain word "+str);
			return "";
		}
		
		String result = "";
		int index = table.getHashIndex(str);
		table.temp = table.Bucketarray[index];
		while(table.temp != null) {
			if(table.temp.data.search_word.equals(str)) {
				MyLinkedList<Position> p1 = table.temp.data.getAllPositionsForThisWord();
				p1.temp2 = p1.head;
				while(p1.temp2 != null) {
					if(p1.temp2.element.page_entry.name_of_page.equals(name_of_page)) {
						result = p1.temp2.element.word_id+ ", "+result;
					}
					p1.temp2 = p1.temp2.next;
				}
			}
			table.temp = table.temp.next;
		}
		result = result.substring(0,result.length()-2);
		return result;
	}
	
	float getInverseDocumentFrequency(String word) {
		int N = pages.l.size();
		int n = this.getPagesWhichContainWord(word).l.size();
		return (float) Math.log((double) N/n);		
	}
	
	float getInverseDocumentFrequencyforPhrase(String str[]) {
		int N = pages.l.size();
		int n = this.getPagesWhichContainPhrase(str).l.size();
		return (float) Math.log((double) N/n);		
	}
	
	float getTermFrequencyforPhrase(String str[], PageEntry p) {
		AVLTree[] arr = new AVLTree[str.length];
		int phrase_count = 0;
		Node<WordEntry> node = new Node<>(null);
			for(int i=0;i<str.length;i++) {
				Node<WordEntry> temp = p.webpageid.webpage_indices.head;
				while(temp != null) {
					if(temp.element.search_word.equals(str[0])) {
						node = temp;
					}
					if(temp.element.search_word.equals(str[i])) {
						arr[i] = temp.element.t;
						break;
					  }
					temp = temp.next;
					}
					
			}
			node.element.word_indices.temp2 = node.element.word_indices.head;
		    for(int n=0; n<node.element.word_indices.size;n++) {
		    	for(int j=1; j<arr.length;j++) {
		    		if(!arr[j].find(node.element.word_indices.temp2.element.word_id_withoutstopwords + j)) {
		    			break;
		    		}
		    		if(j == arr.length-1) {
		    				phrase_count += 1;
			    	}
		    		
		    	}
		    	node.element.word_indices.temp2 = node.element.word_indices.temp2.next;
		    }		    
		    return (float) phrase_count/(p.total_words() - (str.length - 1)*phrase_count);
		}
	

	float getRelevanceOfPage(String str[], boolean doTheseWordsRepresentAPhrase, PageEntry p) {
		float relevance = 0;		
		if(doTheseWordsRepresentAPhrase) {
			relevance = getTermFrequencyforPhrase(str, p) * getInverseDocumentFrequencyforPhrase(str);
		}
		else {
		    for(int i=0 ; i < str.length ; i++) {
		    	relevance = relevance + p.getTermFrequency(str[i]) * getInverseDocumentFrequency(str[i]);
		    }		    	
		}
		return relevance;			
	}
}
