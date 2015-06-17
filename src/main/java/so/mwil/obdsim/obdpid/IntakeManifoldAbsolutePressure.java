package so.mwil.obdsim.obdpid;

import so.mwil.obdsim.utilities.ResponseUtils;

/**
 *  <h1>Mode 01 - Intake manifold absolute pressure</h1>
 *  <p>Min Value: 0</p>
 *  <p>Max Value: 255</p>
 *  <p>Units: kPa (absolute)</p>
 *
 *  Created by matthew on 01/11/14.
 */
public class IntakeManifoldAbsolutePressure implements IOBDPID {

    // Set standard values
    private final String unit = "kPa";
    private final String code = "0B";
    private final String minValue = "0";
    private final String maxValue = "255";

    private int value = 96;
    private int offBoost = 0;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String generateResponse(String mode) {

        if(offBoost > 0) {
            offBoost--;

            return ResponseUtils.buildOBDResponse(mode, getCode(), Integer.toHexString(96));
        } else {
            value++;

            if(value >= 337) {
                offBoost = 200;
                value = 96;
            }

            return ResponseUtils.buildOBDResponse(mode, getCode(), Integer.toHexString(value));
        }
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public String getMinValue() {
        return minValue;
    }

    @Override
    public String getMaxValue() {
        return maxValue;
    }

    @Override
    public String toString() {
        return "Intake manifold absolute pressure";
    }
}
