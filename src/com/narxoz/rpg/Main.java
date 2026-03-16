package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

    AttackAction basic = new BasicAttack("Falcon Slash", 10);
    AttackAction fireSlash = new FireRuneDecorator(basic);
    AttackAction assassinStrike = new PoisonCoatingDecorator(
        new CriticalFocusDecorator(basic)
    );
    AttackAction dragonBreaker = new FireRuneDecorator(
        new PoisonCoatingDecorator(
            new CriticalFocusDecorator(basic)
        )
    );
    AttackAction empoweredThenFocused = new CriticalFocusDecorator(
        new PoisonCoatingDecorator(
            new FireRuneDecorator(basic)
        )
    );

        System.out.println("--- Decorator Preview ---");
    printAttackPreview(basic);
    printAttackPreview(fireSlash);
    printAttackPreview(assassinStrike);
    printAttackPreview(dragonBreaker);

    System.out.println("Decorator order matters:");
    System.out.println("- Fire after focus: " + dragonBreaker.getActionName() + " -> " + dragonBreaker.getDamage() + " damage");
    System.out.println("- Focus after elemental buffs: "
        + empoweredThenFocused.getActionName()
        + " -> "
        + empoweredThenFocused.getDamage()
        + " damage");

        System.out.println("\n--- Facade Preview ---");
    HeroProfile hero = new HeroProfile("Aelric the Ranger", 110);
    BossEnemy boss = new BossEnemy("Grimfang Warden", 125, 14);
        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
    AdventureResult result = facade.runAdventure(hero, boss, dragonBreaker);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());
        System.out.println("Battle log:");
        for (String line : result.getLog()) {
            System.out.println("- " + line);
        }

        System.out.println("\n=== Demo Complete ===");
    }

    private static void printAttackPreview(AttackAction action) {
    System.out.println("Action: " + action.getActionName());
    System.out.println("Damage: " + action.getDamage());
    System.out.println("Effects: " + action.getEffectSummary());
    System.out.println();
    }
}
