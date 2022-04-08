package de.unidue.ltl.feedback.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

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


/*
 * read feedbacks, questions, target answers and answers from excel file
 * 
 */

public class SRAFeedbackReader extends JCasCollectionReader_ImplBase {

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
	int index;
	
	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		items = new LinkedList<SRAFeedbackItem>();
		index = 0;
		
		try {
			inputFileURL = ResourceUtils.resolveLocation(inputFileString, this, aContext);
			
			FileInputStream file = new FileInputStream(new File(inputFileURL.getPath()));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);			
			int numOfSheet = workbook.getNumberOfSheets();
			System.out.println("NumberOfSheet: "+numOfSheet);
			
			for (int i = 0; i < numOfSheet; i++) {
				
				//get number of blank cell 
				int numOfBlankCell =0;				
				String label = "";
				String feedback = "";
				String answer = "";
				String targetAnswer = readCellData(1, 1, inputFileURL.getPath(),i);
				String question = readCellData(0, 1, inputFileURL.getPath(),i);
				String promptId = workbook.getSheetName(i);
								
				XSSFSheet sheet = workbook.getSheetAt(i);
				int rowNum = sheet.getLastRowNum() + 1;
				int numberOfFeedback =0;
				//TODO: adjust the value of j so that it matches the first row containing feedback in the excel file		
				for (int j = 4; j < rowNum; j++) {	
					label =    readCellData(j, 0, inputFileURL.getPath(),i);
					answer = answer+" "+  readCellData(j, 1, inputFileURL.getPath(),i);
					feedback =feedback+" "+ readCellData(j, 2, inputFileURL.getPath(),i);
					//get number of feedback
					if(!readCellData(j, 2, inputFileURL.getPath(),i).equals("")) {
						numberOfFeedback +=1;
					}					
				}
				System.out.println("number of feedback of "+promptId+" is: "+numberOfFeedback);				
				SRAFeedbackItem newItem = new SRAFeedbackItem(promptId, question, targetAnswer, answer, feedback, label,numberOfFeedback);				
				items.add(newItem);				
			}
			
			//Here(from lines 123-139) all the attributes of all the prompts are added together
			StringBuilder feedbackAll = new StringBuilder();
			StringBuilder answerAll = new StringBuilder();
			StringBuilder questionAll = new StringBuilder();
			StringBuilder targetAnswerAll = new StringBuilder();
			int numOfFeedbackAll = 0;
			for (SRAFeedbackItem item : items) {
				feedbackAll.append(item.getFeedback());
				feedbackAll.append(" ");
				answerAll.append(item.getAnswer());
				answerAll.append(" ");
				questionAll.append(item.getQuestion());
				questionAll.append(" ");
				targetAnswerAll.append(item.getTargetAnswer());
				targetAnswerAll.append(" ");
				numOfFeedbackAll += item.getNumOfFeedback();
			}
			items.add(new SRAFeedbackItem("AllPrompt",questionAll.toString(),targetAnswerAll.toString(),answerAll.toString(),feedbackAll.toString(),"X",numOfFeedbackAll));			            
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
		return !items.isEmpty();
	}

	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(currentIndex, currentIndex, Progress.ENTITIES) };
	}

	@Override
	public void getNext(JCas jcas) throws IOException, CollectionException {
		SRAFeedbackItem item = items.poll();
		getLogger().debug(item);
		
		try {
			
			jcas.setDocumentLanguage(language);
			jcas.setDocumentText(item.getFeedback());
			DocumentMetaData dmd = DocumentMetaData.create(jcas);
			//TODO: The name of the getters und setters must be meaningful
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
	
	//read value of a cell in excel with given row and column values
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
