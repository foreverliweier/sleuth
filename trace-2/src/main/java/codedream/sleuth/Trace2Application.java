package codedream.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;

/**
 * @author alice
 * @time 2023-06-09 11:04
 **/
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class Trace2Application {
    private final Logger logger = LoggerFactory.getLogger(Trace2Application.class);

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/trace-2", method = RequestMethod.GET)
    public String trace(HttpServletRequest request) {
        logger.info("===<call trace-2, TraceId={}, SpanId={}>===",
                //X-B3-TraceId：一条请求链路（Trace）的唯一标识，必须值
                //X-B3-SpanId：一个工作单元（Span）的唯一标识，必须值
               // X-B3-ParentSpanId:：标识当前工作单元所属的上一个工作单元，Root Span（请求链路的第一个工作单元）的该值为空
                //X-B3-Sampled：是否被抽样输出的标志，1表示需要被输出，0表示不需要被输出
                //X-Span-Name：工作单元的名称
                request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"));
        return "Trace";
    }

    public static void main(String[] args) {
        SpringApplication.run(Trace2Application.class, args);
    }
}
