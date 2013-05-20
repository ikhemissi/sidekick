package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.entitlementchecks.*;

import models.*;

/**
 * Manage a database of entitlementchecks
 */
public class EntitlementChecks extends Controller {
    
	/**
     * This result directly redirect to entitlementcheck home.
     */
    public static Result GO_HOME = redirect(
        routes.EntitlementChecks.list(0, "date", "asc", "")
    );

    /**
     * Afficher la liste des "EntitlementCheck"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on entitlementcheck names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                EntitlementCheck.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing EntitlementCheck.
     *
     * @param id Id of the entitlementcheck to edit
     */
    public static Result edit(Long id) {
        Form<EntitlementCheck> entitlementcheckForm = form(EntitlementCheck.class).fill(
            EntitlementCheck.find.byId(id)
        );
        return ok(
            editForm.render(id, entitlementcheckForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the entitlementcheck to edit
     */
    public static Result update(Long id) {
        Form<EntitlementCheck> entitlementcheckForm = form(EntitlementCheck.class).bindFromRequest();
        if(entitlementcheckForm.hasErrors()) {
            return badRequest(editForm.render(id, entitlementcheckForm));
        }
        entitlementcheckForm.get().update(id);
        flash("success", "EntitlementCheck " + entitlementcheckForm.get().id + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new entitlementcheck form'.
     */
    public static Result create() {
        Form<EntitlementCheck> entitlementcheckForm = form(EntitlementCheck.class);
        return ok(
            createForm.render(entitlementcheckForm)
        );
    }
    
    /**
     * Handle the 'new entitlementcheck form' submission 
     */
    public static Result save() {
        Form<EntitlementCheck> entitlementcheckForm = form(EntitlementCheck.class).bindFromRequest();
        if(entitlementcheckForm.hasErrors()) {
            return badRequest(createForm.render(entitlementcheckForm));
        }
        entitlementcheckForm.get().save();
        flash("success", "EntitlementCheck " + entitlementcheckForm.get().id + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle entitlementcheck deletion
     */
    public static Result delete(Long id) {
        EntitlementCheck.find.ref(id).delete();
        flash("success", "EntitlementCheck a été supprimée");
        return GO_HOME;
    }
    

}
            
