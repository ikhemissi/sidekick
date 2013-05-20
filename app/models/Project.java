package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Project entity managed by Ebean
 */
@Entity 
public class Project extends Model {

	@Id
    public Long id;
	
	public String name;
	
	@Constraints.Required
	public short _type;
	
	@Constraints.Required
	public String exportname;
	
	public boolean restart = false;
	
	public String svnroot;
	
	public String projectname;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public List<ProjectExport> exports = new ArrayList<ProjectExport>();
	    
    /**
     * Generic query helper for entity Project with id Long
     */
    public static Finder<Long,Project> find = new Finder<Long,Project>(Long.class, Project.class); 
    	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Project> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }
}
