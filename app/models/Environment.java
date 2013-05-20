package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Environment entity managed by Ebean
 */
@Entity 
public class Environment extends Model {

	@Id
    public Long id;
	
	public String name;
	
	@OneToOne
	public IBMi logic;
	
	@Constraints.Required
	@ManyToOne
	public EnvironmentType type;
	
	public String mq;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<DataCheckEnvironment> dataCheck = new HashSet<DataCheckEnvironment>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<WebServer> webserver = new HashSet<WebServer>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<Metrology> metrology = new HashSet<Metrology>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<Backend> backend = new HashSet<Backend>();
	
	@OneToMany(cascade = CascadeType.PERSIST)
	public Set<ReverseProxy> reverseproxy = new HashSet<ReverseProxy>();
    
    /**
     * Generic query helper for entity Environment with id Long
     */
    public static Finder<Long,Environment> find = new Finder<Long,Environment>(Long.class, Environment.class); 
	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Environment> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("type")
                .findPagingList(pageSize)
                .getPage(page);
    }
}
