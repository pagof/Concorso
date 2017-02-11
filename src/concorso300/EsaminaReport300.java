package concorso300;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Enumeration;



public class EsaminaReport300 {
	
	
	static String cartella = "C:\\TEMP\\concorso\\";
	static String nomeTest = "quesiti.txt";
	static String nomeParoleChiave = "listaParoleChiave.txt";
	//static int cont=0;
	static int numRisposteOK = 0;
	static int numDomande=0;
	/***********************************************
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			
			try {
				
				 
				
				// carica i quesiti e mescola le risposte
				SortHashtable elencoQuesiti= acquisisciQuesiti(cartella,nomeTest);
				
				System.out.println("------------------------------" );
				System.out.println(" digitare 'S' per memorizzare il risultato" );
				//System.out.println(" digitare 'L' per  ricaricare i test già effettuati" );
				System.out.println(" digitare 'A B C D' per rispondere" );
				System.out.println(" possono essere utilizzati caratteri maiuscoli o minuscoli" );
				System.out.println("------------------------------" );
				System.out.println(" BUON LAVORO LAURETTA !!!!" );
				System.out.println("------------------------------" );
				int n = elencoQuesiti.size();
				//
				String chiave = "";//"amministrativo";//Tuel";
				SortHashtable provaEsame= sorteggiaQuesiti(elencoQuesiti,n,chiave);
				System.out.println("\nhai risposto correttamente a tutti i quesiti !!!");
				//salvaProva(provaEsame);
				//eseguiProva(provaEsame);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	
	

	private synchronized static SortHashtable sorteggiaQuesiti(SortHashtable elencoQuesiti, int n, String chiave) {
		
		// TODO Auto-generated method stub
		/*
		File fout = new File(cartella+nomeTest);
		FileInputStream fos = new FileInputStream(fout);
		OutputStreamWriter osw = new InputStreamReader(fos);
		BufferedReader wr = new BufferedReader(osw);
		*/
		SortHashtable elencoUsciti = new SortHashtable(); 
		SortHashtable elencoErrati = new SortHashtable(); 
		Quesito quiz=null;
		
		
		// (25/6/2012) - PRIMA DI INIZIARE CARICA LE DOMANDE GIà SVOLTE 
		try {
			//System.out.println("caricamento");
			//elencoErrati=leggi("errati");
			//elencoUsciti=leggi("usciti");
			
			//elencoUsciti.put(""+quiz.progressivo,quiz);
			
			System.out.println("azzero statistiche");
			numRisposteOK = 0;
			numDomande=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		 int size = elencoQuesiti.size();
		 int i =0;
		 int uscite = elencoUsciti.size();
         int sorgente = 0;
         int var1=8;
         // non ho finito le domande oppure ho ancora delle errate
		 while((uscite<size )||(!elencoErrati.isEmpty())){
			 
			 uscite = elencoUsciti.size();
			 //limite al test
			 if(uscite>n) uscite = size;
			 
			 // fa solo le sbagliate
			 if(uscite == size) var1=0;
			 
			 sorgente=(int)(Math.random()*10)+1;
			 
			 //ripresenta una sbagliata
			 if (sorgente >= var1 && !elencoErrati.isEmpty()){
				 
				 //pesca tra quelle sbagliate
				 int h = elencoErrati.size();
				 
				 i=(int)(Math.random()*h)+1;
				 System.out.println("-INFO- RIPROPOSTO QUESITO ERRATO-("+h+")  numero "+i);
				 Enumeration enumeration = elencoErrati.elements();
				 for (int j=0;j<i;j++) quiz = (Quesito)enumeration.nextElement();
				 elencoErrati.remove(quiz.progressivo);
				 
				 
			 }else {
				    //pesca tra quelle non uscite
			 
					i=(int)(Math.random()*size)+1;
					int estrazioni = 0;
					
					try {
						while(   elencoUsciti.containsKey(""+i) || 
								 !((Quesito)elencoQuesiti.get(""+i)).Testo.toUpperCase().contains(chiave.toUpperCase())){
							 i=(int)(Math.random()*size)+1;
							 estrazioni ++;
							 if (estrazioni > 500000) {
								 System.out.println("non trovo altri quesiti...");
								 return null;
							 }
						 }
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("problemi con il quesito "+i);
						System.out.println(((Quesito)elencoQuesiti.get(""+i)).Testo);
					}
					//System.out.println(i);
					
					
					  
					 //System.out.println(""+i);
					 quiz = (Quesito)elencoQuesiti.get(""+i);
				    }
			 
		     int tentativi = 0;
		     boolean azzeccato=false;
		     //int corretti = elencoUsciti.size();
			 
			 
			 elencoUsciti.put(""+quiz.progressivo,quiz);
			 //elencoUsciti.listaElementi();
			 //elencoErrati.listaElementi();
			 System.out.println("copertura :"+100*elencoUsciti.size()/size+"%"+"("+elencoUsciti.size()+"/"+size+")");
			 System.out.println("   errate :"+elencoErrati.size());
		     

		     quiz.stampa();
		     String punteggio ="";
		     
		     if (numDomande < 1) punteggio ="" ;
			 else           punteggio ="["+numRisposteOK+"/"+(numDomande)+"    "+(int)((numRisposteOK*100)/(numDomande))+"%] " ;

		     System.out.println(" punteggio:"+punteggio);
			 
		     numDomande++;
		     while(!azzeccato){
				 System.out.print(" >>>:");	 
			 
				 
			     
				 
				 int x=0;
			     String risposta = "X";
				try {
					x = System.in.read();
					if (x == '.'   ) return null;
					if (x == 'A' || x == 'a') risposta = "A";
					if (x == 'B' || x == 'b') risposta = "B";
					if (x == 'C' || x == 'c') risposta = "C";
					if (x == 'D' || x == 'd') risposta = "D";
					if (x == 'S' || x == 's') {
						
						  try {
							  System.out.println("salvataggio");
							salva(elencoErrati,"errati");
							salva(elencoUsciti,"usciti");
							
							while(System.in.available()>0) System.in.skip(1);
							continue;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
							
					}
					/*
					if (x == 'L' || x == 'l') {
						
						  try {
							  System.out.println("caricamento");
							elencoErrati=leggi("errati");
							elencoUsciti=leggi("usciti");
							//elencoUsciti.caricaElementi(elencoErrati);
							elencoUsciti.put(""+quiz.progressivo,quiz);
							
							System.out.println("azzero statistiche");
							numRisposteOK = 0;
							numDomande=1;
							
							
							while(System.in.available()>0) System.in.skip(1);
							continue;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
							
					}
					*/	
					while(System.in.available()>0) System.in.skip(1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			   if(quiz.rispostaCorretta.equals(risposta.toUpperCase())) {
				   if(tentativi == 0 ) {
					   //elencoUsciti.put(""+quiz.progressivo,quiz);
					   //System.out.println(elencoUsciti);
					   numRisposteOK++;
				   }
				   Toolkit.getDefaultToolkit().beep();
				   
				   System.out.print("corretto !");
				   //piccola pausa
				   System.out.println("\n\n\n");
				   azzeccato= true;
			   }
			   else{

				   tentativi++;
				   elencoErrati.put(""+quiz.progressivo, quiz);
				   //System.out.println(elencoErrati);
				   System.out.print("riprova ");
			       //Toolkit.getDefaultToolkit().beep();
			   }    
			 }
		 }		 
		 return elencoUsciti;
	}




	private static void salva(SortHashtable elenco,String nomeHash) throws Exception {
		FileOutputStream fos = new FileOutputStream(cartella+  nomeHash+".ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(elenco);
		oos.close();
		System.out.println("memorizzato "+elenco.size()+" quiz su 'elenco "+nomeHash+"'");
		 

	}




	private static SortHashtable leggi(String nomeHash) throws Exception {
		// TODO Auto-generated method stub
		
		 

		FileInputStream fis = new FileInputStream(cartella+ nomeHash+".ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		SortHashtable oggettoSerializzato = (SortHashtable) ois.readObject();
		ois.close();
		
		System.out.println("ricaricati "+oggettoSerializzato.size()+" quiz su 'elenco "+nomeHash+"'");

		//oggettoSerializzato.listaElementi();
		return oggettoSerializzato;
		
		

	}




	private static SortHashtable acquisisciQuesiti(String cartella, String nomeTest) throws Exception {

		File fin = new File(cartella+nomeTest);
		FileInputStream fis = new FileInputStream(fin);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		SortHashtable lista = new SortHashtable();
		// si posiziona sul secondo elemento
		String rigaInput = br.readLine();
		Quesito quiz = new Quesito();
		
		String domanda="";
		String rispostaA="";
		String rispostaB="";
		String rispostaC="";
		String rispostaD="";
		String prefix = "300 POSTI ISTRUTTORE AMMINISTRATIVO";
		//String prefix = "110 POSTI FUNZIONARIO AMMINISTRATIVO";
		
		int contatore = 0;
		int linea=1;
		while(rigaInput!=null){
							
			if(rigaInput.startsWith(prefix)) {
				rigaInput=br.readLine();
				continue;
			}
			
			if(linea==1){
				if(rigaInput.startsWith("A)")){
					//salto alla sezione
					linea++;
					//System.out.println(contatore + ">"+domanda);
				}else {
					domanda+=rigaInput+"\n";
					rigaInput = br.readLine();
					continue;
				}
				
			}
			if(linea==2){
				
				if(rigaInput.startsWith("B)")){
					//salto alla sezione
					linea++;
					//System.out.println(rispostaA);
				}else {
					rispostaA+=rigaInput+"\n";
					rigaInput = br.readLine();
					continue;
				}
			}
			if(linea==3){
				if(rigaInput.startsWith("C)")){
					//salto alla sezione
					linea++;
					//System.out.println(rispostaB);
				}else {
					rispostaB+=rigaInput+"\n";
					rigaInput = br.readLine();
					continue;
				}
			}if(linea==4){
				if(rigaInput.startsWith("D)")){
					//salto alla sezione
					linea++;
					//System.out.println(rispostaC);
				}else {
					rispostaC+=rigaInput+"\n";
					rigaInput = br.readLine();
					continue;
				}
			}
			if(linea==5){
				if(rigaInput.indexOf(". ") == 4){
					//salto alla sezione
					contatore++;
					linea=1;
					//System.out.println(rispostaD);
					
					try {
						quiz = new Quesito();
						quiz.risposta[0]= rispostaA.substring(2); rispostaA="";
						quiz.risposta[1]= rispostaB.substring(2); rispostaB="";
						quiz.risposta[2]= rispostaC.substring(2); rispostaC="";
						quiz.risposta[3]= rispostaD.substring(2); rispostaD="";
						quiz.Testo= domanda; domanda="";
						quiz.progressivo= ""+contatore;
						
						quiz.mescolaRisposte();
						//quiz.stampa();
						lista.put(quiz.progressivo,quiz);
					} catch (Exception e) {
						System.out.println(rispostaD+quiz.risposta[2]);
						System.out.println(contatore);
						e.printStackTrace();
					}	
				}else {
					rispostaD+=rigaInput+"\n"; 
					rigaInput = br.readLine();
					continue;
				}			
			}
		}
		System.out.println("info: caricato "+contatore+" quiz");
		fis.close();
		return lista;

	    
	}
}