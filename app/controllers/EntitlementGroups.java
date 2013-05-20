package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.entitlementgroups.*;

import models.*;

/**
 * Manage a database of entitlementgroups
 */
public class EntitlementGroups extends Controller {
    
	/**
     * This result directly redirect to entitlementgroup home.
     */
    public static Result GO_HOME = redirect(
        routes.EntitlementGroups.list(0, "name", "asc", "")
    );

    /**
     * Afficher la liste des "EntitlementGroup"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on entitlementgroup names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                EntitlementGroup.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing EntitlementGroup.
     *
     * @param id Id of the entitlementgroup to edit
     */
    public static Result edit(Long id) {
        Form<EntitlementGroup> entitlementgroupForm = form(EntitlementGroup.class).fill(
            EntitlementGroup.find.byId(id)
        );
        return ok(
            editForm.render(id, entitlementgroupForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the entitlementgroup to edit
     */
    public static Result update(Long id) {
        Form<EntitlementGroup> entitlementgroupForm = form(EntitlementGroup.class).bindFromRequest();
        if(entitlementgroupForm.hasErrors()) {
            return badRequest(editForm.render(id, entitlementgroupForm));
        }
        entitlementgroupForm.get().update(id);
        flash("success", "EntitlementGroup " + entitlementgroupForm.get().name + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new entitlementgroup form'.
     */
    public static Result create() {
        Form<EntitlementGroup> entitlementgroupForm = form(EntitlementGroup.class);
        return ok(
            createForm.render(entitlementgroupForm)
        );
    }
    
    /**
     * Handle the 'new entitlementgroup form' submission 
     */
    public static Result save() {
        Form<EntitlementGroup> entitlementgroupForm = form(EntitlementGroup.class).bindFromRequest();
        if(entitlementgroupForm.hasErrors()) {
            return badRequest(createForm.render(entitlementgroupForm));
        }
        entitlementgroupForm.get().save();
        flash("success", "EntitlementGroup " + entitlementgroupForm.get().name + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle entitlementgroup deletion
     */
    public static Result delete(Long id) {
        EntitlementGroup.find.ref(id).delete();
        flash("success", "EntitlementGroup a été supprimée");
        return GO_HOME;
    }
    

}
            
