package com.minnymin.util.cmd;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Cmd {

	public String label();
	
	public String description();
		
	public String usage();
	
	public String[] aliases() default {};
	
}
