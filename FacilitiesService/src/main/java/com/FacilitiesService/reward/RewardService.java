package com.FacilitiesService.reward;

import com.FacilitiesService.ecopoint.EcoPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private EcoPointService ecoPointService;

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public Optional<Reward> getRewardById(Long id) {
        return rewardRepository.findById(id);
    }

    public Reward createReward(Reward reward) {
        return rewardRepository.save(reward);
    }

    public Reward updateReward(Long id, Reward rewardDetails) {
        Reward reward = rewardRepository.findById(id).orElseThrow(() -> new RuntimeException("Reward not found"));
        reward.setName(rewardDetails.getName());
        reward.setDescription(rewardDetails.getDescription());
        reward.setPointsCost(rewardDetails.getPointsCost());
        reward.setStock(rewardDetails.getStock());
        return rewardRepository.save(reward);
    }

    public void deleteReward(Long id) {
        rewardRepository.deleteById(id);
    }

    public void redeemReward(Long rewardId, Long citizenId) {
        Reward reward = rewardRepository.findById(rewardId).orElseThrow(() -> new RuntimeException("Reward not found"));
        if (reward.getStock() <= 0) {
            throw new RuntimeException("Reward out of stock");
        }
        ecoPointService.deductPoints(citizenId, reward.getPointsCost());
        reward.setStock(reward.getStock() - 1);
        rewardRepository.save(reward);
    }
}
