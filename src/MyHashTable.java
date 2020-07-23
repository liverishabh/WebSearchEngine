public class MyHashTable {
	
	 int Bucketarray_size = 500;
	 HashNode[] Bucketarray = new HashNode[500];
	 HashNode temp;
	 public static class HashNode{
		 HashNode next;
		 WordEntry data;
		 public HashNode(WordEntry a){
			 data = a;
			 next = null;
		 }
	 }
	 public void collideInsert(WordEntry w, HashNode head){
		 HashNode temp = head;
		 while (temp.next != null) {
			 if (temp.data.search_word.equals(w.search_word)) {
				 temp.data.addPositions(w.word_indices);
				 return;
			 }
			 temp = temp.next;
		 }
		 HashNode newest = new HashNode(w);
		 temp.next = newest;
	}
	void printlist(HashNode head) {
		HashNode temp= head;
		while(temp!=null) {
			System.out.println("{ "+temp.data.search_word+": ");
			temp.data.word_indices.temp2 = temp.data.word_indices.head;
			while(temp.data.word_indices.temp2!=null) {
				System.out.print("( " +temp.data.word_indices.temp2.element.page_entry.name_of_page +", "+ temp.data.word_indices.temp2.element.word_id +", "+
			                       temp.data.word_indices.temp2.element.word_id_withoutstopwords+" )");
				temp.data.word_indices.temp2 = temp.data.word_indices.temp2.next;
			}
			System.out.println();
			temp = temp.next;
		}
	}
	 
	public int getHashIndex(String str) {
		 int ascii = 0;
		 int index;
		 for (int i=0;i<str.length();i++) {
			 ascii += str.charAt(i);
		 }
		 index = ascii % Bucketarray_size; 
		 return index;		 
	 }
	 void addPositionsForWord(WordEntry w) {
		 int index;
		 index = getHashIndex(w.search_word);
		 if(Bucketarray[index] == null) {                  
			 Bucketarray[index] = new HashNode(w);
			 
		 }
		 else if(Bucketarray[index].data.search_word.equals(w.search_word)) {
			 Bucketarray[index].data.addPositions(w.word_indices);
		 }
		 else  this.collideInsert(w,Bucketarray[index]);
	 }
	 public void printhash() {
		 for(int i=0;i<500;i++) {
			 if(Bucketarray[i]==null) {
				 
			 }else this.printlist(Bucketarray[i]);
		 }
		 
	 }

}
