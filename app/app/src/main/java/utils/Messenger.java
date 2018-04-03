package utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 前后台交互通信类
 * Created by Thor on 2018/4/3.
 */

public class Messenger implements Serializable{
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private Map<String, Object> extend = new HashMap<String, Object>();

    //定义快捷成功方法
    public static Messenger success() {
        Messenger result = new Messenger();
        result.setCode(100);
        result.setMsg("处理成功");
        return result;
    }

    //定义快捷失败方法
    public static Messenger fail() {
        Messenger result = new Messenger();
        result.setCode(200);
        result.setMsg("处理失败");
        return result;
    }

    //使用自身map添加消息
    public Messenger add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    @Override
    public String toString() {
        return "Messenger [code=" + code + ", msg=" + msg + ", extend=" + extend + "]";
    }
}
