package me.neon.soc.a72.Socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import me.neon.soc.a72.Data.ServerDate;
import me.neon.soc.a72.Message.Message;

public class DataSend extends Thread
{
	  private OutputStream os;
	  private ObjectOutputStream oos;
	  private Socket data_socket;
	  
	  public DataSend(Socket soc)
	  {
	    this.data_socket = soc;
	  }
	  public void User_network()
	  {
	    try
	    {
	      this.os = this.data_socket.getOutputStream();
	      this.oos = new ObjectOutputStream(this.os);
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
	    	HashMap<Integer,ArrayList<?>> $SkmdA12 = ServerDate.M_$sjnSa1();
	    	oos.writeObject((Object)$SkmdA12);
	    	
	    }
	    catch (IOException e)
	    {
	      try
	      {
	        this.oos.close();
	        this.data_socket.close();
	      }
	      catch (Exception localException) {}
	    }
	  }
	}
