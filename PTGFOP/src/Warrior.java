public class Warrior extends Character{
    //Attributes unique to warrior
    protected int armor;
    public int baseArmor;

    public Warrior(String name, int healthPoints, int attackPower, int baseArmor){
        super(name,healthPoints,attackPower);
        this.baseArmor=baseArmor;
        this.armor=baseArmor;
    }

    @Override
    public int attack(){
        return attackPower;
    }

    @Override
    public void damageTaken(int damage){
        int reducedDamage = damage - armor;
        if(reducedDamage <= 0){
            reducedDamage = 0;
        }
        super.damageTaken(reducedDamage);
        armor+= 5;
        System.out.println(name+" took damage and his armor has increased to "+armor);
    }

    public int shieldBash(){
        System.out.println(name+" uses shield bash with "+ armor+" armor points");
        int damage = attackPower + armor;
        armor = baseArmor;
        System.out.println(name+"'s armor has been reset to "+ armor);
        return damage;
    }

    public int getArmor(){
        return armor;
    }

    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Armor: "+armor+" (Base armor: "+baseArmor+")");
        System.out.println("Damage received reduced by armor amount");
        System.out.println("Armor increase by 5 each time he gets hit");
    }


}
