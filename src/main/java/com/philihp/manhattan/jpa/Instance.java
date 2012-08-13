package com.philihp.manhattan.jpa;

import static javax.persistence.AccessType.FIELD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AssociationOverride;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import com.philihp.manhattan.model.InstanceStatus;
import com.philihp.manhattan.model.PlayerColor;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;

@Entity(name = "Instance")
@Table(name = "instance")
@Access(FIELD)
@NamedQueries({
		@NamedQuery(name = "findMyInstances", query = "SELECT i FROM Instance i WHERE i.player1.user = :user OR i.player2.user = :user OR i.player3.user = :user OR i.player4.user = :user OR i.player5.user = :user AND i.status != com.philihp.manhattan.model.InstanceStatus.FINISHED"),
		@NamedQuery(name = "findRecruitingInstances", query = "SELECT i FROM Instance i WHERE i.status = com.philihp.manhattan.model.InstanceStatus.RECRUITING AND (i.player1.user != :user OR i.player1.user IS NULL) AND (i.player2.user != :user OR i.player2.user IS NULL) AND (i.player3.user != :user OR i.player3.user IS NULL) AND (i.player4.user != :user OR i.player4.user IS NULL) AND (i.player5.user != :user OR i.player5.user IS NULL)"),
		@NamedQuery(name = "findReplacingInstances", query = "SELECT i FROM Instance i WHERE i.status = com.philihp.manhattan.model.InstanceStatus.REPLACING AND (i.player1.user != :user OR i.player1.user IS NULL) AND (i.player2.user != :user OR i.player2.user IS NULL) AND (i.player3.user != :user OR i.player3.user IS NULL) AND (i.player4.user != :user OR i.player4.user IS NULL) AND (i.player5.user != :user OR i.player5.user IS NULL)"),
		@NamedQuery(name = "findInProgressInstances", query = "SELECT i FROM Instance i WHERE i.status = com.philihp.manhattan.model.InstanceStatus.IN_PROGRESS"),
		@NamedQuery(name = "findFinishedInstances", query = "SELECT i FROM Instance i WHERE i.status = com.philihp.manhattan.model.InstanceStatus.FINISHED") })
public class Instance extends BasicEntity implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "instance_id")
	private int instanceId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "min_players")
	private int minPlayers;

	@Column(name = "max_players")
	private int maxPlayers;

	@Column(name = "time_limit_a")
	private int timeLimitAMinutes;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private InstanceStatus status;

	@Column(name = "seed")
	private long seed;

	@Embeddable
	@Access(FIELD)
	public static class Player {
		@OneToOne(targetEntity = User.class)
		@JoinColumn(name = "user_user_id", referencedColumnName = "user_id")
		private User user;

		@Enumerated(EnumType.STRING)
		@Column(name = "color")
		private PlayerColor color;

		public Player() {
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public PlayerColor getColor() {
			return color;
		}

		public void setColor(PlayerColor color) {
			this.color = color;
		}
	}

	@Embedded
	@AttributeOverride(name = "color", column = @Column(name = "player1_color"))
	@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "player1_user_id", referencedColumnName = "user_id"))
	private Player player1;

	@Embedded
	@AttributeOverride(name = "color", column = @Column(name = "player2_color"))
	@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "player2_user_id", referencedColumnName = "user_id"))
	private Player player2;

	@Embedded
	@AttributeOverride(name = "color", column = @Column(name = "player3_color"))
	@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "player3_user_id", referencedColumnName = "user_id"))
	private Player player3;

	@Embedded
	@AttributeOverride(name = "color", column = @Column(name = "player4_color"))
	@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "player4_user_id", referencedColumnName = "user_id"))
	private Player player4;

	@Embedded
	@AttributeOverride(name = "color", column = @Column(name = "player5_color"))
	@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "player5_user_id", referencedColumnName = "user_id"))
	private Player player5;

	@OneToMany(targetEntity = Transition.class, mappedBy = "instance")
	@OrderBy
	private List<Transition> transitions;

	public Instance() {
		player1 = new Player();
		player2 = new Player();
		player3 = new Player();
		player4 = new Player();
		player5 = new Player();
	}

	public int getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getTimeLimitAMinutes() {
		return timeLimitAMinutes;
	}

	public void setTimeLimitAMinutes(int timeLimitAMinutes) {
		this.timeLimitAMinutes = timeLimitAMinutes;
	}

	public InstanceStatus getStatus() {
		return status;
	}

	public void setStatus(InstanceStatus status) {
		this.status = status;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Player getPlayer3() {
		return player3;
	}

	public void setPlayer3(Player player3) {
		this.player3 = player3;
	}

	public Player getPlayer4() {
		return player4;
	}

	public void setPlayer4(Player player4) {
		this.player4 = player4;
	}

	public Player getPlayer5() {
		return player5;
	}

	public void setPlayer5(Player player5) {
		this.player5 = player5;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setMoves(List<Transition> moves) {
		this.transitions = moves;
	}

	@Transient
	public Player[] getPlayers() {
		return new Player[] { player1, player2, player3, player4, player5 };
	}

	@Transient
	public int getPlayerCount() {
		int count = 0;
		for (Player player : getPlayers()) {
			if (player != null && player.getUser() != null)
				count++;
		}
		return count;
	}

	@Transient
	public List<PlayerColor> getAvailableColors() {
		List<PlayerColor> availableColors = new ArrayList<PlayerColor>(
				Arrays.asList(PlayerColor.values()));
		for (Player player : getPlayers()) {
			if (player != null && player.getUser() != null
					&& player.getColor() != null)
				availableColors.remove(player.getColor());
		}
		return availableColors;
	}

	@Transient
	public Player getNextOpenPlayerSpot() {
		if (player1 == null || player1.getUser() == null) {
			player1 = new Player();
			return player1;
		} else if (player2 == null || player2.getUser() == null) {
			player2 = new Player();
			return player2;
		} else if (player3 == null || player3.getUser() == null) {
			player3 = new Player();
			return player3;
		} else if (player4 == null || player4.getUser() == null) {
			player4 = new Player();
			return player4;
		} else if (player5 == null || player5.getUser() == null) {
			player5 = new Player();
			return player5;
		} else
			return null;
	}

}
