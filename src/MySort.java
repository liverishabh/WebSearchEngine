import java.util.ArrayList;

public class MySort {
	ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries){
		ArrayList<SearchResult> arrlist = new ArrayList<>(10);
		while(listOfSortableEntries.l.size > 0) {
			Node<SearchResult> temp = listOfSortableEntries.l.head;
			SearchResult max = temp.element;
			while(temp != null) {
				if(max.compareTo(temp.element) == -1) {
					max = temp.element;
				}
				temp = temp.next;
			}
			arrlist.add(max);
			listOfSortableEntries.Delete(max);
		}
		return arrlist;
	}

}
