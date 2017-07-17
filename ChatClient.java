import java.rmi.*;
import java.io.*;
import java.rmi.server.*;
import java.util.*;
import java.net.InetAddress;
 
public class ChatClient {
	public static void main (String[] argv) {
	    int ch;
		try {
		    	System.setSecurityManager(new RMISecurityManager());
		    	Scanner s=new Scanner(System.in);
				ChatInterface server = (ChatInterface)Naming.lookup("rmi://192.168.1.143:1099/joyalot");
		    	System.out.println("Enter Your name and press Enter:");
		    	String name=s.nextLine().trim();		    		    	
		    	ChatInterface client = new Chat(name);
				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				InetAddress IP=InetAddress.getLocalHost();
				do
				{
					System.out.println("\n\t1.Personal Chat \n\t2.Group Chat \n\t3.Ask Question \n\t 4.Exit");
					System.out.println("\n Enter Your Choise :");
					ch=Integer.parseInt(br.readLine());
					switch(ch)
					{
						case 1:
						{
							String msg=client.getName()+">> is connected" +IP.getHostAddress();
							server.send(msg);
							System.out.println("Ready To Chat:");
							server.setClient(client);
							while(true)
							{
								msg=s.nextLine().trim();
								msg=IP.getHostAddress()+"/"+client.getName()+" \t>> "+msg;		    		
								server.send(msg);
							}
							
						}
						case 2:
						{	System.out.println("System Will Update Soon....");
							break;
						}						
						case 3:
						{	System.out.println("Then Ask Question..");
							break;
						}
					}	
				}while(ch<4);
	    	}catch (Exception e) {
	    		System.out.println("[System] Server failed: " + e);
	    	}
		}
}