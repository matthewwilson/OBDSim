package so.mwil.obdsim.obdpid;

/**
 * Created by matthew on 01/11/14.
 */
public interface IOBDPID {

    public String getCode();
    public String generateResponse(String mode);

}
