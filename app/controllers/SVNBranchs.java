package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.svnbranchs.*;

import models.*;

/**
 * Manage a database of svnbranchs
 */
public class SVNBranchs extends Controller {
    
	/**
     * This result directly redirect to svnbranch home.
     */
    public static Result GO_HOME = redirect(
        routes.SVNBranchs.list(0, "root", "asc", "")
    );

    /**
     * Afficher la liste des "SVNBranch"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on svnbranch names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                SVNBranch.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing SVNBranch.
     *
     * @param id Id of the svnbranch to edit
     */
    public static Result edit(Long id) {
        Form<SVNBranch> svnbranchForm = form(SVNBranch.class).fill(
            SVNBranch.find.byId(id)
        );
        return ok(
            editForm.render(id, svnbranchForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the svnbranch to edit
     */
    public static Result update(Long id) {
        Form<SVNBranch> svnbranchForm = form(SVNBranch.class).bindFromRequest();
        if(svnbranchForm.hasErrors()) {
            return badRequest(editForm.render(id, svnbranchForm));
        }
        svnbranchForm.get().update(id);
        flash("success", "SVNBranch " + svnbranchForm.get().root + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new svnbranch form'.
     */
    public static Result create() {
        Form<SVNBranch> svnbranchForm = form(SVNBranch.class);
        return ok(
            createForm.render(svnbranchForm)
        );
    }
    
    /**
     * Handle the 'new svnbranch form' submission 
     */
    public static Result save() {
        Form<SVNBranch> svnbranchForm = form(SVNBranch.class).bindFromRequest();
        if(svnbranchForm.hasErrors()) {
            return badRequest(createForm.render(svnbranchForm));
        }
        svnbranchForm.get().save();
        flash("success", "SVNBranch " + svnbranchForm.get().root + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle svnbranch deletion
     */
    public static Result delete(Long id) {
        SVNBranch.find.ref(id).delete();
        flash("success", "SVNBranch a été supprimée");
        return GO_HOME;
    }
    

}
            
