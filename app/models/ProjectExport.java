package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * ProjectExport entity managed by Ebean
 */
@Entity 
public class ProjectExport extends Model {

	@Id
    public Long id;
	
	@ManyToOne
	public SVNBranch svnbranch;
	
	@ManyToOne
	public User exportedBy;
	
	@Constraints.Required
	@ManyToOne
	public Project project;
	
	public short num;
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date exportDate;
	
	public int svnversion;
	
	public boolean cancelled = false;
	
	public List<String> changelog;
	
	@ManyToMany
	public Set<Release> release = new HashSet<Release>();
	    
    /**
     * Generic query helper for entity ProjectExport with id Long
     */
    public static Finder<Long,ProjectExport> find = new Finder<Long,ProjectExport>(Long.class, ProjectExport.class); 
    	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<ProjectExport> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("num", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("project")
                .findPagingList(pageSize)
                .getPage(page);
    }
}
