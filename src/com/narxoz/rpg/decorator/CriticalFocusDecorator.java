package com.narxoz.rpg.decorator;

public class CriticalFocusDecorator extends ActionDecorator {
    public CriticalFocusDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " + Critical Focus";
    }

    @Override
    public int getDamage() {
        return (int) Math.round(super.getDamage() * 1.5);
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + " | Focused strike (x1.5 total damage).";
    }
}
