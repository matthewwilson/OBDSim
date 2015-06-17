package so.mwil.obdsim.utilities;

/**
 * Created by matthew on 01/11/14.
 */
public class ResponseUtils {

    public static String buildOBDResponse(String mode, String command, String value) {
        String responseMode = convertRequestModeToResponseMode(mode);

        String response = responseMode + " " + command + " " + value;

        return mode + command + "\r\r\n" + response + "\r\n";
    }

    public static String buildOBDEndResponse() {
        return ">";
    }

    private static String convertRequestModeToResponseMode(String requestMode) {
        return requestMode.replace("0", "4");
    }

}
