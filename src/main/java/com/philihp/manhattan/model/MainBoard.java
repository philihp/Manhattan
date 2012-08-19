package com.philihp.manhattan.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.philihp.manhattan.jpa.Instance;
import com.philihp.manhattan.jpa.Instance.Player;
import com.philihp.manhattan.model.building.Building;
import com.philihp.manhattan.model.building.BuildingDeck;

public class MainBoard {

	private final Random rand;

	private BuildingDeck buildingDeck;
	private List<Building> buildingProposal;
	private List<PlayerBoard> playerBoards;

	private List<Integer> implosionCounters;

	public MainBoard(Instance instance) {
		rand = new Random(instance.getSeed());
		buildingDeck = new BuildingDeck(rand);
		buildingProposal = new ArrayList<Building>(7);
		for (int i = 0; i < 7; i++) {
			buildingProposal.add(buildingDeck.draw());
		}
		implosionCounters = resetImplosionCounters(instance.getPlayerCount());

		playerBoards = new ArrayList<PlayerBoard>();
		for (Player player : instance.getPlayers()) {
			if (player.getUser() == null)
				continue;
			playerBoards.add(new PlayerBoard(this, player.getColor()));
		}
	}

	public void applyTurn(List<Move> moves) {
		for (Move move : moves) {
			System.out.println(move);
		}
	}

	public Building getBuildingProposal1() {
		return buildingProposal.get(0);
	}

	public Building getBuildingProposal2() {
		return buildingProposal.get(1);
	}

	public Building getBuildingProposal3() {
		return buildingProposal.get(2);
	}

	public Building getBuildingProposal4() {
		return buildingProposal.get(3);
	}

	public Building getBuildingProposal5() {
		return buildingProposal.get(4);
	}

	public Building getBuildingProposal6() {
		return buildingProposal.get(5);
	}

	public Building getBuildingProposal7() {
		return buildingProposal.get(6);
	}

	public Iterator<Building> getBuildingDeckIterator() {
		return buildingDeck.iterator();
	}

	public List<Integer> getImplosionCounters() {
		return implosionCounters;
	}

	public List<PlayerBoard> getPlayerBoards() {
		return playerBoards;
	}

	private List<Integer> resetImplosionCounters(int players) {
		ArrayList<Integer> counters = new ArrayList<Integer>();
		switch (players) {
		case 5:
			counters.add(8);
		case 4:
			counters.add(2);
			counters.add(4);
		case 2:
			counters.add(6);
			break;
		case 3:
			counters.add(4);
			counters.add(8);
			break;
		default:
			return counters;
		}
		counters.add(0);
		return counters;
	}

}
