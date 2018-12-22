package com.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.web.bean.DO.AdvAdvert;
import com.web.bean.VO.AdvAdvertVO;

import java.util.List;

/**
 * json转换工具
 * @author chxy
 */
public class JsonUtil {

    /**
     * imageList转换成json
     * @param list
     * @return
     */
    public static String imageListToJson(List<AdvAdvertVO> list){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("title", "图片管理");
        jsonObject.put("id", "Images");
        jsonObject.put("start", 0);
        for (int i = 0;i<list.size(); i++){
            JSONObject dataJson = new JSONObject();
            dataJson.put("src", list.get(i).getPic());
            dataJson.put("thumb", list.get(i).getPic());
            dataJson.put("url",list.get(i).getUrl());
            dataJson.put("alt", list.get(i).getTitle());
            dataJson.put("businessId", list.get(i).getBusinessId());
            dataJson.put("id",list.get(i).getId());
            dataJson.put("pid",i);
            dataJson.put("clickToken", list.get(i).getClickToken());
            jsonArray.add(dataJson);
        }
        jsonObject.put("data", jsonArray);
        return jsonObject.toJSONString();
    }

    /**
     * 请求list转换成json
     * @param list
     * @return
     */
    public static String requestListToJson (List list){
        return requestListToJson(list,list.size());
    }

    /**
     * 请求list转换成json
     * @param list
     * @param count
     * @return
     */
    public static String requestListToJson (List list,int count){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","");
        jsonObject.put("count", count);
        jsonObject.put("code", 0);
        if (list.size() == 0) {
            jsonObject.put("data","");
        } else if (list.size()>0){
            jsonObject.put("data", JSONArray.parseArray(JSON.toJSONString(list)));
        }
        return jsonObject.toJSONString();
    }

}
