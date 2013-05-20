package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.backends.*;

import models.*;

/**
 * Manage a database of backends
 */
public class Backends extends Controller {
    
	/**
     * This result directly redirect to backend home.
     */
    public static Result GO_HOME = redirect(
        routes.Backends.list(0, "name", "asc", "")
    );

    /**
     * Afficher la liste des "Backend"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on backend names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Backend.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Backend.
     *
     * @param id Id of the backend to edit
     */
    public static Result edit(Long id) {
        Form<Backend> backendForm = form(Backend.class).fill(
            Backend.find.byId(id)
        );
        return ok(
            editForm.render(id, backendForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the backend to edit
     */
    public static Result update(Long id) {
        Form<Backend> backendForm = form(Backend.class).bindFromRequest();
        if(backendForm.hasErrors()) {
            return badRequest(editForm.render(id, backendForm));
        }
        backendForm.get().update(id);
        flash("success", "Backend " + backendForm.get().name + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new backend form'.
     */
    public static Result create() {
        Form<Backend> backendForm = form(Backend.class);
        return ok(
            createForm.render(backendForm)
        );
    }
    
    /**
     * Handle the 'new backend form' submission 
     */
    public static Result save() {
        Form<Backend> backendForm = form(Backend.class).bindFromRequest();
        if(backendForm.hasErrors()) {
            return badRequest(createForm.render(backendForm));
        }
        backendForm.get().save();
        flash("success", "Backend " + backendForm.get().name + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle backend deletion
     */
    public static Result delete(Long id) {
        Backend.find.ref(id).delete();
        flash("success", "Backend a été supprimée");
        return GO_HOME;
    }
    

}
            
