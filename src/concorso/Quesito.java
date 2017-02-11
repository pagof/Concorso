package concorso;

import java.io.BufferedReader;
import java.io.Serializable;
import java.util.StringTokenizer;

//#Riepilogo*#Trasferimenti tra gruppi:#Priorità SLM#Priorità*#BNL_Severita_Richiesta#BNL_Tipo_Richiesta#Nome*+#Cognome*+#ID incidente*+#Data riportata+#Data chiusura#Data ultima risoluzione#Gruppo assegnato*+#Assegnato.TIME#Assegnato.USER#Risoluzione#Stato*#Note#Note#Data inoltro#Livello categorizzazione operativa 2#Livello categorizzazione operativa 3#BNL_Flag_Sollecito(536871226)#BNL_Sigla_Servizio#BNL_ValidazCateg(1000015976)#

public class Quesito implements Serializable{
String Testo;
String rispostaCorretta;
String risposta[] ={"","","",""};
String mescolata[] ={"","","",""};
String progressivo;

String Lrisposte[] = {"A","B","C","D"};

void mescolaRisposte(){
 int i =0;
 int cont=0;
 i=(int)(Math.random()*4);
 mescolata[i]=Lrisposte[i]+risposta[0];
 //System.out.print(Lrisposte[i]);
 rispostaCorretta=Lrisposte[i];
 cont++;
 while(cont<4){
	 //System.out.println((int)(Math.random()*4));
  
	 i=(int)(Math.random()*4);
	 if( mescolata[i].equals("")) {
		 //System.out.print(Lrisposte[i]);
		 mescolata[i]=Lrisposte[i]+risposta[cont];
		 cont++;
		 
	 }
	 
 }
 //System.out.println();
 
 
	
}

public void stampa() {

	System.out.println("**********************************");
	System.out.println(this.Testo);
	System.out.println("-----------------------------------");
	System.out.println(this.mescolata[0]);
	System.out.println(this.mescolata[1]);
	System.out.println(this.mescolata[2]);
	System.out.println(this.mescolata[3]);
}


}
