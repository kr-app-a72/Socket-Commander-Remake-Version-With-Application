package me.neon.soc.a72.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import me.neon.soc.a72.Main;
import me.neon.soc.a72.Message.Message;

class UserInfo
  extends Thread
{
  private InputStream is;
  private OutputStream os;
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
      this.is = this.user_socket.getInputStream();
      this.dis = new DataInputStream(this.is);
      this.os = this.user_socket.getOutputStream();
      this.dos = new DataOutputStream(this.os);
      
      Message.send_Message(dos,"���� ���� �Ǿ����ϴ�");
    }
    catch (Exception e)
    {
      Message._send_Message("��Ʈ�� ���� ����\n");
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
          Message.send_Message(dos,"��ü �޼��� ���� ���� (" + _msg + ")");
        }
        else
        {
          Message.InputtoServer(msg);
          Message.send_Message(dos,"Ŀ�ǵ� ���� ���� (" + msg + ")");
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
        Message._send_Message("����� ������ ���������ϴ�.\n");
        Main.Connection_Count--;
      }
      catch (Exception localException) {}
    }
  }
}
