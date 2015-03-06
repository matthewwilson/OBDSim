package so.mwil.obdsim;

import so.mwil.obdsim.utilities.ResponseUtils;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by matthew on 07/11/14.
 */
public class OBDBluetoothServer {

    public void start() {
        // retrieve the local Bluetooth device object
        LocalDevice local = null;

        StreamConnectionNotifier notifier;
        StreamConnection connection = null;

        // setup the server to listen for connection
        try {
            local = LocalDevice.getLocalDevice();
            local.setDiscoverable(DiscoveryAgent.GIAC);

            UUID uuid = new UUID(80087355); // "04c6093b-0000-1000-8000-00805f9b34fb"
            String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetooth";
            notifier = (StreamConnectionNotifier) Connector.open(url);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // waiting for connection
        while(true) {
            try {
                System.out.println("waiting for connection...");
                connection = notifier.acceptAndOpen();

                processConnection(connection);

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void processConnection(StreamConnection connection)
    {
        try {
            PrintWriter out = new PrintWriter(connection.openOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.openInputStream()));

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
