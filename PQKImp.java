public class PQKImp<P extends Comparable<P>, T> implements PQK<P, T> {
   private int size;
   private PQNode<P,T> head;
   private int k;
   
   public PQKImp(int k) {
      head = null;
      size = 0;
      this.k=k;
   }
   	// Return the length of the queue
   public int length(){
      return size;
   }

	// Enqueue a new element. The queue keeps the k elements with the highest priority. In case of a tie apply FIFO.
   public void enqueue(P pr, T e){////////////////////////////////
      PQNode<P,T> tmp = new PQNode<P,T>(e, pr);
      if((size == 0)||(pr.compareTo(head.priority)==1)) {
         tmp.next = head;
         head = tmp;
      size++;}
      else if(k>size){
         PQNode<P,T> p = head;
         PQNode<P,T> q = null;
         while((p != null)&&((pr.compareTo(p.priority))==0||(pr.compareTo(p.priority))==-1)) {
            q = p;
            p = p.next;
         }
         tmp.next = p;
         q.next = tmp;
      size++;}
      else{
         PQNode<P,T> p = head;
         PQNode<P,T> q = null;
         while((p != null)&&((pr.compareTo(p.priority))==0||(pr.compareTo(p.priority))==-1)) {
            q = p;
            p = p.next;
         }
         if((p != null)){
            tmp.next = p;
            q.next = tmp;
            size++;}
            }
      if(size>k){
       PQNode<P,T> p = head;
         PQNode<P,T> q = null;
         while(p!= null){
               q = p;
               p = p.next;
            }
            q.next=null;
            size--;
            }
   }


	// Serve the element with the highest priority. In case of a tie apply FIFO.
   public Pair<P, T> serve(){
      PQNode<P,T> node = head;
      PQNode<P,T> pqe=new PQNode<P,T>(node.data,node.priority);//PQElement<T>
      head = head.next;
      size--;
      Pair<P, T> p=new Pair<P, T> (pqe.priority,pqe.data);
      return p;
   }
	
}
