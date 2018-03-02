package tutorials;

import java.util.Random;

public abstract class Character {
	protected String name;
	protected int damage;
	protected int speed; 
	protected int health;

	//Getter & Setter for Damage.
	public int getDamage() {
		double speedPercent = speed/100.0;
		int minDamage = (int)(speedPercent*damage);
		Random rand = new Random();
		int damageRange = rand.nextInt(damage - minDamage);
		return damageRange + minDamage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	// Getter & Setter for Name.

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// Getter & Setter for Health.

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	//Getter & Setter for Speed
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}