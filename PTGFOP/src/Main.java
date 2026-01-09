import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== GAME CHARACTER BATTLE SYSTEM ===\n");

        // Create character objects
        Warrior warrior = new Warrior("Brutus the Brave", 200, 20, 5);
        Wizard wizard = new Wizard("Merlin the Wise", 120, 15, 60);
        Archer archer = new Archer("Legolas the Swift", 150, 18, 0.90);

        // Display all characters
        System.out.println("--- AVAILABLE CHARACTERS ---");
        System.out.println("1. Warrior - " + warrior.getName());
        warrior.displayInfo();
        System.out.println();
        System.out.println("2. Wizard - " + wizard.getName());
        wizard.displayInfo();
        System.out.println();
        System.out.println("3. Archer - " + archer.getName());
        archer.displayInfo();
        System.out.println("\n");

        // Let players choose their characters
        System.out.println("=== CHARACTER SELECTION ===");
        Character player1 = selectCharacter("Player 1");
        Character player2 = selectCharacter("Player 2");

        System.out.println("\n" + "=".repeat(50));
        System.out.println("BATTLE START!");
        System.out.println(player1.getName() + " VS " + player2.getName());
        System.out.println("=".repeat(50) + "\n");

        // Start the battle
        interactiveBattle(player1, player2);

        System.out.println("\n=== GAME OVER ===");
        scanner.close();
    }

    // Method to let players select their character
    public static Character selectCharacter(String playerName) {
        System.out.print(playerName + ", choose your character (1-Warrior, 2-Wizard, 3-Archer): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Character selectedChar = null;

        switch (choice) {
            case 1:
                System.out.print("Enter Warrior name: ");
                String wName = scanner.nextLine();
                selectedChar = new Warrior(wName, 200, 20, 5);
                break;
            case 2:
                System.out.print("Enter Wizard name: ");
                String wizName = scanner.nextLine();
                selectedChar = new Wizard(wizName, 120, 15, 60);
                break;
            case 3:
                System.out.print("Enter Archer name: ");
                String aName = scanner.nextLine();
                selectedChar = new Archer(aName, 150, 18, 0.25);
                break;
            default:
                System.out.println("Invalid choice! Defaulting to Warrior.");
                selectedChar = new Warrior("Default Warrior", 200, 20, 5);
        }

        System.out.println(playerName + " selected: " + selectedChar.getName());
        System.out.println();
        return selectedChar;
    }

    // Interactive battle where users choose moves
    public static void interactiveBattle(Character char1, Character char2) {
        int round = 1;

        while (char1.isAlive() && char2.isAlive()) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("--- Round " + round + " ---");
            System.out.println("=".repeat(50));

            // Display current status
            System.out.println("\n[STATUS]");
            System.out.println(char1.getName() + " - HP: " + char1.getHealth());
            System.out.println(char2.getName() + " - HP: " + char2.getHealth());
            System.out.println();

            // Player 1's turn
            System.out.println(">> " + char1.getName() + "'s Turn <<");
            int damage1 = chooseMove(char1, char2);

            System.out.println("→ Damage dealt: " + damage1);
            char2.damageTaken(damage1);
            System.out.println("→ " + char2.getName() + " HP: " + char2.getHealth());

            // Check if Character 2 is defeated
            if (!char2.isAlive()) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("💀 " + char2.getName() + " has been defeated!");
                System.out.println("🏆 " + char1.getName() + " WINS!");
                System.out.println("=".repeat(50));
                break;
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();

            // Player 2's turn
            System.out.println("\n>> " + char2.getName() + "'s Turn <<");
            int damage2 = chooseMove(char2, char1);

            System.out.println("→ Damage dealt: " + damage2);
            char1.damageTaken(damage2);
            System.out.println("→ " + char1.getName() + " HP: " + char1.getHealth());

            // Check if Character 1 is defeated
            if (!char1.isAlive()) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("💀 " + char1.getName() + " has been defeated!");
                System.out.println("🏆 " + char2.getName() + " WINS!");
                System.out.println("=".repeat(50));
                break;
            }

            round++;

            System.out.println("\nPress Enter to continue to next round...");
            scanner.nextLine();

            // Prevent infinite battles
            if (round > 50) {
                System.out.println("\n=== BATTLE TIMEOUT - IT'S A DRAW! ===");
                break;
            }
        }
    }

    // Method to display move options and get player choice
    public static int chooseMove(Character attacker, Character defender) {
        int damage = 0;

        // Display move options based on character type
        if (attacker instanceof Warrior) {
            Warrior warrior = (Warrior) attacker;
            System.out.println("\nChoose your move:");
            System.out.println("1. Normal Attack (Damage: ~" + warrior.getAttackPower() + ")");
            System.out.println("2. Shield Bash (Damage: " + warrior.getAttackPower() + " + " + warrior.getArmor() + " armor, resets armor)");
            System.out.print("Enter choice (1-2): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 2) {
                damage = warrior.shieldBash();
            } else {
                damage = warrior.attack();
            }

        } else if (attacker instanceof Wizard) {
            Wizard wizard = (Wizard) attacker;
            System.out.println("\nChoose your move:");
            System.out.println("1. Basic Attack (Damage: " + wizard.getAttackPower() + ")");
            System.out.println("2. Arcane Blast (Damage: " + (wizard.getAttackPower() * 3) + ", Cost: 30 mana, Current mana: " + wizard.getMana() + ")");
            System.out.print("Enter choice (1-2): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 2) {
                damage = wizard.arcaneBlast();
            } else {
                damage = wizard.attack();
            }

        } else if (attacker instanceof Archer) {
            Archer archer = (Archer) attacker;
            System.out.println("\nChoose your move:");
            System.out.println("1. Normal Attack (Damage: ~" + archer.getAttackPower() + ", can crit)");
            System.out.println("2. Arrow Rain (Damage: 5 arrows x " + (archer.getAttackPower() / 2) + " = " + (archer.getAttackPower() / 2 * 5) + " total)");
            System.out.print("Enter choice (1-2): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 2) {
                damage = archer.arrowRain();
            } else {
                damage = archer.attack();
            }

        } else {
            // Fallback for base Character type
            damage = attacker.attack();
        }

        return damage;
    }
}