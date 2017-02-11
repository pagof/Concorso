package concorso300;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Collections;
import java.util.Enumeration;



public class SortHashtable extends Hashtable implements Serializable{

  public Hashtable sort() {
    
    Hashtable ht = this;
    Vector v = new Vector(ht.keySet());
    Collections.sort(v);
    
    // Display (sorted) hashtable.
    for (Enumeration e = v.elements(); e.hasMoreElements();) {
      String key = (String)e.nextElement();
      Quesito val = (Quesito)ht.get(key);
      System.out.println("Key: " + key + "     Val: " + val.progressivo);
    }
   return  null;
}

  public Hashtable listaElementi() {
	    
	    Hashtable ht = this;
	    Vector v = new Vector(ht.keySet());
	    Collections.sort(v);
	    
	    // Display (sorted) hashtable.
	    for (Enumeration e = v.elements(); e.hasMoreElements();) {
	      String key = (String)e.nextElement();
	      Quesito val = (Quesito)ht.get(key);
	      System.out.print(" - " + key );
	    }
	    System.out.println(".");
	   return  null;
	}

  public Hashtable caricaElementi(SortHashtable x) {
	    
	    Hashtable ht = this;
	    Vector v = new Vector(ht.keySet());
	    Collections.sort(v);
	    
	    // Display (sorted) hashtable.
	    for (Enumeration e = v.elements(); e.hasMoreElements();) {
	      String key = (String)e.nextElement();
	      Quesito val = (Quesito)ht.get(key);
	      ht.put(key, val);
	      System.out.print(" add " + key );
	    }
	    System.out.println(".");
	   return  null;
	}
}
