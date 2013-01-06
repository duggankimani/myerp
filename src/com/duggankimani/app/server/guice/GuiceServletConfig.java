package com.duggankimani.app.server.guice;

import javax.servlet.ServletContextEvent;

import org.compiere.Adempiere;
import org.compiere.util.Env;

import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.Injector;
import com.google.inject.Guice;
import com.duggankimani.app.server.ERPSessionManager;
import com.duggankimani.app.server.guice.ServerModule;
import com.duggankimani.app.server.guice.DispatchServletModule;

public class GuiceServletConfig extends GuiceServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		super.contextInitialized(servletContextEvent);
		Env.setContextProvider(new ERPSessionManager());
		Adempiere.startup(false);
	}
	@Override
	protected Injector getInjector() {
		return Guice
				.createInjector(new ServerModule(), new DispatchServletModule());
	}
}
