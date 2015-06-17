package so.mwil.obdsim;

import so.mwil.obdsim.obdpid.IOBDPID;
import so.mwil.obdsim.obdpid.IntakeManifoldAbsolutePressure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthew on 30/10/14.
 */
public class OBDProtocol {

    private final List<IOBDPID> supportedPIDS;

    public OBDProtocol() {
        supportedPIDS = new ArrayList<IOBDPID>();

        // Add some PIDs
        supportedPIDS.add(new IntakeManifoldAbsolutePressure());
    }

    public String processInput(String inputLine) {

        if(inputLine != null && inputLine.length() == 4) {
            System.out.println("Received Command: " + inputLine);

            String mode = getModeFromRequest(inputLine);
            String code = getCodeFromRequest(inputLine);

            for(IOBDPID pid : supportedPIDS) {
                if(pid.getCode().equals(code)) {
                    //simulateDelay(500);
                    return pid.generateResponse(mode);
                }
            }
        }

        return null;
    }

    private void simulateDelay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getModeFromRequest(String inputLine) {
        return inputLine.substring(0, 2);
    }

    private String getCodeFromRequest(String inputLine) {
        return inputLine.substring(2, 4);
    }
}
