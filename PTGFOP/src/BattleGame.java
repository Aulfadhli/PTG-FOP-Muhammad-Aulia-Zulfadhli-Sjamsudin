import java.util.*;
public class BattleGame {
    public static Scanner  scanner = new Scanner(System.in);
    public static void main(String[] args) {

        //Character selection
        Warrior warrior = new Warrior("Warrior", 100, 20, 5);
        Wizard wizard = new Wizard("Wizard", 100, 20, 30);
        Archer archer = new Archer("Archer", 100, 20, 0.50);

        //Display character info
        System.out.println("=== GAME BATTLE SYSTEM ===");
        System.out.println("--- AVAILABLE CHARACTERS ---");
        System.out.println("1. "+ warrior.getName());
        warrior.displayInfo();
        System.out.println();
        System.out.println("2. " + wizard.getName());
        wizard.displayInfo();
        System.out.println();
        System.out.println("3. " + archer.getName());
        archer.displayInfo();
        System.out.println();

        //Character selection
        System.out.println("--- CHOOSE YOUR CHARACTERS ---");
        System.out.println();
        Character player1 = selectCharacter("Player1");
        Character player2 = selectCharacter("Player2");

        //Battle start
        System.out.println("=".repeat(50));
        System.out.println("------- BATTLE START -------");
        System.out.println("=".repeat(50));
        System.out.println();
        interactiveBattle(player1, player2);
        System.out.println("\n=== GAME OVER ===");
        scanner.close();
    }

    public static Character selectCharacter(String playerName) {
        System.out.print(playerName + " choose your character (1 - Warrior, 2 - Wizard, 3 - Archer): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Character selectedCharacter = null;

        switch (choice) {
            case 1:
                System.out.print("Enter warrior name: ");
                String wName = scanner.nextLine();
                selectedCharacter = new Warrior(wName, 100, 20, 5);
                break;
            case 2:
                System.out.print("Enter wizard name: ");
                String wiName = scanner.nextLine();
                selectedCharacter = new Wizard(wiName, 100, 20, 30);
                break;
            case 3:
                System.out.print("Enter archer name: ");
                String aName = scanner.nextLine();
                selectedCharacter = new Archer(aName, 100, 20, .20);
                break;
            default:
                System.out.println("Invalid choice! defaulted to warrior class");
                selectedCharacter = new Warrior("Gus the Brave", 150, 20, 5);
                break;
        }

        System.out.println(playerName + " selected: " + selectedCharacter.getName());
        System.out.println();
        return selectedCharacter;
    }


    public static void interactiveBattle(Character char1, Character char2) {
        int turn = 1;

        while(char1.isAlive() && char2.isAlive()) {
            System.out.println("\n" +"=".repeat(50));
            System.out.println("--- Turn"+turn+ " ---");
            System.out.println("=".repeat(50));

            //Display status
            System.out.println("\n[STATUS]");
            System.out.println(char1.getName()+ "- HP: "+char1.getHealth());
            System.out.println(char2.getName()+ "- HP: "+char2.getHealth());
            System.out.println();

            //Player 1's turn
            System.out.println("Player 1's turn");
            int damage1 = chooseMove(char1,char2);
            System.out.println("Damage dealt: "+damage1);
            char2.damageTaken(damage1);
            System.out.println();
            System.out.println(char2.getName() + "'s HP is now "+ char2.getHealth());
            System.out.println();

            //Check if player 2 is dead
            if (!char2.isAlive()) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("💀 " + char2.getName() + " has been defeated!");
                System.out.println("🏆 " + char1.getName() + " WINS!");
                System.out.println("=".repeat(50));
                break;
            }

            //Player 2's turn
            System.out.println("Player 2's turn");
            int damage2 = chooseMove(char2,char1);
            System.out.println("Damage dealt: "+damage2);
            char1.damageTaken(damage2);
            System.out.println();
            System.out.println(char1.getName() + "'s HP is now "+ char1.getHealth());
            System.out.println();
            if (!char1.isAlive()) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("💀 " + char1.getName() + " has been defeated!");
                System.out.println("🏆 " + char2.getName() + " WINS!");
                System.out.println("=".repeat(50));
                break;
            }
            turn++;
        }
    }

    public static int chooseMove(Character attacker, Character defender){
        int damage = 0;

        if(attacker instanceof Warrior) {
            Warrior warrior = (Warrior) attacker;
            System.out.println("Choose your move:");
            System.out.println("1. Basic attack (Damage: "+ attacker.getAttackPower()+ ")");
            System.out.println("2. Shield bash (Damage: "+ attacker.getAttackPower()+ " + " + ((Warrior) attacker).armor+" armor, resets armor)");
            System.out.print("Enter choice (1-2): ");
            int choice = scanner.nextInt();

            if(choice == 1) {
                damage = attacker.attack();
            } else if(choice == 2) {
                damage = ((Warrior) attacker).shieldBash();
            } else{
                System.out.println("Invalid choice! defaulted to using Basic attack");
                damage = attacker.attack();
            }
        } else if(attacker instanceof Wizard) {
            Wizard wizard = (Wizard) attacker;
            System.out.println("Choose your move:");
            System.out.println("1. Basic spell (Damage: "+attacker.getAttackPower()+", restores 15 mana)");
            System.out.println("2. Arcane Blast (Damage: " + (wizard.getAttackPower() * 3) + ", Cost: 30 mana, Regenerate 15 mana, Current mana: " + wizard.getMana() + ")");
            System.out.print("Enter choice (1-2): ");
            int choice = scanner.nextInt();

            if (choice == 1){
                damage = attacker.attack();
            } else if(choice == 2){
                damage = ((Wizard) attacker).arcaneBlast();
            }else {
                System.out.println("Invalid choice! defaulted to using Basic spell");
                damage = attacker.attack();
            }

        } else if(attacker instanceof Archer) {
            Archer archer = (Archer) attacker;
            System.out.println("Choose your move:");
            System.out.println("1. Basic shot (Damage: "+attacker.getAttackPower()+")");
            System.out.println("2. Arrow rain (Damage: "+attacker.getAttackPower()+" + "+((Archer) attacker).arrowsFired+" arrows, resets arrows fired to 0)");
            System.out.print("Enter choice (1-2): ");
            int choice = scanner.nextInt();

            if (choice == 1){
                damage = attacker.attack();
            } else if(choice == 2){
                damage = ((Archer) attacker).arrowRain();
            } else {
                System.out.println("Invalid choice! defaulted to using Basic shot");
                damage = attacker.attack();
            }
        }
        return damage;
    }
}
