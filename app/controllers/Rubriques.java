package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.rubriques.*;

import models.*;

/**
 * Manage a database of rubriques
 */
public class Rubriques extends Controller {
    
	/**
     * This result directly redirect to rubrique home.
     */
    public static Result GO_HOME = redirect(
        routes.Rubriques.list(0, "code", "asc", "")
    );

    /**
     * Afficher la liste des "Rubrique"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on rubrique names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Rubrique.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Rubrique.
     *
     * @param id Id of the rubrique to edit
     */
    public static Result edit(Long id) {
        Form<Rubrique> rubriqueForm = form(Rubrique.class).fill(
            Rubrique.find.byId(id)
        );
        return ok(
            editForm.render(id, rubriqueForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the rubrique to edit
     */
    public static Result update(Long id) {
        Form<Rubrique> rubriqueForm = form(Rubrique.class).bindFromRequest();
        if(rubriqueForm.hasErrors()) {
            return badRequest(editForm.render(id, rubriqueForm));
        }
        rubriqueForm.get().update(id);
        flash("success", "Rubrique " + rubriqueForm.get().code + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new rubrique form'.
     */
    public static Result create() {
        Form<Rubrique> rubriqueForm = form(Rubrique.class);
        return ok(
            createForm.render(rubriqueForm)
        );
    }
    
    /**
     * Handle the 'new rubrique form' submission 
     */
    public static Result save() {
        Form<Rubrique> rubriqueForm = form(Rubrique.class).bindFromRequest();
        if(rubriqueForm.hasErrors()) {
            return badRequest(createForm.render(rubriqueForm));
        }
        rubriqueForm.get().save();
        flash("success", "Rubrique " + rubriqueForm.get().code + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle rubrique deletion
     */
    public static Result delete(Long id) {
        Rubrique.find.ref(id).delete();
        flash("success", "Rubrique a été supprimée");
        return GO_HOME;
    }
    

}
            
