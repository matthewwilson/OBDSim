package so.mwil.obdsim.obdpid;

/**
 * Created by matthew on 01/11/14.
 */
public interface IOBDPID {

    String getCode();

    String generateResponse(String mode);

}
