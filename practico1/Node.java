package practico1;


public class Node<T> {
	
	private T info;
	private Node<T> next;


	
	public Node() {
		this.info = null;
		this.next = null;
	}

	public Node(T info, Node<T>next) {
		this.setInfo(info);
		this.setNext(next);
	}
	
	public Node<T> getNext(){
		return next;
	}
	
	public void setNext(Node<T> next){
		this.next = next;
	}
	

	public T getInfo() {
		return info;
	}
	
	public void setInfo(T info) {
		this.info = info;
	}
	
	/*PREGUNTAR COMO SERIA ESTE EQUALS!!
	@Override
    public boolean equals(Object o) {
        Node<T> otro = (Node <T>) o;
        try{
            return otro.getInfo().equals(this.getInfo());
        }
        catch (Exception e){
            return false;
        }
    }
    *///NO HAY QUE HACER EL EQUALS DE NODO

}
