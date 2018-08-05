package rpcclient.common.model;

/**
 * Created by Administrator on 2018/8/4.
 */
public class RpcResult {

    private String success;

    private Object retval;

    private String errorCode;

    public String getSuccess() {
        return success;
    }

    public Object getRetval() {
        return retval;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setRetval(Object retval) {
        this.retval = retval;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
