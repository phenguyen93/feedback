package de.unidue.ltl.vocabularyprofile2;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasWriter;
import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiWriter;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpChunker;
import de.unidue.ltl.feedback.io.AsapEssayReader;
import de.unidue.ltl.feedback.io.MewsReader;
import de.unidue.ltl.feedback.io.AsapEssayReader.RatingBias;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class BaseExperiment2 {

	public static void main(String[] args) throws ResourceInitializationException, UIMAException, IOException{
		preprocess();
	}
	
	private static void preprocess() throws ResourceInitializationException, UIMAException, IOException {
		/*
		 * String scoreFile= "src/main/resources/data/index_small.csv"; String
		 * essayPath= "src/main/resources/data/"; CollectionReaderDescription reader
		 * =CollectionReaderFactory.createReaderDescription( Toefl11Reader.class,
		 * Toefl11Reader.PARAM_INPUT_PATH, essayPath, Toefl11Reader.PARAM_SCORE_FILE,
		 * scoreFile);
		 */
		
		
		//ASAP Reader	
		
		
		
		
		
		
		
		/*
		 * CollectionReaderDescription reader =
		 * CollectionReaderFactory.createReaderDescription(AsapEssayReader.class,
		 * AsapEssayReader.PARAM_QUESTION_ID,5, AsapEssayReader.PARAM_TARGET_LABEL,
		 * "score", AsapEssayReader.PARAM_RATING_BIAS, RatingBias.low,
		 * AsapEssayReader.PARAM_DO_SPARSECLASSMERGING, false,
		 * AsapEssayReader.PARAM_DO_NORMALIZATION, false,
		 * AsapEssayReader.PARAM_INPUT_FILE,
		 * "D:\\BA\\asap_essays Kopie\\training_set_rel3.tsv");
		 */
		
		
		  
		//MEWS_Reader

		
		  String scoreFile =
		  "D:\\BA\\MEWS_Essays\\MEWS_Essays\\MEWSdocs\\MEWS_FINAL_Deliverable_ScoreFile_120817.tsv";
		  String essayPath = "D:\\BA\\Mews_New\\Prompts/ALL";
		  CollectionReaderDescription reader =
		  CollectionReaderFactory.createReaderDescription( MewsReader.class,
		  MewsReader.PARAM_INPUT_FILE, essayPath, MewsReader.PARAM_SCORE_FILE,
		  scoreFile);
		 
		 		  
		 
	
			AnalysisEngineDescription seg = createEngineDescription(CoreNlpSegmenter.class,
					CoreNlpSegmenter.PARAM_LANGUAGE, "en");
			AnalysisEngineDescription posTagger = createEngineDescription(CoreNlpPosTagger.class,
					CoreNlpPosTagger.PARAM_LANGUAGE, "en");
			AnalysisEngineDescription lemmatizer = createEngineDescription(CoreNlpLemmatizer.class);
			AnalysisEngineDescription chunker = createEngineDescription(OpenNlpChunker.class,
					OpenNlpChunker.PARAM_LANGUAGE, "en");
			AnalysisEngineDescription vocab = createEngineDescription(CVAnnotator2.class);
			
			AnalysisEngineDescription analyzer = createEngineDescription(Analyzer.class);
			
			AnalysisEngineDescription binCasWriter = createEngineDescription(
					BinaryCasWriter.class, 
					BinaryCasWriter.PARAM_FORMAT, "6+",
					BinaryCasWriter.PARAM_OVERWRITE, true,
					BinaryCasWriter.PARAM_TARGET_LOCATION, "target/bincas"
					);
			AnalysisEngineDescription xmiWriter = createEngineDescription(
					XmiWriter.class, 
					XmiWriter.PARAM_OVERWRITE, true,
					XmiWriter.PARAM_TARGET_LOCATION, "target/cas"
					);


			SimplePipeline.runPipeline(reader, 
					seg, 
					posTagger, 
					lemmatizer,
//					chunker,
					vocab,
//					analyzer
					xmiWriter,
					binCasWriter
					);
		}
		
	
	
	
	
}
