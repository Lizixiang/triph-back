package tripleh.happyhappyhappy.com.api.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: zixli
 * Date: 2020/8/17 16:05
 * FileName: SourceEnum
 * Description: 技术来源
 */
public enum SourceEnum {

    PERSON("个人", "0"),
    CSDN("CSDN(爬虫)", "1");

    private String message;

    private String value;

    SourceEnum(String message, String value) {
        this.message = message;
        this.value = value;
    }

    public static SourceEnum getSourceByMsg(String message) {
        SourceEnum[] values = values();
        for (SourceEnum value : values) {
            if (value.getMessage().equals(message)) {
                return value;
            }
        }
        return null;
    }

    public static SourceEnum getSourceByVal(String value) {
        SourceEnum[] sourceEnums = values();
        for (SourceEnum sourceEnum : sourceEnums) {
            if (sourceEnum.getValue().equals(value)) {
                return sourceEnum;
            }
        }
        return null;
    }

    public static List<Map<String, String>> getAll() {
        ArrayList<Map<String, String>> list = new ArrayList<>();
        SourceEnum[] sourceEnums = values();
        for (SourceEnum sourceEnum : sourceEnums) {
            HashMap<String, String> map = new HashMap<>();
            map.put("text", sourceEnum.getMessage());
            map.put("value", sourceEnum.getValue());
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
