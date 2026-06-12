package com.FacilitiesService.ecopoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eco-points")
public class EcoPointController {

    @Autowired
    private EcoPointService ecoPointService;

    @GetMapping
    public List<EcoPoint> getAllEcoPoints() {
        return ecoPointService.getAllEcoPoints();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EcoPoint> getEcoPointById(@PathVariable Long id) {
        return ecoPointService.getEcoPointById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/citizen/{citizenId}")
    public List<EcoPoint> getEcoPointsByCitizenId(@PathVariable Long citizenId) {
        return ecoPointService.getEcoPointsByCitizenId(citizenId);
    }

    @GetMapping("/citizen/{citizenId}/total")
    public ResponseEntity<Integer> getTotalPointsByCitizenId(@PathVariable Long citizenId) {
        return ResponseEntity.ok(ecoPointService.getTotalPointsByCitizenId(citizenId));
    }

    @PostMapping
    public EcoPoint createEcoPoint(@RequestBody EcoPoint ecoPoint) {
        return ecoPointService.createEcoPoint(ecoPoint);
    }
}
