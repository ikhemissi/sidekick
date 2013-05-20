package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.entitlements.*;

import models.*;

/**
 * Manage a database of entitlements
 */
public class Entitlements extends Controller {
    
	/**
     * This result directly redirect to entitlement home.
     */
    public static Result GO_HOME = redirect(
        routes.Entitlements.list(0, "id", "asc", "")
    );

    /**
     * Afficher la liste des "Entitlement"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on entitlement names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Entitlement.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Entitlement.
     *
     * @param id Id of the entitlement to edit
     */
    public static Result edit(Long id) {
        Form<Entitlement> entitlementForm = form(Entitlement.class).fill(
            Entitlement.find.byId(id)
        );
        return ok(
            editForm.render(id, entitlementForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the entitlement to edit
     */
    public static Result update(Long id) {
        Form<Entitlement> entitlementForm = form(Entitlement.class).bindFromRequest();
        if(entitlementForm.hasErrors()) {
            return badRequest(editForm.render(id, entitlementForm));
        }
        entitlementForm.get().update(id);
        flash("success", "Entitlement " + entitlementForm.get().operation + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new entitlement form'.
     */
    public static Result create() {
        Form<Entitlement> entitlementForm = form(Entitlement.class);
        return ok(
            createForm.render(entitlementForm)
        );
    }
    
    /**
     * Handle the 'new entitlement form' submission 
     */
    public static Result save() {
        Form<Entitlement> entitlementForm = form(Entitlement.class).bindFromRequest();
        if(entitlementForm.hasErrors()) {
            return badRequest(createForm.render(entitlementForm));
        }
        entitlementForm.get().save();
        flash("success", "Entitlement " + entitlementForm.get().operation + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle entitlement deletion
     */
    public static Result delete(Long id) {
        Entitlement.find.ref(id).delete();
        flash("success", "Entitlement a été supprimée");
        return GO_HOME;
    }
    

}
            
