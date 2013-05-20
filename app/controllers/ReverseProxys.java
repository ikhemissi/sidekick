package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.reverseproxys.*;

import models.*;

/**
 * Manage a database of reverseproxys
 */
public class ReverseProxys extends Controller {
    
	/**
     * This result directly redirect to reverseproxy home.
     */
    public static Result GO_HOME = redirect(
        routes.ReverseProxys.list(0, "name", "asc", "")
    );

    /**
     * Afficher la liste des "ReverseProxy"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on reverseproxy names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                ReverseProxy.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing ReverseProxy.
     *
     * @param id Id of the reverseproxy to edit
     */
    public static Result edit(Long id) {
        Form<ReverseProxy> reverseproxyForm = form(ReverseProxy.class).fill(
            ReverseProxy.find.byId(id)
        );
        return ok(
            editForm.render(id, reverseproxyForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the reverseproxy to edit
     */
    public static Result update(Long id) {
        Form<ReverseProxy> reverseproxyForm = form(ReverseProxy.class).bindFromRequest();
        if(reverseproxyForm.hasErrors()) {
            return badRequest(editForm.render(id, reverseproxyForm));
        }
        reverseproxyForm.get().update(id);
        flash("success", "ReverseProxy " + reverseproxyForm.get().name + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new reverseproxy form'.
     */
    public static Result create() {
        Form<ReverseProxy> reverseproxyForm = form(ReverseProxy.class);
        return ok(
            createForm.render(reverseproxyForm)
        );
    }
    
    /**
     * Handle the 'new reverseproxy form' submission 
     */
    public static Result save() {
        Form<ReverseProxy> reverseproxyForm = form(ReverseProxy.class).bindFromRequest();
        if(reverseproxyForm.hasErrors()) {
            return badRequest(createForm.render(reverseproxyForm));
        }
        reverseproxyForm.get().save();
        flash("success", "ReverseProxy " + reverseproxyForm.get().name + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle reverseproxy deletion
     */
    public static Result delete(Long id) {
        ReverseProxy.find.ref(id).delete();
        flash("success", "ReverseProxy a été supprimée");
        return GO_HOME;
    }
    

}
            
