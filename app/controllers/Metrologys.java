package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.metrologys.*;

import models.*;

/**
 * Manage a database of metrologys
 */
public class Metrologys extends Controller {
    
	/**
     * This result directly redirect to metrology home.
     */
    public static Result GO_HOME = redirect(
        routes.Metrologys.list(0, "name", "asc", "")
    );

    /**
     * Afficher la liste des "Metrology"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on metrology names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Metrology.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Metrology.
     *
     * @param id Id of the metrology to edit
     */
    public static Result edit(Long id) {
        Form<Metrology> metrologyForm = form(Metrology.class).fill(
            Metrology.find.byId(id)
        );
        return ok(
            editForm.render(id, metrologyForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the metrology to edit
     */
    public static Result update(Long id) {
        Form<Metrology> metrologyForm = form(Metrology.class).bindFromRequest();
        if(metrologyForm.hasErrors()) {
            return badRequest(editForm.render(id, metrologyForm));
        }
        metrologyForm.get().update(id);
        flash("success", "Metrology " + metrologyForm.get().name + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new metrology form'.
     */
    public static Result create() {
        Form<Metrology> metrologyForm = form(Metrology.class);
        return ok(
            createForm.render(metrologyForm)
        );
    }
    
    /**
     * Handle the 'new metrology form' submission 
     */
    public static Result save() {
        Form<Metrology> metrologyForm = form(Metrology.class).bindFromRequest();
        if(metrologyForm.hasErrors()) {
            return badRequest(createForm.render(metrologyForm));
        }
        metrologyForm.get().save();
        flash("success", "Metrology " + metrologyForm.get().name + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle metrology deletion
     */
    public static Result delete(Long id) {
        Metrology.find.ref(id).delete();
        flash("success", "Metrology a été supprimée");
        return GO_HOME;
    }
    

}
            
