import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.Similarity;

public class Main {

	public static void main(String[] args) {
		Similarity bm25 = new BM25Similarity();
		
		Indexer indexerBM25 = new Indexer();
		indexerBM25.createIndex(bm25, BaseConfig.DIREKTORIJUM_SA_INDEKSOM);
		
		try (Searcher searcher = new Searcher(BaseConfig.DIREKTORIJUM_SA_INDEKSOM, bm25)) {
			searcher.logickiUpit();
			searcher.logickiUpitBoostedBM25();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Similarity classic = new ClassicSimilarity();
		
		Indexer indexerClassic = new Indexer();
		indexerClassic.createIndex(classic, BaseConfig.DIREKTORIJUM_SA_INDEKSOM_CLASSIC);
		
		try (Searcher searcher = new Searcher(BaseConfig.DIREKTORIJUM_SA_INDEKSOM_CLASSIC, classic)) {
			searcher.logickiUpit();
			searcher.logickiUpitBoostedClassic();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
