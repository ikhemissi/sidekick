package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.treatments.*;

import models.*;

/**
 * Manage a database of treatments
 */
public class Treatments extends Controller {
    
	/**
     * This result directly redirect to treatment home.
     */
    public static Result GO_HOME = redirect(
        routes.Treatments.list(0, "code", "asc", "")
    );

    /**
     * Afficher la liste des "Treatment"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on treatment names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Treatment.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Treatment.
     *
     * @param id Id of the treatment to edit
     */
    public static Result edit(Long id) {
        Form<Treatment> treatmentForm = form(Treatment.class).fill(
            Treatment.find.byId(id)
        );
        return ok(
            editForm.render(id, treatmentForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the treatment to edit
     */
    public static Result update(Long id) {
        Form<Treatment> treatmentForm = form(Treatment.class).bindFromRequest();
        if(treatmentForm.hasErrors()) {
            return badRequest(editForm.render(id, treatmentForm));
        }
        treatmentForm.get().update(id);
        flash("success", "Treatment " + treatmentForm.get().code + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new treatment form'.
     */
    public static Result create() {
        Form<Treatment> treatmentForm = form(Treatment.class);
        return ok(
            createForm.render(treatmentForm)
        );
    }
    
    /**
     * Handle the 'new treatment form' submission 
     */
    public static Result save() {
        Form<Treatment> treatmentForm = form(Treatment.class).bindFromRequest();
        if(treatmentForm.hasErrors()) {
            return badRequest(createForm.render(treatmentForm));
        }
        treatmentForm.get().save();
        flash("success", "Treatment " + treatmentForm.get().code + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle treatment deletion
     */
    public static Result delete(Long id) {
        Treatment.find.ref(id).delete();
        flash("success", "Treatment a été supprimée");
        return GO_HOME;
    }
    

}
            
