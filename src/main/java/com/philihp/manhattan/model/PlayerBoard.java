package com.philihp.manhattan.model;

import java.util.ArrayList;
import java.util.List;

import com.philihp.manhattan.model.building.Building;

public class PlayerBoard {
	private MainBoard mainBoard;
	private PlayerColor color;
	private List<Building> buildings;
	private List<Worker> laborers;
	private List<Worker> engineers;
	private List<Worker> scientists;
	private List<Worker> untrainedEngineers;
	private List<Worker> untrainedScientists;
	
	public PlayerBoard(MainBoard mainBoard, PlayerColor color) {
		this.mainBoard = mainBoard;
		this.color = color;
		this.laborers = new ArrayList<Worker>(4);
		laborers.add(new Worker(color, WorkerType.LABORER));
		laborers.add(new Worker(color, WorkerType.LABORER));
		laborers.add(new Worker(color, WorkerType.LABORER));
		laborers.add(new Worker(color, WorkerType.LABORER));
		this.engineers = new ArrayList<Worker>(4);
		this.untrainedEngineers = new ArrayList<Worker>(4);
		untrainedEngineers.add(new Worker(color, WorkerType.ENGINEER));
		untrainedEngineers.add(new Worker(color, WorkerType.ENGINEER));
		untrainedEngineers.add(new Worker(color, WorkerType.ENGINEER));
		untrainedEngineers.add(new Worker(color, WorkerType.ENGINEER));
		this.scientists = new ArrayList<Worker>(4);
		this.untrainedScientists = new ArrayList<Worker>(4);
		untrainedScientists.add(new Worker(color, WorkerType.SCIENTIST));
		untrainedScientists.add(new Worker(color, WorkerType.SCIENTIST));
		untrainedScientists.add(new Worker(color, WorkerType.SCIENTIST));
		untrainedScientists.add(new Worker(color, WorkerType.SCIENTIST));
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public MainBoard getMainBoard() {
		return mainBoard;
	}

	public PlayerColor getColor() {
		return color;
	}

	public List<Worker> getLaborers() {
		return laborers;
	}

	public List<Worker> getEngineers() {
		return engineers;
	}

	public List<Worker> getScientists() {
		return scientists;
	}

	public List<Worker> getUntrainedEngineers() {
		return untrainedEngineers;
	}

	public List<Worker> getUntrainedScientists() {
		return untrainedScientists;
	}
	
}
