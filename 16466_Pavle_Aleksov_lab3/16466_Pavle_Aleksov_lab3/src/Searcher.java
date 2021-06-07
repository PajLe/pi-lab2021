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
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * Klasa za testiranje razliÄ�itih naÄ�ina pretraÅ¾ivanja indeksa.
 *
 */
public class Searcher extends BaseConfig implements AutoCloseable {
	private Directory directory;
	private IndexReader indexReader;
	private IndexSearcher indexSearcher;

	// Potrebna su nam 2 parsera za upite:
	// ObiÄ�an (classic) parser upita koji sluÅ¾i za parsiranje velike veÄ‡ine
	// upita,
	// ali ne moÅ¾e da parsira upite nad Point poljima.
	private QueryParser classicParser;
	// Novija implementacija parsera, standardni (flexible) parser upita koji Ä‡emo
	// da koristimo za Point upite.
	private StandardQueryParser pointQueryParser;

	/**
	 * Konstruktor koji inicijalizuje sve pomoÄ‡ne objekte potrebne za
	 * pretraÅ¾ivanje indeksa.
	 */
	public Searcher() throws IOException {
		this.directory = FSDirectory.open(Paths.get(DIREKTORIJUM_SA_INDEKSOM));
		this.indexReader = DirectoryReader.open(directory); // 3
		this.indexSearcher = new IndexSearcher(indexReader);

		this.classicParser = new QueryParser(POLJE_SADRZAJ, this.analyzer); // default polje za pretraÅ¾ivanje je
																			// "sadrÅ¾aj"

		this.pointQueryParser = new StandardQueryParser(this.analyzer);
		// U konfiguraciji za standardni parser moramo da zadamo nazive polja koja su
		// zadata kao Point polja i da ih mapiramo na odgovarajuÄ‡i numeriÄ�ki tip.
		Map<String, PointsConfig> map = new HashMap<String, PointsConfig>();
		map.put(POLJE_VELICINA_LONG, new PointsConfig(NumberFormat.getInstance(), Long.class));
		pointQueryParser.setPointsConfigMap(map);
	}
	
	public Searcher(String dirString) throws IOException {
		this.directory = FSDirectory.open(Paths.get(dirString));
		this.indexReader = DirectoryReader.open(directory); // 3
		this.indexSearcher = new IndexSearcher(indexReader);

		this.classicParser = new QueryParser(POLJE_SADRZAJ, this.analyzer); // default polje za pretraÅ¾ivanje je
																			// "sadrÅ¾aj"

		this.pointQueryParser = new StandardQueryParser(this.analyzer);
		// U konfiguraciji za standardni parser moramo da zadamo nazive polja koja su
		// zadata kao Point polja i da ih mapiramo na odgovarajuÄ‡i numeriÄ�ki tip.
		Map<String, PointsConfig> map = new HashMap<String, PointsConfig>();
		map.put(POLJE_VELICINA_LONG, new PointsConfig(NumberFormat.getInstance(), Long.class));
		pointQueryParser.setPointsConfigMap(map);
	}

	@Override
	public void close() throws Exception {
		// Klase implementira interfejs AutoCloseable da bi se automatizovalo zatvaranje
		// resursa koji se koriste za pretraÅ¾ivanje.
		this.indexReader.close();
		this.directory.close();
	}

	/**
	 * Parsira upit "classic" parserom i izvrÅ¡ava ga.
	 * 
	 * @param stringQuery Upit zadat u tekstualnom obliku.
	 */
	private void parseClassicAndSearch(String stringQuery) {
		System.out.println();
		System.out.println("\"Classic\" parser parsira upit: " + stringQuery);
		try {
			Query query = this.classicParser.parse(stringQuery);
			System.out.println(
					"PretraÅ¾ivanje po parsiranom objektu: '" + query + "' tipa " + query.getClass().getName());
			findAndShowResults(query);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parsira upit "standard" parserom za point podatke i izvrÅ¡ava ga.
	 * 
	 * @param stringQuery Upit zadat u tekstualnom obliku.
	 * @param fieldName   "Standard" parseru se obavezno prosleÄ‘uje i naziv polja
	 *                    za pretraÅ¾ivanje.
	 */
	private void parsePointAndSearch(String stringQuery, String fieldName) {
		System.out.println();
		System.out.println("\"Point\" parser parsira upit: " + stringQuery);
		try {
			Query query = this.pointQueryParser.parse(stringQuery, fieldName);
			System.out.println(
					"PretraÅ¾ivanje po parsiranom objektu: '" + query + "' tipa " + query.getClass().getName());
			findAndShowResults(query);
		} catch (QueryNodeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Radi pretraÅ¾ivanje indeksa na osnovu objektnog modela upita {@code query}.
	 * Prethodno prikazuje taj objektni model upita.
	 * 
	 * @param query Objektni model upita predstavljen nekom od klasa izvedenih iz
	 *              {@link Query}.
	 */
	private void searchIndex(Query query) {
		System.out.println();
		System.out.println("PretraÅ¾ivanje po objektu klase query: '" + query + "' tipa " + query.getClass().getName());
		this.findAndShowResults(query);
	}

	/**
	 * PretraÅ¾uje indeks na osnovu objektnog modela upita {@code query} pa zatim
	 * prikazuje pronaÄ‘ene dokumente.
	 * 
	 * @param query Objektni model upita predstavljen nekom od klasa izvedenih iz
	 *              {@link Query}.
	 */
	private void findAndShowResults(Query query) {
		try {
			TopDocs hits = indexSearcher.search(query, 10);
			System.out.println("Broj pogodaka: " + hits.totalHits.value);

			for (ScoreDoc scoreDoc : hits.scoreDocs) {
				Document document = indexSearcher.doc(scoreDoc.doc);
				String path = document.get(POLJE_PUTANJA);
				System.out.println("PronaÄ‘eni fajl je: " + path);
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void logickiUpit() {
		// query parser
		
		parseClassicAndSearch("izveštaj OR testiranje AND NOT chat");

		// query object
		BooleanQuery.Builder booleanQueryBuilder = new BooleanQuery.Builder();
		TermQuery termIzvestaj = new TermQuery(new Term(POLJE_SADRZAJ, "izveštaj"));
		TermQuery termTestiranje = new TermQuery(new Term(POLJE_SADRZAJ, "testiranje"));
		TermQuery termChat = new TermQuery(new Term(POLJE_SADRZAJ, "chat"));
		booleanQueryBuilder = new BooleanQuery.Builder();
		booleanQueryBuilder.add(termIzvestaj, Occur.SHOULD);
		booleanQueryBuilder.add(termTestiranje, Occur.MUST);
		booleanQueryBuilder.add(termChat, Occur.MUST_NOT);
		searchIndex(booleanQueryBuilder.build());
	}

	public void pointRangeUpit() {
		// query parser
		parsePointAndSearch(POLJE_VELICINA_LONG + ":{270156 TO 570000]", POLJE_VELICINA_LONG);
		
		// query object
		Query pointRangeQuery1 = LongPoint.newRangeQuery(POLJE_VELICINA_LONG, 270157, 570000);
		searchIndex(pointRangeQuery1);
	}

}
