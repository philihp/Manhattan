package com.philihp.manhattan.model;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.philihp.manhattan.jpa.Instance;
import com.philihp.manhattan.model.building.Building;
import com.philihp.manhattan.model.building.BuildingDeck;

public class Board {
	
	private final Random rand;
	
	private BuildingDeck buildingDeck;
	private Building[] buildingProposal;
	
	private int[] implosionCounters;
	
	public Board(Instance instance) {
		rand = new Random(instance.getSeed());
		buildingDeck = new BuildingDeck(rand);
		buildingProposal = new Building[7];
		for(int i = 0; i < buildingProposal.length; i++) {
			buildingProposal[i] = buildingDeck.draw();
		}
		
		implosionCounters = resetImplosionCounters(instance.getPlayerCount());
	}

	public Building getBuildingProposal1() {
		return buildingProposal[0];
	}
	public Building getBuildingProposal2() {
		return buildingProposal[1];
	}
	public Building getBuildingProposal3() {
		return buildingProposal[2];
	}
	public Building getBuildingProposal4() {
		return buildingProposal[3];
	}
	public Building getBuildingProposal5() {
		return buildingProposal[4];
	}
	public Building getBuildingProposal6() {
		return buildingProposal[5];
	}
	public Building getBuildingProposal7() {
		return buildingProposal[6];
	}

	public Iterator<Building> getBuildingDeckIterator() {
		return buildingDeck.iterator();
	}
	
	public int[] getImplosionCounters() {
		return implosionCounters;
	}
	
	private int[] resetImplosionCounters(int players) {
		switch(players) {
		case 2:
			return new int[] {0, 6};
		case 3:
			return new int[] {0, 4, 8};
		case 4:
			return new int[] {0, 2, 4, 6};
		case 5:
			return new int[] {0, 2, 4, 6, 8};
		default:
			throw new RuntimeException("Unexpected number of players");
		}
	}
	
}
