package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * EnvironmentType entity managed by Ebean
 */
@Entity 
public class EnvironmentType extends Model {

	@Id
    public Long id;
	
	@Constraints.Required
	public String name;
	
	public short rank;
	
	public String client;
	
	public String color;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<Environment> environments = new HashSet<Environment>();
	    
    /**
     * Generic query helper for entity EnvironmentType with id Long
     */
    public static Finder<Long,EnvironmentType> find = new Finder<Long,EnvironmentType>(Long.class, EnvironmentType.class); 
	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<EnvironmentType> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }
}
