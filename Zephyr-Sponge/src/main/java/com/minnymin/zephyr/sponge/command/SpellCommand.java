package com.minnymin.zephyr.sponge.command;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;

import com.google.common.base.Optional;
import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.spell.Spell;
import com.minnymin.zephyr.api.spell.SpellManager;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.sponge.spell.SpongeSpellContext;
import com.minnymin.zephyr.sponge.util.directive.ArgumentType;
import com.minnymin.zephyr.sponge.util.directive.Directive;

public class SpellCommand {

	@Directive(names = { "cast" }, argumentLabels = {"spell", "args"}, arguments = {ArgumentType.OPTIONAL_SPELL, ArgumentType.OPTIONAL_REMAINING}, inGameOnly = true)
	public static CommandResult onCast(CommandSource src, CommandContext context) {
		User user = Zephyr.getUserManager().getUser(((Player)src).getUniqueId());
		SpellManager manager = Zephyr.getSpellManager();
		if (context.getOne("spell").isPresent()) {
			Spell spell = manager.getSpell(context.<String>getOne("spell").get());
			Optional<String> options = context.<String>getOne("args");
			manager.cast(spell, new SpongeSpellContext(spell, user, options.isPresent() ? options.get().split(" ") : new String[0]));
			return CommandResult.success();
		} else {
			if (user.isCasting()) {
				user.setCasting(null, null);
			} else {
				user.sendMessage("Usage: /cast <spell> [args...]");
			}
			return CommandResult.success();
		}
	}
	
}
