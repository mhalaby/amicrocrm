package com.crm.view;

import org.apache.wicket.markup.html.WebPage;

/**
 * Created by muhammad on 03.02.15.
 */

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;


/**
 * Simple example of a sign in page.
 *
 * @author Jonathan Locke
 */
public final class LoginPage extends WebPage
{
    /**
     * Constructor
     *  That is all you need to add a logon panel to your application with rememberMe
     *  functionality based on Cookies. Meaning username and password are persisted in a Cookie.
     *  Please see ISecuritySettings#getAuthenticationStrategy() for details.
     */
    public LoginPage()
    {

        add(new SignInPanel("signInPanel",false));
    }
}