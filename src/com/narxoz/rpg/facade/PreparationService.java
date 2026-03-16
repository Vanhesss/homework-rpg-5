package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        validate(hero, boss, action);
        return String.format(
                "Preparation: %s enters with %d HP against %s (%d HP, %d ATK) using %s for %d damage. Effects: %s",
                hero.getName(),
                hero.getHealth(),
                boss.getName(),
                boss.getHealth(),
                boss.getAttackPower(),
                action.getActionName(),
                action.getDamage(),
                action.getEffectSummary()
        );
    }

    private void validate(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null) {
            throw new IllegalArgumentException("Hero must not be null.");
        }
        if (boss == null) {
            throw new IllegalArgumentException("Boss must not be null.");
        }
        if (action == null) {
            throw new IllegalArgumentException("Attack action must not be null.");
        }
        if (hero.getName() == null || hero.getName().isBlank()) {
            throw new IllegalArgumentException("Hero name must not be blank.");
        }
        if (boss.getName() == null || boss.getName().isBlank()) {
            throw new IllegalArgumentException("Boss name must not be blank.");
        }
        if (hero.getHealth() <= 0) {
            throw new IllegalArgumentException("Hero health must be positive.");
        }
        if (boss.getHealth() <= 0) {
            throw new IllegalArgumentException("Boss health must be positive.");
        }
        if (boss.getAttackPower() <= 0) {
            throw new IllegalArgumentException("Boss attack power must be positive.");
        }
        if (action.getDamage() <= 0) {
            throw new IllegalArgumentException("Attack damage must be positive.");
        }
    }
}
