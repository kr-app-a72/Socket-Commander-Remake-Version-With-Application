package me.neon.soc.a72.Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import me.neon.soc.a72.Main;
import me.neon.soc.a72.Message.Message;

public class SocketThread
  extends Thread
{
  public Main plugin;
  public ServerSocket socket;
  int port;
  long buffered;
  private Socket soc;
  
  public SocketThread(Main plug, int listenport)
  {
    this.plugin = plug;
    this.port = listenport;
    setName("Socket Listener");
    start();
  }
  
  public void run()
  {
    try
    {
      this.socket = new ServerSocket(this.port);
      Message._send_Message("Port : " + this.port + " ���� ����������");
      if (this.socket != null) {
        try
        {
          for (;;)
          {
            this.soc = this.socket.accept();
            String cip = this.soc.getInetAddress().getHostAddress();
            if ((this.plugin.allowall) || 
              (this.plugin.configiplist.contains(cip)))
            {
              Main.pwal.put(cip, Boolean.valueOf(false));
              if (Main.Connection_Count < 5){
              UserInfo user = new UserInfo(this.soc);
              user.start();
              Message._send_Message(cip + " ���� ����� ���Ӽ���!!\n");
              Main.Connection_Count++;
              } else {
            	  Message._send_Message("5�� �̻� ���� ����");
              }
              }
            else
            {
            	Message._send_Message(cip + " ���� ����� ���� ����!!\n");
            }
          }
        }
        catch (IOException e)
        {
        	Message._send_Message("accept ���� �߻�... !!!!\n");
        }
      }
      return;
    }
    catch (IOException e)
    {
    	Message._send_Message("������ �̹� ������Դϴ�...\n");
    }
  }
  

}
