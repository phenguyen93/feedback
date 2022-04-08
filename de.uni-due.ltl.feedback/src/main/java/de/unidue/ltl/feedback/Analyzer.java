package de.unidue.ltl.feedback;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.ltl.escrito.core.types.Feedback;

public class Analyzer extends JCasAnnotator_ImplBase {
	
	public static final String PARAM_OUTPUT_FILE = "InputFile";
	@ConfigurationParameter(name = PARAM_OUTPUT_FILE, mandatory = true)
	protected String outputFileString;
	protected URL outputFileURL;

	static Map<String, Object[]> data; 
	static Map<String, Object[]> fdata; 
	int index = 1;	
	@Override
	public void initialize(final UimaContext context) throws ResourceInitializationException {
	    super.initialize(context);
	    data =  new TreeMap<String, Object[]>();
	    //create label for excel file
	    data.put("0", new Object[] {" ","Prompt ID","Label", "NumOfSentences", "NumOfWords","Words/sentence","UniqueWord","Type-TokenRatio",
	    		"NumOfContentWords","Content-TokenRatio","OverlapWithAnswer(%)","OverlapWithQuestion(%)","OverlapWithTargetAnswer(%)","Non Overlap Words"});	   
	}	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
		Feedback fb = JCasUtil.selectSingle(aJCas, Feedback.class);
		String promptId = fb.getPromptId();
		String feedbackText = fb.getFeedback();
		String labelText = fb.getLabel();
		String answerText = fb.getAnswer();
		String questionText = fb.getQuestion();
		String targetAnswerText = fb.getTargetAnswer();
		int numberOfFeedback = fb.getNumOfFeedback();
		
		Collection<Sentence> sentences = JCasUtil.select(aJCas, Sentence.class);
		int numOfSentence = sentences.size();
//		double numOfSentenceNormalize = (double) numOfSentence/numberOfFeedback;
		
		Collection<Token> tokens = JCasUtil.select(aJCas, Token.class);
		
		StringBuilder sbAllWords = new StringBuilder();
		StringBuilder sbContentWords = new StringBuilder();
		int tokenWithoutPunc = 0;
		
		for (Token token : tokens){		
			if(!token.getPos().getCoarseValue().equals("PUNCT")) {
				sbAllWords.append(token.getCoveredText());
				sbAllWords.append(" ");
				tokenWithoutPunc+=1;				
			}	
			if(token.getPos().getCoarseValue().equals("ADJ")||token.getPos().getCoarseValue().equals("NOUN")||
					token.getPos().getCoarseValue().equals("VERB")||token.getPos().getCoarseValue().equals("ADV")) {
				sbContentWords.append(token.getCoveredText());
				sbContentWords.append(" ");	
			}	
		}
		
		String textWithoutPunc = sbAllWords.toString();	
		int numOfWord = numberOfWord(textWithoutPunc);
//		double numOfWordNormalize = (double) numOfWord/numberOfFeedback;
		
		System.out.println("Anzahl der Wörter: "+ numOfWord+"<<<<<" +tokenWithoutPunc);
		double wordsPerSentence = (double) numOfWord/numOfSentence;
				
		int numOfUniqueWords = numberOfUniqueWords(textWithoutPunc);
		double typeTokenRate = (double) numOfUniqueWords/numOfWord;
		
		String textWithOnlyContentWords = sbContentWords.toString();		
//		System.out.println("----Printing content text-----:"+textWithOnlyContentWords);
		
		int numOfContentWord = numberOfWord(textWithOnlyContentWords);
		double contentTokenRate = (double)numOfContentWord/numOfWord;
		
//		double numOfContentWordNormalize = (double) numOfContentWord/numberOfFeedback;						
//		String top5MostFrequentWords = mostFrequentWords(textWithOnlyContentWords); 
		
		int numberOfUniqueWordInContentText = numberOfWord(uniqueTextWithoutPunc(textWithOnlyContentWords));
		
		int numOfWordOverlapWithQuestion = numOfOverlap(uniqueTextWithoutPunc(textWithOnlyContentWords), uniqueTextWithoutPunc(questionText));
		double overlapWithQuestionRatio = (double)numOfWordOverlapWithQuestion/numberOfUniqueWordInContentText;
		
