package com.crm;

import com.crm.view.CustomerPage;
import com.crm.view.HomePage;
import com.crm.view.LoginPage;
import com.crm.view.UserAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.crm.Start#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication
{    
    /**
     * Constructor
     */
	public WicketApplication()
	{
	}

	/**
	 * Add for Spring support
	 */
	@Override
	public void init()
	{
		super.init();
        getDebugSettings().setDevelopmentUtilitiesEnabled(true);
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        mountPage("login", LoginPage.class);
        mountPage("home", HomePage.class);
        mountPage("customers", CustomerPage.class);

    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        System.out.println("websesssion");
        return UserAuthenticatedWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        System.out.println("LOGIN");
        return LoginPage.class;
    }

    /**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

}
