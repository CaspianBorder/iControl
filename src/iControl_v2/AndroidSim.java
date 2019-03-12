package iControl_v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class AndroidSim 
{
	private static Socket socket ;
    public static void main( String[] args) throws IOException {
        try 
        {
            socket = new Socket ( "127.0.0.1" , 11223 ) ;
            System.out.println ( "客户端启动成功" ) ;
            BufferedReader br = new BufferedReader ( new InputStreamReader ( System.in ) ) ;
            PrintWriter write = new PrintWriter ( socket.getOutputStream () ) ;
            BufferedReader in = new BufferedReader ( new InputStreamReader ( socket.getInputStream () ) ) ;
            String input;
            input = br.readLine();
            while ( true ) 
            {
                write.println ( input ) ;
                write.flush();
                System.out.println ( "Sending : " + input ) ;
/*                if ( input.equals ( "001" ) || input.equals ( "002" ) )
                	System.out.println("Server:" + in.readLine());
                if ( input.equals ( "109" ) || input.equals ( "301" ) || input.equals ( "400" ) )
                {
                	input = br.readLine();
                    write.println ( input ) ;
                    write.flush();
                    System.out.println("Server:" + in.readLine());
                }
*/
                input = br.readLine();
            }
        } 
        catch (Exception e)
        {
            System.out.println("can not listen to:" + e);
            socket.close();
        }
    }

}