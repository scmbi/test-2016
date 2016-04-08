package it.netgrid.rbtrees;

import java.util.List;

import it.netgrid.rbtrees.RbTreeElement.Color;

public class RbTree<T extends Comparable<String>> {

	private RbTreeElement<T> root;

	// procedura che restituisce un puntatore all'elemeneto minimo del
	// sottoalbero con radice in un nodo x
	private RbTreeElement<T> treeMinimum(RbTreeElement<T> element) {
		while (element.getLeft() != null) {
			element = element.getLeft();
		}
		return element;
	}

	//procedura che restituisce  un puntatore all'elemeneto massimo del sottoalbero con radice in un nodo x
	private RbTreeElement<T> treeMaximum(RbTreeElement<T> element){
		RbTreeElement<T> result=null;
		while(!(element.getRight()==null)){
			result=element.getRight();
		}
		return result;
	}

	public void leftRotate(RbTreeElement<T> element) {
		RbTreeElement<T> y = element.getRight(); // Imposto y
		element.setRight(y.getLeft()); // Sposto il sottoalbero sinistro di x
										// nel sottoalbero
		if (y.getLeft() != null) { // sinistro destro di x
			y.getLeft().setParent(element);
		}
		y.setParent(element.getParent()); // collego il padre di x con quello di
											// y
		if (element.getParent() == null) {
			root = y;
		} else {
			if (element == element.getParent().getLeft()) {
				element.getParent().setLeft(y);
			} else {
				element.getParent().setRight(y);
			}
		}
		y.setLeft(element); // Pone x a sinistra di y
		element.setParent(y);
	}

	public void rightRotate(RbTreeElement<T> element) {
		RbTreeElement<T> y = element.getLeft(); // Imposto y
		element.setLeft(y.getRight()); // Sposto il sottoalbero destro di x nel
										// sottoalbero
		if (y.getRight() != null) { // sinistro sinistro di x
			y.getRight().setParent(element);
		}
		y.setParent(element.getParent()); // collego il padre di x con quello di
											// y
		if (element.getParent() == null) {
			root = y;
		} else {
			if (element == element.getParent().getRight()) {
				element.getParent().setRight(y);
			} else {
				element.getParent().setLeft(y);
			}
		}
		y.setRight(element); // Pone x a destra di y
		element.setParent(y);
	}

	public void insertFixup(RbTreeElement<T> element) {
		RbTreeElement<T> y = null;
		while (element != null && element.getParent() != null && element.getParent().isRed()) {
			if (element.getParent() == element.getParent().getParent().getLeft()) {
				y = element.getParent().getParent().getRight();

				if (y.isRed()) {
					element.getParent().setColor(Color.BLACK);
					y.setColor(Color.BLACK);
					element.getParent().getParent().setColor(Color.RED);
					element = element.getParent().getParent();

				} else if (element == element.getParent().getRight()) {
					element = element.getParent();
					leftRotate(element);
				} else {
					element.getParent().setColor(Color.BLACK);
					element.getParent().getParent().setColor(Color.RED);
					rightRotate(element.getParent().getParent());

				}
			} else {
				y = element.getParent().getParent().getLeft();
			}
			if (y.isRed()) {
				element.getParent().setColor(Color.BLACK);
				y.setColor(Color.BLACK);
				element.getParent().getParent().setColor(Color.RED);
				element = element.getParent().getParent();

			} else if (element == element.getParent().getLeft()) {
				element = element.getParent();
				rightRotate(element);
			} else {
				element.getParent().setColor(Color.BLACK);
				element.getParent().getParent().setColor(Color.RED);
				leftRotate(element.getParent().getParent());
			}
		}
		if (root != null) {
			root.setColor(Color.BLACK);
		}
	}

