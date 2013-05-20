package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.treatmentchecks.*;

import models.*;

/**
 * Manage a database of treatmentchecks
 */
public class TreatmentChecks extends Controller {
    
	/**
     * This result directly redirect to treatmentcheck home.
     */
    public static Result GO_HOME = redirect(
        routes.TreatmentChecks.list(0, "date", "asc", "")
    );

    /**
     * Afficher la liste des "TreatmentCheck"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on treatmentcheck names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                TreatmentCheck.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing TreatmentCheck.
     *
     * @param id Id of the treatmentcheck to edit
     */
    public static Result edit(Long id) {
        Form<TreatmentCheck> treatmentcheckForm = form(TreatmentCheck.class).fill(
            TreatmentCheck.find.byId(id)
        );
        return ok(
            editForm.render(id, treatmentcheckForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the treatmentcheck to edit
     */
    public static Result update(Long id) {
        Form<TreatmentCheck> treatmentcheckForm = form(TreatmentCheck.class).bindFromRequest();
        if(treatmentcheckForm.hasErrors()) {
            return badRequest(editForm.render(id, treatmentcheckForm));
        }
        treatmentcheckForm.get().update(id);
        flash("success", "TreatmentCheck " + treatmentcheckForm.get().id + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new treatmentcheck form'.
     */
    public static Result create() {
        Form<TreatmentCheck> treatmentcheckForm = form(TreatmentCheck.class);
        return ok(
            createForm.render(treatmentcheckForm)
        );
    }
    
    /**
     * Handle the 'new treatmentcheck form' submission 
     */
    public static Result save() {
        Form<TreatmentCheck> treatmentcheckForm = form(TreatmentCheck.class).bindFromRequest();
        if(treatmentcheckForm.hasErrors()) {
            return badRequest(createForm.render(treatmentcheckForm));
        }
        treatmentcheckForm.get().save();
        flash("success", "TreatmentCheck " + treatmentcheckForm.get().id + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle treatmentcheck deletion
     */
    public static Result delete(Long id) {
        TreatmentCheck.find.ref(id).delete();
        flash("success", "TreatmentCheck a été supprimée");
        return GO_HOME;
    }
    

}
            
