public class Wizard extends Character {
    private int mana;
    private int maxMana;
    private int manaRegeneration;
    private static final int spellCost = 30;

    public Wizard(String name, int healthPoints, int attackPower, int maxMana){
        super(name, healthPoints, attackPower);
        this.mana = maxMana;
        this.maxMana = maxMana;
        this.manaRegeneration = 15;
    }

    @Override
    public int attack(){
        System.out.println(name + " cast a basic spell!");
        regenerateMana();
        return attackPower;
    }

    public int arcaneBlast(){
        if (mana >= spellCost){
            mana -= spellCost;
            System.out.println(name+ " unleashed arcane blast!");
            regenerateMana();
            return attackPower*3;
        } else {
            System.out.println("Insufficient mana! cast basic spell instead!");
            return attackPower;
        }
    }

    public void regenerateMana(){
        if(mana < maxMana){
            mana += manaRegeneration;
            if(mana > maxMana){
                mana = maxMana;
            }
            System.out.println(name + " regenerated mana! (Mana: " + mana + "/" + maxMana + ")");
        }
    }

    public int getMana() {
        return mana;
    }

    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Mana: " + mana + "/" + maxMana + " (Regen: +" + manaRegeneration + " per turn)");
    }


}