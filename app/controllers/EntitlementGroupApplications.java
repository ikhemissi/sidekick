package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.entitlementgroupapplications.*;

import models.*;

/**
 * Manage a database of entitlementgroupapplications
 */
public class EntitlementGroupApplications extends Controller {
    
	/**
     * This result directly redirect to entitlementgroupapplication home.
     */
    public static Result GO_HOME = redirect(
        routes.EntitlementGroupApplications.list(0, "name", "asc", "")
    );

    /**
     * Afficher la liste des "EntitlementGroupApplication"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on entitlementgroupapplication names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                EntitlementGroupApplication.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing EntitlementGroupApplication.
     *
     * @param id Id of the entitlementgroupapplication to edit
     */
    public static Result edit(Long id) {
        Form<EntitlementGroupApplication> entitlementgroupapplicationForm = form(EntitlementGroupApplication.class).fill(
            EntitlementGroupApplication.find.byId(id)
        );
        return ok(
            editForm.render(id, entitlementgroupapplicationForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the entitlementgroupapplication to edit
     */
    public static Result update(Long id) {
        Form<EntitlementGroupApplication> entitlementgroupapplicationForm = form(EntitlementGroupApplication.class).bindFromRequest();
        if(entitlementgroupapplicationForm.hasErrors()) {
            return badRequest(editForm.render(id, entitlementgroupapplicationForm));
        }
        entitlementgroupapplicationForm.get().update(id);
        flash("success", "EntitlementGroupApplication " + entitlementgroupapplicationForm.get().name + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new entitlementgroupapplication form'.
     */
    public static Result create() {
        Form<EntitlementGroupApplication> entitlementgroupapplicationForm = form(EntitlementGroupApplication.class);
        return ok(
            createForm.render(entitlementgroupapplicationForm)
        );
    }
    
    /**
     * Handle the 'new entitlementgroupapplication form' submission 
     */
    public static Result save() {
        Form<EntitlementGroupApplication> entitlementgroupapplicationForm = form(EntitlementGroupApplication.class).bindFromRequest();
        if(entitlementgroupapplicationForm.hasErrors()) {
            return badRequest(createForm.render(entitlementgroupapplicationForm));
        }
        entitlementgroupapplicationForm.get().save();
        flash("success", "EntitlementGroupApplication " + entitlementgroupapplicationForm.get().name + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle entitlementgroupapplication deletion
     */
    public static Result delete(Long id) {
        EntitlementGroupApplication.find.ref(id).delete();
        flash("success", "EntitlementGroupApplication a été supprimée");
        return GO_HOME;
    }
    

}
            
