package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * TreatmentCheck entity managed by Ebean
 */
@Entity 
public class TreatmentCheck extends Model {

	@Id
    public Long id;
	
	@ManyToOne
	public Treatment treatment;
    
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date date;
	
    /**
     * Generic query helper for entity TreatmentCheck with id Long
     */
    public static Finder<Long,TreatmentCheck> find = new Finder<Long,TreatmentCheck>(Long.class, TreatmentCheck.class); 
        	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<TreatmentCheck> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("date", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("treatment")
                .findPagingList(pageSize)
                .getPage(page);
    }

}
