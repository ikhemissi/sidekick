package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.releases.*;

import models.*;

/**
 * Manage a database of releases
 */
public class Releases extends Controller {
    
	/**
     * This result directly redirect to release home.
     */
    public static Result GO_HOME = redirect(
        routes.Releases.list(0, "title", "asc", "")
    );

    /**
     * Afficher la liste des "Release"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on release names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Release.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Release.
     *
     * @param id Id of the release to edit
     */
    public static Result edit(Long id) {
        Form<Release> releaseForm = form(Release.class).fill(
            Release.find.byId(id)
        );
        return ok(
            editForm.render(id, releaseForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the release to edit
     */
    public static Result update(Long id) {
        Form<Release> releaseForm = form(Release.class).bindFromRequest();
        if(releaseForm.hasErrors()) {
            return badRequest(editForm.render(id, releaseForm));
        }
        releaseForm.get().update(id);
        flash("success", "Release " + releaseForm.get().title + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new release form'.
     */
    public static Result create() {
        Form<Release> releaseForm = form(Release.class);
        return ok(
            createForm.render(releaseForm)
        );
    }
	
	    /**
     * Display the 'new release form'.
     */
    public static Result createInVersion(Long versionId) {
        Form<Release> releaseForm = form(Release.class).fill(new Release(versionId));
        return ok(
            createForm.render(releaseForm)
        );
    }
    
    /**
     * Handle the 'new release form' submission 
     */
    public static Result save() {
        Form<Release> releaseForm = form(Release.class).bindFromRequest();
        if(releaseForm.hasErrors()) {
            return badRequest(createForm.render(releaseForm));
        }
        releaseForm.get().save();
        flash("success", "Release " + releaseForm.get().title + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle release deletion
     */
    public static Result delete(Long id) {
        Release.find.ref(id).delete();
        flash("success", "Release a été supprimée");
        return GO_HOME;
    }
    

}
            
