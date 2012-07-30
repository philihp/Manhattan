package com.philihp.manhattan.model.building;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BuildingDeck extends Deck<Building> implements Iterable<Building>  {

	public BuildingDeck(Random randomizer) {
		List<Building> bottomDeck = new ArrayList<Building>();
		bottomDeck.add(new Factory2());
		bottomDeck.add(new Factory3());
		bottomDeck.add(new Factory4());
		bottomDeck.add(new Factory5());
		bottomDeck.add(new Factory6());
		bottomDeck.add(new Factory7());
		bottomDeck.add(new Factory8());
		bottomDeck.add(new Factory9());
		bottomDeck.add(new Mine2());
		bottomDeck.add(new Mine3());
		bottomDeck.add(new Mine4());
		bottomDeck.add(new Mine5());
		bottomDeck.add(new Mine6());
		bottomDeck.add(new Mine7());
		bottomDeck.add(new Mine8());
		bottomDeck.add(new Mine9());
		bottomDeck.add(new Plant0());
		bottomDeck.add(new Plant1());
		bottomDeck.add(new Plant2());
		bottomDeck.add(new Plant3());
		bottomDeck.add(new Plant4());
		bottomDeck.add(new Plant5());
		bottomDeck.add(new Plant6());
		bottomDeck.add(new Plant7());
		bottomDeck.add(new Plant8());
		bottomDeck.add(new Plant9());
		bottomDeck.add(new Reactor0());
		bottomDeck.add(new Reactor1());
		bottomDeck.add(new Reactor2());
		bottomDeck.add(new Reactor3());
		bottomDeck.add(new Reactor4());
		bottomDeck.add(new Reactor5());
		bottomDeck.add(new Reactor6());
		bottomDeck.add(new Reactor7());
		bottomDeck.add(new Reactor8());
		bottomDeck.add(new Reactor9());
		bottomDeck.add(new University2());
		bottomDeck.add(new University3());
		bottomDeck.add(new University4());
		bottomDeck.add(new University5());
		bottomDeck.add(new University6());
		bottomDeck.add(new University7());
		bottomDeck.add(new University8());
		bottomDeck.add(new University9());
		Collections.shuffle(bottomDeck, randomizer);
		
		List<Building> topDeck = new ArrayList<Building>();
		topDeck.add(new Factory0());
		topDeck.add(new Factory1());
		topDeck.add(new University0());
		topDeck.add(new University1());
		topDeck.add(new Mine0());
		topDeck.add(new Mine1());
		Collections.shuffle(topDeck, randomizer);
		
		deck = new ArrayList<Building>(50);
		deck.addAll(bottomDeck);
		deck.addAll(topDeck);
	}
	
}
