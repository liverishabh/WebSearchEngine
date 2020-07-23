public class Position {
	PageEntry page_entry;
	int word_id;
	int word_id_withoutstopwords;
	Position(PageEntry p,int wordIndex,int w){
	page_entry = p;
	word_id = wordIndex;
	word_id_withoutstopwords = w;
	}
	PageEntry getPageEntry() {
		return page_entry;
	}
	int getWordIndex() {
		return word_id;
	}

}
