package iControl_v2;

import java.awt.event.KeyEvent;
import java.util.* ;

public class KeyboardControl 
{
	private static int[] BindTable = null ;
	private static final int MAX_CMD_NO = 73 ; //the max number of BindTable have
	private static boolean keyCombination = false ;
	private static ArrayList <Integer> comboKey = new ArrayList <Integer> () ;
	private static void initBindTable ()
	{
		int i = 0 ;
		int j = 1 ;
		BindTable = new int [ 100 ] ;
		for ( i = 0 ; i < 26 ; i ++ )//BindTable 1 - 26 : alphabet
		{
			BindTable [ j ] = ( KeyEvent.VK_A + i ) ;
			j ++ ;
		}
		for ( i = 0 ; i < 10 ; i ++ )//BindTable 27 - 36 : number 0 - 9
		{
			BindTable [ j ] = ( KeyEvent.VK_0 + i ) ;
			j ++ ;
		}
		for ( i = 0 ; i < 12 ; i ++ )//BindTable 37 -48 : F1 - F12
		{
			BindTable [ j ] = ( KeyEvent.VK_F1 + i ) ;
			j ++ ;
		}
		BindTable [ j ] = ( KeyEvent.VK_ESCAPE ) ;//BindTable 49
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_TAB ) ;//BindTable 50
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_CAPS_LOCK ) ;//BindTable 51
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_SHIFT ) ;//BindTable 52
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_CONTROL ) ;//BindTable 53
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_ALT ) ;//BindTable 54
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_WINDOWS ) ;//BindTable 55
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_SPACE ) ;//BindTable 56
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_ENTER ) ;//BindTable 57
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_BACK_SPACE ) ;//BindTable 58
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_DELETE ) ;//BindTable 59
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_MINUS ) ;//BindTable 60 , "-"
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_EQUALS ) ;//BindTable 61 , "="
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_OPEN_BRACKET ) ;//BindTable 62 , "["
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_CLOSE_BRACKET ) ;//BindTable 63 , "]"
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_BACK_SLASH ) ;//BindTable 64 , "\"
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_SEMICOLON ) ;//BindTable 65 , ";"
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_QUOTE ) ;//BindTable 66 , "'"
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_COMMA ) ;//BindTable 67 , ","
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_PERIOD ) ;//BindTable 68 , "."
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_SLASH ) ;//BindTable 69 , "/"
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_UP ) ;//BindTable 70 , "¡ü"
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_DOWN ) ;//BindTable 71 , "¡ý"
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_LEFT ) ;//BindTable 72 , "¡û"
		j ++ ;
		BindTable [ j ] = ( KeyEvent.VK_RIGHT ) ;//BindTable 73 , "¡ú"
		j ++ ;	
	}
	private static void singleKC ( int cmdNo )//press one key at a time
	{
		if ( BindTable == null )//if BindTable hasn't been initialized ,create it first
			initBindTable () ;
		if ( cmdNo > 0 && cmdNo <= MAX_CMD_NO )//command validation check 
		{
			ActionControl.robot.keyPress ( BindTable [ cmdNo ] ) ;
			ActionControl.robot.keyRelease ( BindTable [ cmdNo ] ) ;
		}
		return ;
	}
	private static void comboKC ( int cmdNo )//press more than one key at a time
	{
		if ( cmdNo > 0 && cmdNo <= MAX_CMD_NO )//command validation check
		{
			comboKey.add ( cmdNo ) ;//if it isn't the end , push it in to ArrayList
			ActionControl.robot.keyPress ( BindTable [ cmdNo ] ) ;
		}
		else if ( cmdNo == 0 )
		{
			keyCombination = false ;
			for ( int i = 0 ; i < comboKey.size () ; i ++ )
			{
				ActionControl.robot.keyRelease ( BindTable [ comboKey.get ( i ) ] ) ;
			}
			comboKey.clear () ;//clear the ArrayList
		}
		return ;
	}
	public static void KC ( int cmdNo )
	{
		if ( cmdNo == 0 && keyCombination == false )
		{
			keyCombination = true ;
		}
		else
		{
			if ( keyCombination == false )
				singleKC ( cmdNo ) ;
			else
				comboKC ( cmdNo ) ;
		}
		return ;
	}
}