		int numOfWordOverlapWithTargetAnswer = numOfOverlap(uniqueTextWithoutPunc(textWithOnlyContentWords), uniqueTextWithoutPunc(targetAnswerText));
		double overlapWithTargetAnswerRatio = (double)numOfWordOverlapWithTargetAnswer/numberOfUniqueWordInContentText;
		
		int numOfWordOverlapWithAnswer = numOfOverlap(uniqueTextWithoutPunc(textWithOnlyContentWords), uniqueTextWithoutPunc(answerText));
		double overlapWithAnswerRatio = (double)numOfWordOverlapWithAnswer/numberOfUniqueWordInContentText;
		
		//get List of overlap words with question
		List<String> overlapWithQuestion = overlapWordsList(uniqueTextWithoutPunc(feedbackText), uniqueTextWithoutPunc(questionText));
		//get List of overlap words with target answer
		List<String> overlapWithTargetAnswer = overlapWordsList(uniqueTextWithoutPunc(feedbackText), uniqueTextWithoutPunc(targetAnswerText));
		//get List of overlap words with answer
		List<String> overlapWithAnswer = overlapWordsList(uniqueTextWithoutPunc(feedbackText), uniqueTextWithoutPunc(answerText));
		//create List of all overlap words
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(overlapWithQuestion);
		list.addAll(overlapWithTargetAnswer);
		list.addAll(overlapWithAnswer);
		String allOverlapText = String.join(" ", list);
		String nonOverlapWords = nonOverlapWords(uniqueTextWithoutPunc(feedbackText), allOverlapText);
						
