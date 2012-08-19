package com.philihp.manhattan.model.building;

import java.util.Iterator;
import java.util.List;

abstract class Deck<T> implements Iterable<T> {
	
	protected List<T> deck;

	public T draw() {
		if(deck.size() <= 0) return null;
		return deck.remove(deck.size()-1);
	}

	public Iterator<T> iterator() {
		return deck.iterator();
	}
	
	
	
}
