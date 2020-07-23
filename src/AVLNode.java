public class AVLNode {
	Position pos;
	AVLNode rightChild;
	AVLNode leftChild;
	AVLNode parent;
	int balance;
	AVLNode(Position p,AVLNode parentNode){
		pos = p;
		parent = parentNode;
		rightChild = null;
		leftChild = null;
		balance = 0;
	}
	AVLNode Insert(Position p,AVLNode parentNode) {
		if(p.word_id <= pos.word_id) {
			if(leftChild == null) {
				leftChild = new AVLNode(p,parentNode);
			}
			else leftChild.Insert(p, parentNode);
		}
		else {
			if(rightChild == null) {
				rightChild = new AVLNode(p,parentNode);
			}
			else rightChild.Insert(p, parentNode);
		}
		return parentNode;
	}

}
