import java.net.*;
import java.io.*;
import java.util.*;

public class Client2
{
   public static void main(String[] args)
   {
      Scanner sc=new Scanner(System.in);
      String arg,arg2,passwd;
      System.out.println("Enter Server name i.e ip of server");
      arg=sc.nextLine();
      System.out.println("Enter Server port for client to contact");
      arg2=sc.nextLine();
      String serverName = arg;
      int port = Integer.parseInt(arg2);
      try
      {
         System.out.println("Connecting to " + serverName + " on port " + port);
         Socket client = new Socket(serverName, port);
         System.out.println("Connected!");
         System.out.println("Enter your password");
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);  //for send stream of data to server.
         
         passwd=sc.nextLine(); 
         out.writeUTF(passwd);  
         InputStream inFromServer = client.getInputStream();
         DataInputStream in =new DataInputStream(inFromServer);
         
         String response,compmsg;
         response=in.readUTF();
         compmsg="WELCOME User!\n";
         
         if(compmsg.equals(response))
         {
             String msgrecc,msgsends;
             System.out.println("Now communication of client and server begins");
             System.out.println("------------------------------");
             while(true)
             {
                msgrecc=in.readUTF();
                System.out.println("CLIENT:message from server is:" + msgrecc);
                System.out.println("CLIENT:enter response and querry of client:");
                msgsends=sc.nextLine();
                out.writeUTF(msgsends);
                if(msgsends.equals("bye"))
                  break;
             }
         }
         System.out.println();
         client.close();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}
