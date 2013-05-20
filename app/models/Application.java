package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Application entity managed by Ebean
 */
@Entity 
public class Application extends Model {

	@Id
    public Long id;
	
	@Constraints.Required
	public String code;
	
	public String client;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<Treatment> treatments = new HashSet<Treatment>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<EntitlementGroup> entitlementGroups = new HashSet<EntitlementGroup>();
    
    /**
     * Generic query helper for entity Application with id Long
     */
    public static Finder<Long,Application> find = new Finder<Long,Application>(Long.class, Application.class); 
	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the code column
     */
    public static Page<Application> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("code", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }
}
