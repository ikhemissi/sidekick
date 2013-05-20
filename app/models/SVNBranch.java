package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * SVNBranch entity managed by Ebean
 */
@Entity 
public class SVNBranch extends Model {

	@Id
    public Long id;
	
	@Constraints.Required
	public String root;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public List<ProjectExport> exports = new ArrayList<ProjectExport>();
	
	@OneToOne
	public Version version;
	
	public Date creation;
	    
    /**
     * Generic query helper for entity SVNBranch with id Long
     */
    public static Finder<Long,SVNBranch> find = new Finder<Long,SVNBranch>(Long.class, SVNBranch.class); 
        	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<SVNBranch> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("root", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("version")
                .findPagingList(pageSize)
                .getPage(page);
    }
	
	public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(SVNBranch branch: SVNBranch.find.orderBy("creation").findList()) {
            options.put(branch.id.toString(), branch.root);
        }
        return options;
    }
}
