package com.bs.travel.util;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class RepResult implements Serializable {

    public static JSONObject repResult(int code,String msg,Object data){
        JSONObject res = new JSONObject();
        res.put("code",code);
        res.put("msg",msg);
        res.put("data",data);
        return res;
    }

    public static JSONObject repResult(int code,String msg,Object data,Long count){
        JSONObject res = new JSONObject();
        res.put("code",code);
        res.put("msg",msg);
        res.put("data",data);
        res.put("count",count);
        return res;
    }
}
