package com.dailycodebuffer.common.util;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.Normalizer;

public class URLEncodingUtils {

    public static String decodeURIComponent(String s) {
        if (s == null) {
            return null;
        } else if (StringUtils.isBlank(s)) {
            return "";
        } else {
            String result = null;

            try {
                result = URLDecoder.decode(s, "UTF-8");
            } catch (UnsupportedEncodingException var3) {
                result = s;
            }

            return result;
        }
    }

    public static String encodeURIComponent(String s) {
        String result = null;

        try {
            if (s != null) {
                if (!s.trim().equals("")) {
                    result = URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\%7E", "~");
                } else {
                    result = "";
                }
            }
        } catch (UnsupportedEncodingException var3) {
            result = s;
        }

        return result;
    }

    public static String decodeURIComponent4Hash(String s) {
        if (s == null) {
            return null;
        } else if (StringUtils.isBlank(s)) {
            return "";
        } else {
            String result = decodeURIComponent(s);
            result = Normalizer.normalize(result, Normalizer.Form.NFD);
            result = result.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            result = result.replaceAll("[^a-z A-Z0-9-_]", "");
            return result;
        }
    }

    public static String normalizeURI(String s) {
        if (s == null) {
            return null;
        } else if (StringUtils.isBlank(s)) {
            return "";
        } else {
            String result = Normalizer.normalize(s, Normalizer.Form.NFD);
            result = result.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            result = result.replaceAll("[^a-z A-Z0-9-_]", "");
            return result;
        }
    }
}
