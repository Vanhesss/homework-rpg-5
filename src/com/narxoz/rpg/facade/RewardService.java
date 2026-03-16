package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            throw new IllegalArgumentException("Battle result must not be null.");
        }
        if (!"Hero".equals(battleResult.getWinner())) {
            return "No reward. The hero must recover before the next attempt.";
        }
        if (battleResult.getRounds() <= 3) {
            return "Legendary cache: dragon scale, 120 gold, and a phoenix potion.";
        }
        if (battleResult.getRounds() <= 5) {
            return "Champion's chest: 80 gold and an enchanted whetstone.";
        }
        return "Survivor's bundle: 40 gold and restorative herbs.";
    }
}
