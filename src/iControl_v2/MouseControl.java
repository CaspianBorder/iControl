package iControl_v2;

import java.awt.* ;
import java.awt.event.InputEvent;

public class MouseControl 
{
	private static int MOUSE_SENSITIVE = MainBlock.DEFAULT_MOUSE_SENSITIVE ;
	private static Point mousePos ;//object Point , shows the position of mouse pointer
	private static boolean LKeyHold = false ;
	private static boolean RKeyHold = false ;
	private static int x ;//mouse pointer's x coordinate
	private static int y ;//mouse pointer's y coordinate
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//get the screen size of current computer
	
	private static void getMousePos ()//get mouse pointer's location and re-write the x and y coordinate
	{
		mousePos = MouseInfo.getPointerInfo().getLocation() ;
		x = mousePos.x ;
		y = mousePos.y ;
	}
	private static void ChangeSensitive ( int input )
	{
		MOUSE_SENSITIVE = input ;
		return ;
	}
	public static void ResetSensitive ()
	{
		MOUSE_SENSITIVE = MainBlock.DEFAULT_MOUSE_SENSITIVE ;
		return ;
	}
	private static void clearHoldStatus ()//to clear current hold key before hold/press another key
	{
		if ( LKeyHold == true )
			ActionControl.robot.mouseRelease ( InputEvent.BUTTON1_MASK ) ;
		if ( RKeyHold == true )
			ActionControl.robot.mouseRelease ( InputEvent.BUTTON3_MASK ) ;
		return ;
	}
	public static void MC ( int cmdNo )
	{
		switch ( cmdNo )
		{
			case 1 ://mouse up
			{
				getMousePos() ;//refresh mouse position
				if ( y - 3 >= 0 )//boundary check
					ActionControl.robot.mouseMove ( x , y - MOUSE_SENSITIVE ) ;
				else
					ActionControl.robot.mouseMove ( x , 0 ) ;
				break ;
			}
			case 2 ://mouse down
			{
				getMousePos() ;
				if ( y + 3 < (int)screenSize.getHeight() )
					ActionControl.robot.mouseMove ( x , y + MOUSE_SENSITIVE ) ;
				else
					ActionControl.robot.mouseMove ( x , (int)screenSize.getHeight() ) ;
				break ;
			}
			case 3 ://mouse left
			{
				getMousePos () ;
				if ( x - 3 >= 0 )
					ActionControl.robot.mouseMove ( x - MOUSE_SENSITIVE , y ) ;
				else
					ActionControl.robot.mouseMove ( 0 , y ) ;
				break ;
			}
			case 4 ://mouse right
			{
				getMousePos () ;
				if ( x + 3 < (int)screenSize.getWidth() )
					ActionControl.robot.mouseMove ( x + MOUSE_SENSITIVE , y ) ;
				else
					ActionControl.robot.mouseMove ( (int)screenSize.getWidth() , y ) ;
				break ;
			}
			case 5 ://click left mouse button once
			{
				clearHoldStatus() ;//if any mouse buttons are holding , release them first
				ActionControl.robot.mousePress ( InputEvent.BUTTON1_MASK ) ;
				ActionControl.robot.mouseRelease ( InputEvent.BUTTON1_MASK ) ;
				break ;
			}
			case 6 ://hold/release left mouse button
			{
				if ( LKeyHold == false )//if LKeyHold is false ,then hold the left mouse button
				{
					clearHoldStatus() ;//if LKeyHold is false but RKeyHold is true ,then means right mouse button is holding. need to release Right key before hold Left Key
					ActionControl.robot.mousePress ( InputEvent.BUTTON1_MASK ) ;
					LKeyHold = true ;
				}
				else//release the left mouse button
				{
					ActionControl.robot.mouseRelease ( InputEvent.BUTTON1_MASK ) ;
					LKeyHold = false ;
				}
				return ;
			}
			case 7 ://click right mouse button once
			{
				clearHoldStatus() ;//same to case 5
				ActionControl.robot.mousePress ( InputEvent.BUTTON3_MASK ) ;
				ActionControl.robot.mouseRelease ( InputEvent.BUTTON3_MASK ) ;
				break ;
			}
			case 8 ://hold/release right mouse button
			{
				if ( RKeyHold == false )
				{
					clearHoldStatus() ;//same to case 6
					ActionControl.robot.mousePress ( InputEvent.BUTTON3_MASK ) ;
					RKeyHold = true ;
				}
				else
				{
					ActionControl.robot.mouseRelease ( InputEvent.BUTTON3_MASK ) ;
					RKeyHold = false ;
				}
				return ;
			}
			case 9 ://change mouse sensitive
			{
				String tempS ;
//				tempS = TempInput.getPara() ;//local debug version
				tempS = ServerManagement.getPara () ;//network version
				ChangeSensitive ( Integer.valueOf ( tempS ) ) ;
				break ;
			}
			default :
				break ;
		}
	}
}
