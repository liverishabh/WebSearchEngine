public class AVLTree{
	AVLNode root;
	
	AVLTree(){
		root = null;
	}
	
	AVLTree getLeftSubtree(AVLNode node) {
		AVLTree tree = new AVLTree();
		tree.root = node.leftChild;
		return tree;
	}
	
	AVLTree getRightSubtree(AVLNode node) {
		AVLTree tree = new AVLTree();
		tree.root = node.rightChild;
		return tree;
	}
	
	void Insert(Position p) {
		AVLNode node = root;
		if(root == null) {
			node = new AVLNode(p,null);
			root = node;
		}
		else node = root.Insert(p, root);
		this.rebalanceTree(node);
	}
	
	void rebalanceTree(AVLNode node) {
		this.updateBalance(node);
		if(node.balance <= -1) {
			if(height(node.leftChild.leftChild) < height(node.leftChild.rightChild)) {
				node = this.rotateLeftRight(node);
			}
			else node = this.rotateRight(node);
		}
		else if(node.balance > 1) {
			if(height(node.rightChild.rightChild) < height(node.rightChild.leftChild)) {
				node = this.rotateRightLeft(node);
			}
			else node = this.rotateLeft(node);
		}
		
		if(node.parent != null) {
			this.rebalanceTree(node.parent);
		}
		else root = node;
	}
	
	AVLNode rotateRight(AVLNode node) {
		AVLNode temp = node;
		AVLNode temp1 = node.leftChild.rightChild;
		AVLNode temp2 = node.parent;
		node.leftChild.rightChild = node;
		node.parent = node.leftChild;
		node.leftChild = temp1;
		if(temp1 != null)
			temp1.parent = node;
		node = node.parent;
		node.parent = temp2;
		
		if(node.parent != null) {
			if(node.parent.rightChild == temp)
				node.parent.rightChild = node;
			else node.parent.leftChild = node;
		}
		
		this.updateBalance(temp);
		this.updateBalance(node);
		return node;
	}
	
	AVLNode rotateLeft(AVLNode node) {
		AVLNode temp = node;
		AVLNode temp1 = node.rightChild.leftChild;
		AVLNode temp2 = node.parent;
		node.rightChild.leftChild = node;
		node.parent = node.rightChild;
		node.rightChild = temp1;
		if(temp1 != null)
			temp1.parent = node;
		node = node.parent;
		node.parent = temp2;
		
		if(node.parent != null) {
			if(node.parent.rightChild == temp)
				node.parent.rightChild = node;
			else node.parent.leftChild = node;
		}
		
		this.updateBalance(temp);
		this.updateBalance(node);
		return node;
		
	}
	
	AVLNode rotateLeftRight(AVLNode node) {
		node.leftChild = this.rotateLeft(node.leftChild);
		node = this.rotateRight(node);
		return node;
	}
	
	AVLNode rotateRightLeft(AVLNode node) {
		node.rightChild = this.rotateRight(node.rightChild);
		node = this.rotateLeft(node);
		return node;
	}
	
	void updateBalance(AVLNode node) {
		node.balance = height(node.rightChild) - height(node.leftChild); 
	}
	
	Boolean find(int n) {
		if(root == null) {
			return false;
		}
		if(n == root.pos.word_id_withoutstopwords) {
			return true;
		}
		else if(n < root.pos.word_id_withoutstopwords) {
			if(this.getLeftSubtree(root).find(n))
				return true;
		}
		else if(n > root.pos.word_id_withoutstopwords) {
			if(this.getRightSubtree(root).find(n))
				return true;
		}
		return false;
	}
	
	int max(int a,int b) {
		if(a>b) return a;
		else return b;
	}
	
	int height(AVLNode node) {
		if (node == null) {
			return -1;
		}
		else return 1 + max(height(node.rightChild) , height(node.leftChild));
	}
	void preOrder(AVLNode node) { 
        if (node != null) { 
        	
            System.out.print(node.pos.word_id_withoutstopwords + " "); 
            preOrder(node.leftChild);
            preOrder(node.rightChild); 
        } 
    }
	
}