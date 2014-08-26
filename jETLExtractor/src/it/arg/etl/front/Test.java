package it.arg.etl.front;

//import java.sql.Connection;

import it.arg.etl.OraConnection;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//Connection orac = OraConnection.getConnection(18, "10.1.20.4:1521:cupqlik", "suetl01", "suetl011", false, true);
		
		OraConnection.getConnection(18, "10.1.20.4:1521:cupqlik", "suetl01", "suetl011", false, true);
		
		String truncStatement = OraConnection.getStepParm("EXEC_BEFORE");
		
		if (truncStatement==null)
			System.out.println("KO");
		else
			System.out.println(truncStatement);
		
	}

}
