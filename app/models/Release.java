package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Release entity managed by Ebean
 */
@Entity 
public class Release extends Model {

	@Id
    public Long id;
	
	@OneToOne
	private Data data;
	
	@ManyToOne
	public Version version;
	
	public String title;
	
	public Integer status = 0;
	
	public Date dueDate;
	
	@ManyToMany
	public Set<ProjectExport> projects = new HashSet<ProjectExport>();
	
	public Release() {
		// Date par défaut de la release (le lendemain)
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		this.dueDate = calendar.getTime();
	}
	
	public Release(Long versionId) {
		this();
		// Version contenant cette release
		if (versionId != null){
			Version v = Version.find.byId(versionId);
			if (v != null){ this.version = v; }
		}
	}
	
    /**
     * Generic query helper for entity Release with id Long
     */
    public static Finder<Long,Release> find = new Finder<Long,Release>(Long.class, Release.class); 
    	
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Release> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("title", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("version")
                .findPagingList(pageSize)
                .getPage(page);
    }
	
	
	// Statuts des releases
	
	private static final Map<String, String> releaseStatus;
    static {
        Map<String, String> statuses = new HashMap<String, String>();
		
		// Phase définition
        statuses.put("0", "Créée");
		statuses.put("1", "Planifiée");
		statuses.put("2", "Remplie");
		// Phase pré-livraison
        statuses.put("3", "Vérifiée");
		statuses.put("4", "Vérification échouée");
		statuses.put("5", "Validée");
		// Phase livraison
		statuses.put("6", "Livrée");
		statuses.put("7", "Livraison échouée");
		
        releaseStatus = Collections.unmodifiableMap(statuses);
    }
	
	public static Map<String,String> statusOptions() {
        return releaseStatus;
    }
}
