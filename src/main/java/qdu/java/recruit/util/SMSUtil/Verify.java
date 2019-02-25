package qdu.java.recruit.util.SMSUtil;


import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Verify {
    private static final String SERVER_URL = "https://api.netease.im/sms/verifycode.action";


    private static final String
            APP_KEY="de6bd7b7d5cdaa24b142880cd7ed031a";
    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET="86cae9945b52";

    private static final String NONCE="1234";

    public static int verifyCode(String MOBILE,String CODE){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(SERVER_URL);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);

        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

        // 设置请求的header
        httpPost.addHeader("AppKey", APP_KEY);
        httpPost.addHeader("Nonce", NONCE);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        //requestBody 参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("mobile",MOBILE));
        nvps.add(new BasicNameValuePair("code", CODE));



        // 执行请求
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            HttpResponse response = httpClient.execute(httpPost);
//            System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            JSONObject jsonObject = JSONObject.fromObject(EntityUtils.toString(response.getEntity()));
            if(jsonObject.getString("code").equals("413")||jsonObject.getString("code").equals("404")){
                System.out.println(jsonObject.toString());
                System.out.println("验证未通过");
                return 0;
            }else {
                System.out.println("验证已通过");
                return 1;
            }
        } catch (IOException e) {
            System.out.println("验证出错");
            e.printStackTrace();
        }
        return 0;
    }
}
