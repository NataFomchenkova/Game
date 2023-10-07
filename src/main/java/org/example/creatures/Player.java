package org.example.creatures;

public class Player extends Creature {

    private int healAmount = 4;

    public Player(int attack, int defense, int health, int damageMin, int damageMax) {
        super(attack, defense, health, damageMin, damageMax);
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public void heal() {
        //Only alive players can be healed
        String info = "Heal. " + getSimpleInfo();
        if (!isAlive) {
            logger.info(info + " The player is dead.");
            return;
        }
        if (healAmount > 0) {
            healAmount--;
            int newHealth = (int) (health + healthMax * 0.3);
            logger.info(info + " The remaining number of healings: " + healAmount);
            setHealth(newHealth);
        } else {
            logger.info(info + " A player cannot heal himself more than 4 times. Healing is impossible.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "; heal: " + healAmount;
    }
}
