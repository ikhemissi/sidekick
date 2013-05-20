package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Database entity managed by Ebean
 */
@Entity 
public class Database extends Model {

	@Id
    public Long id;
	
	@Constraints.Required
	@OneToOne
	private IBMi logicServer;
	
	@Constraints.Required
	public String name;
	
	@Constraints.Required
	public String url;
	
	public String user;
	
	public String password;
	
	public boolean enabled = false;
	    
    /**
     * Generic query helper for entity Database with id Long
     */
    public static Finder<Long,Database> find = new Finder<Long,Database>(Long.class, Database.class); 
	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Database> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("logicServer")
                .findPagingList(pageSize)
                .getPage(page);
    }
}
