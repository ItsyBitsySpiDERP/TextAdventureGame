package tutorials;

import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		//System objects
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		
		//Game variables
		Enemy skeleton = new Enemy("Skeleton", 10, 50, 20, 20);
		Enemy zombie = new Enemy("Zombie", 15, 65, 25, 30);
		Enemy barbarian = new Enemy("Barbarian", 20, 90, 40, 40);
		Enemy assassin = new Enemy("Assassin", 30, 70, 60, 50);
		
		Enemy dragon = new Enemy("Migi the Deathly Dragon", 50, 100, 50, 50);
		Enemy beast = new Enemy("Sinatra the Giant", 40, 130, 50, 50);
		
		Enemy[] enemies = {skeleton, zombie, barbarian, assassin};
		Enemy[] bosses = {dragon, beast};
		
		//Player Variable
		int healthPotionHealAmount = 40;
		int healthPotionDropChance = 50; // Percentage
		
		boolean running = true;
		
		System.out.println("Welcome to the Dungeon!");
		System.out.println("\nChoose a name for your character: ");
		String input = in.nextLine();
		Hero hero = new Hero(input);
		
		int stageNumber = 0;
		
		GAME:
		while(running) {
			stageNumber++;
			System.out.println("-------------------------------------------------");
			
			int enemyIndex;
			Enemy enemy;
			int enemyHealth;
			
			if (stageNumber%10==0) {
				enemyIndex = rand.nextInt(bosses.length);
				enemy = bosses[enemyIndex];
			} 	
			else {
				enemyIndex= rand.nextInt(enemies.length);
				enemy = enemies[enemyIndex];
			}
			
			enemyHealth = enemy.getHealth();
			
			System.out.println("\t# " + enemy.getName() + " has appeared! #\n");
			
			while(enemyHealth > 0) {
				System.out.println("\t " + hero.getName() + "'s HP: " + hero.getHealth());
				System.out.println("\t " + enemy.getName() + "'s HP: " + enemyHealth);
				System.out.println("\n\tWhat would you like " + hero.getName() + " to do?");
				System.out.println("\t1. Attack");
				System.out.println("\t2. Drink health potion");
				System.out.println("\t3. Run!");
				
				//ATTACK!!!!!
				input = in.nextLine();
				if(input.equals("1")) {
					int damageDealt = rand.nextInt(hero.getDamage());
					int damageTaken = enemy.getDamage();
					
					enemyHealth -= damageDealt;
					hero.takeDamage(damageTaken);
					
					System.out.println("\t> " + hero.getName() + " strikes the " + enemy.getName() + " for " + damageDealt + " damage.");
					System.out.println("\t> " + hero.getName() +  " recieves " + damageTaken + " damage in retaliation!");
					
					
					if(hero.getHealth() < 1) {
						System.out.println("\t> " + hero.getName() + " has taken too much damage, you are too weak to go on!");
						break;
					}
				}
				
				//HEALTH POTIONS
				else if(input.equals("2")) {
					if(hero.getPotions() > 0) {
						hero.drinkPotion(healthPotionHealAmount);
						System.out.println("\t> " + hero.getName() + " drank a health potion, healing themself for " + healthPotionHealAmount + "."
										+ "\n\t> They now have " + hero.getHealth() + " HP and " + hero.getPotions() + " health potions left.\n");
					}
					else {
						System.out.println("\t> " + hero.getName() + " has no health potions left! Defeat enemies for a chance to get one!\n");
					}
				}
				
				//RUN
				else if(input.equals("3")) {
					
					int escapeChance = enemy.getEscapeChance();
					if(rand.nextInt(100) < escapeChance) {
						System.out.println("\t"+ hero.getName() + " runs away from the " + enemy.getName() + "!");
						continue GAME;
					}
					else {
						System.out.println("\t***" + hero.getName() + " CANNOT ESCAPE***\n");
					}
				}
				else {
					System.out.println("\tInvalid command!");
				}
			}
			
			if(hero.getHealth() < 1) {
				System.out.println( hero.getName() + " limps out of the dungeon, weak from battle.");
				break;
			}
			
			System.out.println("--------------------------------------------------------");
			System.out.println(" # " + enemy.getName() + " was defeated! #");
			System.out.println(" # " + hero.getName() + " has " + hero.getHealth() + " HP left. #");
			
			// Experience & Level Up
			hero.receiveExperience(enemy.getExpReward());
			System.out.println(" # " + hero.getName() + " gained " + enemy.getExpReward() + " experience. #");
			if(hero.getExperience() >= hero.getRequiredExperience()) {
				int experienceDifference = hero.getExperience() - hero.getRequiredExperience();
				hero.setExperience(experienceDifference);
				hero.gainLevel();
				hero.setHealth(100);
				System.out.println(" # Congratulations, " + hero.getName() + " is now level " + hero.getLevel() + "! #");
			}
			System.out.println(" # You have " + hero.getExperience() + "/" + hero.getRequiredExperience() + " experience. #");
			// Loot
			if(rand.nextInt(100) < healthPotionDropChance) {
				hero.receivePotion();
				System.out.println(" # The " + enemy.getName() + " dropped a health potion!  #");
				System.out.println(" # " + hero.getName() + " now has " + hero.getPotions() + " health potion(s). # ");
			}
			System.out.println("--------------------------------------------------------");
			System.out.println("What do you want " + hero.getName() + " to do now?" );
			System.out.println("1. Continue fighting.");
			System.out.println("2. Exit dungeon");
			
			input = in.nextLine();
			
			while(!input.equals("1") && !input.equals("2")) {
				System.out.println("Invalid command!");
				input = in.nextLine();
			}
				
			if(input.equals("1")) {
				System.out.println( hero.getName() + " continues on their adventure!\n");
			}
			else if(input.equals("2")) {
				System.out.println( hero.getName() + " exits the dungeon, successful from their adventures!\n");
				break;
			}
		}
		System.out.println("#######################");
		System.out.println("# THANKS FOR PLAYING! #");
		System.out.println("#######################");
		
		in.close();
	}
}
