package de.unidue.ltl.feedback;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.corenlp.CoreNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.io.bincas.BinaryCasWriter;
import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiWriter;
import de.unidue.ltl.feedback.io.AnswerReader;
import de.unidue.ltl.feedback.io.PCFeedbackReader;
import de.unidue.ltl.feedback.io.QuestionReader;
import de.unidue.ltl.feedback.io.SRAFeedbackLineReader;
import de.unidue.ltl.feedback.io.SRAFeedbackReader;
import de.unidue.ltl.feedback.io.TargetAnswerReader;
import de.unidue.ltl.feedback.io.CFeedbackReader;
import de.unidue.ltl.feedback.io.ICFeedbackReader;

public class BaseExperiment {

	public static void main(String[] args) throws ResourceInitializationException, UIMAException, IOException{
		preprocess();
	}
	
	private static void preprocess() throws ResourceInitializationException, UIMAException, IOException {
		
		/*
		 * //CorefExampleReader // TODO: adjust path String documentPath =
		 * "D:\\\\HIWI\\\\Kickoff\\\\CorefExample.xlsx";
		 * 
		 * CollectionReaderDescription reader =
		 * CollectionReaderFactory.createReaderDescription( CorefExampleReader.class,
		 * CorefExampleReader.PARAM_INPUT_FILE, documentPath);
		 */
		
		
		  //SRAFeedbackLineReader // TODO: adjust path 
			String essayPath ="D:\\\\HIWI\\\\Kickoff\\\\Datensammlungf.xlsx"; 
			String outputPath ="D:\\HIWI\\Kickoff\\Ergebnisse\\FeedbackDaten.xlsx"; CollectionReaderDescription
		  reader = CollectionReaderFactory.createReaderDescription(
		  SRAFeedbackLineReader.class, SRAFeedbackLineReader.PARAM_INPUT_FILE,
		  essayPath);
		 
			/*
			 * //SRAFeedback Reader // TODO: adjust path String essayPath =
			 * "D:\\\\HIWI\\\\Kickoff\\\\Datensammlungf.xlsx"; String scoreFile = "";
			 * CollectionReaderDescription reader =
			 * CollectionReaderFactory.createReaderDescription( SRAFeedbackReader.class,
			 * SRAFeedbackReader.PARAM_INPUT_FILE, essayPath,
			 * SRAFeedbackReader.PARAM_SCORE_FILE, scoreFile );
			 */
		
		/*
		 * //QuestionReader
		 * 
		 * String essayPath = "D:\\\\HIWI\\\\Kickoff\\\\Datensammlungf.xlsx"; String
		 * scoreFile = ""; CollectionReaderDescription reader =
		 * CollectionReaderFactory.createReaderDescription( QuestionReader.class,
		 * QuestionReader.PARAM_INPUT_FILE, essayPath, QuestionReader.PARAM_SCORE_FILE,
		 * scoreFile );
		 */
		  
		
			/*
			 * //TargetAnswerReader
			 * 
			 * String essayPath = "D:\\\\HIWI\\\\Kickoff\\\\Datensammlungf.xlsx"; String
			 * scoreFile = ""; CollectionReaderDescription reader =
			 * CollectionReaderFactory.createReaderDescription( TargetAnswerReader.class,
			 * TargetAnswerReader.PARAM_INPUT_FILE, essayPath,
			 * TargetAnswerReader.PARAM_SCORE_FILE, scoreFile );
			 */
		
		
		/*
		 * //AnswerReader
		 * 
		 * String essayPath = "D:\\\\HIWI\\\\Kickoff\\\\Datensammlungf.xlsx"; String
		 * scoreFile = ""; CollectionReaderDescription reader =
		 * CollectionReaderFactory.createReaderDescription(AnswerReader.class,
		 * AnswerReader.PARAM_INPUT_FILE, essayPath,AnswerReader.PARAM_SCORE_FILE,
		 * scoreFile );
		 */
		
		
		
		
		/*
		 * //CFeedbackReader
		 * 
		 * String essayPath = "D:\\\\HIWI\\\\Kickoff\\\\Datensammlungf.xlsx"; String
		 * scoreFile = ""; CollectionReaderDescription reader =
		 * CollectionReaderFactory.createReaderDescription(CFeedbackReader.class,
		 * CFeedbackReader.PARAM_INPUT_FILE, essayPath,CFeedbackReader.PARAM_SCORE_FILE,
		 * scoreFile );
		 */
		 
		
		
		/*
		 * //ICFeedbackReader
		 * 
		 * String essayPath = "D:\\\\HIWI\\\\Kickoff\\\\Datensammlungf.xlsx"; String
		 * scoreFile = ""; CollectionReaderDescription reader =
		 * CollectionReaderFactory.createReaderDescription(ICFeedbackReader.class,
		 * ICFeedbackReader.PARAM_INPUT_FILE,
		 * essayPath,ICFeedbackReader.PARAM_SCORE_FILE, scoreFile );
		 */
		
		
		
		/*
		 * //PCFeedbackReader
		 * 
		 * String essayPath = "D:\\\\HIWI\\\\Kickoff\\\\Datensammlungf.xlsx"; String
		 * scoreFile = ""; CollectionReaderDescription reader =
		 * CollectionReaderFactory.createReaderDescription(PCFeedbackReader.class,
		 * PCFeedbackReader.PARAM_INPUT_FILE,
		 * essayPath,PCFeedbackReader.PARAM_SCORE_FILE, scoreFile );
		 */
		AnalysisEngineDescription posTagger = createEngineDescription(CoreNlpPosTagger.class,
					CoreNlpPosTagger.PARAM_LANGUAGE, "en");  						
		AnalysisEngineDescription seg = createEngineDescription(CoreNlpSegmenter.class,
				CoreNlpSegmenter.PARAM_LANGUAGE, "en");
		AnalysisEngineDescription analyzer = createEngineDescription(Analyzer.class,Analyzer.PARAM_OUTPUT_FILE,outputPath);
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
				analyzer
	//			xmiWriter,
	//			binCasWriter
				);
	}
	
	
	
}
