package iControl_v2;

public class ConnectionControl 
{
	public static void passiveListen ()
	{
//		if ( TempInput.getString ().equals ( MainBlock.CONNECT_SIGNAL ) )//local debug version
		if ( ServerManagement.getString().equals ( MainBlock.CONNECT_SIGNAL ) )//network version
		{
			MainBlock.isConnect = true ;
//			System.out.println ( "System connected!" ) ;
//			System.out.println ( "Sending : " + MainBlock.ACKNOWLEDGEMENT ) ;
			UserInterface.changeText ( 1 , 3 ) ;
			ServerManagement.sendMsg ( MainBlock.ACKNOWLEDGEMENT ) ;
		}
		return ;
	}
	public static boolean disconnectCheck ( String input )
	{
		if ( input.equals ( MainBlock.DISCONNECT_SIGNAL ) ) 
		{
			MainBlock.isConnect = false ;
//			System.out.println ( "System disconnected!" ) ;
//			System.out.println ( "Sending : " + MainBlock.DISCONNECT_SIGNAL ) ;
			UserInterface.changeText ( 1 , 2 ) ;
			ServerManagement.sendMsg ( MainBlock.DISCONNECT_SIGNAL ) ;
			return true ;
		}
		return false ;
	}
}
