package com.ledomatic.server;

import com.google.inject.servlet.ServletModule;
import com.wideplay.warp.jpa.JpaUnit;

public class RestModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/*").with(RestletServlet.class);

		// cannot use @ImplementedBy
		//bind(EggService.class).to(EggServiceImpl.class);
		//bind(LoginService.class).to(LoginServiceImpl.class);

		bindConstant().annotatedWith(JpaUnit.class).to("default");
	}

}