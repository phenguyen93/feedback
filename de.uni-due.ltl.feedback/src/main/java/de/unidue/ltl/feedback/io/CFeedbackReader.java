package de.unidue.ltl.feedback.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.io.FileUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
import org.dkpro.tc.api.type.TextClassificationOutcome;
import org.dkpro.tc.api.type.TextClassificationTarget;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.resources.ResourceUtils;
import de.unidue.ltl.escrito.core.types.LearnerAnswer;
import de.unidue.ltl.escrito.io.util.Utils;

/*
 * read only correct feedbacks
 * 
 */

public class CFeedbackReader extends JCasCollectionReader_ImplBase {

	public static final String PARAM_INPUT_FILE = "InputFile";
	@ConfigurationParameter(name = PARAM_INPUT_FILE, mandatory = true)
	protected String inputFileString;
	protected URL inputFileURL;

	public static final String PARAM_SCORE_FILE = "ScoreFile";
	@ConfigurationParameter(name = PARAM_SCORE_FILE, mandatory = true)
	protected String scoreFileString;

	public static final String PARAM_LANGUAGE = "Language";
	@ConfigurationParameter(name = PARAM_LANGUAGE, mandatory = false, defaultValue = "en")
	protected String language;

	public static final String PARAM_ENCODING = "Encoding";
	@ConfigurationParameter(name = PARAM_ENCODING, mandatory = false, defaultValue = "UTF-8")
	private String encoding;

	public static final String PARAM_SEPARATOR = "Separator";
	@ConfigurationParameter(name = PARAM_SEPARATOR, mandatory = false, defaultValue = "\t")
	private String separator;

	protected int currentIndex;

	protected Queue<SRAFeedbackItem> items;
	protected Queue<SRAFeedbackItem> correctItems;
	protected Queue<SRAFeedbackItem> partialCorrectItems;
	protected Queue<SRAFeedbackItem> incorrectItems;

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		items = new LinkedList<SRAFeedbackItem>();
		correctItems = new LinkedList<SRAFeedbackItem>();
		partialCorrectItems = new LinkedList<SRAFeedbackItem>();
		incorrectItems = new LinkedList<SRAFeedbackItem>();
		try {

			inputFileURL = ResourceUtils.resolveLocation(inputFileString, this, aContext);
			
			FileInputStream file = new FileInputStream(new File(inputFileURL.getPath()));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			int numOfSheet = workbook.getNumberOfSheets();
			System.out.println("NumberOfSheet: "+numOfSheet);
			
			for (int i = 0; i < numOfSheet; i++) {
				
				int numOfBlank =0;
				String label = "";
				String feedback = "";
				String answer = "";
				String targetAnswer = readCellData(1, 1, inputFileURL.getPath(),i);
				String question = readCellData(0, 1, inputFileURL.getPath(),i);
				String promptId = workbook.getSheetName(i);
				
				
				XSSFSheet sheet = workbook.getSheetAt(i);
				int rowNum = sheet.getLastRowNum() + 1;
				System.out.println("number of rows of "+promptId+" is: "+rowNum);
				
				LinkedList<SRAFeedbackItem> items = null;
				items = new LinkedList<SRAFeedbackItem>();
				
				
				for (int j = 4; j < rowNum; j++) {	
					label =    readCellData(j, 0, inputFileURL.getPath(),i);
					answer = readCellData(j, 1, inputFileURL.getPath(),i);
					feedback =readCellData(j, 2, inputFileURL.getPath(),i);
					
//					SRAItem newItem = new SRAItem(promptId, question, targetAnswer, answer, feedback, label);
					
//					items.add(newItem);
		
					
//					label = label + " "+readCellData(j, 0, inputFileURL.getPath(),i);					
//					answer =answer +" "+ readCellData(j, 1, inputFileURL.getPath(),i);
//					feedback = feedback+ " "+ readCellData(j, 2, inputFileURL.getPath(),i);
					//get number of blank cell
//					if(readCellData(j, 2, inputFileURL.getPath(),i).equals("")) {
//						numOfBlank +=1;
//					}
					SRAFeedbackItem newItem = new SRAFeedbackItem(promptId, question, targetAnswer, answer, feedback, label,1);
					
					items.add(newItem);
				}
				
				
				
				String answerCorrect = "";
				String answerIncorrect ="";
				String answerPartialCorrect ="";
				String feedbackCorrect = "";
				String feedbackIncorrect ="";
				String feedbackPartialCorrect ="";
				int numOfFeedbackCorrect = 0;
				int numOfFeedbackIncorrect = 0;
				int numOfFeedbackPartialCorrect = 0;
												
				for (SRAFeedbackItem item1 : items) {
					if (item1.getLabel().equals("correct")) {
						answerCorrect = answerCorrect+" "+ item1.getAnswer();
						feedbackCorrect =feedbackCorrect+" " + item1.getFeedback();
						numOfFeedbackCorrect += item1.getNumOfFeedback();
					}else if (item1.getLabel().equals("partially_correct_incomplete")) {
						answerPartialCorrect = answerPartialCorrect+" " +item1.getAnswer();
						feedbackPartialCorrect = feedbackPartialCorrect+" "+ item1.getFeedback();
						numOfFeedbackPartialCorrect += item1.getNumOfFeedback();
					}else {
						answerIncorrect = answerIncorrect+" "+ item1.getAnswer();
						feedbackIncorrect =feedbackIncorrect+" "+  item1.getFeedback();
						numOfFeedbackIncorrect += item1.getNumOfFeedback();
					}
				}
				SRAFeedbackItem sraIncorrect = new SRAFeedbackItem(promptId, question, targetAnswer, answerIncorrect, feedbackIncorrect, "incorrect",numOfFeedbackIncorrect);
				SRAFeedbackItem sraCorrect = new SRAFeedbackItem(promptId, question, targetAnswer, answerCorrect, feedbackCorrect, "correct",numOfFeedbackCorrect);
				SRAFeedbackItem sraPartialCorrect = new SRAFeedbackItem(promptId, question, targetAnswer, answerPartialCorrect, feedbackPartialCorrect, "partially_correct",numOfFeedbackPartialCorrect);
				
				correctItems.add(sraCorrect);				
				partialCorrectItems.add(sraPartialCorrect);
				incorrectItems.add(sraIncorrect);
//				for(SRAItem test :incorrectItems) {
//					System.out.println(test.getFeedback());
//				}
//				System.out.println("Number of Blank:  "+numOfBlank);
//				System.out.println(label);
//				SRAItem newItem = new SRAItem(promptId, "", "", answer, feedback, label);
//				items.add(newItem);
			}
			//getAll
			String feedbackAll = null;
			String answerAll = null;
			String questionAll = null;
			String targetAnswerAll = null;
			int numOfFeedbackAll = 0;
			for (SRAFeedbackItem item : correctItems) {
				feedbackAll = feedbackAll+" "+item.getFeedback();
				answerAll = answerAll+" "+item.getAnswer();
				questionAll = questionAll+" "+item.getQuestion();
				targetAnswerAll= targetAnswerAll+" "+item.getTargetAnswer();
				numOfFeedbackAll += item.getNumOfFeedback();
			}
			correctItems.add(new SRAFeedbackItem("AllPrompt",questionAll,targetAnswerAll,answerAll,feedbackAll,"X",numOfFeedbackAll));
            
		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
		currentIndex = 0;
	}

