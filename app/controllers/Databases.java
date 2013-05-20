package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.databases.*;

import models.*;

/**
 * Manage a database of databases
 */
public class Databases extends Controller {
    
	/**
     * This result directly redirect to database home.
     */
    public static Result GO_HOME = redirect(
        routes.Databases.list(0, "name", "asc", "")
    );

    /**
     * Afficher la liste des "Database"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on database names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Database.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Database.
     *
     * @param id Id of the database to edit
     */
    public static Result edit(Long id) {
        Form<Database> databaseForm = form(Database.class).fill(
            Database.find.byId(id)
        );
        return ok(
            editForm.render(id, databaseForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the database to edit
     */
    public static Result update(Long id) {
        Form<Database> databaseForm = form(Database.class).bindFromRequest();
        if(databaseForm.hasErrors()) {
            return badRequest(editForm.render(id, databaseForm));
        }
        databaseForm.get().update(id);
        flash("success", "Database " + databaseForm.get().name + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new database form'.
     */
    public static Result create() {
        Form<Database> databaseForm = form(Database.class);
        return ok(
            createForm.render(databaseForm)
        );
    }
    
    /**
     * Handle the 'new database form' submission 
     */
    public static Result save() {
        Form<Database> databaseForm = form(Database.class).bindFromRequest();
        if(databaseForm.hasErrors()) {
            return badRequest(createForm.render(databaseForm));
        }
        databaseForm.get().save();
        flash("success", "Database " + databaseForm.get().name + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle database deletion
     */
    public static Result delete(Long id) {
        Database.find.ref(id).delete();
        flash("success", "Database a été supprimée");
        return GO_HOME;
    }
    

}
            
