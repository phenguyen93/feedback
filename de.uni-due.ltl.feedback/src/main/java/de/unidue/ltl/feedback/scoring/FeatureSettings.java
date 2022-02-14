package de.unidue.ltl.feedback.scoring;

import org.dkpro.lab.task.Dimension;
import org.dkpro.tc.api.features.TcFeatureFactory;
import org.dkpro.tc.api.features.TcFeatureSet;
import org.dkpro.tc.core.Constants;
import org.dkpro.tc.features.ngram.WordNGram;

import de.unidue.ltl.feedback.features.CVRangRatio;
import de.unidue.ltl.feedback.features.CVRangRatio2;
import de.unidue.ltl.feedback.features.EVPLevelRatio;
import de.unidue.ltl.feedback.features.NrOfTokens;
import de.unidue.ltl.feedback.features.TypeBasedTokenRatioCV;
import de.unidue.ltl.feedback.features.TypeBasedTokenRatioEVP;

public class FeatureSettings implements Constants {

	
	public static Dimension<TcFeatureSet> getFeatureSetNGrams()
	{
		Dimension<TcFeatureSet> dimFeatureSets = Dimension.create(
				DIM_FEATURE_SET,
				new TcFeatureSet(
						TcFeatureFactory.create(
								WordNGram.class,
								WordNGram.PARAM_NGRAM_MIN_N, 1,
								WordNGram.PARAM_NGRAM_MAX_N, 3,
								WordNGram.PARAM_NGRAM_USE_TOP_K, 10000
								)
						)
				);
		return dimFeatureSets;
	}	
	public static Dimension<TcFeatureSet> getFeatureVocabAndNgram()
	{
		Dimension<TcFeatureSet> dimFeatureSets = Dimension.create(
				DIM_FEATURE_SET,
				new TcFeatureSet(
						
						TcFeatureFactory.create( WordNGram.class, WordNGram.PARAM_NGRAM_MIN_N, 1,
						  WordNGram.PARAM_NGRAM_MAX_N, 3, WordNGram.PARAM_NGRAM_USE_TOP_K, 10000 ),
						 
						TcFeatureFactory.create(
								EVPLevelRatio.class
									
						)
				)
		);
		return dimFeatureSets;
	}	
	public static Dimension<TcFeatureSet> getFeatureEVP()
	{
		Dimension<TcFeatureSet> dimFeatureSets = Dimension.create(
				DIM_FEATURE_SET,
				new TcFeatureSet(
						
						TcFeatureFactory.create(
								EVPLevelRatio.class
									
						)
				)
		);
		return dimFeatureSets;
	}
	
	public static Dimension<TcFeatureSet> getFeatureTypeBasedEVP()
	{
		Dimension<TcFeatureSet> dimFeatureSets = Dimension.create(
				DIM_FEATURE_SET,
				new TcFeatureSet(
						
						TcFeatureFactory.create(
								TypeBasedTokenRatioEVP.class
									
						)
				)
		);
		return dimFeatureSets;
	}
	
	public static Dimension<TcFeatureSet> getFeatureTypeBasedEVPAndNgram()
	{
		Dimension<TcFeatureSet> dimFeatureSets = Dimension.create(
				DIM_FEATURE_SET,
				new TcFeatureSet(
						TcFeatureFactory.create( WordNGram.class, WordNGram.PARAM_NGRAM_MIN_N, 1,
								  WordNGram.PARAM_NGRAM_MAX_N, 3, WordNGram.PARAM_NGRAM_USE_TOP_K, 10000 ),
						
						TcFeatureFactory.create(
								TypeBasedTokenRatioEVP.class
									
						)
				)
		);
		return dimFeatureSets;
	}
	
	public static Dimension<TcFeatureSet> getFeatureCVAndNgram()
	{
		Dimension<TcFeatureSet> dimFeatureSets = Dimension.create(
				DIM_FEATURE_SET,
				new TcFeatureSet(
						
						TcFeatureFactory.create( WordNGram.class, WordNGram.PARAM_NGRAM_MIN_N, 1,
						  WordNGram.PARAM_NGRAM_MAX_N, 3, WordNGram.PARAM_NGRAM_USE_TOP_K, 10000 ),
						 
						TcFeatureFactory.create(
								CVRangRatio2.class
									
						)
				)
		);
		return dimFeatureSets;
	}
	public static Dimension<TcFeatureSet> getFeatureCV()
	{
		Dimension<TcFeatureSet> dimFeatureSets = Dimension.create(
				DIM_FEATURE_SET,
				new TcFeatureSet(
						
						TcFeatureFactory.create(
								CVRangRatio2.class
									
						)
				)
		);
		return dimFeatureSets;
	}
	public static Dimension<TcFeatureSet> getFeatureTypeBasedCV()
	{
		Dimension<TcFeatureSet> dimFeatureSets = Dimension.create(
				DIM_FEATURE_SET,
				new TcFeatureSet(
						
						TcFeatureFactory.create(
								TypeBasedTokenRatioCV.class
									
						)
				)
		);
		return dimFeatureSets;
	}
	public static Dimension<TcFeatureSet> getFeatureTypeBasedCVAndNgram()
	{
		Dimension<TcFeatureSet> dimFeatureSets = Dimension.create(
				DIM_FEATURE_SET,
				new TcFeatureSet(
						TcFeatureFactory.create( WordNGram.class, WordNGram.PARAM_NGRAM_MIN_N, 1,
								  WordNGram.PARAM_NGRAM_MAX_N,3, WordNGram.PARAM_NGRAM_USE_TOP_K, 10000 ),
						
						TcFeatureFactory.create(
								TypeBasedTokenRatioCV.class
									
						)
						
				)
		);
		return dimFeatureSets;
	}
	
	
	
}
