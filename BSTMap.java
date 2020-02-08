public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {
   public BSTNode<K, T> root; // Do not change this
   private int size;
  
   public BSTMap() {
      root=null;
      size =0;
   }
   public int size(){
      return size;
   }
   
   public boolean full(){
      return false;
   }
   
   public void clear(){
      root=null;
      size=0;
   }
	// Update the data of the key k if it exists and return true. If k does not exist, the method returns false.
   public boolean update(K k, T e){
      BSTNode<K, T> p = root;				
      if(size==0)
         return false;
   	
      while(p != null) {
         if(p.key.compareTo(k)==0) {
            p.data=e;
            return true;
         }
         else if(k.compareTo(p.key)<0)
            p = p.left;
         else
            p = p.right;
      }
      return false;
   }

	// Search for element with key k and returns a pair containing true and its data if it exists. If k does not exist, the method returns false and null.
   public Pair<Boolean, T> retrieve(K k){
      BSTNode<K, T> p = root;				
      if(size==0){
         Pair<Boolean, T> t=new Pair<Boolean, T>(false,null);
         return t;}
   	
      while(p != null) {
         if(p.key.compareTo(k)==0) {
            Pair<Boolean, T> t=new Pair<Boolean, T>(true,p.data);
            return t;
         }
         else if(k.compareTo(p.key)<0)
            p = p.left;
         else
            p = p.right;
      }
      return new Pair<Boolean, T>(false,null);
   }

	// Insert a new element if does not exist and return true. If k already exists, return false.
   public boolean insert(K k, T e){
       if(k==null||e==null)
       return false;
       
      if(retrieve(k).first==true)
         return false;
      BSTNode<K, T> n=new BSTNode<K, T>(k, e),q =root,p = root;
      if(size==0)
         root=n;
      else {
         while(p != null) {
            q = p;
            if(k.compareTo(p.key)<0)
               p = p.left;
            else
               p = p.right;
         }
         if(k.compareTo(q.key)<0)
            q.left=n;
         else
            q.right=n;
      }
      size++;
      return true;
   }

	// Remove the element with key k if it exists and return true. If the element does not exist return false.
   public boolean remove(K k){
      if(retrieve(k).first==false)
         return false;
      else{
         Boolean removed = new Boolean(false);
         BSTNode<K, T> p;
         p = remove_aux(k, root, removed);
         boolean re=Boolean.valueOf(removed);////////////
            size--;
         return true ;
      }
   }
   private BSTNode<K, T> remove_aux(K k,BSTNode<K, T> p, Boolean flag) {
   
      BSTNode<K, T> q, child = null;
      if(p == null)
         return null;
      if(k.compareTo(p.key)<0)
         p.left = remove_aux(k, p.left, flag); //go left
      else if(k.compareTo(p.key)>0)
         p.right = remove_aux(k, p.right, flag); //go right
      else {
         flag= true;
         if (p.left != null && p.right != null){ //two children
            q = find_min(p.right);
            p.key = q.key;
            p.data = q.data;
            p.right = remove_aux(q.key, p.right, flag);
         }
         else {
            if (p.right == null) //one child
               child = p.left;
            else if (p.left == null) //one child
               child = p.right;
            return child;
         }
      }
      return p;
   }
   private BSTNode<K, T> find_min(BSTNode<K, T> p){
      if(p == null)
         return null;
   	
      while(p.left != null){
         p = p.left;
      }
   	
      return p;
   }

	// Return the list of keys in increasing order.
   public List<K> getKeys(){
      List<K> l=new LinkedList<K>();
      getKeysin(root,l);
      return l;
   
   }
   private void getKeysin(BSTNode<K, T> p,List<K> l){
      if (p==null)
         return;
      getKeysin(p.left,l);
      l.insert(p.key);
      getKeysin(p.right,l);
   
   }
}
