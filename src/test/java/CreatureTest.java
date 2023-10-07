import junit.framework.TestCase;
import org.example.creatures.Creature;
import org.example.creatures.Monster;
import org.example.creatures.Player;

public class CreatureTest extends TestCase {
    @Override
    protected void setUp() throws Exception {


    }

    public void testCreatureConstructorNegativeAttack() {
        Player player = new Player(-3, 11, 6, 12, 16);
        assertEquals(30, player.getAttack());
    }

    public void testCreatureConstructorExtraAttack() {
        Player player = new Player(36, 11, 6, 12, 16);
        assertEquals(30, player.getAttack());
    }

    public void testCreatureConstructorNegativeDefense() {
        Player player = new Player(3, -11, 6, 12, 16);
        assertEquals(30, player.getDefense());
    }

    public void testCreatureConstructorExtraDefense() {
        Player player = new Player(3, 110, 6, 12, 16);
        assertEquals(30, player.getDefense());
    }

    public void testCreatureConstructorNegativeHealth() {
        Player player = new Player(3, 11, -6, 12, 16);
        assertEquals(60, player.getHealth());
    }

    public void testCreatureConstructorNegativeDamageMin() {
        Player player = new Player(3, 11, 34, -12, 16);
        assertEquals(0, player.getDamageMin());
    }

    public void testCreatureConstructorNegativeDamageMax() {
        Player player = new Player(3, 11, 34, 12, -16);
        assertEquals(18, player.getDamageMax());
    }

    public void testCreatureConstructorDamageMinExceedsDamageMax() {
        Player player = new Player(3, 11, 34, 102, 16);
        assertEquals(102, player.getDamageMin());
        assertEquals(108, player.getDamageMax());
    }

    public void testCreatureConstructorDamageMinEqualDamageMax() {
        Player player = new Player(3, 11, 34, 16, 16);
        assertEquals(16, player.getDamageMin());
        assertEquals(16, player.getDamageMax());
    }

    public void testPlayerConstructorCheckingAllFieldsIncorrectValue() {
        Player player1 = new Player(-3, 31, -516, 120, 16);

        assertEquals(30, player1.getAttack());
        assertEquals(30, player1.getDefense());
        assertEquals(60, player1.getHealth());
        assertEquals(120, player1.getDamageMin());
        assertEquals(126, player1.getDamageMax());
    }

    public void testMonsterConstructorCheckingAllFieldsIncorrectValue() {
        Monster monster = new Monster(100, -100, -256, -15, 16);

        assertEquals(30, monster.getAttack());
        assertEquals(30, monster.getDefense());
        assertEquals(60, monster.getHealth());
        assertEquals(0, monster.getDamageMin());
        assertEquals(16, monster.getDamageMax());
    }

    public void testCreatureConstructor() {
        Player player1 = new Player(8, 11, 216, 23, 30);

        assertEquals(8, player1.getAttack());
        assertEquals(11, player1.getDefense());
        assertEquals(216, player1.getHealth());
        assertEquals(23, player1.getDamageMin());
        assertEquals(30, player1.getDamageMax());
    }

    public void testCreatureSetHealth() {
        Monster monster = new Monster(10, 3, 100, 10, 16);
        monster.setHealth(20);
        assertEquals(20, monster.getHealth());
        assertTrue(monster.isAlive());
    }

    public void testCreatureSetNegativeHealth() {
        Monster monster = new Monster(10, 3, 100, 10, 16);
        monster.setHealth(-20);
        assertEquals(0, monster.getHealth());
        assertFalse(monster.isAlive());
    }

    public void testCreatureSetHealthDead() {
        Monster monster = new Monster(10, 3, 10, 10, 16);
        monster.setHealth(0);
        assertFalse(monster.isAlive());
        monster.setHealth(5);
        assertEquals(5, monster.getHealth());
        assertTrue(monster.isAlive());
    }

    public void testCreatureSetHealthExtra() {
        Monster monster = new Monster(10, 3, 10, 10, 16);
        monster.setHealth(50);
        assertEquals(10, monster.getHealth());
    }

    public void testPlayerHealCorrectCounting() {
        Player player = new Player(15, 4, 100, 10, 16);
        player.setHealth(10);
        player.heal();
        assertEquals(40, player.getHealth());
    }

    public void testPlayerHealChangingNumberHealings() {
        Player player = new Player(15, 4, 100, 10, 16);
        player.heal();
        player.heal();
        player.heal();
        assertEquals(1, player.getHealAmount());
        player.heal();
        player.heal();
        assertEquals(0, player.getHealAmount());
    }

    public void testPlayerExtraHeal() {
        Player player = new Player(15, 4, 100, 10, 16);
        player.heal();
        assertEquals(100, player.getHealth());
    }

    public void testPlayerHealHealingDead() {
        Player player = new Player(15, 4, 100, 10, 16);
        player.setHealth(0);
        player.heal();
        assertEquals(0, player.getHealth());
        assertFalse(player.isAlive());
    }

    public void testCreatureAttack() {
        Player player = new Player(20, 4, 16, 10, 16);
        Monster monster = new Monster(10, 16, 100, 10, 16);
        player.attack(monster);
        if (monster.getHealth() != monster.getHealthMax()) {
            assertTrue((100 - 16) <= monster.getHealth() && monster.getHealth() <= (100 - 10));
        }
    }

    public void testCreatureAttackDeadly() {
        Player player = new Player(30, 4, 16, 10, 16);
        Monster monster = new Monster(10, 1, 1, 10, 16);
        assertTrue(monster.isAlive());
        do {
            player.attack(monster);
        } while (monster.getHealth() == monster.getHealthMax());
        assertEquals(0, monster.getHealth());
        assertFalse(monster.isAlive());
    }

    public void testCreatureAttackDamageCheck() {
        Player player = new Player(10, 1, 40, 10, 10);
        Monster monster = new Monster(100, 1, 100, 10, 16);
        do {
            monster.attack(player);
        } while (player.getHealth() == player.getHealthMax());
        assertTrue((40 - 16) <= player.getHealth() && player.getHealth() <= (40 - 10));
    }


    /*public void testCreatureAttackDamageCheck() {
        Player player = new Player(100, 4, 16, 10, 10);
        Monster monster = new Monster(10, 1, 100, 10, 16);
        for (int i = 0; i < 9; i++) {
            player.attack(monster);
        }
        assertEquals(10, monster.getHealth());
    }*/
}
