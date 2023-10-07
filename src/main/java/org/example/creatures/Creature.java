package org.example.creatures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Creature {

    protected static final Logger logger = LogManager.getRootLogger();
    private static final int MAX_ATTACK = 30;
    private static final int MIN_ATTACK = 1;
    private static final int MAX_DEFENSE = 30;
    private static final int MIN_DEFENSE = 1;
    private static final int DEFAULT_HEALTH = 60;
    private static final int DEFAULT_DIFFERENCE_MIN_MAX_DAMAGE = 6;


    private static int countCreature;
    protected int id;
    protected final int attack; // от 1 до 30
    protected final int defense; // от 1 до 30
    protected int health; // от 0 до N
    protected final int healthMax;
    protected final int damageMin;
    protected final int damageMax;
    protected boolean isAlive;


    public Creature(int attack, int defense, int health, int damageMin, int damageMax) {
        this.id = ++countCreature;
        // Checking the attack value
        this.attack = validateValue(attack, MIN_ATTACK, MAX_ATTACK, "attack");
        // Checking the defense value
        this.defense = validateValue(defense,  MIN_DEFENSE, MAX_DEFENSE, "defense");
        // Checking the health value
        if(health >= 0){
            this.health = health;
        }else{
            logger.warn(getSimpleInfo() + " The health value cannot be negative. The default value (" + DEFAULT_HEALTH + ") is set.");
            this.health = DEFAULT_HEALTH;
        }
        this.healthMax = this.health;
        // Checking the damage value
        if(damageMin < 0){
            logger.warn(getSimpleInfo() + " The min damage cannot be negative. Set value 0.");
            damageMin = 0;
        }
        if ((damageMin > damageMax) || (damageMax == 0)) {
            damageMax = damageMin + DEFAULT_DIFFERENCE_MIN_MAX_DAMAGE;
            logger.warn(getSimpleInfo() + " The maximum damage value was set incorrectly. New value: " + damageMax);
        }
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        //set alive/dead status
        if (this.health > 0){
            isAlive = true;
        }
        logger.info(getSimpleInfo() + " Creation competed." + " Attack: " + attack + "; defense: " + defense +
                "; health: " + health +  "; damage: " + damageMin + "-" + damageMax + "; status: " + getStatus());
    }

    private int validateValue(int value, int min, int max, String parameter) {
         if ( value < min || value > max){
             logger.warn(getSimpleInfo() + " Out-of-range " + parameter + " value (" + min + "-" + max + "). Max value is set.");
         return max;
    }
        return value;
    }

    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public int getDamageMin() {
        return damageMin;
    }
    public int getDamageMax() {
        return damageMax;
    }
    public int getHealth() {
        return health;
    }
    public int getHealthMax() {
        return healthMax;
    }
    public boolean isAlive() {
        return isAlive;
    }

    public void attack(Creature target) {
        String info = "Attack. " + getSimpleInfo() + " attacks " + target.getSimpleInfo() + ". ";
        //If the target is dead
        if (!target.isAlive){
            logger.info(info + "The target is dead. No attack is possible.");
            return;
        }
        //If target is alive
        int attackModifier = attack - target.defense + 1;
        int N = Math.max(attackModifier, 1);
        for (int i = 0; i < N; i++) {
            int diceValue = (int) (Math.random() * 6) + 1;
            if (diceValue >= 5) {
                int damage = (int) (Math.random() * (damageMax - damageMin + 1) + damageMin);
                logger.info(info + "Damage: " + damage);
                target.setHealth(target.getHealth() - damage);
                return;
            }
        }
        logger.info(info + "The attacker couldn't hit.");
    }

    public void setHealth(int health) {
        //It is not possible to set health higher than maximum health
        health = Math.min(health, healthMax);
        if (health <= 0) {
            this.health = 0;
            isAlive = false;
            logger.info("SetHealth. " + getSimpleInfo() + " is dead.");
        } else {
            this.health = health;
            isAlive = true;
            logger.info("SetHealth. " + getSimpleInfo() +  " health: " + health + "/" + healthMax);
        }
    }


    public String getSimpleInfo(){
        return getClass().getSimpleName() + " (id = " + this.id + ")";
    }
    public String getStatus(){
        return (isAlive)? "alive": "dead";
    }

    @Override
    public String toString() {
        String info = getSimpleInfo()  + "\n " + "attack: " + attack + "; defense: " + defense + "; health: " + health +
                "; damage: " + damageMin + "-" + damageMax +"; status: " + getStatus();
        return info;
    }
}
