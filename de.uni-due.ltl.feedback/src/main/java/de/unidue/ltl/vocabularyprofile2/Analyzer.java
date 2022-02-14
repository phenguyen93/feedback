package de.unidue.ltl.vocabularyprofile2;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.PennTree;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;

import de.unidue.ltl.escrito.core.types.GrammarProfile;
import de.unidue.ltl.escrito.core.types.VocabularyProfile;
import de.unidue.ltl.feedback.Vocabulary;

public class Analyzer extends JCasAnnotator_ImplBase {
	
	double sumNone = 0;
	double sum1 = 0;
	double sum2 = 0;
	double sum3 = 0;
	double sum4 = 0;
	double sum5 = 0;
	double sum6 = 0;
	double sumRest= 0;
	
	Map<String, Object[]> data = new TreeMap<String, Object[]>();
	int i = 1;
	

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		/*
		 * // TODO Auto-generated method stub int numberOfTokens =
		 * JCasUtil.select(aJCas, POS.class).size(); //get number of tokens without
		 * punctuation int numberOfPunc =0; Collection<Token> tokens =
		 * JCasUtil.select(aJCas, Token.class); for (Token t: tokens) {
		 * if(t.getPos().getCoarseValue()!=null) {
		 * if(t.getPos().getCoarseValue().equals("PUNCT")) { numberOfPunc +=1; } } } int
		 * numberOfTokensWithoutPunct = numberOfTokens-numberOfPunc;
		 */
		/*
		 * int numberOf1 = 0; int numberOf2 = 0; int numberOf3 = 0; int numberOf4 = 0;
		 * int numberOf5 = 0; int numberOf6 = 0; int numberOfRest=0;
		 */
	
		Collection<VocabularyProfile> vps = JCasUtil.select(aJCas, VocabularyProfile.class);
		for (VocabularyProfile vp : vps){
			
			
			/*
			 * if(vp.getLevel().equals("Rang1")) { numberOf1 += 1; }
			 * if(vp.getLevel().equals("Rang2")) { numberOf2 += 1; }
			 * if(vp.getLevel().equals("Rang3")) { numberOf3 += 1; }
			 * if(vp.getLevel().equals("Rang4")) { numberOf4 += 1; }
			 * if(vp.getLevel().equals("Rang5")) { numberOf5 += 1; }
			 * if(vp.getLevel().equals("Rang6")) { numberOf6 += 1; }
			 * if(vp.getLevel().equals("Rang7")||vp.getLevel().equals("Rang8")||vp.getLevel(
			 * ).equals("Rang9")||vp.getLevel().equals("Rang10")||vp.getLevel().equals(
			 * "Rang11")
			 * ||vp.getLevel().equals("Rang12")||vp.getLevel().equals("Rang13")||vp.getLevel
			 * ().equals("Rang14")||vp.getLevel().equals("Rang15")||vp.getLevel().equals(
			 * "Rang16")
			 * ||vp.getLevel().equals("Rang17")||vp.getLevel().equals("Rang18")||vp.getLevel
			 * ().equals("Rang19")||vp.getLevel().equals("Rang20")||vp.getLevel().equals(
			 * "Rang21")
			 * ||vp.getLevel().equals("Rang22")||vp.getLevel().equals("Rang23")||vp.getLevel
			 * ().equals("Rang24")||vp.getLevel().equals("Rang25")) { numberOfRest += 1; }
			 */
		  
		  
		  
		  if(vp.getLevel().equals("No")) { 
			  data.put(String.valueOf(i), new Object[] {i, vp.getCoveredText(), vp.getName()});
			  i++;	
		  }
		}
		
		//create Exel File
        XSSFWorkbook workbook = new XSSFWorkbook(); 
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("source-dependent");
          
        //This data needs to be written (Object[])
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
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
            FileOutputStream out = new FileOutputStream(new File("D:\\BA_ExcelData\\NoLevelsourcedpd.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("written successfully on disk.");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	  
		
		

		
		/*
		 * double r1 = (1.0*numberOf1)/numberOfTokensWithoutPunct; double r2 =
		 * (1.0*numberOf2)/numberOfTokensWithoutPunct; double r3 =
		 * (1.0*numberOf3)/numberOfTokensWithoutPunct; double r4 =
		 * (1.0*numberOf4)/numberOfTokensWithoutPunct; double r5 =
		 * (1.0*numberOf5)/numberOfTokensWithoutPunct; double r6 =
		 * (1.0*numberOf6)/numberOfTokensWithoutPunct; double rest =
		 * (1.0*numberOfRest)/numberOfTokensWithoutPunct; double none =
		 * (1.0*(numberOfTokensWithoutPunct-numberOf1-numberOf2-numberOf3-numberOf4-
		 * numberOf5-numberOf6-numberOfRest))/numberOfTokensWithoutPunct; sumNone +=
		 * none; sum1 +=r1; sum2 +=r2; sum3 +=r3; sum4 +=r4; sum5 +=r5; sum6 +=r6;
		 * sumRest +=rest; int anzahl = 39; System.out.println("none: "+
		 * sumNone/anzahl); System.out.println("1: "+ sum1/anzahl);
		 * System.out.println("2: "+ sum2/anzahl); System.out.println("3: "+
		 * sum3/anzahl); System.out.println("4: "+ sum4/anzahl);
		 * System.out.println("5: "+ sum5/anzahl); System.out.println("6: "+
		 * sum6/anzahl); System.out.println("7: "+ sumRest/anzahl);
		 */
		
	}	
}
