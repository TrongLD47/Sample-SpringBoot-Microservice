package com.dailycodebuffer.common.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by son.nguyen on 7/26/2020.
 */
public class CommonUtils {
    public static void main(String[] args) {
//        System.out.println(toValidPhoneNumber("+84938789900"));
//        System.out.println(toValidPhoneNumber("+60103016121"));
//        PhoneDTO dto = parsePhoneNumber("+60103016121");
//        PhoneDTO dto = parsePhoneNumber("60103016121");
//        System.out.println(dto.getCountryCode());
//        System.out.println(dto.getNationalNumber());
//        System.out.println(dto.getFullNumber());
    }

    public static boolean isValidEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        return rfc2822.matcher(email.toLowerCase()).matches();
    }

    private static final Pattern rfc2822 = Pattern.compile(
            "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
    );

    public static Date convertTimeInMilliToDate(Long timeInMilli) {
        return convertUnixTimeToDate(timeInMilli != null ? timeInMilli / 1000 : null, false);
    }

    public static Date convertTimeInMilliToDate(Long timeInMilli, Boolean isEndDate) {
        return convertUnixTimeToDate(timeInMilli != null ? timeInMilli / 1000 : null, isEndDate);
    }

    public static Date convertUnixTimeToDate(Long unixTime, Boolean isEndDate) {
        if (unixTime != null) {
            Date date = new Date(unixTime * 1000);
            if (isEndDate) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.SECOND, 86399);
                return calendar.getTime();
            }
            return date;
        } else {
            if (isEndDate) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.SECOND, 86399);
                return calendar.getTime();
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, -12);
                return calendar.getTime();
            }
        }
    }

    public static String convertDateToString(Date date) {
        String dateTimeStr = null;
        if (date != null) {
            dateTimeStr = dMYFormat.format(date);
        }
        return dateTimeStr;
    }

    public static String autoCreateCustomerCode(String theLastCode) {
        String code = "";
        if (StringUtils.isNotBlank(theLastCode)) {
            DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
            df.setMaximumFractionDigits(340);
            Double numCode = Double.parseDouble(theLastCode.replaceAll("CUS", "")) + 1;
            String numStr = df.format(numCode);
            if (numStr.length() < 5) {
                for (int i = 0; i < (5 - numStr.length()); i++) {
                    code += "0";
                }
            }
            code = "CUS" + code + numStr;
        } else {
            code = "CUS00001";
        }
        return code;
    }

    public static Timestamp calculatorExpiredFromNowWithMonthInput(Integer month) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, month);
        int res = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, res);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static String genCode(int length) {
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(OTP_DIGIT_CHARSET.charAt(rand.nextInt(OTP_DIGIT_CHARSET.length())));
        }
        return sb.toString();
    }

    public static String getBase64EncodedImage(String imageURL) {
        try {
            java.net.URL url = new java.net.URL(imageURL);
            InputStream is = url.openStream();
            byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(is);
            return Base64.encodeBase64String(bytes);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static <T> List<T> intersect(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();
        for (T t : list1) {
            if (list2.contains(t)) {
                list.add(t);
            }
        }
        return list;
    }

    public static void ignoringExc(RunnableExc r) {
        try {
            r.run();
        } catch (Exception e) {
            System.out.println("Ignoring error " + e.getMessage());
        }
    }

    @FunctionalInterface
    public interface RunnableExc {
        void run() throws Exception;
    }

    public static Timestamp move2EndTimeOfDay(Timestamp input) {
        Timestamp res = null;
        if (input != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(input.getTime());
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            res = new Timestamp(cal.getTimeInMillis());
        }
        return res;
    }

    public static Timestamp move2BeginTimeOfDay(Timestamp input) {
        Timestamp res = null;
        if (input != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(input.getTime());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            res = new Timestamp(cal.getTimeInMillis());
        }
        return res;
    }

    public static HttpResponse<String> sendPostRequest(String host, String uri, Map<String, String> header, Map<String, Object> params) {
        try {
            Unirest.setTimeouts(5 * 60 * 1000, 5 * 60 * 1000);
            System.out.println(host + uri);
            System.out.println(params.toString());
            HttpResponse<String> response = Unirest.post(host + uri).headers(header).fields(params).asString();
            return response;
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static final String OTP_DIGIT_CHARSET = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static SimpleDateFormat dMYFormat = new SimpleDateFormat("dd/MM/yyyy");
}
