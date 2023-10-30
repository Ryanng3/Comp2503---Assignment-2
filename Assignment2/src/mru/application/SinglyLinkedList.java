package mru.application;

import java.util.Comparator;

public class SinglyLinkedList<T>
{
    private Node<T> start;
    private Comparator<T> comparator;

    public SinglyLinkedList() { 
    	this.start = null;
    }
    
    public SinglyLinkedList(Comparator<T> comparator) {
    	this();
    	this.comparator = comparator;
    }
    
    public boolean isEmpty(){ return (start == null); }

    
    public void addAt (int index, T data){        	
    	int length = size();        
    	if (length == 0 || index <= 0)
    		addToStart(data);
    	else if (length <= index)
    		addToEnd (data);
    	else {
    		Node<T> nodeToAdd = new Node<>(data);
    		Node<T> curr = start;                
    		for (int count = 0; count < index - 1; count++)                			
    			curr = curr.getNext();            
    		nodeToAdd.setNext (curr.getNext());            				
    		curr.setNext(nodeToAdd);
    	}
    }

    public Node<T> getFirst(){
    	return start;
    }

    public T removeFromStart()
    {
        T data = null;

        if(start != null)
        {
            if(start.getNext() == null)
            {
                data = start.getData();
                start = null;
            }
            else
            {
                Node<T> tmp = start;
                data = start.getData();
                start = start.getNext();

                tmp.setNext(null);
            }

        }
        return data;
    }
    
    public T removeFromEnd()
    {
        if (start == null) {
        	return null;
        }
        
    	T data = null;
    	
    	if (size() == 1) {
    		data = start.getData();
        	start = null;
        	return data;
        }

        Node<T> curr = start;
        Node<T> prev = start;
       
        while (curr.getNext() != null) 
        {
        	prev = curr;
        	curr = curr.getNext();
        }
        data = curr.getData();
        prev.setNext(null);
        return data;
    }
    
    public T get(int index){
        T data = null;
        int length = size();
        Node<T> curr = start;
        
        if (index <= length && index >=0){
            curr = start;
            for (int count = 0; count < index; count++)
                curr = curr.getNext();
            data = curr.getData();    
        }
        return data;
    }
    

    
    
    public int size(){
        Node<T> curr = start;
        int length = 0;

        while(curr != null)
        {
            length++;
            curr = curr.getNext();
        }

        return length;
    }
    
    public void addToEnd(T data) {
        //without the if and else you will get a null pointer 
        //expection when adding the first element.
        
        Node<T> nodeToAdd = new Node<>(data); 
        if(start != null){
            Node<T> curr = start;

            while(curr.getNext() != null){
               curr = curr.getNext();
            }
            curr.setNext(nodeToAdd);
        }
        else
            start = nodeToAdd;
    }
    
    
    public void addToStart(T data) 
    { 
        Node<T> nodeToAdd = new Node<>(data); 
        if(isEmpty())
            start = nodeToAdd;
        else
        {
          nodeToAdd.setNext(start);
          start = nodeToAdd;
        }
    }
    
    public void print()
    {
        Node<T> curr = start;

        System.out.print("Start->");

        while(curr != null)
        {
            System.out.print("["+curr.getData()+"]->");
            curr = curr.getNext();
        }

        System.out.println("null");
    }

}

