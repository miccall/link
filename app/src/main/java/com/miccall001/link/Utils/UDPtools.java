package com.miccall001.link.Utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * Created by miccall on 2017/6/2 0002.
 */

public class UDPtools {
    private  Thread thread;
    private  InetAddress serverAddress ;
    private  DatagramSocket socket;
    private  String CONTENT = "";
    private  int PORT = 5200;
    private  String HOST ;
    private  boolean mWorking ;
    private Context context;


    public UDPtools(Context context ,int PORT ,String HOST)
    {
       this.context = context ;
       this.PORT = PORT ;
       this.HOST = HOST;

        try {
            serverAddress = InetAddress.getByName(HOST);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public  void sendDataByUDP() {

        try {
            socket = new DatagramSocket();
            DatagramPacket sendPacket = new DatagramPacket(CONTENT.getBytes(),CONTENT.getBytes().length,serverAddress,PORT);
            socket.send(sendPacket);
        }  catch (SocketException e) {
            e.printStackTrace();
            Log.e("sendUDP","SocketException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("sendUDP","IOException");
        }
    }

    public   void start() {
        mWorking = true;
        if (thread != null && thread.isAlive()) {

        }else{
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    sendDataByUDP();
                }
            });
        }
        thread.start();
    }

    public  void stop() {

        if (mWorking) {
            if (thread != null && thread.isAlive()) {
                if(socket!=null)
                    socket.close();
                thread.interrupt();
                thread = null;
            }
            mWorking = false;
        }
    }



    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }
}
