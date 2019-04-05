package com.fzy.pms.utils;

import com.alibaba.fastjson.JSON;
import com.fzy.pms.entity.rest.Result;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @program: ResponseUtil
 * @description:
 * @author: fzy
 * @date: 2019/03/23 18:32:39
 **/
@Slf4j
public class ResponseUtil {

    /**
     *  使用response输出JSON
     * @param response
     * @param result
     */
    public static void out(ServletResponse response,Result result){
        PrintWriter out = null;
        try {
            response.setContentType("application/json;charset=UTF-8");
            out = response.getWriter();
            out.println(JSON.toJSON(result));
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        }finally{
            if(out!=null){
                out.flush();
                out.close();
            }
        }
    }
}
