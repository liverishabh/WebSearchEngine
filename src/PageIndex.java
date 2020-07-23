public class PageIndex {
	MyLinkedList<WordEntry> webpage_indices = new MyLinkedList<WordEntry>();
	void addPositionForWord(String str, Position p) {
		webpage_indices.temp2 = webpage_indices.head;
		while(webpage_indices.temp2!=null) {
			if(webpage_indices.temp2.element.search_word.equals(str)) {
				webpage_indices.temp2.element.addPosition(p);
				return;
			}
			webpage_indices.temp2 = webpage_indices.temp2.next;			
		}
		WordEntry a = new WordEntry(str);
		a.addPosition(p);
		webpage_indices.InsertLast(a);
	}
	MyLinkedList<WordEntry> getWordEntries(){
		return webpage_indices;
	}
}
