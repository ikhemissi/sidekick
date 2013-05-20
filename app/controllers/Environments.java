package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.environments.*;

import models.*;

/**
 * Manage a database of environments
 */
public class Environments extends Controller {
    
	/**
     * This result directly redirect to environment home.
     */
    public static Result GO_HOME = redirect(
        routes.Environments.list(0, "name", "asc", "")
    );

    /**
     * Afficher la liste des "Environment"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on environment names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Environment.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Environment.
     *
     * @param id Id of the environment to edit
     */
    public static Result edit(Long id) {
        Form<Environment> environmentForm = form(Environment.class).fill(
            Environment.find.byId(id)
        );
        return ok(
            editForm.render(id, environmentForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the environment to edit
     */
    public static Result update(Long id) {
        Form<Environment> environmentForm = form(Environment.class).bindFromRequest();
        if(environmentForm.hasErrors()) {
            return badRequest(editForm.render(id, environmentForm));
        }
        environmentForm.get().update(id);
        flash("success", "Environment " + environmentForm.get().name + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new environment form'.
     */
    public static Result create() {
        Form<Environment> environmentForm = form(Environment.class);
        return ok(
            createForm.render(environmentForm)
        );
    }
    
    /**
     * Handle the 'new environment form' submission 
     */
    public static Result save() {
        Form<Environment> environmentForm = form(Environment.class).bindFromRequest();
        if(environmentForm.hasErrors()) {
            return badRequest(createForm.render(environmentForm));
        }
        environmentForm.get().save();
        flash("success", "Environment " + environmentForm.get().name + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle environment deletion
     */
    public static Result delete(Long id) {
        Environment.find.ref(id).delete();
        flash("success", "Environment a été supprimée");
        return GO_HOME;
    }
    

}
            
