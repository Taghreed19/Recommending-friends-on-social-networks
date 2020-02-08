public class MGraph<K extends Comparable<K>> implements Graph<K> {
	public Map<K, List<K>> adj; // Do not change this
   
	public MGraph() {
	adj=new BSTMap<K, List<K>>(); 	
	}
	public boolean addNode(K i){
   if(adj.retrieve(i).first==true)
   return false;
   List<K> l=new LinkedList<K>();
    adj.insert(i,l);
   return true;
   }

	// Check if i is a node
	public boolean isNode(K i){
   if(adj.retrieve(i).first==false)
   return false;
   return true;
   }

	// Add an edge to the graph if it does not exist and return true. If i or j do not exist or the edge (i, j) already exists, return false.
	public boolean addEdge(K i, K j){
   if(!isNode(i))
   return false;
   if(!isNode(j))
   return false;
   if(isEdge(i,j))
   return false;
   adj.retrieve(i).second.insert(j);
   adj.retrieve(j).second.insert(i);
   return true;
   }

	// Check if (i, j) is an edge.
	public boolean isEdge(K i, K j){
   if(i==null||j==null)
   return false;
   if(adj.retrieve(i).first==false)
   return false;
   if(adj.retrieve(j).first==false)
   return false;
   List<K> l =adj.retrieve(i).second;
   List<K> l2=adj.retrieve(j).second;
   boolean a=l.exists(j)&&l2.exists(i);
   return a;
   }

	// Return the set of neighbors of node i. If i does not exist, the method returns null.
	public List<K> neighb(K i){
   if(adj.retrieve(i).first==false)
   return null;
   List<K> l =adj.retrieve(i).second;
   return l;
   }

	// Return the degree (the number of neighbors) of node i. If i does not exist, the method returns -1.
	public int deg(K i){
   if(adj.retrieve(i).first==false)
   return -1;
   List<K> l =adj.retrieve(i).second;
   return l.size();
   }

	// Return a list containing the nodes in increasing order.
	public List<K> getNodes(){
   return adj.getKeys();
   }
}
