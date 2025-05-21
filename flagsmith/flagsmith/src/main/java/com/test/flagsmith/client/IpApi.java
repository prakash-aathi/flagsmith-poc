package com.test.flagsmith.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.test.flagsmith.dto.IpDto;


@FeignClient(name = "ip-api", url = "http://ip-api.com")
public interface IpApi {

    @GetMapping("/json/{ip}")
    IpDto getIpInfo(@PathVariable("ip") String ip);

}
