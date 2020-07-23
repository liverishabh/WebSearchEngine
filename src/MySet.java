public class MySet<E> {
	
	MyLinkedList<E> l = new MyLinkedList<E>();
	
	public Boolean IsMember(E o){
		if (l.IsMember(o)){
			return true;
		}
		else return false;	
	}	
	public void Insert(E o){
		l.InsertFirst(o);
	}
	public void Delete(E o){
		l.Delete(o);
	}
	public MySet<E> Union(MySet<E> a){
		MySet<E> b = new MySet<E>();
		l.temp2 = l.head;
		while (l.temp2 != null){
			if (!a.IsMember(l.temp2.getData())){
				b.Insert(l.temp2.getData());
			}
			l.temp2 = l.temp2.next;
		}
		l.temp2 = l.head;
		while (l.temp2 != null){
			b.Insert(l.temp2.element);
			l.temp2 = l.temp2.next;
		}
		return b;
	}
	public MySet<E> Intersection(MySet<E> a){
		MySet<E> b = new MySet<E>();
		l.temp2 = l.head;
		while(l.temp2 != null){
			if (a.IsMember(l.temp2.element)){
				b.Insert(l.temp2.element);
			}
			l.temp2 = l.temp2.next;
		}
		return b;
	}
}
