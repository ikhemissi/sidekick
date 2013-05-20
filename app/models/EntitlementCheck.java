package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * EntitlementCheck entity managed by Ebean
 */
@Entity 
public class EntitlementCheck extends Model {

	@Id
    public Long id;
	
	@Constraints.Required
	@ManyToOne
	private Entitlement entitlement;
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
    public Date date;
	
    /**
     * Generic query helper for entity EntitlementCheck with id Long
     */
    public static Finder<Long,EntitlementCheck> find = new Finder<Long,EntitlementCheck>(Long.class, EntitlementCheck.class); 
	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<EntitlementCheck> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .orderBy(sortBy + " " + order)
                .fetch("entitlement")
                .findPagingList(pageSize)
                .getPage(page);
    }
}
