package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.webservers.*;

import models.*;

/**
 * Manage a database of webservers
 */
public class WebServers extends Controller {
    
	/**
     * This result directly redirect to webserver home.
     */
    public static Result GO_HOME = redirect(
        routes.WebServers.list(0, "name", "asc", "")
    );

    /**
     * Afficher la liste des "WebServer"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on webserver names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                WebServer.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing WebServer.
     *
     * @param id Id of the webserver to edit
     */
    public static Result edit(Long id) {
        Form<WebServer> webserverForm = form(WebServer.class).fill(
            WebServer.find.byId(id)
        );
        return ok(
            editForm.render(id, webserverForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the webserver to edit
     */
    public static Result update(Long id) {
        Form<WebServer> webserverForm = form(WebServer.class).bindFromRequest();
        if(webserverForm.hasErrors()) {
            return badRequest(editForm.render(id, webserverForm));
        }
        webserverForm.get().update(id);
        flash("success", "WebServer " + webserverForm.get().name + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new webserver form'.
     */
    public static Result create() {
        Form<WebServer> webserverForm = form(WebServer.class);
        return ok(
            createForm.render(webserverForm)
        );
    }
    
    /**
     * Handle the 'new webserver form' submission 
     */
    public static Result save() {
        Form<WebServer> webserverForm = form(WebServer.class).bindFromRequest();
        if(webserverForm.hasErrors()) {
            return badRequest(createForm.render(webserverForm));
        }
        webserverForm.get().save();
        flash("success", "WebServer " + webserverForm.get().name + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle webserver deletion
     */
    public static Result delete(Long id) {
        WebServer.find.ref(id).delete();
        flash("success", "WebServer a été supprimée");
        return GO_HOME;
    }
    

}
            
