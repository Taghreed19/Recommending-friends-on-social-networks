import java.io.File;
import java.util.Scanner;

public class Recommender {

	// Return the top k recommended friends for user i using the popular nodes method. If i does not exist, return null. In case of a tie, users with the lowest id are selected.
   public static <K extends Comparable<K>> PQK<Double, K> recommendPop(Graph<K> g, K i, int k) {
      if(!g.isNode(i))
         return null;
      List<K> ll =g.neighb(i);
      List<K> l =g.getNodes();
      List<K>list;
      PQK<Double, K> pq =new PQKImp<Double, K>(k);
      Map<K,Double> pql=new BSTMap<K,Double>();
      double d;
      K ss;
      boolean f=false;
      l.findFirst();
      for(int j=0;j<l.size();j++){
         ss=l.retrieve();
         if(ll.exists(ss)||ss.compareTo(i)==0)//////////
            f=true;
         if(!f){
            d= g.deg(ss);
            pql.insert(ss,d);}
         l.findNext();
         f=false;
      }
      list=pql.getKeys();
      K s1;
      list.findFirst();
      for(int j=0;j<list.size();j++){
         s1=list.retrieve();
         d= g.deg(s1);
         pq.enqueue(d,s1);
         list.findNext();
      }
   
   
   
      // if(!g.isNode(i))
   //       return null;
   //       List<K> ll =g.neighb(i);
   //       List<K> l =g.getNodes();
   //       PQK<Double, K> pq =new PQKImp<Double, K>(k);
   //       double d;
   //       K ss;
   //       boolean f=false;
   //       l.findFirst();
   //       for(int j=0;j<l.size();j++){
   //       ss=l.retrieve();
   //       if(ll.exists(ss)||ss.compareTo(i)==0)//////////
   //       f=true;
   //       if(!f){
   //       d= g.deg(ss);
   //       pq.enqueue(d,ss);}
   //       l.findNext();
   //       f=false;
   //       }
   //       
   //       Map<K,Double> pql=new BSTMap<K,Double>();
   //       Pair<Double,K> p1;
   //       List<K>list;
   //       K s1;
   //       int length=pq.length();
   //       for(int j=0;j<length;j++){
   //       p1=pq.serve();
   //       pql.insert(p1.second, p1.first);}
   //       
   //       list=pql.getKeys();
   //       list.findFirst();
   //       for(int j=0;j<length;j++){
   //       s1=list.retrieve();
   //       d= g.deg(s1);
   //       pq.enqueue(d,s1);
   //       list.findNext();}
   //////////////
      // Pair<P, T> p= pq.serve(),p1;
   //       for(int j=0;j<length;j++){
   //       p1=pq.serve();
   //       if(p1.second==p.second){
   //       if(p1.first<p.first)
   //       pq.enqueue(p1.first,p1.second)
   //       pq.enqueue(p.first,p.second)}
      
      return pq;
      
   }

	// Return the top k recommended friends for user i using common neighbors method. If i does not exist, return null. In case of a tie, users with the lowest id are selected.
   public static <K extends Comparable<K>> PQK<Double, K> recommendCN(Graph<K> g, K i, int k) {
     
   	if(!g.isNode(i))
      return null;
      K ss,s1;
      double c=0;
      boolean f=false;
      List<K> ll =g.neighb(i),lll=new LinkedList<K>();;
      List<K> l =g.getNodes();
      //system.out.print(g.getNodes());
      PQK<Double, K> pq =new PQKImp<Double, K>(k);
      int size=l.size();
      l.findFirst();
      //System.out.println(i+"=");
      for(int j=0;j<size;j++){
      ss=l.retrieve();
      //System.out.println(ss);
      if(ll.exists(l.retrieve())||ss.compareTo(i)==0){
     // System.out.println(ss+"+"+(ll.exists(l.retrieve())||ss.compareTo(i)==0));
      l.remove();
      continue;}
      if(!(ll.exists(l.retrieve()))&&ss.compareTo(i)!=0){
      lll=g.neighb(l.retrieve());
      if(lll!=null){
      lll.findFirst();
      for(int kk=0;kk<lll.size();kk++){
      s1=lll.retrieve();
      if(ll.exists(s1))
      c++;
      lll.findNext();
      }}}
      pq.enqueue(c,ss);
      l.findNext();
      c=0;
      }

   // 	if(!g.isNode(i))
//       return null;
//       K ss,s1;
//       int dj=0;
//       double c=0;
//       boolean f=false; 
//       List<K> ll =g.neighb(i),lll;
//       List<K> l =g.getNodes();
//       PQK<Double, K> pq =new PQKImp<Double, K>(k);
//       int size=l.size();
//       l.findFirst();
//       for(int j=0;j<size;j++){
//       ss=l.retrieve();
//       if(ll.exists(ss)||ss.compareTo(i)==0)
//       f=true;
//       if(!f){
//       lll=g.neighb(ss);
//       if(lll!=null){
//       lll.findFirst();
//       for(int kk=0;kk<lll.size();kk++){
//       s1=lll.retrieve();
//       if(ll.exists(s1))
//       c++;
//       lll.findNext();
//       }}
//       pq.enqueue(c,ss);
//       //dj++;
//       l.remove();}
//       if(f)
//       l.findNext();
//       f=false;
//       c=0;
//       }
//       
//      boolean n=false;
//       
//       while(dj<k){
//       l.findFirst();
//       ss=l.retrieve();
//       if(ss.compareTo(i)==0)
//       n=true;
//       if(!n){
//       pq.enqueue(0.0,ss);
//       dj++;}
//       l.findNext();
//       n=false;
//       }
      return pq;
	}
 
	// Read graph from file. The file is a text file where each line contains an edge. The end and start of the edge are separated by space(s) or tabs.
   public static Graph<Integer> read(String fileName) {
   
      try {
         Graph<Integer> g = new MGraph<Integer>();
         Scanner scanner = new Scanner(new File(fileName));
         while (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            g.addNode(i);
            int j = scanner.nextInt();
            g.addNode(j);
            g.addEdge(i, j);
         }
         scanner.close();
         return g;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }
}
