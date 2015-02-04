package com.crm.view;

import com.crm.main.UserAuthenticatedWebSession;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Created by muhammad on 04.02.15.
 *
 * Only the base panel knows about the session
 */
public class BasePanel extends Panel {

    final UserAuthenticatedWebSession session;

    public BasePanel(String id) {
        super(id);
        session=(UserAuthenticatedWebSession)getSession();
    }
    /**
     * The method disabled components based on the user rights
     *
     * @param component
     *            The component to be checked against the role
     *
     */
    protected void checkRoles(Component component) {
        UserAuthenticatedWebSession session=(UserAuthenticatedWebSession)getSession();
        if(!session.getRoles().contains("ROLE_ADMIN")){
            component.setEnabled(false);
        }
    }
}
