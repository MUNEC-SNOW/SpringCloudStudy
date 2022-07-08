package com.kade.cloud.controller;

import com.kade.cloud.config.LoadBalancer;
import com.kade.cloud.entities.CommonResult;
import com.kade.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author kade
 * @date 2022/07/04
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        URI uri = getUri("CLOUD-PAYMENT-SERVICE");
        return restTemplate.getForObject(uri+"/payment/zipkin", String.class);
    }

    @GetMapping("/consumer/payment/lb")
    public String getB(){
        URI uri = getUri("CLOUD-PAYMENT-SERVICE");
        return restTemplate.getForObject(uri+"/payment/lb", String.class);
    }

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        URI uri = getUri("CLOUD-PAYMENT-SERVICE");
        return restTemplate.postForObject(uri + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        URI uri = getUri("CLOUD-PAYMENT-SERVICE");
        return restTemplate.getForObject(uri + "/payment/get/"+ id, CommonResult.class);
    }

    private URI getUri(String id) {
        List<String> services = discoveryClient.getServices();
        List<ServiceInstance> instances = discoveryClient.getInstances(id);
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        return serviceInstance.getUri();
    }
}
