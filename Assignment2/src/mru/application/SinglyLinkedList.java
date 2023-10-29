package mru.application;

public class SinglyLinkedList<type>
{
    private Node<type> start;

    public SinglyLinkedList() { start = null; }
    
    public boolean isEmpty(){ return (start == null); }

    
    public void addAt (int index, type data){        	
    	int length = size();        
    	if (length == 0 || index <= 0)
    		addToStart(data);
    	else if (length <= index)
    		addToEnd (data);
    	else {
    		Node<type> nodeToAdd = new Node<>(data);
    		Node<type> curr = start;                
    		for (int count = 0; count < index - 1; count++)                			
    			curr = curr.getNext();            
    		nodeToAdd.setNext (curr.getNext());            				
    		curr.setNext(nodeToAdd);
    	}
    }

    

    public type removeFromStart()
    {
        type data = null;

        if(start != null)
        {
            if(start.getNext() == null)
            {
                data = start.getData();
                start = null;
            }
            else
            {
                Node<type> tmp = start;
                data = start.getData();
                start = start.getNext();

                tmp.setNext(null);
            }

        }
        return data;
    }
    
    public type removeFromEnd()
    {
        if (start == null) {
        	return null;
        }
        
    	type data = null;
    	
    	if (size() == 1) {
    		data = start.getData();
        	start = null;
        	return data;
        }

        Node<type> curr = start;
        Node<type> prev = start;
       
        while (curr.getNext() != null) 
        {
        	prev = curr;
        	curr = curr.getNext();
        }
        data = curr.getData();
        prev.setNext(null);
        return data;
    }
    
    public type get(int index){
        type data = null;
        int length = size();
        Node<type> curr = start;
        
        if (index <= length && index >=0){
            curr = start;
            for (int count = 0; count < index; count++)
                curr = curr.getNext();
            data = curr.getData();    
        }
        return data;
    }
    

    
    
    public int size(){
        Node<type> curr = start;
        int length = 0;

        while(curr != null)
        {
            length++;
            curr = curr.getNext();
        }

        return length;
    }
    
    public void addToEnd(type data) {
        //without the if and else you will get a null pointer 
        //expection when adding the first element.
        
        Node<type> nodeToAdd = new Node<>(data); 
        if(start != null){
            Node<type> curr = start;

            while(curr.getNext() != null){
               curr = curr.getNext();
            }
            curr.setNext(nodeToAdd);
        }
        else
            start = nodeToAdd;
    }
    
    
    public void addToStart(type data) 
    { 
        Node<type> nodeToAdd = new Node<>(data); 
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
        Node<type> curr = start;

        System.out.print("Start->");

        while(curr != null)
        {
            System.out.print("["+curr.getData()+"]->");
            curr = curr.getNext();
        }

        System.out.println("null");
    }

}

