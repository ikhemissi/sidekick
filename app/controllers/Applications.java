package controllers;

import java.util.*;

import models.Application;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.applications.*;
import models.*;


/**
 * Manage a database of applications
 */
public class Applications extends Controller {
    
	/**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.Applications.list(0, "code", "asc", "")
    );

    /**
     * Afficher la liste des "Application"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on application names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Application.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Application.
     *
     * @param id Id of the application to edit
     */
    public static Result edit(Long id) {
        Form<Application> applicationForm = form(Application.class).fill(
            Application.find.byId(id)
        );
        return ok(
            editForm.render(id, applicationForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the application to edit
     */
    public static Result update(Long id) {
        Form<Application> applicationForm = form(Application.class).bindFromRequest();
        if(applicationForm.hasErrors()) {
            return badRequest(editForm.render(id, applicationForm));
        }
        applicationForm.get().update(id);
        flash("success", "Application " + applicationForm.get().code + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new application form'.
     */
    public static Result create() {
        Form<Application> applicationForm = form(Application.class);
        return ok(
            createForm.render(applicationForm)
        );
    }
    
    /**
     * Handle the 'new application form' submission 
     */
    public static Result save() {
        Form<Application> applicationForm = form(Application.class).bindFromRequest();
        if(applicationForm.hasErrors()) {
            return badRequest(createForm.render(applicationForm));
        }
        applicationForm.get().save();
        flash("success", "Application " + applicationForm.get().code + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle application deletion
     */
    public static Result delete(Long id) {
        Application.find.ref(id).delete();
        flash("success", "Application a été supprimée");
        return GO_HOME;
    }
    

}
            
