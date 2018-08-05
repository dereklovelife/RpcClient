package rpcclient.common.encode.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import rpcclient.common.encode.EncodeExecutor;
import rpcclient.common.model.RpcRequest;
import rpcclient.common.model.RpcResult;

/**
 * Created by Administrator on 2018/8/4.
 */

@Component
public class JsonEncodeExecutor implements EncodeExecutor{

    public byte[] buildRequest(RpcRequest request) {
        String requestStr = JSONObject.toJSONString(request);
        return requestStr.getBytes();
    }

    public byte[] buildResponse(RpcResult result) {
        String resultStr = JSONObject.toJSONString(result);
        return resultStr.getBytes();
    }

    public RpcRequest toRequest(byte[] bytes) {
        RpcRequest request = JSONObject.parseObject(new String(bytes), RpcRequest.class);
        return request;

    }

    public RpcResult toResult(byte[] bytes) {
        RpcResult result = JSONObject.parseObject(new String(bytes), RpcResult.class);
        return result;
    }
}
