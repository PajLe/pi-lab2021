import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Indexer extends BaseConfig {

	public void createIndex(Similarity similarity, String indexDirPath) {
		IndexWriterConfig config = new IndexWriterConfig(this.analyzer);
		config.setOpenMode(OpenMode.CREATE);
		config.setSimilarity(similarity);

		try (Directory indexDir = FSDirectory.open(Paths.get(indexDirPath));
				IndexWriter indexWriter = new IndexWriter(indexDir, config);) {
			
			File dataDir = new File(DIREKTORIJUM_SA_PODACIMA);
			
			long start = System.currentTimeMillis();
			addDataToIndex(dataDir, indexWriter);
			long end = System.currentTimeMillis();
			System.out.println("Indexing normal files took "
				      + (end - start) + " milliseconds");
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private void addDataToIndex(File dataDir, IndexWriter indexWriter) throws IOException {
		File[] files = dataDir.listFiles();
		
		for (File file : files) {
			Document document = new Document();
			String title = file.getName();
			String titleWithoutExtension = title.substring(0, title.length() - 4);
			
			document.add(new StringField(POLJE_PUTANJA, file.getCanonicalPath(), Field.Store.YES));
			document.add(new TextField(POLJE_NASLOV, titleWithoutExtension, Field.Store.YES));
			document.add(new StringField(POLJE_VELICINA_STRING, Long.toString(file.length()), Field.Store.YES));
			document.add(new LongPoint(POLJE_VELICINA_LONG, file.length()));
			document.add(new TextField(POLJE_SADRZAJ, new FileReader(file)));

			indexWriter.addDocument(document);
		}
	}
}
