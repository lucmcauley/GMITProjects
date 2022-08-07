package ie.gmit.dip;

/**
 * Object containing two Maps from either <code>String</code> or
 * <code>Integer</code> to <code>Integer.</code>
 * 
 * @author Luc McAuley
 *
 */
public class MapInfoManager {
	private MapInfo queryMap;
	private MapInfo SearchMap;

	/**
	 * 
	 * @return Instance of <code>MapInfo</code> for the query map.
	 */
	public MapInfo getQueryMap() {
		return queryMap;
	}

	/**
	 * 
	 * @param queryMap Sets the instance of <code>MapInfo</code> for the query map.
	 */
	public void setQueryMap(MapInfo queryMap) {
		this.queryMap = queryMap;
	}

	/**
	 * 
	 * @return Instance of <code>MapInfo</code> for the search map.
	 */
	public MapInfo getSearchMap() {
		return SearchMap;
	}

	/**
	 * 
	 * @param searchMap Sets the instance of <code>MapInfo</code> for the search map.
	 */
	public void setSearchMap(MapInfo searchMap) {
		SearchMap = searchMap;
	}

}
