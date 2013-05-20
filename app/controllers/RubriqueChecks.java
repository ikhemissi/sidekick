package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.rubriquechecks.*;

import models.*;

/**
 * Manage a database of rubriquechecks
 */
public class RubriqueChecks extends Controller {
    
	/**
     * This result directly redirect to rubriquecheck home.
     */
    public static Result GO_HOME = redirect(
        routes.RubriqueChecks.list(0, "date", "asc", "")
    );

    /**
     * Afficher la liste des "RubriqueCheck"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on rubriquecheck names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                RubriqueCheck.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing RubriqueCheck.
     *
     * @param id Id of the rubriquecheck to edit
     */
    public static Result edit(Long id) {
        Form<RubriqueCheck> rubriquecheckForm = form(RubriqueCheck.class).fill(
            RubriqueCheck.find.byId(id)
        );
        return ok(
            editForm.render(id, rubriquecheckForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the rubriquecheck to edit
     */
    public static Result update(Long id) {
        Form<RubriqueCheck> rubriquecheckForm = form(RubriqueCheck.class).bindFromRequest();
        if(rubriquecheckForm.hasErrors()) {
            return badRequest(editForm.render(id, rubriquecheckForm));
        }
        rubriquecheckForm.get().update(id);
        flash("success", "RubriqueCheck " + rubriquecheckForm.get().id + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new rubriquecheck form'.
     */
    public static Result create() {
        Form<RubriqueCheck> rubriquecheckForm = form(RubriqueCheck.class);
        return ok(
            createForm.render(rubriquecheckForm)
        );
    }
    
    /**
     * Handle the 'new rubriquecheck form' submission 
     */
    public static Result save() {
        Form<RubriqueCheck> rubriquecheckForm = form(RubriqueCheck.class).bindFromRequest();
        if(rubriquecheckForm.hasErrors()) {
            return badRequest(createForm.render(rubriquecheckForm));
        }
        rubriquecheckForm.get().save();
        flash("success", "RubriqueCheck " + rubriquecheckForm.get().id + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle rubriquecheck deletion
     */
    public static Result delete(Long id) {
        RubriqueCheck.find.ref(id).delete();
        flash("success", "RubriqueCheck a été supprimée");
        return GO_HOME;
    }
    

}
            
