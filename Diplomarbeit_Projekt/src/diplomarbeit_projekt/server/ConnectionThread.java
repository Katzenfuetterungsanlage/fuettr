/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.server;

import diplomarbeit_projekt.gui.MainWindow;
import diplomarbeit_projekt.singleton.errors_warnings.ErrorAndWarningHandler_Singleton;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Florian
 */
public class ConnectionThread implements Runnable
{

    private final Socket socket;

    public ConnectionThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        System.out.println("ConnectionThread gestartet");
        try
        {
            // o -> timeout infinity, readLine will block
            socket.setSoTimeout(0);
            BufferedReader bufferdReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            LinkedList<String> inputLines = new LinkedList<>();
            System.out.println("Wait for Request:");
            while (true)
            {
                String line = bufferdReader.readLine();
                if (line == null)
                {
                    return;
                }
                if (line.length() == 0)
                {
                    handleRequest(inputLines);
                    System.out.println("Wait for Request:");
                }
                else
                {
                    System.out.println(line);
                    inputLines.add(line);
                }
            }
        }
        catch (SocketException se)
        {
            System.out.println("done");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            System.out.println("ConnectionThread beendet " + socket);
        }
    }

    private void handleRequest(List<String> header) throws IOException
    {
        Request req = new Request(header);

        if (!req.getMethod().equals("GET"))
        {
            if (!req.getMethod().equals("PUT"))
            {
                sendResponse(404);
            }
        }

        // send to client
        if (req.getMethod().equals("GET"))
        {
            switch (req.getUrl())
            {
                case "/errors_warnings":
                    sendResponse(200, ErrorAndWarningHandler_Singleton.getInstance().toJson().toString());
                    break;
                default:
                    sendResponse(404);
            }
        }

        // receive from client
        if (req.getMethod().equals("PUT"))
        {
            switch (req.getUrl())
            {
                case "/ChangeMachineState":
                    // System.out.println(req.getAttributes()); // test
                    sendResponse(200, "MachineStae received");
                    MainWindow.getInstance().machineStateChanger();
                    break;
                default:
                    sendResponse(404);
            }
        }

    }

    private void sendResponse(int statusCode) throws IOException
    {
        sendResponse(statusCode, null);
    }

    private void sendResponse(int statusCode, String body) throws IOException
    {
        if (body == null)
        {
            body = "";
        }
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));

        writer.append("HTTP/1.1 ").append("" + statusCode).append("\n");
        writer.append("Date: ").append(String.format("%tc", new GregorianCalendar())).append("\n");
        writer.append("Server: ").append("Example JSON response Server by Florian Greistorfer feat. SX").append("\n");
        writer.append("Connection: Keep-Alive\n");
        writer.append("Content-Length: ").append(String.valueOf(body.length())).append("\n");
        writer.append("Content-Type: application/json\n");
        writer.append("\n");
        writer.append(body);
        writer.flush();

    }

    private String getDefaultBody()
    {
        String string = "{" + '"' + "warnings" + '"' + ": [{" + '"' + "warning" + '"' + ": " + '"' + "warning 1" + '"' + "}, {" + '"' + "warning" + '"' + ": " + '"' + "warning 2" + '"' + "}, {" + '"' + "warning" + '"' + ": " + '"' + "warning 3" + '"' + "}]}";
        return string;
    }
}
