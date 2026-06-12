package com.FacilitiesService.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getOverallAnalytics() {
        return ResponseEntity.ok(analyticsService.getOverallAnalytics());
    }

    @GetMapping("/citizen/{citizenId}")
    public ResponseEntity<Map<String, Object>> getCitizenAnalytics(@PathVariable Long citizenId) {
        return ResponseEntity.ok(analyticsService.getCitizenAnalytics(citizenId));
    }
}
