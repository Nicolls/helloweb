package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer extends Thread{
	public static final int port=62224;
	private ServerSocket serverSocket;
	public MyServer (){
		//创建服务端
       try {
		serverSocket = new ServerSocket(port);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       System.out.println("服务端启动");
	}
	@Override
	public void run() {
		try {
           
            while(true){
            	Socket  socket = serverSocket.accept();
                System.out.println("服务端收到一客户端连接。");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                try {
                    String msg;//客户端发过来的信息
                    while ((msg = bufferedReader.readLine()) != null) {
                        System.out.println("服务端收到客户端的求合请求：" + msg);
                        String[] values = msg.toString().split(",");
                        int first = Integer.parseInt(values[0]);
                        int second = Integer.parseInt(values[1]);
                        String result = "服务器我帮你计算求和结果为："+(first+second);
                        sendBackToClient(socket,result);
                    }
                } catch (IOException e) {
                    try {
                        if (!socket.isClosed()) {
                            socket.close();
                        }
                    } catch (IOException e1) {
                        System.out.println("关闭socket出现错误");
                    }
                }
                System.out.println("提示：当前客户端已经断开连接，服务器正等待下一个客户端的连接。");
            }
         } catch (IOException e) {
             e.printStackTrace();
         }
	}
	
	private void sendBackToClient(final Socket  socket,final String msg){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("服务端把求值结果返回给客户端");
				PrintWriter printWriter=null;
				try {
					printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                printWriter.println(msg);
				
			}
		}).start();
	}
	
}
