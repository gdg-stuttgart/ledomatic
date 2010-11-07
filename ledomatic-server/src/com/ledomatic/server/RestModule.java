package com.ledomatic.server;

import com.google.inject.servlet.ServletModule;

public class RestModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/*").with(RestletServlet.class);

		// cannot use @ImplementedBy
		//bind(EggService.class).to(EggServiceImpl.class);
		//bind(LoginService.class).to(LoginServiceImpl.class);
	}

}