public abstract class Character {
    // Attributes
    protected String name;
    protected int health;
    protected int attackPower;

    // Constructor
    public Character(String name, int health, int attackPower) {
        this.name =     name;
        this.health = health;
        this.attackPower = attackPower;
    }

    // Abstract method - each subclass implements differently
    public abstract int attack();

    // Regular method - same for all characters
    public void damageTaken(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    // Check if character is still alive
    public boolean isAlive() {
        return health > 0;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    // Display character info
    public void displayInfo() {
        System.out.println(name + " - HP: " + health + " | Attack Power: " + attackPower);
    }
}