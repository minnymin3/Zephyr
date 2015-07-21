package com.minnymin.zephyr.api.user;

import java.util.UUID;

import com.minnymin.zephyr.api.spell.ContinuousSpell;
import com.minnymin.zephyr.api.spell.SpellContext;

public interface User {

	/**
	 * Adds level progress and levels up the player if required
	 */
	public int addLevelProgress(int progress);

	/**
	 * Adds a state to the user's state map
	 */
	public void addState(UserState userState, int duration);

	/**
	 * Drains mana from user's mana. Only accepts positive values
	 */
	public void drainMana(int toDrain);

	/**
	 * Gets the user's mana
	 */
	public int getMana();

	/**
	 * Gets user's maximum mana
	 */
	public int getMaximumMana();

	/**
	 * Gets the player object from the implementation
	 */
	public <T> T getPlayerObject();

	/**
	 * Gets the progress required for the user to level up
	 */
	public int getRequiredLevelProgress();

	/**
	 * Get user's UserData
	 */
	public UserData getUserData();

	/**
	 * Gets the UUID of the user
	 */
	public UUID getUUID();

	/**
	 * Checks if user has more than specified drain amount
	 * 
	 * @return True if user has enough mana, false otherwise
	 */
	public boolean hasMana(int toDrain);

	/**
	 * Checks if user is currently casting a spell
	 */
	public boolean isCasting();

	/**
	 * Sends a message to the user
	 */
	public void sendMessage(String... message);

	/**
	 * Sets the spell the user should currently cast
	 */
	public void setCasting(ContinuousSpell spell, SpellContext context);

	/**
	 * Sets the user's mana
	 */
	public void setMana(int mana);

	public void tick();

}