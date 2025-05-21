package com.test.flagsmith.service;

import com.flagsmith.FlagsmithClient;
import com.flagsmith.exceptions.FlagsmithClientError;
import com.flagsmith.models.Flags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FeatureFlagService {

    private final FlagsmithClient flagsmithClient;

    public FeatureFlagService(FlagsmithClient flagsmithClient) {
        this.flagsmithClient = flagsmithClient;
    }

    public boolean isMainLocationFeatureEnabled(String featureName) {
        try {
            Flags flags = flagsmithClient.getEnvironmentFlags();
            return flags.isFeatureEnabled(featureName);
        } catch (FlagsmithClientError e) {
            log.error("Failed to fetch feature flags from Flagsmith", e);
            return false;
        }
    }
    

}

