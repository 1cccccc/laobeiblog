package com.xi.config.thread;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ThreadPoolExecutorProperties {
    @Value("${thread.corePoolSize}")
    private Integer corePoolSize;
    @Value("${thread.maximumPoolSize}")
    private Integer maximumPoolSize;
    @Value("${thread.keepAliveTime}")
    private Integer keepAliveTime;
    @Value("${thread.workQueueLength}")
    private Integer workQueueLength;
}