		//add to map to export parameters to excel file
		data.put(String.valueOf(index),new Object[] {index,promptId,labelText,numOfSentence,
				numOfWord,wordsPerSentence,numOfUniqueWords,typeTokenRate,numOfContentWord,contentTokenRate,
				overlapWithAnswerRatio,overlapWithQuestionRatio,overlapWithTargetAnswerRatio,nonOverlapWords});
		index++;				
	}
		
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		//TODO adjust where to export data
		String fileName = outputFileString;
		String sheetName = "Feedback Daten";
		exportExcelData(data,sheetName,fileName);							
	}
	
	//export to excel file
	public static void exportExcelData(Map<String,Object[]> map,String sheetName, String fileName) {
		//create Exel File
        XSSFWorkbook workbook = new XSSFWorkbook();          
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet(sheetName);
        //This data needs to be written (Object[])
        //Iterate over data and write to sheet
        Set<String> keyset = map.keySet();
        int rownum = 1;
        for (String key : keyset){
            Row row = sheet.createRow(rownum++);
            Object [] objArr = map.get(key);
            int cellnum = 0;
            for (Object obj : objArr){
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
            }
        }
        try{
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();
            System.out.println("written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }           			
	}
	 //calculate number of unique words from text
	public static int numberOfUniqueWords(String text) {
		
		 String[] strWithoutPunc = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");		    
		 String wordWithoutPunc = String.join(" ", strWithoutPunc);			    			    
		 String[] words = wordWithoutPunc.toLowerCase().split(" ");		         		        
		 HashSet<String> uniqueWords = new HashSet<String>(Arrays.asList(words));
		 int num = uniqueWords.size();
		 return num;
	}
	//calculate number of words
	public static int numberOfWord(String text) {
//		String[] strWithoutPunc = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		String[] strWithoutPunc = text.toLowerCase().split(" ");
		return strWithoutPunc.length ;
	}
	
	//generate a hashmap with word frequency from a text
	 public static HashMap<String, Integer> wordFrequency (String text){		 
//		 String[] strWithoutPunc = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");		    
//  	 String textWithoutPunc = String.join(" ", strWithoutPunc);	
		 String[] arr = text.toLowerCase().split(" ");
    	 int [] fr = new int [arr.length];
         int visited = -1;
         for (int i = 0; i < arr.length; i++) {
 			int count = 1;
 			for (int j = i+1; j < arr.length; j++) {
 				if(arr[i].equals(arr[j])) {
 					count++;
 					//To avoid counting same element again  
                     fr[j] = visited;  
 				}
 			}
 			if(fr[i]!=visited) {
 				fr[i]=count;
 			}
 		}
         HashMap<String, Integer> hm = new HashMap<String, Integer>();
         for(int i = 0; i < fr.length; i++){  
         	if(fr[i] != visited)  
         		hm.put(arr[i], fr[i]);
         }
         
         return hm;
     }
	 
	 //get key by value of Hashmap
	 public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
 	    Set<T> keys = new HashSet<T>();
 	    for (Entry<T, E> entry : map.entrySet()) {
 	        if (Objects.equals(value, entry.getValue())) {
 	            keys.add(entry.getKey());
 	        }
 	    }
 	    return keys;
	 }
	 //get max value of Hashmap
	 public static <K, V extends Comparable<V>> V maxUsingCollectionsMax(Map<K, V> map) {
 	    Entry<K, V> maxEntry = Collections.max(map.entrySet(), new Comparator<Entry<K, V>>() {
 	        public int compare(Entry<K, V> e1, Entry<K, V> e2) {
 	            return e1.getValue()
 	                .compareTo(e2.getValue());
 	        }
 	    });
 	    return maxEntry.getValue();
 	}
	 
	 //get top 5 max values of HashMap
	 public static int [] top5maxValues(Map<String,Integer>map) {
		 Map.Entry<String, Integer> max1 = null;
   		 Map.Entry<String, Integer> max2 = null;
   		 Map.Entry<String, Integer> max3 = null;
   		 Map.Entry<String, Integer> max4 = null;
   		 Map.Entry<String, Integer> max5 = null;

   	      //searching the first biggest value
   	      for(Map.Entry<String, Integer> en : map.entrySet()){
   	          if (max1 == null || en.getValue().compareTo(max1.getValue()) > 0){
   	              max1 = en;
   	              }                   
   	      }
   	      //searching the second biggest value
   	      for(Map.Entry<String, Integer> en : map.entrySet()){
   	    	if (en != max1 &&(max2 == null || en.getValue().compareTo(max2.getValue()) > 0 
   	              && en.getValue().compareTo(max1.getValue())< 0)) {
   	         max2 = en;
   	    	}              
   	      }
   	      for(Map.Entry<String, Integer> en : map.entrySet()){
  	    	if ((en != max1 && en != max2)&& (max3 == null || en.getValue().compareTo(max3.getValue()) > 0 
  	              && en.getValue().compareTo(max2.getValue())< 0 && en.getValue().compareTo(max1.getValue())< 0)) {
  	         max3 = en;
  	    	}              
  	      }
   	      
   	      for(Map.Entry<String, Integer> en : map.entrySet()){
 	    	if ((en != max1 && en != max2 && en != max3) && (max4 == null || en.getValue().compareTo(max4.getValue()) > 0 
 	              && en.getValue().compareTo(max3.getValue())< 0 && en.getValue().compareTo(max2.getValue())< 0
 	             && en.getValue().compareTo(max1.getValue())< 0)) {
 	         max4 = en;
 	    	}              
 	      }
   	   
   	      for(Map.Entry<String, Integer> en : map.entrySet()){
	    	if ((en != max1 && en != max2 && en != max3&& en != max4) && (max5 == null || en.getValue().compareTo(max5.getValue()) > 0 
	              && en.getValue().compareTo(max4.getValue())< 0 && en.getValue().compareTo(max3.getValue())< 0
	              && en.getValue().compareTo(max2.getValue())< 0 && en.getValue().compareTo(max1.getValue())< 0)) {
	         max5 = en;
	    	}              
	      }
   	      int [] temp = {max1.getValue(),max2.getValue(),max3.getValue(),max4.getValue(),max5.getValue()}; 
   	      return temp;
	 }
	// get top 5 most frequent words from a text 
	public static String  mostFrequentWords(String text) {
		//get a hashmap with word frequency
		HashMap<String,Integer> hm = wordFrequency(text);
		//get top 5 max value of the hashmap
		int [] num = top5maxValues(hm);
		int max = num[0];
		int max2 = num[1];
		int max3 = num[2];
		int max4 = num[3];
		int max5 = num[4];
		
		
		Set<String> set1 = getKeysByValue(hm,max);
		StringBuilder sb = new StringBuilder();
		for(String w : set1) {
			sb.append(w);
			sb.append(" ");
		}
		Set<String> set2 = getKeysByValue(hm,max2);
		
		for(String w : set2) {
			sb.append(w);
			sb.append(" ");
		}
		Set<String> set3 = getKeysByValue(hm,max3);
		
		for(String w : set3) {
			sb.append(w);
			sb.append(" ");
		}
		Set<String> set4 = getKeysByValue(hm,max4);
		
		for(String w : set4) {
			sb.append(w);
			sb.append(" ");
		}
		Set<String> set5 = getKeysByValue(hm,max5);
		
		for(String w : set5) {
			sb.append(w);
			sb.append(" ");
		}
		return sb.toString();
	}
	
	//export to excel file
	public static void exportHashMap(HashMap<String,Object[]> map,String sheetName, String fileName) {
		//create Exel File
        XSSFWorkbook workbook = new XSSFWorkbook();          
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet(sheetName);
        //This data needs to be written (Object[])
        //Iterate over data and write to sheet
        Set<String> keyset = map.keySet();
        int rownum = 1;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = map.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();
            System.out.println("written successfully on disk.");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
           			
	}
	
	//calculate the number of similar words from 2 text
	public static int numOfOverlap(String s1, String s2) {

		  int num = 0;
		  String[] a = s1.split(" ");
		  String[] b = s2.split(" ");

		  for (int i = 0; i < a.length; i++) {
		      for (int j = 0; j < b.length; j++) {
		          if (a[i].equals(b[j])) {
		              num++;
		          }
		      }
		  }

		  return num;
	}
	//create a new text without punctuation from a given text
  	public static String textWithoutPunc(String text) {
  		
  		 String[] strWithoutPunc = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");		    
  		 return String.join(" ", strWithoutPunc);			    			    
  		
  	}
  	
  	// create text of unique word without punc from a given text
  	public static String uniqueTextWithoutPunc(String text) {
  		
  		 String[] strWithoutPunc = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");		    
  		 String wordWithoutPunc = String.join(" ", strWithoutPunc);			    			    
  		 String[] words = wordWithoutPunc.split(" ");		         		        
  		 HashSet<String> uniqueWords = new HashSet<String>(Arrays.asList(words));
  		 int num = uniqueWords.size();
  		 StringBuilder sb = new StringBuilder();

  		 Iterator<String> iterator = uniqueWords.iterator();
  		 String lastElement = "";
  		 while(iterator.hasNext()){
  	        lastElement = iterator.next();
  		 }
//	  		 System.out.println(lastElement);
  		 for(String a : uniqueWords) {
  			 if(!a.equals(lastElement)) {
  				 sb.append(a);
  				sb.append(" ");
  			 }else {
  				 sb.append(a);
  				 sb.append("");
	  			 }
  		 }
  		 return sb.toString();	  		 	  		 
  	}
  	// get list of overlap words
 	public static List<String> overlapWordsList(String s1, String s2) {
 		ArrayList<String> list = new ArrayList<String>();
 		String result = s1;
 		
 	    int num = 0;
 	    String[] a = s1.split(" ");
 	    String[] b = s2.split(" ");

 	    for (int i = 0; i < a.length; i++) {
 	        for (int j = 0; j < b.length; j++) {
 	            if (a[i].equals(b[j])) {
 	        	    list.add(a[i]);
 	                num++;
 	            }
 	        }
 	    }
 	    return list;	    
 	}
 	// To remove a word from a String
    public static String remove(String str, String word){        
        String msg[] = str.split(" ");
        String new_str = "";
        for (String words : msg) {         
            if (!words.equals(word)) { 
                new_str += words + " ";
            }
        } 
        return new_str;
    }
    //return not-ovelap words
  	public static String nonOverlapWords(String s1, String s2) {
  		ArrayList<String> list = new ArrayList<String>();
  		String result = s1;
  		
  	    String[] a = s1.split(" ");
  	    ArrayList<String> arrList = new ArrayList<String>();
  	    for (int i = 0; i < a.length; i++) {
  			arrList.add(a[i]);
  		}
  	    String[] b = s2.split(" ");

  	    for (int i = 0; i < a.length; i++) {
  	        for (int j = 0; j < b.length; j++) {
  	            if (a[i].equals(b[j])) {
  	        	    list.add(a[i]);
  	            }
  	        }
  	    }
  	    for (int i = 0; i < list.size(); i++) {
  			result =remove(result,list.get(i));
  		}
  	    //replace 2 or more spaces with single space
  	    result = result.trim().replaceAll(" +", " ");
  	    
  	    return result;
  	    
  	}
    
}
