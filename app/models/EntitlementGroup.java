package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * EntitlementGroup entity managed by Ebean
 */
@Entity 
public class EntitlementGroup extends Model {

	@Id
    public Long id;
	
	@Constraints.Required
	public String name;
	
	public short versioning = 0;
	
	public List<String> users;
	
	@ManyToMany
	public Set<EntitlementGroupApplication> entitlementsByApplication = new HashSet<EntitlementGroupApplication>();
	    
    /**
     * Generic query helper for entity EntitlementGroup with id Long
     */
    public static Finder<Long,EntitlementGroup> find = new Finder<Long,EntitlementGroup>(Long.class, EntitlementGroup.class); 
	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<EntitlementGroup> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }
}
