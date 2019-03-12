package iControl_v2;

import java.io.IOException;

public class ShutdownControl 
{
	private static final String shutdownExecute = new String ( "shutdown -s -t " ) ;
	private static final String shutdownAbort = new String ( "shutdown -a" ) ;
	public static void SC ( int cmdNo ) 
	{
		switch ( cmdNo )
		{
			case 1 ://shutdown PC after X seconds
			{
				try 
				{
					Runtime.getRuntime().exec ( shutdownAbort ) ;//clear possible shutdown schedule 
//					Runtime.getRuntime().exec ( shutdownExecute + TempInput.getPara () ) ;//pass command line to system's command console//local debug version
					Runtime.getRuntime().exec ( shutdownExecute + ServerManagement.getPara() ) ;//network version
					UserInterface.changeText ( 2 , 2 ) ;
				}
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break ;
			}
			case 2 : //abort shutdown
			{
				try 
				{
					Runtime.getRuntime().exec ( shutdownAbort ) ;//pass command line to system's command console
					UserInterface.changeText ( 2 , 1 ) ;
				}
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break ;
			}
		}
	}
}
