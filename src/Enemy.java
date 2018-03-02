
public class Enemy extends Character {

	private int escapeChance;
	private int expReward;
	
	public Enemy(String name, int damage, int health, int speed, int expReward) {
		setName(name);
		setDamage(damage);
		setHealth(health);
		setSpeed(speed);
		setEscapeChance();
		setExpReward(expReward);
	}
	
	//Getter & Setter for Player Escape Chance.
	
	public int getEscapeChance() {
		return escapeChance;
	}
	
	public void setEscapeChance() {
		this.escapeChance = 100-speed;
	}
	
	public int getExpReward() {
		return expReward;
	}
	public void setExpReward(int expReward) {
		this.expReward = expReward;
	}
}