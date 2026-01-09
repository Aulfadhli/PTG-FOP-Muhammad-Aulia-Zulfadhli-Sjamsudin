public class Archer extends Character{
    public double dodgeChance;
    public int arrowsFired;


    public Archer(String name, int healthPoints, int attackPower, double dodgeChance){
        super(name,healthPoints,attackPower);
        this.dodgeChance = dodgeChance;
    }

    @Override
    public int attack(){
        arrowsFired+= 5;
        return attackPower;
    }

    public int arrowRain(){
        System.out.println(name+" uses ARROW RAIN!");
        int totalDamage = attackPower;
        for(int i = 0; i<arrowsFired; i++){
            totalDamage++;
        }
        arrowsFired = 0;
        return totalDamage;
    }

    @Override
    public void damageTaken(int damage){
        if(Math.random() < dodgeChance){
            System.out.println(name+" dodged the attack!");
        }else {
            super.damageTaken(damage);
        }
    }

    public double getDodgeChance(){
        return dodgeChance;
    }

    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Dodge Chance: " + (dodgeChance * 100) + "%");
        System.out.println("Arrows Fired: " + arrowsFired);
    }
}
