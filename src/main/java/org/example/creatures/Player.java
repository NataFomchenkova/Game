package org.example.creatures;

public class Player extends Creature {

    private int healAmount = 4;
    public Player(int attack, int defense, int health, int damageMin, int damageMax) {
        super(attack, defense, health, damageMin, damageMax);
    }
    public void heal (){
        if(!isAlive){
            System.out.println("The player (id = " + id + ") is dead.");
            return;
        }
        if (healAmount > 0){
            healAmount--;
            int newHealth = (int) (health + healthMax * 0.3);
            setHealth(Math.min(newHealth, healthMax));
        }else{

            logger.info("A player cannot heal himself more than 4 times. Healing is impossible.");
            System.out.println("A player cannot heal himself more than 4 times. Healing is impossible.");
        }
    }

    @Override
    public String toString() {
        String info = getClass().getSimpleName() + "\n " +
                "id: " + id + "\n " +
                "attack: " + attack + "\n " +
                "defense: " + defense + "\n " +
                "health: " + health + "/" + healthMax +"\n " +
                "Number of available healings: " + healAmount +"\n " +
                "status: " + getStatus();
        return info;
    }



}
