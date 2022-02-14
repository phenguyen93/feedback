package de.unidue.ltl.feedback.features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.fit.descriptor.TypeCapability;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractor;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.features.FeatureType;
import org.dkpro.tc.api.type.TextClassificationTarget;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.FrequencyDistribution;
import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.ltl.escrito.core.types.VocabularyProfile;
import de.unidue.ltl.feedback.Vocabulary;

/*
 * Ratio of a particular POS to total number of tokens
 * Currently implemented: Noun ratio, verb ratio
 */

@TypeCapability(inputs = { "de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS" })
public class TypeBasedTokenRatioEVP extends FeatureExtractorResource_ImplBase implements FeatureExtractor
{
	public static final String FN_A1Ratio = "A1Ratio";
	public static final String FN_A2Ratio = "A2Ratio";
	public static final String FN_B1Ratio = "B1Ratio";
	public static final String FN_B2Ratio = "B2Ratio";
	public static final String FN_C1Ratio = "C1Ratio";
	public static final String FN_C2Ratio = "C2Ratio";
	public static final String FN_NoneRatio = "NoneRatio";
	
	Map<Vocabulary, String> typeBasedVocab;
	

	@Override
	public Set<Feature> extract(JCas jcas, TextClassificationTarget aTarget)
			throws TextClassificationException
	{
		
		int numberOfTokens = JCasUtil.select(jcas, POS.class).size();
		//get number of tokens without punctuation
		int numberOfPunc =0;		
		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
		for (Token t: tokens) {
			if(t.getPos().getCoarseValue()!=null) {
				if(t.getPos().getCoarseValue().equals("PUNCT")) {
					numberOfPunc +=1;
				}
			}
		}
		int numberOfTokensWithoutPunct = numberOfTokens-numberOfPunc;
		
		int typeBasedA1 = 0;
		int typeBasedA2 = 0;
		int typeBasedB1 = 0;
		int typeBasedB2 = 0;
		int typeBasedC1 = 0;
		int typeBasedC2 = 0;
		
	
		Collection<VocabularyProfile> vps = JCasUtil.select(jcas, VocabularyProfile.class);
		
		typeBasedVocab = new HashMap<Vocabulary,String>();
		for (VocabularyProfile vp : vps){
			Vocabulary vo = new Vocabulary(vp.getCoveredText().toLowerCase(), vp.getName());
			typeBasedVocab.put(vo, vp.getLevel());
		}
		for(String level : typeBasedVocab.values()) {
			if(level.equals("A1")) {typeBasedA1 += 1; }
			if(level.equals("A2")) {typeBasedA2 += 1; }
			if(level.equals("B1")) {typeBasedB1 += 1; }
			if(level.equals("B2")) {typeBasedB2 += 1; }
			if(level.equals("C1")) {typeBasedC1+= 1; }
			if(level.equals("C2")) {typeBasedC2 += 1; }
		}
		int numberOfTypeBasedToken = typeBasedVocab.size() + (numberOfTokensWithoutPunct-vps.size());
		int numOfNone = numberOfTokensWithoutPunct-vps.size();
		
		double a1 = (1.0*typeBasedA1)/numberOfTypeBasedToken;
		double a2 = (1.0*typeBasedA2)/numberOfTypeBasedToken;
		double b1 = (1.0*typeBasedB1)/numberOfTypeBasedToken;
		double b2 = (1.0*typeBasedB2)/numberOfTypeBasedToken;
		double c1 = (1.0*typeBasedC1)/numberOfTypeBasedToken;
		double c2 = (1.0*typeBasedC2)/numberOfTypeBasedToken;
		double none = (1.0*numOfNone)/numberOfTypeBasedToken;
		
		
		Set<Feature> features = new HashSet<Feature>();
		features.add(new Feature(FN_A1Ratio, a1, FeatureType.NUMERIC));
		features.add(new Feature(FN_A2Ratio, a2, FeatureType.NUMERIC));
		features.add(new Feature(FN_B1Ratio, b1, FeatureType.NUMERIC));
		features.add(new Feature(FN_B2Ratio, b2, FeatureType.NUMERIC));
		features.add(new Feature(FN_C1Ratio, c1, FeatureType.NUMERIC));
		features.add(new Feature(FN_C2Ratio, c2, FeatureType.NUMERIC));
		features.add(new Feature(FN_NoneRatio, none, FeatureType.NUMERIC));
		return features;
	}

}
