package ie.gmit.dip;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Controls the flow of methods and classes called in order to compare the files
 * correctly.
 * <p>
 * Implements the <code>Executor Service</code> for the threaded aspect of the
 * project.
 * </p>
 * 
 * @author Luc McAuley
 * 
 *
 */
public class CompareFiles {
	private ExecutorService executorService = Executors.newFixedThreadPool(2);

	private LineDivisionInfo ldi = new LineDivisionInfo();
	private MapInfoManager mim = new MapInfoManager();
	private MapInfo queryMap = new MapInfo();
	private MapInfo searchMap = new MapInfo();

	private String searchFilePath;
	private String queryFilePath;

	/**
	 * Starting point for the threaded file comparison.
	 * 
	 * @param fcm Used to set the file paths as well as the ways in which the files
	 *            are divided and subsequently compared.
	 */
	public void start(FileComparisonManager fcm) {
		this.ldi = fcm.getLineDivisionInfo();
		this.searchFilePath = fcm.getSearchFile().getFilePath();
		this.queryFilePath = fcm.getQueryFile().getFilePath();

		convertFileToMaps();
	}

	private void convertFileToMaps() { // Converts the user set files to word maps to compare

		if (ldi.getTypeOfMapping() == 1) {// Converts and compares files using hash codes
			compareUsingIntegerMaps();
		} else if (ldi.getTypeOfMapping() == 2) {// Converts and compares files using strings
			compareUsingStringMaps();
		} else {
			System.out.println("Oops something went wrong in convertFileToMaps, please try again");
		}
	}

	/**
	 * Uses the <code>Executor Service</code> to convert two files in a threaded
	 * manner, up to two threads.
	 * <p>
	 * The <code>Executor Service</code> returns two maps which map
	 * <code>hash code keys</code> to the number of times that <code>hash code</code>
	 * appears in the text. One <code>Map</code> for search and one for query.
	 * </p>
	 * <p>
	 * Then sends the two maps to be compared.
	 * </p>
	 */
	private void compareUsingIntegerMaps() {

		final Future<Map<Integer, Integer>> map1 = executorService.submit(new HashFileHandler(ldi, searchFilePath));
		final Future<Map<Integer, Integer>> map2 = executorService.submit(new HashFileHandler(ldi, queryFilePath));

		try {

			queryMap.setIntegerMap(map1.get());
			mim.setQueryMap(queryMap); // Could not use queryMap.setIntegerMapInfo(mim.setQueryMap(queryMap))

			searchMap.setIntegerMap(map2.get());
			mim.setSearchMap(searchMap); // Similarly as above

		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Oops, something went wrong when sorting out the files, please try again");
		}

		executorService.shutdown();

		new CosineComparisonHashMaps().start(mim);
	}

	/**
	 * Uses the <code>Executor Service</code> to convert two files in a threaded
	 * manner, up to two threads.
	 * <p>
	 * The <code>Executor Service</code> returns two maps which map a
	 * <code>String</code> to the number of times that <code>String</code> appears
	 * in the text. One <code>Map</code> for search and one for query.
	 * </p>
	 * <p>
	 * Then sends the two maps to be compared.
	 * </p>
	 */
	private void compareUsingStringMaps() {

		final Future<Map<String, Integer>> map1 = executorService.submit(new StringFileHandler(ldi, searchFilePath));
		final Future<Map<String, Integer>> map2 = executorService.submit(new StringFileHandler(ldi, queryFilePath));

		try {
			queryMap.setStringMap(map1.get()); // Could not use queryMap.setStringMapInfo(mim.setQueryMap(queryMap))
			mim.setQueryMap(queryMap);

			searchMap.setStringMap(map2.get()); // Similarly as above
			mim.setSearchMap(searchMap);

		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Oops, something went wrong when sorting out the files, please try again");
		}

		executorService.shutdown();

		new CosineComparisonStringMaps().start(mim);
	}
}
