package so.mwil.obdsim;

import so.mwil.obdsim.utilities.ResponseUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by matthew on 30/10/14.
 */
public class OBDServer {

    public void start(int portNumber) {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);

            InetAddress IP = InetAddress.getLocalHost();
            System.out.println("Simulator is running on : " + IP.getHostAddress() + " port number : " + portNumber);

            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine, outputLine;

            OBDProtocol obdp = new OBDProtocol();

            System.out.println("Connected to client");

            while ((inputLine = in.readLine()) != null) {

                outputLine = obdp.processInput(inputLine);

                if(outputLine != null) {
                    out.println(outputLine);
                    out.println(ResponseUtils.buildOBDEndResponse());
                }
            }

            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}