public class PQNode<P extends Comparable<P>,T> {
	public T data;
	public P priority;
	public PQNode<P,T> next;
	
	public PQNode() {
		next = null;
	}
	
	public PQNode(T e, P p) {
		data = e;
		priority = p;
	}
}
