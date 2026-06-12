package com.FacilitiesService.facility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    public Optional<Facility> getFacilityById(Long id) {
        return facilityRepository.findById(id);
    }

    public Facility createFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    public Facility updateFacility(Long id, Facility facilityDetails) {
        Facility facility = facilityRepository.findById(id).orElseThrow(() -> new RuntimeException("Facility not found"));
        facility.setName(facilityDetails.getName());
        facility.setLocation(facilityDetails.getLocation());
        facility.setType(facilityDetails.getType());
        facility.setMaxCapacity(facilityDetails.getMaxCapacity());
        facility.setCurrentLoad(facilityDetails.getCurrentLoad());
        return facilityRepository.save(facility);
    }

    public void deleteFacility(Long id) {
        facilityRepository.deleteById(id);
    }
}
