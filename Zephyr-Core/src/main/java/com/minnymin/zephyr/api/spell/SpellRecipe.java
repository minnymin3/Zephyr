package com.minnymin.zephyr.api.spell;

import java.util.Map.Entry;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.aspect.Aspect;
import com.minnymin.zephyr.api.aspect.AspectList;

public class SpellRecipe {

	private AspectList list;
	private Class<? extends Spell> prerequisite;
	
	public SpellRecipe(AspectList list) {
		this(list, null);
	}
	
	public SpellRecipe(AspectList list, Class<? extends Spell> prerequisite) {
		this.list = list;
		this.prerequisite = prerequisite;
	}
	
	public AspectList getAspects() {
		return this.list;
	}
	
	public Spell getPrerequisite() {
		return Zephyr.getSpellManager().getSpell(this.prerequisite);
	}
	
	public boolean hasPrerequisite() {
		return this.prerequisite != null;
	}
	
	public boolean isSatisfied(AspectList check) {
		for (Entry<Aspect, Integer> e : this.list.getAspects().entrySet()) {
			if (!check.getAspects().containsKey(e.getKey())|| check.getAspects().get(e.getKey()) < e.getValue()) {
				return false;
			}
		}
		return true;
	}
	
}
