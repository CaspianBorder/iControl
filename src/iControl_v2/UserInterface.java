package iControl_v2;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
//import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

//import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
//import javax.swing.JLabel;

public class UserInterface {

	private static JFrame frmIcontrol;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private static String notReady = new String ( "\u672A\u5C31\u7EEA" ) ;
	private static String standBy = new String ( "\u5DF2\u5C31\u7EEA/\u672A\u8FDE\u63A5" ) ;
	private static String isConnect = new String ( "\u5DF2\u8FDE\u63A5" ) ;
	private static String notWorking = new String ( "\u672A\u542F\u52A8" ) ;
	private static String isWorking = new String ( "\u5DF2\u542F\u52A8" ) ;
	private static String isDefining = new String ( "\u5B9A\u4E49\u4E2D" ) ;
	private static JTextField textField_4;
	private static JTextField textField_5;
	private static JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void changeText ( int jpanelNo , int textNo )
	{
		switch ( jpanelNo )
		{
			case 1 :
			{
				if ( textNo == 1 )
				{
					textField_4.setText ( notReady ) ;
					textField_4.setForeground(Color.RED);
					frmIcontrol.getContentPane().add(textField_4);
				}
				else if ( textNo == 2 )
				{
					textField_4.setText ( standBy ) ;
					textField_4.setForeground(Color.ORANGE);
					frmIcontrol.getContentPane().add(textField_4);
				}
				else if ( textNo == 3 )
				{
					textField_4.setText ( isConnect ) ;
					textField_4.setForeground(Color.GREEN);
					frmIcontrol.getContentPane().add(textField_4);
				}
				break ;
			}
			case 2 :
			{
				if ( textNo == 1 )
				{
					textField_5.setText ( notWorking ) ;
					textField_5.setForeground(Color.RED);
					frmIcontrol.getContentPane().add(textField_5);
				}
				else if ( textNo == 2 )
				{
					textField_5.setText ( isWorking ) ;
					textField_5.setForeground(Color.GREEN);
					frmIcontrol.getContentPane().add(textField_5);
				}
				break ;
			}
			case 3 :
			{
				if ( textNo == 1)
				{
					textField_6.setText ( notWorking ) ;
					textField_6.setForeground(Color.RED);
					frmIcontrol.getContentPane().add(textField_6);
				}
				else if ( textNo == 2 )
				{
					textField_6.setText ( isDefining ) ;
					textField_6.setForeground(Color.ORANGE);
					frmIcontrol.getContentPane().add(textField_6);
				}
				else if ( textNo == 3 )
				{
					textField_6.setText ( isWorking ) ;
					textField_6.setForeground(Color.GREEN);
					frmIcontrol.getContentPane().add(textField_6);
				}
				break ;
			}
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frmIcontrol.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmIcontrol = new JFrame();
		frmIcontrol.getContentPane().setBackground(Color.WHITE);
		frmIcontrol.setResizable(false);
		frmIcontrol.setTitle("iControl");
		frmIcontrol.setBounds(100, 100, 680, 394);
		frmIcontrol.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIcontrol.getContentPane().setLayout(null);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("",false);
		tglbtnNewToggleButton.setToolTipText("\u5355\u51FB\u6309\u94AE\u4EE5\u5207\u6362\u7CFB\u7EDF\u72B6\u6001");
		tglbtnNewToggleButton.setForeground(Color.BLACK);
		tglbtnNewToggleButton.setBounds(418, 252, 242, 94);
		ImageIcon stopIcon = new ImageIcon ( "icon" + File.separator + "stopButton.png" ) ;
		ImageIcon startIcon = new ImageIcon ( "icon" + File.separator + "startButton.png" ) ;
		tglbtnNewToggleButton.setIcon ( stopIcon ) ;
		tglbtnNewToggleButton.setSelectedIcon ( startIcon ) ;
		frmIcontrol.getContentPane().add(tglbtnNewToggleButton);
		tglbtnNewToggleButton.addItemListener(new ItemListener() 
		{
			  public void itemStateChanged(ItemEvent itemEvent) 
			  {
			    int state = itemEvent.getStateChange() ;
			    if (state == ItemEvent.SELECTED) 
			    {
			    	
//			      System.out.println("Selected");
			    	changeText ( 1 , 2 ) ;
			    	MainBlock.startThread () ;
			    }  
			    else 
			    {
//			      System.out.println("Deselected");
			    	changeText ( 1 , 1 ) ;
			    	MainBlock.stopThread() ;
			    }
			  }
		});
		
		JTextArea txtricontrolwifi = new JTextArea();
		txtricontrolwifi.setEditable(false);
		txtricontrolwifi.setBackground(Color.WHITE);
		txtricontrolwifi.setFont(new Font("Monospaced", Font.PLAIN, 22));
		txtricontrolwifi.setText("\t\u6B22\u8FCE\u4F7F\u7528iControl\r\n\u82E5\u9700\u8981\u4F7F\u7528\u5BF9\u5E94\u5B89\u5353\u8F6F\u4EF6\u63A7\u5236\u672C\u673A\r\n\u8BF7\u786E\u4FDD\u624B\u673A\u4E0E\u672C\u673A\u8FDE\u63A5\u5728\u540C\u4E00Wifi\u4E2D");
		txtricontrolwifi.setBounds(14, 35, 362, 113);
		frmIcontrol.getContentPane().add(txtricontrolwifi);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setForeground(Color.BLUE);
		textField.setFont(new Font("宋体", Font.PLAIN, 34));
		textField.setText("\u5F53\u524D\u72B6\u6001\uFF1A");
		textField.setBounds(39, 153, 315, 52);
		textField.setBorder(new EmptyBorder(0,0,0,0) );
		frmIcontrol.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setFont(new Font("宋体", Font.PLAIN, 23));
		textField_1.setEditable(false);
		textField_1.setText("\u8F6F\u4EF6\u72B6\u6001\uFF1A");
		textField_1.setBounds(76, 218, 126, 33);
		textField_1.setBorder(new EmptyBorder(0,0,0,0) );
		frmIcontrol.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setText("\u5B9A\u65F6\u5173\u673A\uFF1A");
		textField_2.setFont(new Font("宋体", Font.PLAIN, 23));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBorder(new EmptyBorder(0,0,0,0));
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(76, 264, 126, 33);
		frmIcontrol.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText("\u81EA\u52A8\u63A7\u5236\uFF1A");
		textField_3.setFont(new Font("宋体", Font.PLAIN, 23));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBorder(new EmptyBorder(0,0,0,0));
		textField_3.setBackground(Color.WHITE);
		textField_3.setBounds(76, 313, 126, 33);
		frmIcontrol.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setForeground(Color.RED);
		textField_4.setText ( notReady ) ;
		textField_4.setFont(new Font("宋体", Font.PLAIN, 23));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBorder(new EmptyBorder(0,0,0,0));
		textField_4.setBackground(Color.WHITE);
		textField_4.setBounds(216, 218, 126, 33);
		frmIcontrol.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setForeground(Color.RED);
		textField_5.setText ( notWorking ) ;	
		textField_5.setFont(new Font("宋体", Font.PLAIN, 23));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBorder(new EmptyBorder(0,0,0,0));
		textField_5.setBackground(Color.WHITE);
		textField_5.setBounds(216, 264, 126, 33);
		frmIcontrol.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setForeground(Color.RED);
		textField_6.setText ( notWorking ) ;
		textField_6.setFont(new Font("宋体", Font.PLAIN, 23));
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBorder(new EmptyBorder(0,0,0,0));
		textField_6.setBackground(Color.WHITE);
		textField_6.setBounds(216, 313, 126, 33);
		frmIcontrol.getContentPane().add(textField_6);
	}
}
