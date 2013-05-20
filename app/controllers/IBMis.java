package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.ibmis.*;

import models.*;

/**
 * Manage a database of ibmis
 */
public class IBMis extends Controller {
    
	/**
     * This result directly redirect to ibmi home.
     */
    public static Result GO_HOME = redirect(
        routes.IBMis.list(0, "name", "asc", "")
    );

    /**
     * Afficher la liste des "IBMi"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on ibmi names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                IBMi.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing IBMi.
     *
     * @param id Id of the ibmi to edit
     */
    public static Result edit(Long id) {
        Form<IBMi> ibmiForm = form(IBMi.class).fill(
            IBMi.find.byId(id)
        );
        return ok(
            editForm.render(id, ibmiForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the ibmi to edit
     */
    public static Result update(Long id) {
        Form<IBMi> ibmiForm = form(IBMi.class).bindFromRequest();
        if(ibmiForm.hasErrors()) {
            return badRequest(editForm.render(id, ibmiForm));
        }
        ibmiForm.get().update(id);
        flash("success", "IBMi " + ibmiForm.get().name + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new ibmi form'.
     */
    public static Result create() {
        Form<IBMi> ibmiForm = form(IBMi.class);
        return ok(
            createForm.render(ibmiForm)
        );
    }
    
    /**
     * Handle the 'new ibmi form' submission 
     */
    public static Result save() {
        Form<IBMi> ibmiForm = form(IBMi.class).bindFromRequest();
        if(ibmiForm.hasErrors()) {
            return badRequest(createForm.render(ibmiForm));
        }
        ibmiForm.get().save();
        flash("success", "IBMi " + ibmiForm.get().name + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle ibmi deletion
     */
    public static Result delete(Long id) {
        IBMi.find.ref(id).delete();
        flash("success", "IBMi a été supprimée");
        return GO_HOME;
    }
    

}
            
