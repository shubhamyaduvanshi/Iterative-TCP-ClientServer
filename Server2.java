import java.net.*;
import java.io.*;
import java.util.*;
public class Server2 extends Thread
{
   private ServerSocket serverSocket;
   
   public Server2(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(1007000);  //this time limit in mili second.
   }

   public void run()
   {
      int flg=0,i;
      String passwd,passWD[]={"1234","abcd","abcde","123","shubham","shivam","aniket","suraj"};
       Scanner sc=new Scanner(System.in);
      while(true)
      {
      flg=0;
         try
         {
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
           // System.out.println("Just connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            System.out.println("server is waiting for client password");
            passwd=in.readUTF();
            int l=passWD.length;
            String msgsendc,msgrecs;
            System.out.println("length of password string array is " + l );
            for(i=0;i<l;i++)
             if(passwd.equals(passWD[i])) {flg=1; break;}
            if(flg==1)
            { System.out.println("WELCOME!!!!!");
              out.writeUTF("WELCOME User!\n");
              
                System.out.println(" SERVER:Client ,now you can talk to me");
                System.out.println("----------------------------------------");
                while(true)
                {
                   System.out.println("SERVER:enter message for client :");
                   msgsendc=sc.nextLine();
                   out.writeUTF(msgsendc);
                   msgrecs=in.readUTF();
                   System.out.println("SERVER: message send by client is " + msgrecs);
                   if(msgrecs.equals("bye"))
                     break;
                }

            }
            else
            {
              System.out.println("Invalid user!!");
              out.writeUTF("Invalid User!!!\n");
            }
            server.close();
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   public static void main(String []args)
   {
      String arg;
      System.out.println("Enter Server port");
      Scanner sc=new Scanner(System.in);
      arg=sc.nextLine(); 
      int port = Integer.parseInt(arg);
      try
      {
         Thread t = new Server2(port);
         t.start(); 
      }catch(IOException e)
      {
         e.printStackTrace();
      }
      while(true);
   }
}
