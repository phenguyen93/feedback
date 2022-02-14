package de.unidue.ltl.feedback.features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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

/*
 * Ratio of a particular POS to total number of tokens
 * Currently implemented: Noun ratio, verb ratio
 */

@TypeCapability(inputs = { "de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS" })
public class EVPLevelRatio extends FeatureExtractorResource_ImplBase implements FeatureExtractor
{
	public static final String FN_A1Ratio = "A1Ratio";
	public static final String FN_A2Ratio = "A2Ratio";
	public static final String FN_B1Ratio = "B1Ratio";
	public static final String FN_B2Ratio = "B2Ratio";
	public static final String FN_C1Ratio = "C1Ratio";
	public static final String FN_C2Ratio = "C2Ratio";
	public static final String FN_NoneRatio = "NoneRatio";
	

	@Override
	public Set<Feature> extract(JCas jcas, TextClassificationTarget aTarget)
			throws TextClassificationException
	{
		
		int numberOfTokens = JCasUtil.select(jcas, POS.class).size();
		int numberOfPunc = 0;
		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
		for (Token t: tokens) {
			if(t.getPos().getCoarseValue()!=null) {
				if(t.getPos().getCoarseValue().equals("PUNCT")) {
					numberOfPunc +=1;
				}
			}
			
		}
		int numberOfTokensWithoutPunct = numberOfTokens-numberOfPunc;
		
		
		int numberOfA1 = 0;
		int numberOfA2 = 0;
		int numberOfB1 = 0;
		int numberOfB2 = 0;
		int numberOfC1 = 0;
		int numberOfC2 = 0;
		int numberOfNone = 0;
	
		Collection<VocabularyProfile> vps = JCasUtil.select(jcas, VocabularyProfile.class);
		for (VocabularyProfile vp : vps){
			
			
			  if(vp.getLevel().equals("A1")) { numberOfA1 += 1; }
			  if(vp.getLevel().equals("A2")) { numberOfA2 += 1; }
			  if(vp.getLevel().equals("B1")) { numberOfB1 += 1; }
			  if(vp.getLevel().equals("B2")) { numberOfB2 += 1; }
			  if(vp.getLevel().equals("C1")) { numberOfC1 += 1; }
			  if(vp.getLevel().equals("C2")) { numberOfC2 += 1; }
			  if(vp.getLevel().equals("No")) { numberOfNone += 1; }
			 
		}

		
		double a1 = (1.0*numberOfA1)/numberOfTokensWithoutPunct;
		double a2 = (1.0*numberOfA2)/numberOfTokensWithoutPunct;
		double b1 = (1.0*numberOfB1)/numberOfTokensWithoutPunct;
		double b2 = (1.0*numberOfB2)/numberOfTokensWithoutPunct;
		double c1 = (1.0*numberOfC1)/numberOfTokensWithoutPunct;
		double c2 = (1.0*numberOfC2)/numberOfTokensWithoutPunct;
		double none = (1.0*numberOfNone)/numberOfTokensWithoutPunct;
		
		
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
