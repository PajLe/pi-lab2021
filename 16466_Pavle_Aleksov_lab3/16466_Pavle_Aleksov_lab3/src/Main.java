

public class Main {

	public static void main(String[] args) {
		Indexer indexer = new Indexer();
		indexer.createIndex();

		try (Searcher searcher = new Searcher(BaseConfig.DIREKTORIJUM_SA_INDEKSOM)) {
			searcher.logickiUpit();
			searcher.pointRangeUpit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
