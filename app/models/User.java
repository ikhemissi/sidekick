package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * User entity managed by Ebean
 */
@Entity 
public class User extends Model {

	@Id
    public Long id;
	
	@Constraints.Required
	public String profile;
	
	public String name;
	
	public String password;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public List<ProjectExport> exports = new ArrayList<ProjectExport>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public List<Treatment> createdTreatments = new ArrayList<Treatment>();
	
	@ManyToMany
	public List<Treatment> modifiedTreatments = new ArrayList<Treatment>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public List<Entitlement> createdEntitlements = new ArrayList<Entitlement>();
	
	@ManyToMany
	public List<Entitlement> modifiedEntitlements = new ArrayList<Entitlement>();
	    
    /**
     * Generic query helper for entity User with id Long
     */
    public static Finder<Long,User> find = new Finder<Long,User>(Long.class, User.class); 
            	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<User> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }
}
