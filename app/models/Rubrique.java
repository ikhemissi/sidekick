package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Rubrique entity managed by Ebean
 */
@Entity 
public class Rubrique extends Model {

	@Id
    public Long id;
	
	@Constraints.Required
	public String code;
	
	public String pays;
	
	public String invite;
	
	public short versioning = 0;
	
	@ManyToMany
	public Set<Data> releaseData = new HashSet<Data>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<RubriqueCheck> check = new HashSet<RubriqueCheck>();
    
    /**
     * Generic query helper for entity Rubrique with id Long
     */
    public static Finder<Long,Rubrique> find = new Finder<Long,Rubrique>(Long.class, Rubrique.class); 
    	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Rubrique> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("code", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }
}
