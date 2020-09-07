package cn.stylefeng.guns.config.weixin;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class OpenIdUtil {
    public static JSONObject oauth2GetOpenid(String code,String appId,String appSecret) {
       /* String appid = "wxb8ab40921cba9ba7";
        String appsecret = "c7e313bb4c5b17e9210909abe259bb1d";*/
        //授权（必填）
        String grant_type = "authorization_code";
        //URL
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        //请求参数
        String params = "appid=" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String data = HttpSendUtil.sendGet(requestUrl, params);
        //解析相应内容（转换成json对象）
        JSONObject  json = JSONObject.fromObject(data);
        //用户的唯一标识（openid）

        return json;
    }

    /**
     * 对url发送请求并获取返回的数据
     *
     * @param url
     * @return
     */
    public static String urlToJson(String url) {
        StringBuffer json1 = new StringBuffer();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "utf-8"));
            String inputLine = null;
            // StringBuffer json1 = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                json1.append(inputLine);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return json1.toString();
    }
    public static JSONObject oauth2GetUnionid(String openid,String appId,String appSecret) {
        String accessToken = "https://api.weixin.qq.com/cgi-bin/token";

        String grant_type1 = "client_credential";
        String params1 = "appid=" + appId + "&secret=" + appSecret + "&grant_type=" + grant_type1;
        //解析相应内容（转换成json对象）
        String data1 = HttpSendUtil.sendGet(accessToken, params1);
        //用户的唯一标识（openid）
        JSONObject  json1 = JSONObject.fromObject(data1);
        String access_tokens = String.valueOf(json1.get("access_token"));
        String  url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + access_tokens + "&openid=" + openid
                + "&lang=zh_CN";
        //获取返回的用户信息
        String json2 = urlToJson(url);
        //打印用户信息
        System.out.println("公众号用户信息:" + json2);
        JSONObject  json = JSONObject.fromObject(json2);
        return json;
    }
    public static  String getRedirectUrl(String appId,String redirectUrl) {
       /*
       String appId, String scope, String responseType, String redirectUri, String state
        String openWeiUrl="https://open.weixin.qq.com/connect/oauth2/authorize";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("appid=").append(appId).append("&");
        stringBuilder.append("redirect_uri=").append(redirectUri).append("&");
        stringBuilder.append("response_type=").append(responseType).append("&");
        stringBuilder.append("scope=").append(scope).append("&");
        stringBuilder.append("state=").append(state);
        return openWeiUrl + "?" + stringBuilder.toString() + "#wechat_redirect";*/
        String redirect_uri= URLEncoder.encode(redirectUrl);
        // String redirect_uri= URLEncoder.encode("http://192.168.1.133:8083/agent/showLogin");
        //String redirect_uri= "http://huolong.lsprite.com/agent/showLogin";
        String s="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
        return s;
    }
}
