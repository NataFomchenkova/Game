package org.example.creatures;
public class Monster extends Creature {

    public Monster(int attack, int defense, int health, int damageMin, int damageMax) {
        super(attack, defense, health, damageMin, damageMax);
    }

    @Override
    public String toString() {
        String info = getClass().getSimpleName() + "\n " +
                "id: " + id + "\n " +
                "attack: " + attack + "\n " +
                "defense: " + defense + "\n " +
                "health: " + health + "/" + healthMax +"\n " +
                "status: " + getStatus();
        return info;
    }
}
