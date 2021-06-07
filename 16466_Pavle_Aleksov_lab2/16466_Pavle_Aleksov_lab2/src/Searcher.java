import java.io.IOException;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.flexible.core.QueryNodeException;
import org.apache.lucene.queryparser.flexible.standard.StandardQueryParser;
import org.apache.lucene.queryparser.flexible.standard.config.PointsConfig;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BoostQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Searcher extends BaseConfig implements AutoCloseable {
	private Directory directory;
	private IndexReader indexReader;
	private IndexSearcher indexSearcher;

//	private QueryParser classicParser;
	private StandardQueryParser pointQueryParser;

	public Searcher() throws IOException {
		this(DIREKTORIJUM_SA_INDEKSOM, new BM25Similarity());
	}

	public Searcher(String dirString, Similarity similarity) throws IOException {
		this.directory = FSDirectory.open(Paths.get(dirString));
		this.indexReader = DirectoryReader.open(directory);
		this.indexSearcher = new IndexSearcher(indexReader);
		this.indexSearcher.setSimilarity(similarity);

//		this.classicParser = new QueryParser(POLJE_SADRZAJ, this.analyzer); 

		this.pointQueryParser = new StandardQueryParser(this.analyzer);
		Map<String, PointsConfig> map = new HashMap<String, PointsConfig>();
		map.put(POLJE_VELICINA_LONG, new PointsConfig(NumberFormat.getInstance(), Long.class));
		pointQueryParser.setPointsConfigMap(map);
	}

	@Override
	public void close() throws Exception {
		this.indexReader.close();
		this.directory.close();
	}

//	private void parseClassicAndSearch(String stringQuery) {
//		System.out.println();
//		System.out.println("\"Classic\" parser parsira upit: " + stringQuery);
//		try {
//			Query query = this.classicParser.parse(stringQuery);
//			System.out.println(
//					"PretraÅ¾ivanje po parsiranom objektu: '" + query + "' tipa " + query.getClass().getName());
//			findAndShowResults(query);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}

//	private void parsePointAndSearch(String stringQuery, String fieldName) {
//		System.out.println();
//		System.out.println("\"Point\" parser parsira upit: " + stringQuery);
//		try {
//			Query query = this.pointQueryParser.parse(stringQuery, fieldName);
//			System.out.println(
//					"PretraÅ¾ivanje po parsiranom objektu: '" + query + "' tipa " + query.getClass().getName());
//			findAndShowResults(query);
//		} catch (QueryNodeException e) {
//			e.printStackTrace();
//		}
//	}

	private void searchIndex(Query query) {
		System.out.println();
		System.out.println("Pretrazvanje po objektu klase query: '" + query + "' tipa " + query.getClass().getName());
		this.findAndShowResults(query);
	}

	private void findAndShowResults(Query query) {
		try {
			TopDocs hits = indexSearcher.search(query, 10);
			System.out.println("Broj pogodaka: " + hits.totalHits.value);

			for (ScoreDoc scoreDoc : hits.scoreDocs) {
				Document document = indexSearcher.doc(scoreDoc.doc);
				String path = document.get(POLJE_PUTANJA);
				System.out.println("Pronadjeni fajl je: " + path + ", score: " + scoreDoc.score);
				System.out.println("Objasnjenje: " + indexSearcher.explain(query, scoreDoc.doc));
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// 4. tacka
	public void logickiUpit() {
		BooleanQuery.Builder booleanQueryBuilder;
		TermQuery termRomeo = new TermQuery(new Term(POLJE_SADRZAJ, "romeo"));
		TermQuery termGulliver = new TermQuery(new Term(POLJE_SADRZAJ, "gulliver"));
		booleanQueryBuilder = new BooleanQuery.Builder();
		booleanQueryBuilder.add(termRomeo, Occur.MUST);
		booleanQueryBuilder.add(termGulliver, Occur.MUST_NOT);
		BooleanQuery sadrzajQuery = booleanQueryBuilder.build();
		searchIndex(sadrzajQuery);

		termRomeo = new TermQuery(new Term(POLJE_NASLOV, "romeo"));
		termGulliver = new TermQuery(new Term(POLJE_NASLOV, "gulliver"));
		booleanQueryBuilder = new BooleanQuery.Builder();
		booleanQueryBuilder.add(termRomeo, Occur.MUST);
		booleanQueryBuilder.add(termGulliver, Occur.MUST_NOT);
		BooleanQuery naslovQuery = booleanQueryBuilder.build();
		searchIndex(naslovQuery);
	}

	// 5. tacka
	public void logickiUpitBoostedBM25() {
		BooleanQuery.Builder booleanQueryBuilder;
		BoostQuery termRomeo = new BoostQuery(new TermQuery(new Term(POLJE_SADRZAJ, "romeo")), 1);
		TermQuery termGulliver = new TermQuery(new Term(POLJE_SADRZAJ, "gulliver"));
		booleanQueryBuilder = new BooleanQuery.Builder();
		booleanQueryBuilder.add(termRomeo, Occur.MUST);
		booleanQueryBuilder.add(termGulliver, Occur.MUST_NOT);
		BooleanQuery sadrzajQuery = booleanQueryBuilder.build();
		searchIndex(sadrzajQuery);

		termRomeo = new BoostQuery(new TermQuery(new Term(POLJE_NASLOV, "romeo")), 1.1994779f/0.6221136f);
		termGulliver = new TermQuery(new Term(POLJE_NASLOV, "gulliver"));
		booleanQueryBuilder = new BooleanQuery.Builder();
		booleanQueryBuilder.add(termRomeo, Occur.MUST);
		booleanQueryBuilder.add(termGulliver, Occur.MUST_NOT);
		BooleanQuery naslovQuery = booleanQueryBuilder.build();
		searchIndex(naslovQuery);
	}
	
	// 5. tacka
	public void logickiUpitBoostedClassic() {
		BooleanQuery.Builder booleanQueryBuilder;
		BoostQuery termRomeo = new BoostQuery(new TermQuery(new Term(POLJE_SADRZAJ, "romeo")), 1);
		TermQuery termGulliver = new TermQuery(new Term(POLJE_SADRZAJ, "gulliver"));
		booleanQueryBuilder = new BooleanQuery.Builder();
		booleanQueryBuilder.add(termRomeo, Occur.MUST);
		booleanQueryBuilder.add(termGulliver, Occur.MUST_NOT);
		BooleanQuery sadrzajQuery = booleanQueryBuilder.build();
		searchIndex(sadrzajQuery);

		termRomeo = new BoostQuery(new TermQuery(new Term(POLJE_NASLOV, "romeo")), 16.062378f*0.007360905f/0.4082483f);
		termGulliver = new TermQuery(new Term(POLJE_NASLOV, "gulliver"));
		booleanQueryBuilder = new BooleanQuery.Builder();
		booleanQueryBuilder.add(termRomeo, Occur.MUST);
		booleanQueryBuilder.add(termGulliver, Occur.MUST_NOT);
		BooleanQuery naslovQuery = booleanQueryBuilder.build();
		searchIndex(naslovQuery);
	}
}
