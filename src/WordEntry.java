public class WordEntry {
	String search_word;
	MyLinkedList<Position> word_indices = new MyLinkedList<Position>();
	AVLTree t = new AVLTree();
	WordEntry(String word){
		search_word = word;
	}
	void addPosition(Position position) {
		word_indices.InsertFirst(position);
	}
	void addPositions(MyLinkedList<Position> positions) {
		word_indices.temp2 = word_indices.head;
		while (word_indices.temp2.next!= null) {
			word_indices.temp2 = word_indices.temp2.next;
		}
		word_indices.temp2.next = positions.head;
	}
	MyLinkedList<Position> getAllPositionsForThisWord(){
		return word_indices;
	}
	AVLTree getTree() {
		return t;
	}

}
