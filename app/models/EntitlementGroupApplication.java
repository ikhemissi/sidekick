package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * EntitlementGroupApplication entity managed by Ebean
 */
@Entity 
public class EntitlementGroupApplication extends Model {

	@Id
    public Long id;
	
	@Constraints.Required
	@ManyToOne
	private Application application;
	
	@Constraints.Required
	public String name;
	
	public short versioning = 0;
	
	@ManyToMany
	public Set<EntitlementGroup> group = new HashSet<EntitlementGroup>();
	
	@ManyToMany
	public Set<Entitlement> entitlements = new HashSet<Entitlement>();
    
    /**
     * Generic query helper for entity EntitlementGroupApplication with id Long
     */
    public static Finder<Long,EntitlementGroupApplication> find = new Finder<Long,EntitlementGroupApplication>(Long.class, EntitlementGroupApplication.class); 
	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<EntitlementGroupApplication> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("application")
                .findPagingList(pageSize)
                .getPage(page);
    }
}
