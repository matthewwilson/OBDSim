package so.mwil.obdsim;

/**
 * Created by matthew on 30/10/14.
 */
public class Main {

    public static boolean USE_BLUETOOTH = false;

    public static void main(String[] args) {

        int restartCount = 10;

        while(restartCount > 0) {
            restartCount--;

            if(USE_BLUETOOTH) {
                OBDBluetoothServer server = new OBDBluetoothServer();
                server.start();
            } else {
                OBDServer server = new OBDServer();
                server.start(35000);
            }
        }
    }

}
