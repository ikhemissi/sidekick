package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.versions.*;

import models.*;

/**
 * Manage a database of versions
 */
public class Versions extends Controller {
    
	/**
     * This result directly redirect to version home.
     */
    public static Result GO_HOME = redirect(
        routes.Versions.list(0, "dueDate", "desc", "")
    );

    /**
     * Afficher la liste des "Version"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on version names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Version.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Version.
     *
     * @param id Id of the version to edit
     */
    public static Result edit(Long id) {
        Form<Version> versionForm = form(Version.class).fill(
            Version.find.byId(id)
        );
        return ok(
            editForm.render(id, versionForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the version to edit
     */
    public static Result update(Long id) {
        Form<Version> versionForm = form(Version.class).bindFromRequest();
        if(versionForm.hasErrors()) {
            return badRequest(editForm.render(id, versionForm));
        }
        versionForm.get().update(id);
        flash("success", "Version " + versionForm.get().name + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new version form'.
     */
    public static Result create() {
        Form<Version> versionForm = form(Version.class);
        return ok(
            createForm.render(versionForm)
        );
    }
    
    /**
     * Handle the 'new version form' submission 
     */
    public static Result save() {
        Form<Version> versionForm = form(Version.class).bindFromRequest();
        if(versionForm.hasErrors()) {
            return badRequest(createForm.render(versionForm));
        }
        versionForm.get().save();
        flash("success", "Version " + versionForm.get().name + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle version deletion
     */
    public static Result delete(Long id) {
        Version.find.ref(id).delete();
        flash("success", "Version a été supprimée");
        return GO_HOME;
    }
    

}
            
