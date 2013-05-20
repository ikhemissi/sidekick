package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.datas.*;

import models.*;

/**
 * Manage a database of datas
 */
public class Datas extends Controller {
    
	/**
     * This result directly redirect to data home.
     */
    public static Result GO_HOME = redirect(
        routes.Datas.list(0, "id", "asc", "")
    );

    /**
     * Afficher la liste des "Data"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on data names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Data.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Data.
     *
     * @param id Id of the data to edit
     */
    public static Result edit(Long id) {
        Form<Data> dataForm = form(Data.class).fill(
            Data.find.byId(id)
        );
        return ok(
            editForm.render(id, dataForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the data to edit
     */
    public static Result update(Long id) {
        Form<Data> dataForm = form(Data.class).bindFromRequest();
        if(dataForm.hasErrors()) {
            return badRequest(editForm.render(id, dataForm));
        }
        dataForm.get().update(id);
        flash("success", "Data " + dataForm.get().id + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new data form'.
     */
    public static Result create() {
        Form<Data> dataForm = form(Data.class);
        return ok(
            createForm.render(dataForm)
        );
    }
    
    /**
     * Handle the 'new data form' submission 
     */
    public static Result save() {
        Form<Data> dataForm = form(Data.class).bindFromRequest();
        if(dataForm.hasErrors()) {
            return badRequest(createForm.render(dataForm));
        }
        dataForm.get().save();
        flash("success", "Data " + dataForm.get().id + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle data deletion
     */
    public static Result delete(Long id) {
        Data.find.ref(id).delete();
        flash("success", "Data a été supprimée");
        return GO_HOME;
    }
    

}
            
