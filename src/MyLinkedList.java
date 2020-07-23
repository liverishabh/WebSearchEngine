class Node<E>{
	E element;
	Node<E> next;
	Node(E o){
		element = o;
		next = null;
	}
	public E getData(){
	    return element;
    }
    public Node<E> getNext(){
    	return next;
    }
}
public class MyLinkedList<E> {
	
	Node<E> head = null;
	Node<E> temp2 = head;
	int size = 0;

	
	public int size(){
		return size;
	}
	
	public Boolean IsEmpty(){
		if (size == 0){
			return true;
		}
		else return false;
	}
	public Boolean IsMember(E o){
		Node<E> temp = head;
		while (temp != null ){
			if (o.equals(temp.element)){
				return true;
			}
			temp = temp.next;
		}
		return false;
	}
	public void InsertFirst(E o){
		Node<E> new_node = new Node<E>(o);
		new_node.next = head;
		head = new_node;
		size ++;
	}
	public void InsertLast(E o){
		Node<E> new_node = new Node<E>(o);
		Node<E> temp = head;
		if (temp == null) {
			head = new_node;
			size++;
		}
		else {
			while (temp.next != null ) {
				temp =temp.next;
			}
			temp.next = new_node;
			new_node.next = null;
			size ++;
		}
				
	}
	
	public void Delete(E o){
		try{
			Node<E> temp = head;
			Node<E> temp1 = head;
			while (temp != null){
				if(o == head.element){           //if the data to be deleted is in the head node. 
					head = head.next;
					size --;
					break;
				}
				else if(o == temp.element){
					temp1.next = temp.next;
					size --;
					break;
				}
				temp1 = temp;
				temp = temp.next;
	
				
			}
			if (temp == null)
				throw new Exception();
			
		}
		catch (Exception e){
			System.out.println("Element to be deleted is not in the set");
		}
	}
}
