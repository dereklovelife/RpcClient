package service.rpctest.service;


import service.rpctest.service.model.StringResultModel;

/**
 * Created by Administrator on 2018/8/4.
 */
public interface StringService {

    String reverseString(String str);

    StringResultModel buildModel(String s1, String s2);
}
