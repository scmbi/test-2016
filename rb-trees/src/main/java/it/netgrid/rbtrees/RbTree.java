package it.netgrid.rbtrees;

import it.netgrid.rbtrees.RbTreeElement.Color;

public class RbTree<T extends Comparable<String>> {

	private RbTreeElement<T> root;

	private void leftRotate(RbTreeElement<T> element) {
	}

	private void rightRotate(RbTreeElement<T> element) {
	}

	private void insertFixup(RbTreeElement<T> element) {
		
	}

	private void deleteFixup(RbTreeElement<T> element) {
	}

	public void insert(RbTreeElement<T> element) {
		RbTreeElement<T> y = null;
		RbTreeElement<T> x = root;
		
		while (!x.equals(null)){
			y = x;
			
			if ((element.getElement().toString().compareTo(x.getElement().toString())) < 0){
				x = x.getLeft();
			}
			else{
				x = x.getRight();
			}	
		}
		//element.getParent() = y;
		if (y.equals(null)){
			root = element;
		}
		else if ((element.getElement().toString().compareTo(y.getElement().toString())) < 0){
			y.setLeft(element);
		}
		else{
			y.setRight(element);
		}
		
		element.setLeft(null);
		element.setRight(null);	
		element.setColor(Color.RED);
		//--RB-INSERT-FIXUP
	}

	public void delete(T element) {

	}

	public T get(String element) {
		return null;
	}

	public RbTreeElement<T> getRoot() {
		return root;
	}

	private void setRoot(RbTreeElement<T> root) {
		this.root = root;
	}
}
