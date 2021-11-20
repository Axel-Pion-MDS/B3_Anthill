package fr.axelpion.anthill;

/**
 * 
 * @author axel.pion
 *
 */
public class Ant {
	// Id of the ant
	private int birthRank;
	// Number of day since birth
	private int age;
	/* 
	 * Max age that depends on ant role
	 * 50 days for a queen ant
	 * 50 days for workers
	 * 20 days for male
	 * 10 days for larvae
	 */
	private int maxAge;
	/*
	 * The status decided at birth for the larvae
	 * 1 - Queen
	 * 2 - Worker
	 * 3 - Male
	 */
	private int birthStatus;
	// To know the future birthStatus after the larvae state
	private int futureBirthStatus;
	/*
	* Listing members of the colony
	* {{Queen, Worker, Male, Larvae}, {nbQueen, nbWorker, nbMale}}
	*/
	static int[][] colony = {{1, 2, 3, 4}, {0, 0, 0, 0}};
	public int sum;
	public boolean isDead;	
	
	static int count;
	
	/**
	 * 
	 * @param birthRank
	 * @param age
	 * @param maxAge
	 * @param birthStatus
	 * @param futureBirthStatus
	 */
	public Ant(int birthRank, int age, int maxAge, int birthStatus, int futureBirthStatus) {
		this.setNumberOfAnts(birthStatus);
		this.futureBirthStatus = futureBirthStatus;
		this.birthRank = birthRank;
		this.age = age;
		this.maxAge = maxAge;
	}
	
	/**
	 * 
	 */
	public Ant() {
		this.sum = this.getNumberOfAnts();
		this.birthRank = sum + 1;
		this.age = 1;
		this.birthStatus = 4;
		int randomBirthStatus = (int) (Math.random() * (20 - 1 + 1) + 1);
		if (randomBirthStatus == 1) {
			this.futureBirthStatus = 1;
			this.maxAge = 50;
			new Larvae(this.birthRank, this.age, this.maxAge, this.birthStatus, this.futureBirthStatus);
		}
		if (randomBirthStatus == 10 || randomBirthStatus == 20) {
			this.futureBirthStatus = 3;
			this.maxAge = 20;
			new Larvae(this.birthRank, this.age, this.maxAge, this.birthStatus, this.futureBirthStatus);
		} 
		else if (randomBirthStatus != 1 && randomBirthStatus != 10 && randomBirthStatus != 20) {
			this.futureBirthStatus = 2;
			this.maxAge = 50;
			new Larvae(this.birthRank, this.age, this.maxAge, this.birthStatus, this.futureBirthStatus);
		}
		
	}

	/**
	 * 
	 * @return birthRank
	 */
	public int getBirthRank() {
		return this.birthRank;
	}
	
	/**
	 * 
	 * @param birthRank
	 */
	public void setBirthRank(int birthRank) {
		this.birthRank = birthRank;
	}
	
	/**
	 * 
	 * @return birthStatus
	 */
	public int getBirthStatus() {
		return this.birthStatus;
	}
	
	/**
	 * 
	 * @param birthStatus
	 */
	public void setBirthStatus(int birthStatus) {
		this.birthStatus = birthStatus;
	}
	
	/**
	 * 
	 * @return futureBirthStatus
	 */
	public int getFutureBirthStatus() {
		return this.futureBirthStatus;
	}
	
	/**
	 * 
	 * @param futureBirthStatus
	 */
	public void setFutureBirthStatus(int futureBirthStatus) {
		this.futureBirthStatus = futureBirthStatus;
	}
	
	/**
	 * 
	 * @return age
	 */
	public int getAge() {
		return this.age;
	}
	
	/**
	 * 
	 */
	public void setAge() {
		if (this.birthStatus == 4 && this.age == 9) {
			switch (this.futureBirthStatus) {
				case 1:
					colony[1][3] -= 1;
					colony[1][0] += 1; 
					break;
				case 2:
					colony[1][3] -= 1;
					colony[1][1] += 1; 
					break;
				case 3:
					colony[1][3] -= 1;
					colony[1][2] += 1; 
					break;
			}
		}
		if (this.age < this.maxAge - 1) {
			this.age += 1;
		} else if (this.age == this.maxAge - 1) {
			this.age += 1;
			this.isDead = true;
			switch (this.birthStatus) {
				case 1:
					colony[1][0] -= 1;
					break;
				case 2:
					colony[1][1] -= 1;
					break;
				case 3:
					colony[1][2] -= 1;
					break;
			}
		}
	}
	
	/**
	 * 
	 * @return maxAge
	 */
	public int getMaxAge() {
		return this.maxAge;
	}
	
	/**
	 * 
	 * @param maxAge
	 */
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	
	/**
	 * 
	 * @return sum
	 */
	public int getNumberOfAnts() {
		sum = 0;
		for (int i = 0; i < colony[1].length; i++) {
			if (colony[1][i] != 0) {
				sum += colony[1][i];	
			}
		}
		
		return sum;
	}
	
	/**
	 * 
	 * @param birthStatus
	 */
	public void setNumberOfAnts(int birthStatus) {
		this.birthStatus = birthStatus;
		switch (this.birthStatus) {
			case 1:
				colony[1][0] += 1;
				break;
			case 2:
				colony[1][1] += 1;
				break;
			case 3:
				colony[1][2] += 1;
				break;
			case 4:
				colony[1][3] += 1;
				break;
		};
	}

	/**
	 * 
	 * @return listOfAnts
	 */
	public int[][] getNumberOfAntsByBirthStatus() {
		int[][] listOfAnts = colony;
		return listOfAnts;
	}

	/**
	 * 
	 * @return listOfAnts
	 */
	public String getListOfAnts() {
		int[][] nTotalAnts = this.getNumberOfAntsByBirthStatus();
		int totalQueens = 0;
		int totalWorkers = 0;
		int totalMales = 0;
		int totalLarvaes = 0;
		
		
		for (int i = 0; i < nTotalAnts[1].length; i++) {
			for (int j = 0; j < nTotalAnts[1][i]; j++) {
				switch (i + 1) {
					case 1:
						totalQueens += 1;
						break;
					case 2:
						totalWorkers += 1;
						break;
					case 3:
						totalMales += 1;
						break;
					case 4:
						totalLarvaes += 1;
						break;
				}
			}
		}
		String listOfAnts = "\nYour anthill has:\n"
				+ "  " + totalQueens + ConsoleColor.PURPLE + " Queen(s)" + ConsoleColor.RESET + "\n" 
				+ "  " + totalWorkers + ConsoleColor.CYAN + " Worker(s)" + ConsoleColor.RESET + "\n"
				+ "  " + totalMales + ConsoleColor.GREEN + " Male(s)" + ConsoleColor.RESET + "\n"
				+ "  " + totalLarvaes + ConsoleColor.YELLOW + " Larvae(s)" + ConsoleColor.RESET + "\n";
		
		return listOfAnts;
	}
	
	@Override
	public String toString() {
		return (this.isDead == true) ?
				"Ant with birthRank: " + this.birthRank + " is dead at age : " + this.age : 
				"Ant [birthRank=" + this.birthRank + ", age=" + this.age + ", maxAge=" + this.maxAge +  ", birthStatus=" + this.birthStatus + ", futureBirthStatus=" + this.futureBirthStatus + "]";
	}
	
}
