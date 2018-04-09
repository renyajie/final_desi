package utils;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import bean.User;
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
    private static final String DATE_FORMAT_FOR_SERVER = "yyyy-MM-dd";

    /**
     * 获取yyyy-MM-dd HH:mm:ss格式的时间
     * @param date
     * @return
     */
    public static String getStringFromDateForCheck(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * 将时间转化为 yyyy-MM-dd HH:mm-HH:mm的形式，用于查看约课详细情况
     * @param date
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getStringFromDateForDetail(
            Date date, Date startTime, Date endTime) {
        DateFormat dateFormatOne = new SimpleDateFormat("HH:mm");
        DateFormat dateFormatTwo = new SimpleDateFormat("yyyy-MM-dd");
        String day = dateFormatTwo.format(date);
        String start =  dateFormatOne.format(startTime);
        String end =  dateFormatOne.format(endTime);
        return day + ' ' + start + '-' + end;
    }

    /**
     * 将DATE转化为小时分的字符串
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getStringFromDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }

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

    /**
     * 发送给服务器的字符串格式
     * @param nextAmount
     * @return
     */
    public static String theNextNDayForServer(int nextAmount) {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, nextAmount);
        Date tomorrow = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_FOR_SERVER);
        String dateString = formatter.format(tomorrow);
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
                result = "明天" + theNextNDay(1);
                break;
            case 2:
                result = "后天" + theNextNDay(2);
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
     * 将json转化为指定类型的Messenger
     * @param response
     * @param token
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> Messenger<T> getFromJson(int gsonType, Response response, TypeToken<Messenger<T>> token) throws IOException {
        Type type = token.getType();
        return MyApplication.getGson(gsonType).fromJson(response.body().string(), type);
    }

    /**
     * 保存登录用户的信息
     * @param user
     */
    public static void saveUserInfo(User user) {
        SharedPreferences sharedPreferences =
                MyApplication.getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", user.getId());
        editor.putString("phone", user.getPhone());
        editor.putString("passwd", user.getPasswd());
        editor.putString("uName", user.getuName());
        editor.putString("gender", user.getGender());
        editor.putBoolean("login", true);
        editor.commit();
    }

    /**
     * 设置用户登录状态为未登录
     */
    public static void setUserLogOut() {
        SharedPreferences sharedPreferences =
                MyApplication.getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login", false);
        editor.commit();
    }

    /**
     * 获取用户当前的登录状态
     * @return 若用户已登录，返回true，否则返回false
     */
    public static boolean getLogInStatus() {
        SharedPreferences sharedPreferences =
                MyApplication.getSharedPreferences();
        return sharedPreferences.getBoolean("login", false);
    }

    /**
     * 获取用户编号
     * @return
     */

    public static int getUserId() {
        SharedPreferences sharedPreferences =
                MyApplication.getSharedPreferences();
        return sharedPreferences.getInt("userId", 1);
    }

    /**
     * 获取用户的手机号
     * @return
     */
    public static String getUsername() {
        SharedPreferences sharedPreferences =
                MyApplication.getSharedPreferences();
        return sharedPreferences.getString("uName", "任亚捷");
    }

    /**
     * 获取用户的手机号
     * @return
     */
    public static String getUserPhone() {
        SharedPreferences sharedPreferences =
                MyApplication.getSharedPreferences();
        return sharedPreferences.getString("phone", "17826856214");
    }
}
