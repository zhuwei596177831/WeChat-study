package com.zhuweiwei.wechatstudy.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author 朱伟伟
 * @date 2020-11-07 18:03:53
 * @description
 */
public class StreamUtil {
    public static String inputStream2String(InputStream inputStream, String charset) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        char[] data = new char[1024];
        StringBuilder stringBuilder = new StringBuilder(512);
        while (inputStreamReader.read(data) != -1) {
            stringBuilder.append(data);
        }
        return stringBuilder.toString();
    }
}
