/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsclient.controller;

import com.luizalabs.friendsclient.client.FriendClient;
import com.luizalabs.friendsclient.model.Friend;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;

/**
 *
 * @author alanfrank
 */
@ManagedBean
@SessionScoped
public class FacebookController {
	
    private SocialAuthManager socialManager;
    private Profile profile;

    private final String mainURL = "http://localhost:8080/FriendsClient/index.xhtml";
    private final String redirectURL = "http://localhost:8080/FriendsClient/redirectLogin.xhtml";
    private final String provider = "facebook";

    public void connect() {
        Properties prop = System.getProperties();
        prop.put("graph.facebook.com.consumer_key", "829854193843990");
        prop.put("graph.facebook.com.consumer_secret", "95ba28d2f2faea4a29d03393121a7ccc");
        prop.put("graph.facebook.com.custom_permissions", "public_profile,email,user_friends");

        SocialAuthConfig socialConfig = SocialAuthConfig.getDefault();
        try {
            socialConfig.load(prop);
            socialManager = new SocialAuthManager();
            socialManager.setSocialAuthConfig(socialConfig);
            String URLRetorno = socialManager.getAuthenticationUrl(provider, redirectURL);
            FacesContext.getCurrentInstance().getExternalContext().redirect(URLRetorno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getUserProfile() throws Exception {
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ex.getRequest();

        Map<String, String> params = SocialAuthUtil.getRequestParametersMap(request);

        if (socialManager != null) {
            AuthProvider provider = socialManager.connect(params);
            this.setProfile(provider.getUserProfile());
                    
            List<Contact> contacts = provider.getContactList();
            for (Contact c : contacts) {
                Friend friend = new Friend();
                friend.setName(c.getDisplayName());
                friend.setEmail(c.getEmail());
                friend.setProfileImage(c.getProfileImageURL());
                friend.setProfileLink("https://www.facebook.com/" + c.getId());
                friend.setBirthDate("1900-01-01");
                new FriendClient().postRequest(friend);
            }
        }
        
        

        FacesContext.getCurrentInstance().getExternalContext().redirect(mainURL);
    }

    public Profile getProfile() {
            return profile;
    }

    public void setProfile(Profile profile) {
            this.profile = profile;
    }    
}
