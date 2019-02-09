package cn.inkroom.log.web.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 墨盒
 * @date 19-2-9
 */
public class MessageDto<T> {

    private int code;
    private String message;
    private T data;

    public MessageDto() {
    }

    public MessageDto(int code) {
        this.code = code;
    }

    public MessageDto(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
