package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.projectexports.*;

import models.*;

/**
 * Manage a database of projectexports
 */
public class ProjectExports extends Controller {
    
	/**
     * This result directly redirect to projectexport home.
     */
    public static Result GO_HOME = redirect(
        routes.ProjectExports.list(0, "num", "asc", "")
    );

    /**
     * Afficher la liste des "ProjectExport"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on projectexport names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                ProjectExport.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing ProjectExport.
     *
     * @param id Id of the projectexport to edit
     */
    public static Result edit(Long id) {
        Form<ProjectExport> projectexportForm = form(ProjectExport.class).fill(
            ProjectExport.find.byId(id)
        );
        return ok(
            editForm.render(id, projectexportForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the projectexport to edit
     */
    public static Result update(Long id) {
        Form<ProjectExport> projectexportForm = form(ProjectExport.class).bindFromRequest();
        if(projectexportForm.hasErrors()) {
            return badRequest(editForm.render(id, projectexportForm));
        }
        projectexportForm.get().update(id);
        flash("success", "ProjectExport " + projectexportForm.get().num + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new projectexport form'.
     */
    public static Result create() {
        Form<ProjectExport> projectexportForm = form(ProjectExport.class);
        return ok(
            createForm.render(projectexportForm)
        );
    }
    
    /**
     * Handle the 'new projectexport form' submission 
     */
    public static Result save() {
        Form<ProjectExport> projectexportForm = form(ProjectExport.class).bindFromRequest();
        if(projectexportForm.hasErrors()) {
            return badRequest(createForm.render(projectexportForm));
        }
        projectexportForm.get().save();
        flash("success", "ProjectExport " + projectexportForm.get().num + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle projectexport deletion
     */
    public static Result delete(Long id) {
        ProjectExport.find.ref(id).delete();
        flash("success", "ProjectExport a été supprimée");
        return GO_HOME;
    }
    

}
            
