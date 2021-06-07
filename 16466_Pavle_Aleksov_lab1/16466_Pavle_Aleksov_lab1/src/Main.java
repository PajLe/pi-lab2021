

public class Main {

	public static void main(String[] args) {
		Indexer indexer = new Indexer();
		indexer.createIndex();
		// normal files: 127 milliseconds
		// split files : 242 milliseconds
		
		try (Searcher searcher = new Searcher(BaseConfig.DIREKTORIJUM_SA_INDEKSOM)) {
			searcher.logickiUpit();
			searcher.pointRangeUpit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("\n\n\n Podeljeni fajlovi:\n\n");
		
		try (Searcher searcher = new Searcher(BaseConfig.DIREKTORIJUM_SA_INDEKSOM_PODELJENI)) {
			searcher.logickiUpit();
			searcher.pointRangeUpit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
