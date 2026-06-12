package com.FacilitiesService.analytics;

import com.FacilitiesService.ecopoint.EcoPoint;
import com.FacilitiesService.ecopoint.EcoPointRepository;
import com.FacilitiesService.wastelog.WasteLog;
import com.FacilitiesService.wastelog.WasteLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    @Autowired
    private WasteLogRepository wasteLogRepository;

    @Autowired
    private EcoPointRepository ecoPointRepository;

    public Map<String, Object> getOverallAnalytics() {
        Map<String, Object> analytics = new HashMap<>();

        List<WasteLog> allWasteLogs = wasteLogRepository.findAll();
        double totalWaste = allWasteLogs.stream().mapToDouble(WasteLog::getWeight).sum();

        List<EcoPoint> allEcoPoints = ecoPointRepository.findAll();
        int totalPointsAwarded = allEcoPoints.stream().mapToInt(EcoPoint::getPoints).sum();

        analytics.put("totalWasteCollectedKg", totalWaste);
        analytics.put("totalEcoPointsAwarded", totalPointsAwarded);
        analytics.put("totalPickupsProcessed", allWasteLogs.size());

        return analytics;
    }

    public Map<String, Object> getCitizenAnalytics(Long citizenId) {
        Map<String, Object> analytics = new HashMap<>();

        List<EcoPoint> citizenPoints = ecoPointRepository.findByCitizenId(citizenId);
        int totalPoints = citizenPoints.stream().mapToInt(EcoPoint::getPoints).sum();

        analytics.put("citizenId", citizenId);
        analytics.put("totalEcoPoints", totalPoints);
        analytics.put("totalPickups", citizenPoints.size());

        return analytics;
    }
}
