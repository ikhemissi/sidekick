package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Entitlement entity managed by Ebean
 */
@Entity 
public class Entitlement  extends Model {

	@Id
    public Long id;
	
	@Constraints.Required
	@ManyToOne
	private Treatment treatment;
	
	@ManyToMany
	public List<User> modifiedBy = new ArrayList<User>();
	
	@ManyToMany
	public List<EntitlementGroupApplication> applications = new ArrayList<EntitlementGroupApplication>();
	
	@Constraints.Required
	public String operation;
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date creation;
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date modification;
	
	public short versioning = 0;
	
	@ManyToMany
	public Set<Data> releaseData = new HashSet<Data>();
	
	@ManyToOne
	private User createdBy;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<EntitlementCheck> check = new HashSet<EntitlementCheck>();
	    
    /**
     * Generic query helper for entity Entitlement with id Long
     */
    public static Finder<Long,Entitlement> find = new Finder<Long,Entitlement>(Long.class, Entitlement.class); 
	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Entitlement> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .orderBy(sortBy + " " + order)
                .fetch("treatment")
                .findPagingList(pageSize)
                .getPage(page);
    }
}
