package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Data entity managed by Ebean
 */
@Entity 
public class Data extends Model {

	@Id
    public Long id;
	
	@OneToOne
	public Release release;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<Treatment> treatments = new HashSet<Treatment>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<Rubrique> rubriques = new HashSet<Rubrique>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<Entitlement> entitlements = new HashSet<Entitlement>();
	    
    /**
     * Generic query helper for entity Data with id Long
     */
    public static Finder<Long,Data> find = new Finder<Long,Data>(Long.class, Data.class); 
	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Data> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("id", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("release")
                .findPagingList(pageSize)
                .getPage(page);
    }
}
