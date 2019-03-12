package iControl_v2;

import java.awt.* ;

public class ActionControl 
{
	private static String input ;
	private static int cmdNo ;
	public static Robot robot = null ;
	
	private static void initRobot ()
	{
		try
		{
			robot = new Robot () ;
		}
		catch ( AWTException e )
		{
//			e.printStackTrace () ;
		}
	}
	public static void AC ()
	{
//		input = TempInput.getString () ;//local debug version
		input = ServerManagement.getString () ;
		getCmd ( input ) ;
	}
	public static void getCmd ( String input )
	{
//		input = TempInput.getString () ;
		if ( ConnectionControl.disconnectCheck ( input ) == true )
			return ;
		else
		{
			if ( robot == null )
				initRobot () ;
			cmdNo = Integer.valueOf ( input ) % 100 ;
//			System.out.println ( cmdNo ) ;
			switch ( Integer.valueOf ( input ) / 100 )
			{
				case 1 :
				{
//					System.out.println ( "Starting mouse control!" ) ;
					MouseControl.MC ( cmdNo ) ;
					break ;
				}
				case 2 :
				{
//					System.out.println ( "Starting keyboard control!" ) ;
					KeyboardControl.KC ( cmdNo ) ;
					break ;
				}
				case 3 :
				{
//					System.out.println ( "Starting shutdown control!" ) ;
					ShutdownControl.SC ( cmdNo ) ;
					break ;
				}
				case 4 :
				{
//					System.out.println ( "Starting robot control!" ) ;
					RobotControl.RC ( cmdNo ) ;
					break ;
				}
				default :
					break ;
			}
			
		}
		
	}
}
