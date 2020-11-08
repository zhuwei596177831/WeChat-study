package com.zhuweiwei.wechatstudy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 朱伟伟
 * @date 2020-11-07 15:47:45
 * @description signature校验
 */
public class WeiXinSignatureUtils {
    private static final String TOKEN = "wechat-study";
    private static final Logger logger = LoggerFactory.getLogger(AccessController.class);

    /**
     * @param signature:
     * @param timestamp:
     * @param nonce:
     * @author: 朱伟伟
     * @date: 2020-11-07 16:06
     * @description: 校验微信signature
     **/
    public static Boolean checkSignature(String signature, String timestamp, String nonce) {
        List<String> list = new ArrayList<>();
        list.add(TOKEN);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s);
        }
        return signature.equals(hash(stringBuilder.toString(), "SHA1"));
    }

    /**
     * @param content:
     * @param algorithm:
     * @author: 朱伟伟
     * @date: 2020-11-07 16:16
     * @description: 根据算法进行加密
     **/
    private static String hash(String content, String algorithm) {
        if (content.isEmpty()) {
            return "";
        }
        MessageDigest hash = null;
        try {
            hash = MessageDigest.getInstance(algorithm);
            byte[] bytes = hash.digest(content.getBytes(StandardCharsets.UTF_8));
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("SHA1加密失败");
        }
        return "";
    }
}
