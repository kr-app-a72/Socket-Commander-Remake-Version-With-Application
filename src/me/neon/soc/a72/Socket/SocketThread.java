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
      Message._send_Message("Port : " + this.port + " 에서 서버실행중");
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
              Message._send_Message(cip + " 에서 사용자 접속성공!!\n");
              Main.Connection_Count++;
              } else {
            	  Message._send_Message("5명 이상 접속 차단");
              }
              }
            else
            {
            	Message._send_Message(cip + " 에서 사용자 접속 차단!!\n");
            }
          }
        }
        catch (IOException e)
        {
        	Message._send_Message("accept 에러 발생... !!!!\n");
        }
      }
      return;
    }
    catch (IOException e)
    {
    	Message._send_Message("소켓이 이미 사용중입니다...\n");
    }
  }
  

}
