package mru.application;

public class Node < type extends Comparable <type> >{
	private type data;
	private Node<type> next = null;
	
	public Node (type data) {
		this.data = data;
	}

	public type getData() {
		return data;
	}

	public void setData(type data) {
		this.data = data;
	}

	public Node<type> getNext() {
		return next;
	}

	public void setNext(Node<type> next) {
		this.next = next;
	}
	
}
