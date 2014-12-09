package me.neon.soc.a72.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.neon.soc.a72.Main;
import me.neon.soc.a72.GeneralClasses.LoadData;
import me.neon.soc.a72.Message.Message;

class UserInfo
  extends Thread
{
  private DataInputStream dis;
  private DataOutputStream dos;
  private Socket user_socket;
  
  public UserInfo(Socket soc)
  {
    this.user_socket = soc;
    User_network();
  }
  
  public void User_network()
  {
    try
    {
      this.dis = new DataInputStream(this.user_socket.getInputStream());
      this.dos = new DataOutputStream(this.user_socket.getOutputStream());
      
      Message.send_Message(dos,"정상 접속 되었습니다");
    }
    catch (Exception e)
    {
      Message._send_Message("스트림 셋팅 에러\n");
    }
  }
  
  
  
  public void run()
  {
    try
    {
      for (;;)
      {
        String msg = this.dis.readUTF();
        if (msg.startsWith("/"))
        {
          String _msg = msg.replace("/", "");
          Message.__send_Message(_msg);
          Message.send_Message(dos,"전체 메세지 전송 성공 (" + _msg + ")");
        }
        else
        {
          Message.InputtoServer(msg);
          Message.send_Message(dos,"커맨드 전송 성공 (" + msg + ")");
        }
      }
    }
    catch (IOException e)
    {
      try
      {
        this.dos.close();
        this.dis.close();
        this.user_socket.close();
        Message._send_Message("사용자 접속이 끊어졌습니다.\n");
        Main.Connection_Count--;
      }
      catch (Exception localException) {}
    }
    
  }
}
