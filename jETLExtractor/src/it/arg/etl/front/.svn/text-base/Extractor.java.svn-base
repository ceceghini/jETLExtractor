package it.arg.etl.front;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import it.arg.etl.Cursor;
import it.arg.etl.OraConnection;

public class Extractor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Recupero l'ID dello step
		/*if(args.length<1) {
			System.out.println("ID dello step mancante");
			System.out.println("java -jar jETLExtractor idstep");
			return;
		}
		
		int idStep = Integer.parseInt(args[0]);*/
		/*args = new String[2];
		args[0] = "--idstep=1";
		args[1] = "";*/
		
		String sIdStep = getParms(args, "--idstep");
		if (sIdStep == "") {
			System.out.println("ID dello step mancante");
			System.out.println("java -jar jETLExtractor --idstep=1");
			return;
		}
		int idStep = Integer.parseInt(sIdStep);
		
		
		boolean command = Boolean.parseBoolean(getParms(args, "--command"));
		
		// Apertura del file proprietà e recupero delle informazioni
		Properties properties = new Properties();
		try {
	        properties.load(new FileInputStream("jETLExtractor.properties"));
	    } catch (IOException e) {
	    	System.out.println(e.getMessage());
	    	return;
	    }
	    String cs = properties.getProperty("ORA_CS");
	    String userName = properties.getProperty("ORA_USERNAME");
	    String password = properties.getProperty("ORA_PASSWORD");
	    
	    boolean infolog = false;
	    if (properties.getProperty("INFO_LOG").compareTo("true")==0) {
	    	infolog = true;
	    }
	    
	    Connection orac;
		// Connessione al DB Oracle
		try {
//			 Connessione al db oracle
			orac = OraConnection.getConnection(idStep, cs, userName, password, infolog, command);
			orac.setAutoCommit(false);
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		// Esecuzione del cursore oracle
		try { 
		
			Cursor c = new Cursor();
			c.Execute();
			
			orac.commit();
			orac.close();
		
		}
		catch(Exception e) {
			e.printStackTrace();
			try {
				orac.rollback();
				orac.close();
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	public static String getParms(String[] args, String parmName) {
		
		String[] a;
		
		for(int i=0;i<args.length;i++) {
			a = args[i].split("=");
			if (a[0].compareTo(parmName)==0) 
				return a[1];
		}
		
		return "";
		
	}
}
