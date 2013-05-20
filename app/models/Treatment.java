package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Treatment entity managed by Ebean
 */
@Entity 
public class Treatment extends Model {

	@Id
    public Long id;
	
	@ManyToOne
	private User createdBy;
	
	@Constraints.Required
	@ManyToOne
	public Application application;
	
	@Constraints.Required
	public String code;
	
	public boolean menu;
	
	public boolean active;
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date creation;
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date modification;
	
	public short versioning = 0;
	
	public List<String> operations;
	
	@ManyToMany
	public Set<Data> releaseData = new HashSet<Data>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<TreatmentCheck> checks = new HashSet<TreatmentCheck>();
	
	@ManyToMany
	public List<User> modifiedBy = new ArrayList<User>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<Entitlement> entitlements = new HashSet<Entitlement>();
	    
    /**
     * Generic query helper for entity Treatment with id Long
     */
    public static Finder<Long,Treatment> find = new Finder<Long,Treatment>(Long.class, Treatment.class); 
        	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Treatment> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("code", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("application")
                .findPagingList(pageSize)
                .getPage(page);
    }
}
