package tripleh.happyhappyhappy.com.tripleh.happy.entity.mongodto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Author: zixli
 * Date: 2020/9/10 17:02
 * FileName: HotWord
 * Description: 热词
 */
@Data
@Document("hotword")
public class HotWord implements Serializable {

    @Id
    private String id;

    private String keyword;

    private int count;

    private int year;

    private int month;

    private int day;

    private int status;

}