	// HOTFIX for Issue 445 in DKPro Core
	private static String cleanString(String textForCas) {
		textForCas = textForCas.replaceAll("[^a-zA-Z0-9\\-\\.,:;\\(\\)\\? ]", "");
		textForCas = textForCas.replace("…", "...");
		textForCas = textForCas.replace("´", "'");
		return textForCas.replace("’", "'").trim();
	}



	public boolean hasNext() throws IOException, CollectionException {
		return !correctItems.isEmpty();
	}

	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(currentIndex, currentIndex, Progress.ENTITIES) };
	}

	@Override
	public void getNext(JCas jcas) throws IOException, CollectionException {
		SRAFeedbackItem item = correctItems.poll();
		getLogger().debug(item);
		try {
			jcas.setDocumentLanguage(language);
			jcas.setDocumentText(item.getFeedback());
			DocumentMetaData dmd = DocumentMetaData.create(jcas);
			dmd.setDocumentId(item.getPromptId());
			dmd.setDocumentTitle(item.getFeedback());
			dmd.setCollectionId(item.getAnswer());
			dmd.setDocumentBaseUri(item.getQuestion());
			dmd.setDocumentUri(item.getTargetAnswer());					
			dmd.setEnd(item.getNumOfFeedback());
		}

		catch (Exception e) {
			throw new CollectionException(e);
		}

		LearnerAnswer learnerAnswer = new LearnerAnswer(jcas, 0, jcas.getDocumentText().length());
		learnerAnswer.setPromptId("-1");
		learnerAnswer.addToIndexes();

		TextClassificationTarget unit = new TextClassificationTarget(jcas, 0, jcas.getDocumentText().length());
		// will add the token content as a suffix to the ID of this unit
		unit.setSuffix(item.getPromptId());
		unit.addToIndexes();
		TextClassificationOutcome outcome = new TextClassificationOutcome(jcas, 0, jcas.getDocumentText().length());
		// TODO
		outcome.setOutcome(String.valueOf(item.getLabel()));
		outcome.addToIndexes();
		currentIndex++;
	}

	public static String readCellData(int row, int column, String path, int sheetNumber ) {

		String value = null;
		Workbook wb = null;

		try {
			// reading data from a file in the form of bytes
			FileInputStream fis=new FileInputStream(path);  
			// constructs an XSSFWorkbook object, by buffering the whole stream into the
			// memory
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Sheet sheet = wb.getSheetAt(sheetNumber); // getting the XSSFSheet object at given index
		Row row1 = sheet.getRow(row); // returns the logical row
		Cell cell = row1.getCell(column); // getting the cell representing the given column
		if(cell == null||row1 == null) {
			value = ""; // getting cell value
		}else {
			value = cell.getStringCellValue();
		}
		
		return value; // returns the cell value

	}


}
