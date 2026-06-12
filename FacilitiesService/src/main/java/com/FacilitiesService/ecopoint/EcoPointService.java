package com.FacilitiesService.ecopoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EcoPointService {

    @Autowired
    private EcoPointRepository ecoPointRepository;

    public List<EcoPoint> getAllEcoPoints() {
        return ecoPointRepository.findAll();
    }

    public Optional<EcoPoint> getEcoPointById(Long id) {
        return ecoPointRepository.findById(id);
    }

    public List<EcoPoint> getEcoPointsByCitizenId(Long citizenId) {
        return ecoPointRepository.findByCitizenId(citizenId);
    }

    public Integer getTotalPointsByCitizenId(Long citizenId) {
        Integer total = ecoPointRepository.getTotalPointsByCitizenId(citizenId);
        return total != null ? total : 0;
    }

    public EcoPoint createEcoPoint(EcoPoint ecoPoint) {
        return ecoPointRepository.save(ecoPoint);
    }

    public void deductPoints(Long citizenId, Integer points) {
        Integer currentTotal = getTotalPointsByCitizenId(citizenId);
        if (currentTotal < points) {
            throw new RuntimeException("Not enough points");
        }
    }
}
