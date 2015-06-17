package so.mwil.obdsim.obdpid;

import so.mwil.obdsim.utilities.ResponseUtils;

/**
 * Created by matthew on 01/11/14.
 */
public class IntakeManifoldAbsolutePressure implements IOBDPID {

    private int kpa = 96;
    private int offBoost = 0;

    public String getCode() {
        return "0B";
    }

    public String generateResponse(String mode) {

        if(offBoost > 0) {
            offBoost--;

            return ResponseUtils.buildOBDResponse(mode, getCode(), Integer.toHexString(96));
        } else {
            kpa++;

            if(kpa >= 337) {
                offBoost = 200;
                kpa = 96;
            }

            return ResponseUtils.buildOBDResponse(mode, getCode(), Integer.toHexString(kpa));
        }
    }
}
