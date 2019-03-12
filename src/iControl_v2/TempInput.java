package iControl_v2;

import java.util.* ;

public class TempInput	//for debug purpose , providing same input interface of network version 
{
	private static Scanner in = new Scanner ( System.in ) ;
	private static String input ;
	private static boolean pushString ;
	
	private static boolean connectCheck ( String input ) //check if string is KEEP_ALIVE signal
	{
		if ( input.equals ( MainBlock.CONNECT_CHECK ) ) //if it's KEEP_ALIVE signal , then return false
		{
			return false ;
		}
		return true ; //if isn't , then return true
	}
	
	public static String getString ()
	{
		pushString = false ;
		do
		{
			input = in.nextLine () ;
			pushString = connectCheck ( input );
		}while ( pushString == false ) ; // only return string when pushString is true ( in other word , when input isn't KEEP_ALIVE signal )
		try 
		{
			Thread.sleep ( MainBlock.SLEEP_SECOND * 1000 ) ;
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input ;
	}
	
	public static String getPara ()//another version of getString
	{
		input = getString () ;
		System.out.println ( "Sending : " + MainBlock.ACKNOWLEDGEMENT ) ;//send back a ACK to confirm that parameter has been got 
		return input ;
	}
}