	public void deleteFixup(RbTreeElement<T> element) {

		RbTreeElement<T> w;

		while (element != root && element.isBlack() == true) {

			if (element == element.getParent().getLeft()) {
				w = element.getParent().getRight();
				if (w.isRed()) {
					w.setColor(Color.BLACK);
					element.getParent().setColor(Color.RED);
					leftRotate(element.getParent());
					w = element.getParent().getRight();
				}

				if (w.getLeft().isBlack() && w.getRight().isBlack()) {
					w.setColor(Color.RED);
					element = element.getParent();
				}

				else {
					if (w.getRight().isBlack()) {
						w.getLeft().setColor(Color.BLACK);
						w.setColor(Color.RED);
						rightRotate(w);
						w = element.getParent().getRight();
					}
					w.setColor(element.getParent().getColor());
					element.getParent().setColor(Color.BLACK);
					w.getRight().setColor(Color.BLACK);
					leftRotate(element.getParent());
					element = root;

				}

			} else {
				w = element.getParent().getLeft();
				if (w.isRed()) {
					w.setColor(Color.BLACK);
					element.getParent().setColor(Color.RED);
					rightRotate(element.getParent());
					w = element.getParent().getLeft();
				}

				if (w.getRight().isBlack() && w.getLeft().isBlack()) {
					w.setColor(Color.RED);
					element = element.getParent();
				}

				else {
					if (w.getLeft().isBlack()) {
						w.getRight().setColor(Color.BLACK);
						w.setColor(Color.RED);
						leftRotate(w);
						w = element.getParent().getLeft();
					}
					w.setColor(element.getParent().getColor());
					element.getParent().setColor(Color.BLACK);
					w.getLeft().setColor(Color.BLACK);
					rightRotate(element.getParent());
					element = root;

				}

			}

		}
		if (element != null) {
			element.setColor(Color.BLACK);
		}
	}

	public void insert(RbTreeElement<T> element) {
		if (element == null || element.getElement() == null) {
			return;
		}
		RbTreeElement<T> y = null;
		RbTreeElement<T> x = this.getRoot();
		while (x != null) {
			y = x;
			if (element.getElement().toString().compareTo(x.getElement().toString()) == -1) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}
		}

		element.setParent(y);
		if (y == null) {
			this.setRoot(element);
		} else if (element.getElement().toString().compareTo(y.getElement().toString()) == -1) {
			y.setLeft(element);
		} else {
			y.setRight(element);
		}

		element.setLeft(null);
		element.setRight(null);
		element.setColor(Color.RED);
		this.insertFixup(element);
	}

	// Funzione che restituisce il successore del nodo da eliminare
	public RbTreeElement<T> treeSuccessor(RbTreeElement<T> element) {
		RbTreeElement<T> result;
		if (element.getRight() != null) {
			return treeMinimum(element.getRight());
		}
		result = element.getParent();
		while ((result != null) && (element.equals(result.getRight()))) {
			element = result;
			result = result.getParent();
		}
		return result;
	}
	
	/* Funzione che elemina un nodo dall'albero
	 * -Ritorna null se viene inserito come nodo da eliminare null oppure una radice con {0} figli	  
	 * -Ritorna il nodo eliminato se il nodo ha {0, 1} figli
	 * -Ritorna il nodo successore del nodo eliminato se il nodo ha {2} figli
	 */
	public RbTreeElement<T> delete(RbTreeElement<T> elementZ) {
		RbTreeElement<T> result;
		RbTreeElement<T> elementX;
		
		
		//ritorna null se il tree � vuoto
		if(elementZ==(null)){
			
			return null;
		}
		
		
		//eliminazione radice senza figli
		if(elementZ == this.getRoot()){
			if((elementZ.getLeft()==null)&&(elementZ.getRight()==null)){
			
				return null;
			}
		}

		
		//1-6----------------------------------------------------------------------------------------------|
		if((elementZ.getLeft()==null)||(elementZ.getRight()==null)){
			result= elementZ;	
		}
		else{
			result=treeSuccessor(elementZ);
		}
		if(!(result.getLeft()==null)){
			elementX=result.getLeft();
		}
		else{
			elementX=result.getRight();
		}
		
		
		
		//7------------------------------------------------------------------------------------------------|
		if(!(elementX==null)){
		elementX.setParent(result.getParent());
		}
		//8-12---------------------------------------------------------------------------------------------|
		if(result.getParent()==null){
			this.setRoot(elementX);
		}
		else if (result.equals(result.getParent().getLeft())) {
			result.getParent().setLeft(elementX);
		}
		else {
			result.getParent().setRight(elementX);
		}
		
		//13-15--------------------------------------------------------------------------------------------|
		if(!(result.equals(elementZ))){
			//copia dati satellite
			elementZ.setElement(result.getElement());
			
			
		}
		
		//16-17--------------------------------------------------------------------------------------------|
		if(result.getColor().equals(Color.BLACK)){
			deleteFixup(elementX);
		}
		
		//18-----------------------------------------------------------------------------------------------|
		return result;
	}

	public T get(String element) {
		return null;
	}

	public RbTreeElement<T> getRoot() {
		return root;
	}

	void setRoot(RbTreeElement<T> root) {
		this.root = root;
	}

	public List<T> getList(String element) {
		return null;
	}
}
