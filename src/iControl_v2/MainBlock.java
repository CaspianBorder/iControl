package iControl_v2;

import java.io.IOException;

class MainThread extends Thread //main thread 
{
	public void run ()
	{
//		System.out.println ( "Welcome!" ) ;
		while ( true )
		{
			if ( MainBlock.threadRunning == false )
				return ;
			else if ( MainBlock.isConnect == false )
			{
//				System.out.println ( "System standing by!" ) ;
				ConnectionControl.passiveListen () ;
			}
			else
			{
//				System.out.println ( "Awaiting signal!" ) ;
				ActionControl.AC () ;
			}
		}
	}
}

public class MainBlock 
{
	public static boolean isConnect = false ;
	public static final String CONNECT_SIGNAL = "001" ;
	public static final String DISCONNECT_SIGNAL = "002" ;
	public static final String CONNECT_CHECK = "003" ;
	public static final String ACKNOWLEDGEMENT = "004" ;
	public static final String END_OF_ROBOT_INPUT = "400" ;
	public static final int SLEEP_SECOND = 0 ;
	public static final int DEFAULT_MOUSE_SENSITIVE = 10 ;
	public static boolean threadRunning = false ;
	private static Thread t = null ;
/*
	public static void main ( String[] args )
	{
		Thread t = new Thread ( new MainThread () ) ;
		t.start () ;
		return ;
	}
*/
	public static void startThread ()
	{
		if ( threadRunning == false )
		{
			t = new Thread ( new MainThread () ) ;
			threadRunning = true ;
			t.start () ;
			return ;
		}	
	}
	public static void stopThread ()
	{

		try 
		{
			ServerManagement.shutdownServer() ;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
//			System.out.println ( "Shutdown server failed" ) ;
		}

		threadRunning = false ;
		t.stop () ;
		System.runFinalization () ;
		UserInterface.changeText ( 3 , 1 ) ;
		return ;
	}
}
