package tripleh.happyhappyhappy.com.api.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: zixli
 * Date: 2020/8/17 16:40
 * FileName: StatusEnum
 * Description: 状态枚举
 */
public enum StatusEnum {

    NOT_FINISHED("未完成", "0"),
    FINISHING("进行中", "1"),
    FINISHED("已完成", "2");

    private String message;

    private String value;

    StatusEnum(String message, String value) {
        this.message = message;
        this.value = value;
    }

    public static StatusEnum getSourceByMsg(String message) {
        StatusEnum[] values = values();
        for (StatusEnum value : values) {
            if (value.getMessage().equals(message)) {
                return value;
            }
        }
        return null;
    }

    public static StatusEnum getSourceByVal(String value) {
        StatusEnum[] StatusEnums = values();
        for (StatusEnum StatusEnum : StatusEnums) {
            if (StatusEnum.getValue().equals(value)) {
                return StatusEnum;
            }
        }
        return null;
    }

    public static List<Map<String, String>> getAll() {
        ArrayList<Map<String, String>> list = new ArrayList<>();
        StatusEnum[] StatusEnums = values();
        for (StatusEnum StatusEnum : StatusEnums) {
            HashMap<String, String> map = new HashMap<>();
            map.put("text", StatusEnum.getMessage());
            map.put("value", StatusEnum.getValue());
            list.add(map);
        }
        return list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
