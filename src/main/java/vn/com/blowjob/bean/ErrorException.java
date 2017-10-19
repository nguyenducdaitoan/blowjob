package vn.com.blowjob.bean;

/**
 * ErrorException
 *
 * @version 1.0
 * @author ToanNDD
 */
public class ErrorException {

    private String errCode;
    private String errMsg;

    /**
     * Constructor
     */
    public ErrorException() {
    }

    /**
     * Constructor error code and error message of objects to create
     *
     */
    public ErrorException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
     *
     * @return errCode
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * @param errCode the errCode to set
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    /**
    *
    * @return errMsg
    */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * @param errMsg the errMsg to set
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
