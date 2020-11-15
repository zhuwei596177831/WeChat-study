package com.zhuweiwei.wechatstudy.interceptor;

import com.zhuweiwei.wechatstudy.util.VerifySignatureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.AccessController;

/**
 * @author 朱伟伟
 * @date 2020-11-07 16:48:19
 * @description 微信signature校验拦截器
 */
public class SignatureInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AccessController.class);

    /**
     * @param httpServletRequest:
     * @author: 朱伟伟
     * @date: 2020-11-07 15:42
     * @description: signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp 时间戳
     * nonce	 随机数
     * echostr	 随机字符串
     * 开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，
     * 请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：
     * 1）将token、timestamp、nonce三个参数进行字典序排序
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     **/

    /**
     * <xml>
     * <ToUserName><![CDATA[gh_7d9341dff5a4]]></ToUserName>
     * <FromUserName><![CDATA[oCsUh6M1NMuQfl2EnwPEHIvYUCjQ]]></FromUserName>
     * <CreateTime>1604744086</CreateTime>
     * <MsgType><![CDATA[text]]></MsgType>
     * <Content><![CDATA[哈哈哈]]></Content>
     * <MsgId>22974410562227740</MsgId>
     * </xml>
     **/
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object handler) throws Exception {
        logger.info("-------------验证微信服务号开始----------");
        //开发者微信号
        String signature = httpServletRequest.getParameter("signature");
        //时间戳
        String timestamp = httpServletRequest.getParameter("timestamp");
        //随机数
        String nonce = httpServletRequest.getParameter("nonce");
        //随机字符串
        String echostr = httpServletRequest.getParameter("echostr");
        logger.info("signature is：{},timestamp is：{},nonce is：{},echostr is：{}",
                signature, timestamp, nonce, echostr);
        if (VerifySignatureUtil.checkSignature(signature, timestamp, nonce)) {
            logger.info("-------------验证微信服务号结束-----------");
            ServletOutputStream outputStream = response.getOutputStream();
            if (StringUtils.hasLength(echostr)) {
                outputStream.write(echostr.getBytes());
                return false;
            }
            return true;
        }
        logger.warn("不是微信服务号发来的请求");
        return false;
    }

}
