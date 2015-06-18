package so.mwil.obdsim.obdpid;

import so.mwil.obdsim.utilities.ResponseUtils;

/**
 *  <h1>Mode 01 - Engine RPM</h1>
 *  <p>Min Value: 0</p>
 *  <p>Max Value: 16,383.75</p>
 *  <p>Units: rpm</p>
 *
 *  Created by amouly on 6/17/15.
 */
public class EngineRpm implements IOBDPID {

    // Set standard values
    private final String unit = "rpm";
    private final String code = "0C";
    private final String minValue = "0";
    private final String maxValue = "16,383.75";

    // Set random value
    private final Integer value = 1200;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String generateResponse(String mode) {
        return ResponseUtils.buildOBDResponse(mode, getCode(), Integer.toHexString(value));
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
        return "Engine RPM";
    }
}
