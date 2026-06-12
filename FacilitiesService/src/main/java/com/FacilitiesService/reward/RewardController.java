package com.FacilitiesService.reward;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping
    public List<Reward> getAllRewards() {
        return rewardService.getAllRewards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reward> getRewardById(@PathVariable Long id) {
        return rewardService.getRewardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reward createReward(@RequestBody Reward reward) {
        return rewardService.createReward(reward);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reward> updateReward(@PathVariable Long id, @RequestBody Reward rewardDetails) {
        return ResponseEntity.ok(rewardService.updateReward(id, rewardDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReward(@PathVariable Long id) {
        rewardService.deleteReward(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{rewardId}/redeem")
    public ResponseEntity<Void> redeemReward(@PathVariable Long rewardId, @RequestParam Long citizenId) {
        rewardService.redeemReward(rewardId, citizenId);
        return ResponseEntity.ok().build();
    }
}
