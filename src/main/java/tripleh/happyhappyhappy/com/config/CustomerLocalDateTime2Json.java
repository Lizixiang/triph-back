package tripleh.happyhappyhappy.com.config;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author: zixli
 * Date: 2020/8/25 10:39
 * FileName: CustomerLocalDateTime2Json
 * Description: LocalDateTime转换成json
 */
public class CustomerLocalDateTime2Json extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (localDateTime == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime));
        }
    }
}
