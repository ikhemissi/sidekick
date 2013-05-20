package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.users.*;

import models.*;

/**
 * Manage a database of users
 */
public class Users extends Controller {
    
	/**
     * This result directly redirect to user home.
     */
    public static Result GO_HOME = redirect(
        routes.Users.list(0, "name", "asc", "")
    );

    /**
     * Afficher la liste des "User"
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on user names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                User.page(page, 20, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing User.
     *
     * @param id Id of the user to edit
     */
    public static Result edit(Long id) {
        Form<User> userForm = form(User.class).fill(
            User.find.byId(id)
        );
        return ok(
            editForm.render(id, userForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the user to edit
     */
    public static Result update(Long id) {
        Form<User> userForm = form(User.class).bindFromRequest();
        if(userForm.hasErrors()) {
            return badRequest(editForm.render(id, userForm));
        }
        userForm.get().update(id);
        flash("success", "User " + userForm.get().name + " a été mise à jour");
        return GO_HOME;
    }
    
    /**
     * Display the 'new user form'.
     */
    public static Result create() {
        Form<User> userForm = form(User.class);
        return ok(
            createForm.render(userForm)
        );
    }
    
    /**
     * Handle the 'new user form' submission 
     */
    public static Result save() {
        Form<User> userForm = form(User.class).bindFromRequest();
        if(userForm.hasErrors()) {
            return badRequest(createForm.render(userForm));
        }
        userForm.get().save();
        flash("success", "User " + userForm.get().name + " a été créé");
        return GO_HOME;
    }
    
    /**
     * Handle user deletion
     */
    public static Result delete(Long id) {
        User.find.ref(id).delete();
        flash("success", "User a été supprimée");
        return GO_HOME;
    }
    

}
            
