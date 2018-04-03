package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Thor on 2018/3/12.
 *
 * 程序中需要用到的快捷方法
 */

public class UtilsMethod {

    private static final String DATE_FORMAT = "MM月dd日";
    private static final Gson gson = new Gson();

    /**
     * 返回日期的字符串，可以指定是第几天后
     * @param nextAmount 天数增量
     * @return 对应日期的字符串
     */
    public static String theNextNDay(int nextAmount) {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, nextAmount);
        Date tomorrow = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String dateString = formatter.format(tomorrow);
        if(dateString.startsWith("0")) {
            dateString = dateString.substring(1, dateString.length());
        }
        return dateString;
    }

    //在theNextNDay的基础上加上“今天明天和后天”
    public static String theNextNDayWithText(int nextAmount) {
        String result = null;
        switch (nextAmount) {
            case 0:
                result = "今天" + theNextNDay(0);
                break;
            case 1:
                result = "明天" + theNextNDay(0);
                break;
            case 2:
                result = "后天" + theNextNDay(0);
                break;
            default:
                result = theNextNDay(nextAmount);
                break;
        }
        return result;
    }

    //创建get请求参数
    public static String makeGetParams(String url, Map<String,String> params){

        Iterator<String> keys = params.keySet().iterator();
        Iterator<String> values = params.values().iterator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");

        for (int i=0;i<params.size();i++ ) {
            String value=null;
            try {
                value= URLEncoder.encode(values.next(),"utf-8");
            }catch (Exception e){
                e.printStackTrace();
            }

            stringBuffer.append(keys.next()+"="+value);
            if (i!=params.size()-1) {
                stringBuffer.append("&");
            }
        }

        return url + stringBuffer.toString();
    }

    /**
     * GET方法，后台交互
     * @param url 地址，如果需要传参，这个地址需要调用上一个方法对地址进行修改
     * @param callback
     */
    public static void getDataAsync(String url, Callback callback) {
        OkHttpClient client = MyApplication.getOkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * POST方法与后台交互
     * @param url 地址
     * @param body Gson转化后的JSON，封装成RequestBody
     * @param callback
     */
    public static void postDataAsync(String url, RequestBody body, Callback callback) {
        OkHttpClient client = MyApplication.getOkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * DELETE方法与后台交互
     * @param url 地址
     * @param formBody Builder对象
     * @param callback
     */
    public static void deleteDataAsync(String url, FormBody.Builder formBody, Callback callback) {
        OkHttpClient client = MyApplication.getOkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody.build())
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * PUT方法与后台交互
     * @param url 地址
     * @param body Gson转化后的JSON，封装成RequestBody
     * @param callback
     */
    public static void putDataAsync(String url, RequestBody body, Callback callback) {
        OkHttpClient client = MyApplication.getOkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * 将服务器传来的response对象转化为Messenger对象
     * @param response
     * @return
     */
    public static Messenger getMessengerFromJson(Response response) throws IOException {
        return gson.fromJson(response.body().string(), new TypeToken<Messenger>() {}.getType());
    }
}
