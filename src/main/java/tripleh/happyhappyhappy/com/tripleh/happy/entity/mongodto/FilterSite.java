package tripleh.happyhappyhappy.com.tripleh.happy.entity.mongodto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tripleh.happyhappyhappy.com.config.CustomerLocalDateTime2Json;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Author: zixli
 * Date: 2020/8/21 15:33
 * FileName: FilterSite
 * Description: 爬虫实体类
 */
@Data
@Document("filter_site")
public class FilterSite implements Serializable {

    @Id
    private String id;

    private String title;

    private String link;

    private String desc;

    private String content;

    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
