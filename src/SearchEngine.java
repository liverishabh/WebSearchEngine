import java.util.ArrayList;

public class SearchEngine {
	InvertedPageIndex inverted_index;
	SearchEngine(){
		inverted_index = new InvertedPageIndex();
	}
	void printhashtable() {
		inverted_index.table.printhash();
	}
	void performAction(String actionMessage) {
		String[] temp = actionMessage.split("\\s");
		if(temp[0].equals("addPage")) {
			PageEntry p = new PageEntry(temp[1]);
//			System.out.println("totalwords "+ String.valueOf(p.wordCount()-1));
			inverted_index.addPage(p);
		}
		if(temp[0].equals("queryFindPagesWhichContainWord")) {
			String output ="";
			if(temp[1].equals("stacks")||temp[1].equals("structures")||temp[1].equals("applications")) {
							   temp[1] = temp[1].substring(0,temp[1].length()-1);
						}
			MySet<PageEntry> result = inverted_index.getPagesWhichContainWord(temp[1].toLowerCase());
				result.l.temp2 = result.l.head;
				while(result.l.temp2 != null) {
					if(result.l.temp2.next == null) {
						output = output + result.l.temp2.element.name_of_page;
					}else output = output + result.l.temp2.element.name_of_page+", ";
					result.l.temp2 = result.l.temp2.next;
				}
				if(!output.equals("")) {
					System.out.println(output);
				}
				else {
			     System.out.println("No webpage contains word "+ temp[1]);
			    }
		}
		if(temp[0].equals("queryFindPositionsOfWordInAPage")) {
			if(temp[1].equals("stacks")||temp[1].equals("structures")||temp[1].equals("applications")) {
							   temp[1] = temp[1].substring(0,temp[1].length()-1);
						}
			String output = inverted_index.getPositionsOfWordInAPage(temp[1].toLowerCase(), temp[2]);
			if(!output.equals("")) {
				System.out.println(output);
			}
			
		}
		if(temp[0].equals("queryFindPagesWhichContainAllWords")){
			String[] str = new String[temp.length-1];
			for(int j=0;j<temp.length-1;j++) {
				str[j] = temp[j+1].toLowerCase();
				if(temp[j+1].equals("stacks")||temp[j+1].equals("structures")||temp[j+1].equals("applications")) {
							   str[j] = temp[j+1].substring(0,temp[j+1].length()-1);
						}
			}
			MySet<PageEntry> myset = new MySet<>();
			for(int i=0;i<str.length;i++) {
				if(i == 0)
					myset = inverted_index.getPagesWhichContainWord(str[0]);
				else myset = myset.Intersection(inverted_index.getPagesWhichContainWord(str[i]));				
			}
			MySet<SearchResult> myset2 = new MySet<>();
			myset.l.temp2 = myset.l.head;
			while(myset.l.temp2 != null) {
				SearchResult s = new SearchResult(myset.l.temp2.element,inverted_index.getRelevanceOfPage(str, false, myset.l.temp2.element));
				myset2.Insert(s);
				myset.l.temp2 = myset.l.temp2.next;
			}
			MySort tosort = new MySort(); 
			ArrayList<SearchResult> result = tosort.sortThisList(myset2);
			String output = "";
			for (int i=0; i<result.size(); i++) 
	            output = output + result.get(i).getPageEntry().name_of_page +" "+ result.get(i).relevance+ ", ";
			System.out.println(output.substring(0, output.length()-2));
		}
		if(temp[0].equals("queryFindPagesWhichContainAnyOfTheseWords")) {
			String[] str = new String[temp.length-1];
			for(int j=0;j<temp.length-1;j++) {
				str[j] = temp[j+1].toLowerCase();
				if(temp[j+1].equals("stacks")||temp[j+1].equals("structures")||temp[j+1].equals("applications")) {
							   str[j] = temp[j+1].substring(0,temp[j+1].length()-1);
						}
			}
			MySet<PageEntry> myset = new MySet<>();
			MySet<PageEntry> myset1 = new MySet<>();
			for(int i=0;i<str.length;i++) {
				if(i == 0)
					myset = inverted_index.getPagesWhichContainWord(str[0]);
				else myset = myset.Union(inverted_index.getPagesWhichContainWord(str[i]));				
			}
			myset.l.temp2 = myset.l.head;
			while(myset.l.temp2 != null) {
				if(!myset1.IsMember(myset.l.temp2.element))
					myset1.Insert(myset.l.temp2.element);
				myset.l.temp2 = myset.l.temp2.next;
			}
			MySet<SearchResult> myset2 = new MySet<>();
			myset1.l.temp2 = myset1.l.head;
			while(myset1.l.temp2 != null) {
				SearchResult s = new SearchResult(myset1.l.temp2.element,inverted_index.getRelevanceOfPage(str, false, myset1.l.temp2.element));
				myset2.Insert(s);
				myset1.l.temp2 = myset1.l.temp2.next;
			}
			MySort tosort = new MySort(); 
			ArrayList<SearchResult> result = tosort.sortThisList(myset2);
			String output = "";
			for (int i=0; i<result.size(); i++) 
	            output = output + result.get(i).getPageEntry().name_of_page + " "+result.get(i).relevance+", ";
			System.out.println(output.substring(0, output.length()-2));
		}
		if(temp[0].equals("queryFindPagesWhichContainPhrase")) {
			String[] str = new String[temp.length-1];
			for(int j=0;j<temp.length-1;j++) {
				str[j] = temp[j+1].toLowerCase();
				if(temp[j+1].equals("stacks")||temp[j+1].equals("structures")||temp[j+1].equals("applications")) {
							   str[j] = temp[j+1].substring(0,temp[j+1].length()-1);
						}
			}
			MySet<PageEntry> myset = new MySet<>();
			myset = inverted_index.getPagesWhichContainPhrase(str);
			MySet<SearchResult> myset2 = new MySet<>();
			myset.l.temp2 = myset.l.head;
			while(myset.l.temp2 != null) {
				SearchResult s = new SearchResult(myset.l.temp2.element,inverted_index.getRelevanceOfPage(str, true, myset.l.temp2.element));
				myset2.Insert(s);
				myset.l.temp2 = myset.l.temp2.next;
			}
//			System.out.println(myset2.l.head);
			MySort tosort = new MySort(); 
			ArrayList<SearchResult> result = tosort.sortThisList(myset2);
			String output = "";
			for (int i=0; i<result.size(); i++) 
	            output = output + result.get(i).getPageEntry().name_of_page +" "+result.get(i).relevance+ ", ";
//			System.out.println(output);
			if(output.equals("")) {
				System.out.println("No webpage with the given phrase found.");
			}
			else {
			System.out.println(output.substring(0, output.length()-2));
			}			
		}
		
	}

}
