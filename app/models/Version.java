package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Version entity managed by Ebean
 */
@Entity 
public class Version extends Model {

	@Id
    public Long id;
	
	@OneToOne
	private SVNBranch svnBranch;
	
	@Constraints.Required
	public String code;
	
	public String name;
	
	public String description;
	
	@Formats.DateTime(pattern="yyyy-MM-dd")
	public Date dueDate;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public List<Release> releases = new ArrayList<Release>();
	
	public Version() {
		// Date par défaut de la version (dans 3 mois)
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.MONTH, 3);
		this.dueDate = calendar.getTime();
	}
	    
    /**
     * Generic query helper for entity Version with id Long
     */
    public static Finder<Long,Version> find = new Finder<Long,Version>(Long.class, Version.class); 
                	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Version> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }
	
	
	public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Version version: Version.find.orderBy("dueDate desc").findList()) {
            options.put(version.id.toString(), version.code);
        }
        return options;
    }
}
