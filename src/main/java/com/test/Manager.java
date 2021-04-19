package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Manager {
	
	
	private static  Manager instance;
	
	private MyClient myClient;
	private String message;
	private int a=0;
	public int readInt(){
		return a++;
	}
	private Manager(){
		init();
	}
	
	public static synchronized Manager getInstance(){
		if(instance==null){
			instance = new Manager();
		}
		return instance;
	}
	
	private void init(){
		new MyServer().start();
	}

	
	public void connectToServer(){
		if(myClient==null){
			System.out.println("客户端向服务端发起链接");
			myClient = new MyClient();
			myClient.setMesageCallBack(new MessageCallBack() {
				
				@Override
				public void onMessage(String message) {
					Manager.this.message = message;
					
				}
			});
			myClient.start();
		}
	
	}
	

	
	public String getResult(){
		return message;
	}
	
	public void sendMessage(int first,int second){
		myClient.sendData(first+","+second);
        
	}
	
}
