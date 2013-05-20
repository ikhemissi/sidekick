package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.datacheckenvironments.*;

import models.*;

/**
 * Manage a database of datacheckenvironments
 */
public class DataCheckEnvironments extends Controller {
    
	/**
     * This result directly redirect to datacheckenvironment home.
     */
    public static Result GO_HOME = redirect(
        routes.DataCheckEnvironments.list(0, "time", "asc", "")
    );

    /**
     * Afficher la liste des "DataCheckEnvironment"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on datacheckenvironment names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                DataCheckEnvironment.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing DataCheckEnvironment.
     *
     * @param id Id of the datacheckenvironment to edit
     */
    public static Result edit(Long id) {
        Form<DataCheckEnvironment> datacheckenvironmentForm = form(DataCheckEnvironment.class).fill(
            DataCheckEnvironment.find.byId(id)
        );
        return ok(
            editForm.render(id, datacheckenvironmentForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the datacheckenvironment to edit
     */
    public static Result update(Long id) {
        Form<DataCheckEnvironment> datacheckenvironmentForm = form(DataCheckEnvironment.class).bindFromRequest();
        if(datacheckenvironmentForm.hasErrors()) {
            return badRequest(editForm.render(id, datacheckenvironmentForm));
        }
        datacheckenvironmentForm.get().update(id);
        flash("success", "DataCheckEnvironment " + datacheckenvironmentForm.get().id + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new datacheckenvironment form'.
     */
    public static Result create() {
        Form<DataCheckEnvironment> datacheckenvironmentForm = form(DataCheckEnvironment.class);
        return ok(
            createForm.render(datacheckenvironmentForm)
        );
    }
    
    /**
     * Handle the 'new datacheckenvironment form' submission 
     */
    public static Result save() {
        Form<DataCheckEnvironment> datacheckenvironmentForm = form(DataCheckEnvironment.class).bindFromRequest();
        if(datacheckenvironmentForm.hasErrors()) {
            return badRequest(createForm.render(datacheckenvironmentForm));
        }
        datacheckenvironmentForm.get().save();
        flash("success", "DataCheckEnvironment " + datacheckenvironmentForm.get().id + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle datacheckenvironment deletion
     */
    public static Result delete(Long id) {
        DataCheckEnvironment.find.ref(id).delete();
        flash("success", "DataCheckEnvironment a été supprimée");
        return GO_HOME;
    }
    

}
            
