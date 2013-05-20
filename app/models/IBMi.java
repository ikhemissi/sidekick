package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * IBMi entity managed by Ebean
 */
@Entity 
public class IBMi extends Model {

	@Id
    public Long id;
	
	@Constraints.Required
	public String name;
	
	public String iasp;
	
	public List<String> libraries;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<Environment> environment = new HashSet<Environment>();
	
	@OneToOne
	public Database database;
	    
    /**
     * Generic query helper for entity IBMi with id Long
     */
    public static Finder<Long,IBMi> find = new Finder<Long,IBMi>(Long.class, IBMi.class); 
	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<IBMi> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }
}
