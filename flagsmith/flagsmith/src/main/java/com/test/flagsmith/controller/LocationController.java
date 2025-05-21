package com.test.flagsmith.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.flagsmith.service.FeatureFlagService;
import com.test.flagsmith.service.IpService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class LocationController {

    private final FeatureFlagService featureFlagService;
    private final IpService ipService;

    @GetMapping("/locations")
    public List<String> getLocations(@RequestHeader String ip) {

        log.info("Received request to get locations for IP: {}", ip);
        return ipService.getLocation(ip);
    }

    @GetMapping("/feature")
    public boolean getFeatures(@RequestParam String featureName) {
        return featureFlagService.isMainLocationFeatureEnabled(featureName);
    }

    // https://api.ipify.org/?format=json
    // http://ip-api.com/json/49.204.133.27
}
