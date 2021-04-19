package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient extends Thread {
	private Socket socket;
	private PrintWriter printWriter;
	private MessageCallBack callBack;
	public MyClient (){
		this.callBack =callBack;
		try {
			socket = new Socket("127.0.0.1", MyServer.port);
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setMesageCallBack(MessageCallBack callBack){
		this.callBack = callBack;
	}
	
	@Override
	public void run() {
		BufferedReader bufferedReader =null;

        try {
        	bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;//服务器发过来的信息
            while ((msg = bufferedReader.readLine()) != null) {
                System.out.println("客户端收到服务器的返回：" + msg);
                if(callBack!=null){
                	callBack.onMessage(msg);
                }
            }
        } catch (IOException e) {
            System.out.println("警告：断开连接！");
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e1) {
                System.out.println("读取线程：关闭socket出现错误");
            }
        }
	}
	
	public void sendData(String msg){
		System.out.println("客户端向服务器请求帮忙计算:"+msg);
		printWriter.println(msg);
	}
}
