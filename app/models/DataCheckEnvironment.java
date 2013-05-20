package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * DataCheckEnvironment entity managed by Ebean
 */
@Entity 
public class DataCheckEnvironment extends Model {

	@Id
    public Long id;
	
	@Constraints.Required
	@ManyToOne
	private Environment environment;
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date time;
	
	public short status;
	
	public String error;
    
    /**
     * Generic query helper for entity DataCheckEnvironment with id Long
     */
    public static Finder<Long,DataCheckEnvironment> find = new Finder<Long,DataCheckEnvironment>(Long.class, DataCheckEnvironment.class); 
	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<DataCheckEnvironment> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }
}
