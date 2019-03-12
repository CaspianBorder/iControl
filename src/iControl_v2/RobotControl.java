package iControl_v2;

import java.util.* ;

class TimingThread extends Thread//timing thread, for controlling robot's start timing and actions
{
	public void run ()
	{
		do
		{
			RobotControl.setCurrentTime () ;
			if ( RobotControl.robotStatus == false )
				return ;
		}
		while ( !RobotControl.isTimeUp () ) ;
		MouseControl.ResetSensitive () ;
		ActionControl.robot.mouseMove ( 0 , 0 ) ;
		for ( int i = 0 ; i < RobotControl.cmdTable.size() ; i ++ )
		{
			if ( RobotControl.robotStatus == false )
				return ;
			try 
			{
				Thread.sleep( RobotControl.timeTable.get ( i ) ) ;
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ActionControl.getCmd ( RobotControl.cmdTable.get ( i ) ) ;
		}
		RobotControl.clearRC () ;
		return ;
	}
}

public class RobotControl 
{
	private static long startTime = 0 ;
	private static long actionTime = 0 ;
	private static long currentTime = 0 ;
	private static long lastTime = 0 ;
	private static long tempTime = 0 ;
	public static ArrayList <String> cmdTable = new ArrayList <String> () ;
	public static ArrayList <Long> timeTable = new ArrayList <Long> () ;
	public static boolean robotStatus = false ;
	private static boolean inputingRobot = false ;
	private static String input ;
	private static void initStartTime ()
	{
		startTime = System.currentTimeMillis() ;
		lastTime = startTime ;
		return ;
	}
	private static void setActionTime ( long input )
	{
		actionTime = System.currentTimeMillis() + ( input * 1000 ) ;
		return ;
	}
	public static void setCurrentTime ()
	{
		currentTime = System.currentTimeMillis() ;
		return ;
	}
	private static long getDelay ( long targetTime )
	{
		tempTime = targetTime - lastTime ;
		lastTime = targetTime ;
		return ( tempTime ) ;
	}
	public static boolean isTimeUp ()
	{
		if ( currentTime >= actionTime )
			return true ;
		return false ;
	}
	public static void clearRC ()
	{
		robotStatus = false ;
		UserInterface.changeText ( 3 , 1 ) ;
		cmdTable.clear () ;
		timeTable.clear () ;
		startTime = 0 ;
		actionTime = 0 ;
		currentTime = 0 ;
		tempTime = 0 ;
		lastTime = 0 ;
	}
	public static void RC ( int cmdNo )
	{
		switch ( cmdNo )
		{
			case 1 ://start robot control
			{
				//input robot control's command
				if ( robotStatus == true )
					clearRC () ;
				if ( inputingRobot == true )//in case of accidentally active another robot control while setting a robot control 
					return ;
				UserInterface.changeText ( 3 , 2 ) ;
				MouseControl.ResetSensitive () ;//reset mouse sensitive to avoid differences
				inputingRobot = true ;
				robotStatus = true ;
				initStartTime () ;//set benchmark
				ActionControl.robot.mouseMove ( 0 , 0 ) ;//reset mouse position, set benchmark
//				input = TempInput.getString () ;//local debug version
				input = ServerManagement.getString () ;//network version
				while ( !input.equals ( MainBlock.END_OF_ROBOT_INPUT ) )
				{
					ActionControl.getCmd ( input ) ;
					cmdTable.add ( input ) ;
					setCurrentTime () ;
					timeTable.add ( getDelay ( currentTime ) ) ;
//					input = TempInput.getString () ;//local debug version
					input = ServerManagement.getString () ;//network version
				}
//				input = TempInput.getPara () ;//local debug version
				input = ServerManagement.getPara () ;//network version
//				input = ServerManagement.getString () ;
				setActionTime ( Long.valueOf ( input ) ) ;
				UserInterface.changeText ( 3 , 3 ) ;
				Thread t1 = new Thread ( new TimingThread () ) ;
				t1.start () ;
				inputingRobot = false ;
				break ;
			}
			case 2 ://abort robot control
			{
				//abort robot control's schedule
				clearRC () ;
				break ;
			}
			default :
				break ;
		}
	}
}
