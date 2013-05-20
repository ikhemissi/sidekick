package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * RubriqueCheck entity managed by Ebean
 */
@Entity 
public class RubriqueCheck extends Model {

	@Id
    public Long id;
	
	@ManyToOne
	private Rubrique rubrique;
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
    public Date date;
	    
    /**
     * Generic query helper for entity RubriqueCheck with id Long
     */
    public static Finder<Long,RubriqueCheck> find = new Finder<Long,RubriqueCheck>(Long.class, RubriqueCheck.class); 
    	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<RubriqueCheck> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("date", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("rubrique")
                .findPagingList(pageSize)
                .getPage(page);
    }
}
