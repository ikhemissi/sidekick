package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.projects.*;

import models.*;

/**
 * Manage a database of projects
 */
public class Projects extends Controller {
    
	/**
     * This result directly redirect to project home.
     */
    public static Result GO_HOME = redirect(
        routes.Projects.list(0, "name", "asc", "")
    );

    /**
     * Afficher la liste des "Project"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on project names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Project.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Project.
     *
     * @param id Id of the project to edit
     */
    public static Result edit(Long id) {
        Form<Project> projectForm = form(Project.class).fill(
            Project.find.byId(id)
        );
        return ok(
            editForm.render(id, projectForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the project to edit
     */
    public static Result update(Long id) {
        Form<Project> projectForm = form(Project.class).bindFromRequest();
        if(projectForm.hasErrors()) {
            return badRequest(editForm.render(id, projectForm));
        }
        projectForm.get().update(id);
        flash("success", "Project " + projectForm.get().name + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new project form'.
     */
    public static Result create() {
        Form<Project> projectForm = form(Project.class);
        return ok(
            createForm.render(projectForm)
        );
    }
    
    /**
     * Handle the 'new project form' submission 
     */
    public static Result save() {
        Form<Project> projectForm = form(Project.class).bindFromRequest();
        if(projectForm.hasErrors()) {
            return badRequest(createForm.render(projectForm));
        }
        projectForm.get().save();
        flash("success", "Project " + projectForm.get().name + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle project deletion
     */
    public static Result delete(Long id) {
        Project.find.ref(id).delete();
        flash("success", "Project a été supprimée");
        return GO_HOME;
    }
    

}
            
