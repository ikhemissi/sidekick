package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.environmenttypes.*;

import models.*;

/**
 * Manage a database of environmenttypes
 */
public class EnvironmentTypes extends Controller {
    
	/**
     * This result directly redirect to environmenttype home.
     */
    public static Result GO_HOME = redirect(
        routes.EnvironmentTypes.list(0, "name", "asc", "")
    );

    /**
     * Afficher la liste des "EnvironmentType"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on environmenttype names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                EnvironmentType.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing EnvironmentType.
     *
     * @param id Id of the environmenttype to edit
     */
    public static Result edit(Long id) {
        Form<EnvironmentType> environmenttypeForm = form(EnvironmentType.class).fill(
            EnvironmentType.find.byId(id)
        );
        return ok(
            editForm.render(id, environmenttypeForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the environmenttype to edit
     */
    public static Result update(Long id) {
        Form<EnvironmentType> environmenttypeForm = form(EnvironmentType.class).bindFromRequest();
        if(environmenttypeForm.hasErrors()) {
            return badRequest(editForm.render(id, environmenttypeForm));
        }
        environmenttypeForm.get().update(id);
        flash("success", "EnvironmentType " + environmenttypeForm.get().name + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new environmenttype form'.
     */
    public static Result create() {
        Form<EnvironmentType> environmenttypeForm = form(EnvironmentType.class);
        return ok(
            createForm.render(environmenttypeForm)
        );
    }
    
    /**
     * Handle the 'new environmenttype form' submission 
     */
    public static Result save() {
        Form<EnvironmentType> environmenttypeForm = form(EnvironmentType.class).bindFromRequest();
        if(environmenttypeForm.hasErrors()) {
            return badRequest(createForm.render(environmenttypeForm));
        }
        environmenttypeForm.get().save();
        flash("success", "EnvironmentType " + environmenttypeForm.get().name + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle environmenttype deletion
     */
    public static Result delete(Long id) {
        EnvironmentType.find.ref(id).delete();
        flash("success", "EnvironmentType a été supprimée");
        return GO_HOME;
    }
    

}
            
