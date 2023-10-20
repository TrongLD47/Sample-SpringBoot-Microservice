package com.dailycodebuffer.common.util;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Map;

public class HttpUrlConnectionUtils {

//    public static void main (String []args) throws Exception {
//        String url = "https://robotapitest.mrspeedy.vn/api/business/1.1/create-order";
//        Map<String, String> mapHeader = new HashMap<>();
//        mapHeader.put("X-DV-Auth-Token", "FC6DCDCB030EDE4B6BE6A1FF363A55CAB3C05CEB");
//        String body = "{\"matter\":\"MrSpeedy_1604459676531\",\"vehicle_type_id\":8,\"total_weight_kg\":133,\"insurance_amount\":\"0.00\",\"is_client_notification_enabled\":false,\"is_contact_person_notification_enabled\":false,\"is_route_optimizer_enabled\":false,\"loaders_count\":0,\"is_motobox_required\":false,\"payment_method\":\"cash\",\"points\":[{\"address\":\"449 Trường Chinh, Phường 14, Tan Binh, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972372233\",\"name\":\"Văn\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"899 Cách Mạng Tháng Tám, Phường 4, Tan Binh, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972374444\",\"name\":\"Nguyen w\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"67 Lê Trung Nghĩa, Phường 12, Tan Binh, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972371010\",\"name\":\"notify\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"89a Đường số 12, khu phố 4, Tam Bình, Thủ Đức, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972372222\",\"name\":\"TAEMst\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"12 ton dan\",\"contact_person\":{\"phone\":\"84978927852\",\"name\":\"sww\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"6 Tân Sơn, Phường 12, Gò Vấp, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972372222\",\"name\":\"TAEMst\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"67 Lê Trung Nghĩa, Phường 12, Tan Binh, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972374444\",\"name\":\"Nguyen w\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"1222 Đường Quang Trung, phường 8, Gò Vấp, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972374444\",\"name\":\"Nguyen w\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"406 Cộng Hòa, Phường 13, Tan Binh, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972371010\",\"name\":\"notify\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"05 đường tân thới nhất, Tan Thoi Nhat, District 12, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972374444\",\"name\":\"Nguyen w\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"6 Tân Sơn, Phường 12, Gò Vấp, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972372222\",\"name\":\"TAEMst\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"776 Phạm Văn Bạch, Phường 12, Gò Vấp, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972374444\",\"name\":\"Nguyen w\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"553 Tân Sơn, Phường 12, Gò Vấp, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972374444\",\"name\":\"Nguyen w\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"975 Nguyễn Kiệm, Phường 3, Phú Nhuận, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972372222\",\"name\":\"TAEMst\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"65 Đường Lê Lợi, Bến Nghé, District 1, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972374444\",\"name\":\"Nguyen w\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"1116 Đường Quang Trung, phường 8, Gò Vấp, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972374444\",\"name\":\"Nguyen w\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"89a Đường số 12, khu phố 4, Tam Bình, Thủ Đức, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972372222\",\"name\":\"TAEMst\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"677 Điện Biên Phủ, Phường 17, Bình Thạnh, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972372222\",\"name\":\"TAEMst\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false},{\"address\":\"134 Lê Minh Xuân, Phường 7, Tan Binh, Ho Chi Minh City, Vietnam\",\"contact_person\":{\"phone\":\"84972371010\",\"name\":\"notify\"},\"taking_amount\":\"0.00\",\"buyout_amount\":\"0.00\",\"is_order_payment_here\":false,\"is_cod_cash_voucher_required\":false}]}";
//        sendPost(url, mapHeader, body);
//    }

    public static String sendPost(String url, Map<String, String> headers, String body) throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, null, new SecureRandom());

        HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
        con.setSSLSocketFactory(sslContext.getSocketFactory());

        //request post is default
        con.setRequestMethod("POST");
        //Set the Request Content-Type Header Parameter
        for (String key : headers.keySet()) {
            con.setRequestProperty(key, headers.get(key));
        }
        //Set Response Format Type
        con.setRequestProperty("Accept", "application/json; utf-8");

        //Ensure the Connection Will Be Used to Send Content
        con.setDoOutput(true);

        //Create the Request Body
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        //Read the Response from Input Stream
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
        return response.toString();
    }
}
