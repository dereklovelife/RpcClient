package rpcclient.common.encode;

import rpcclient.common.model.RpcRequest;
import rpcclient.common.model.RpcResult;

/**
 * Created by Administrator on 2018/8/4.
 */
public interface EncodeExecutor {

    byte[] buildRequest(RpcRequest request);

    byte[] buildResponse(RpcResult result);

    RpcRequest toRequest(byte[] bytes);

    RpcResult toResult(byte[] bytes);
}
