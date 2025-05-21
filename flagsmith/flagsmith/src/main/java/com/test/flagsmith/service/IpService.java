package com.test.flagsmith.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.flagsmith.client.IpApi;
import com.test.flagsmith.dto.IpDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IpService {

    private final IpApi ipApi;

    public IpService(IpApi ipApi) {
        this.ipApi = ipApi;
    }

    public List<String> getLocation(String ip) {
        IpDto ipDto = ipApi.getIpInfo(ip);

        if (ipDto != null && ipDto.getRegionName() != null) {

            if (ipDto.getRegionName().equalsIgnoreCase("Tamil Nadu")) {
                return List.of("Chennai", "Coimbatore", "Saidapet", "Anna Nagar");
            } else if (ipDto.getRegionName().equalsIgnoreCase("Karnataka")) {
                return List.of("Bengaluru", "Mysore", "Mangalore");
            } else {
                return List.of(ipDto.getCity());
            }

        } else {
            log.warn("No IP information found for IP: {}", ip);
            return List.of();
        }

    }

}
