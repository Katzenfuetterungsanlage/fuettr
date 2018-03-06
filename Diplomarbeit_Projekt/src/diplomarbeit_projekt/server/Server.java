/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Florian
 */
public class Server
{

    private final int port;
    private ServerSocket serverSocket;
    private Socket socket;

    private static Server instance = null;
    
    protected Server() throws IOException, InterruptedException
    {
        port = 62222;
    }
    
    public static Server getInstance() throws IOException, InterruptedException
    {
        if (instance == null)
        {
            instance = new Server();
        }
        return instance;
    }

    public void start() throws IOException, InterruptedException
    {
        serverSocket = new ServerSocket(port);
        while (true)
        {
            socket = serverSocket.accept();
            new Thread(new ConnectionThread(socket)).start();
        }
    }

    public void stop() throws IOException
    {
        socket.close();
    }

//    public static void main(String[] args) throws IOException, InterruptedException
//    {
//        new Server(666).start();
//    }
}
