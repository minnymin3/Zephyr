package com.minnymin.zephyr.sponge;

import java.io.File;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.config.ConfigDir;

import com.google.inject.Inject;
import com.minnymin.zephyr.PluginInfo;
import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.ZephyrAPI;
import com.minnymin.zephyr.projectile.ProjectileHandler;
import com.minnymin.zephyr.spell.SpellManager;
import com.minnymin.zephyr.sponge.command.SpellCommand;
import com.minnymin.zephyr.sponge.command.UserCommand;
import com.minnymin.zephyr.sponge.projectile.SpongeProjectileHandler;
import com.minnymin.zephyr.sponge.spell.SpongeSpellManager;
import com.minnymin.zephyr.sponge.user.SpongeUserManager;
import com.minnymin.zephyr.sponge.util.directive.DirectiveHandler;
import com.minnymin.zephyr.user.UserManager;

@Plugin(id = PluginInfo.ARTIFACT_ID, name = PluginInfo.NAME, version = PluginInfo.VERSION)
public class ZephyrPlugin implements ZephyrAPI {

	@Inject
	private Logger logger;
	
	@Inject
	private Game game;
	
	@Inject
	@ConfigDir(sharedRoot = false)
	private File configDirectory;
	
	private ProjectileHandler<Entity> projectileHandler;
	private SpellManager spellManager;
	private UserManager userManager;

	public ZephyrPlugin() {
		Zephyr.setAPISingleton(this);
	}

	@Subscribe
	public void onPreInit(PreInitializationEvent event) {
		this.projectileHandler = new SpongeProjectileHandler();
		this.spellManager = new SpongeSpellManager();
		this.userManager = new SpongeUserManager();

		DirectiveHandler handler = new DirectiveHandler(this, event.getGame());
		handler.addDirectives(SpellCommand.class);
		handler.addDirectives(UserCommand.class);
		handler.registerDirectives();
		
		//new AspectManager().addAll();

	}

	@Subscribe
	public void disable(ServerStoppingEvent event) {
	}

	@Override
	public ProjectileHandler<?> getProjectileHandler() {
		return this.projectileHandler;
	}
	
	@Override
	public SpellManager getSpellManager() {
		return this.spellManager;
	}

	@Override
	public UserManager getUserManager() {
		return this.userManager;
	}
	
	public static ZephyrPlugin getInstance() {
		return (ZephyrPlugin) Zephyr.getAPI();
	}

	public static File getConfigDirectory() {
		return ((ZephyrPlugin) Zephyr.getAPI()).configDirectory;
	}
	
	public static Game getGame() {
		return ((ZephyrPlugin) Zephyr.getAPI()).game;
	}

	public static Logger getLogger() {
		return ((ZephyrPlugin) Zephyr.getAPI()).logger;
	}

}
