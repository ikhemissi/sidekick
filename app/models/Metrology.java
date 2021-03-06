package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Metrology entity managed by Ebean
 */
@Entity 
public class Metrology extends Model {

	@Id
    public Long id;
	
	@ManyToOne
	private Environment environment;
	
	public String url;
	
	public String name;
	
	public String _type;
	
	public String path;
	
	public String description;
	    
    /**
     * Generic query helper for entity Metrology with id Long
     */
    public static Finder<Long,Metrology> find = new Finder<Long,Metrology>(Long.class, Metrology.class); 
	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Metrology> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("environment")
                .findPagingList(pageSize)
                .getPage(page);
    }
}
