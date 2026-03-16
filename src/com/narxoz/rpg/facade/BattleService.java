package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private static final int MAX_ROUNDS = 8;
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        result.addLine("Battle begins. The hero attacks first each round.");

        for (int round = 1; round <= MAX_ROUNDS; round++) {
            int heroDamage = action.getDamage() + random.nextInt(3);
            boss.takeDamage(heroDamage);
            result.addLine(String.format(
                    "Round %d: %s hits %s with %s for %d damage. Boss HP: %d",
                    round,
                    hero.getName(),
                    boss.getName(),
                    action.getActionName(),
                    heroDamage,
                    boss.getHealth()
            ));

            if (!boss.isAlive()) {
                result.setWinner("Hero");
                result.setRounds(round);
                result.addLine(String.format("%s is defeated before it can counterattack.", boss.getName()));
                return result;
            }

            int bossDamage = boss.getAttackPower() + random.nextInt(4);
            hero.takeDamage(bossDamage);
            result.addLine(String.format(
                    "%s retaliates for %d damage. Hero HP: %d",
                    boss.getName(),
                    bossDamage,
                    hero.getHealth()
            ));

            if (!hero.isAlive()) {
                result.setWinner("Boss");
                result.setRounds(round);
                result.addLine(String.format("%s falls in battle.", hero.getName()));
                return result;
            }
        }

        result.setRounds(MAX_ROUNDS);
        result.setWinner(hero.getHealth() >= boss.getHealth() ? "Hero" : "Boss");
        result.addLine(String.format(
                "Round limit reached. Final HP totals - %s: %d, %s: %d",
                hero.getName(),
                hero.getHealth(),
                boss.getName(),
                boss.getHealth()
        ));

        return result;
    }
}
