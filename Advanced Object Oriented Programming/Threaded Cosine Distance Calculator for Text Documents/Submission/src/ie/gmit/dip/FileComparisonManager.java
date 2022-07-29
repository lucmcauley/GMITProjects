package ie.gmit.dip;

/**
 * Contains the information for the query and search files in addition to the
 * variables for comparison.
 * 
 * @author LucMcAuley
 *
 */
public class FileComparisonManager {
	private FileInfo queryFile;
	private FileInfo subjectFile;
	private LineDivisionInfo ldi;

	/**
	 * 
	 * @return LineDivisionInfo instance with all methods and variables stored in
	 *         it.
	 */
	public LineDivisionInfo getLineDivisionInfo() {
		return ldi;
	}

	/**
	 * 
	 * @param ldi LineDivisionInfo to set.
	 */
	public void setLineDivisionInfo(LineDivisionInfo ldi) {
		this.ldi = ldi;
	}

	/**
	 * 
	 * @return FileInfo instance for the query file with all methods and variables
	 *         stored in it.
	 */
	public FileInfo getQueryFile() {
		return queryFile;
	}

	/**
	 * 
	 * @param queryFile Set the FileInfo instance for the query file.
	 */
	public void setQueryFile(FileInfo queryFile) {
		this.queryFile = queryFile;
	}

	/**
	 * 
	 * @return FileInfo instance for the search file with all methods and variables
	 *         stored in it.
	 */
	public FileInfo getSearchFile() {
		return subjectFile;
	}

	/**
	 * 
	 * @param searchFile Set the FileInfo instance for the search file.
	 */
	public void setSearchFile(FileInfo searchFile) {
		this.subjectFile = searchFile;
	}

}
