package tripleh.happyhappyhappy.com.constant;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;
import org.springframework.stereotype.Component;

/**
 * @author lizixiang
 * @since 2021/5/10
 */
@NacosPropertySources({
        @NacosPropertySource(groupId = "gateway", dataId = "gateway", autoRefreshed = true)
})
@Component
public class NacosConstant {

    @NacosValue(value = "${message}", autoRefreshed = true)
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
