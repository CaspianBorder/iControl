package iControl_v2;

import java.io.* ;
import java.net.* ;

public class ServerManagement 
{
	private static ServerSocket server = null ;
	private static Socket socket = null ;
	private static BufferedReader in ;
	private static PrintWriter out ;
	private static String input ;
//	private static char[] temp = new char [ 3 ] ;
	private static boolean pushString = false ;
//	private static int i ;
	private static void initServer () throws IOException
	{
		server = new ServerSocket ( 11223 ) ;
		socket = server.accept () ;
		in = new BufferedReader ( new InputStreamReader ( socket.getInputStream () ) ) ;
		out = new PrintWriter ( socket.getOutputStream () ) ;
	}
	private static boolean connectCheck ( String input ) //check if string is KEEP_ALIVE signal
	{
		if ( !input.equals ( null ) &&input.equals ( MainBlock.CONNECT_CHECK ) ) //if it's KEEP_ALIVE signal , then return false
		{
			return false ;
		}
		return true ; //if isn't , then return true
	}
	public static void sendMsg ( String msg )
	{
		out.println ( msg ) ;
		out.flush () ;
		return ;
	}
	public static String getString ()
	{
		if ( server == null )
		{
			try 
			{
				initServer () ;
			} 
			catch (IOException e) 
			{
//				System.out.println ( "Initialize server error" ) ;
				e.printStackTrace();
			}
		}
		pushString = false ;
		do
		{
/*
			for ( i = 0 ; i < 3 ; i ++ )
			{
				try 
				{
					temp [ i ] = (char) in.read () ;
				} 
				catch (IOException e) 
				{
					System.out.println ( "Read error" ) ;
				}
			}
			input = String.valueOf ( temp ) ;
*/
			try 
			{
				input = in.readLine () ;
			} 
			catch (IOException e) 
			{
//				System.out.println ( "Read error" ) ;
			}
			System.out.println ( "Recive:" + input ) ;
			pushString = connectCheck ( input );
		}
		while ( pushString == false ) ; // only return string when pushString is true ( in other word , when input isn't KEEP_ALIVE signal )
		try 
		{
			Thread.sleep ( MainBlock.SLEEP_SECOND * 1000 ) ;
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return input ;
	}
	public static String getPara ()
	{
		input = getString () ;
//		sendMsg ( MainBlock.ACKNOWLEDGEMENT ) ;//send back a ACK to confirm that parameter has been got 
		return input ;
	}
	public static void shutdownServer () throws IOException
	{
		if ( server != null )
		{
			server.close () ;
			server = null ;
		}
		if ( socket != null )
		{
			socket.close() ;
			socket = null ;
		}
	}
}
