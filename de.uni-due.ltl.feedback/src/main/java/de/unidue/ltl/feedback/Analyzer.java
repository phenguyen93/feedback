package de.unidue.ltl.feedback;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.PennTree;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;
import de.unidue.ltl.escrito.core.types.GrammarProfile;
import de.unidue.ltl.escrito.core.types.VocabularyProfile;
import de.unidue.ltl.feedback.io.MewsItem;

public class Analyzer extends JCasAnnotator_ImplBase {

	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		DocumentMetaData meta = JCasUtil.selectSingle(aJCas, DocumentMetaData.class);
		String id = meta.getDocumentId();
		System.out.println("Printing PromptId: "+id);
		System.out.println("Printing feedback text:"+meta.getDocumentTitle());
		
		
		
		ArrayList<Integer> a = new ArrayList<>();
		Collection<Sentence> sentences = JCasUtil.select(aJCas, Sentence.class);
		System.out.println("Anzahl der Sätze: "+sentences.size());
		for (Sentence sentence : sentences){
			 String sentenceText = sentence.getCoveredText()+sentence.getCoveredText().length();
		//	System.out.println(numOfWord(sentenceText));
			a.add(numOfWord(sentenceText));
//			System.out.println("Sentence: X"+sentence.getCoveredText()+"X"+sentence.getCoveredText().length());
		}
		for (int i = 0; i < sentences.size(); i++) {
			
		}
		
		Collection<Token> tokens = JCasUtil.select(aJCas, Token.class);
		System.out.println("Anzahl der Tokens: "+ tokens.size());
		int numOfPunct = 0;
		for (Token token : tokens){
			if(token.getPos().getCoarseValue().equals("PUNCT"))
				numOfPunct +=1;
		//	System.out.println(token.getCoveredText() +  " "+ token.getPos().getPosValue() + " "+ token.getLemma().getValue());
		}
		int numOfWords = tokens.size()-numOfPunct;
		System.out.println("Anzahl der Wörter: "+ numOfWords);
		
//		Collection<Chunk> chunks = JCasUtil.select(aJCas, Chunk.class);
//		for (Chunk chunk : chunks){
//			System.out.println(chunk.getCoveredText() + " "+ chunk.getChunkValue());
//		}
//		Collection<PennTree> pennTrees = JCasUtil.select(aJCas, PennTree.class);
//		if (pennTrees.isEmpty()){
//			System.err.println("No Trees found!");
//			System.exit(-1);
//		}
//		for (PennTree penntree : pennTrees){
//			System.out.println("TREE: "+penntree.toString());
//		}
//		Collection<Dependency> dependencies = JCasUtil.select(aJCas, Dependency.class);
//		for (Dependency dep : dependencies){
//			System.out.println(dep.getGovernor().getCoveredText() + " " + dep.getDependencyType().toString() + " " + dep.getDependent().getCoveredText());
//		}		
//		for (GrammarAnomaly ga : JCasUtil.select(aJCas, GrammarAnomaly.class)){
//			System.out.println(ga.getCoveredText()+ ": "+ ga.getCategory()+ " - "+ga.getDescription());
//		}
		
		/*
		 * Collection<VocabularyProfile> vps = JCasUtil.select(aJCas,
		 * VocabularyProfile.class); for (VocabularyProfile vp : vps){
		 * System.out.println(vp.getCoveredText() + " - " +vp.getName() +
		 * " ("+vp.getLevel()+")"); }
		 */
	}
	
	//count number of word pro sentence	
	public static int numOfWord(String sentence) {
		
		String[] count_words= sentence.split(" ");
	    return count_words.length;
	}
	public static void exportFeedbackData(String Id, String numOfWord, String numOfSentence, String numOfWordInSentence)
    {
  
        
  
        // new file object
        File file = new File("D:/ProjectCopie/write.txt");
  
        BufferedWriter bf = null;
  
        try {
  
            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file));
            
           
            	bf.write(Id + ": "+ numOfSentence );
 
               // new line
               bf.newLine();
			
  
            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
  
            try {
  
                // always close the writer
                bf.close();
            }
            catch (Exception e) {
            }
        }
               
    }
	
	
	
}
