
/* First created by JCasGen Wed Apr 06 16:57:33 CEST 2022 */
package de.unidue.ltl.escrito.core.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Fri Apr 08 13:29:28 CEST 2022
 * @generated */
public class Feedback_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Feedback.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unidue.ltl.escrito.core.types.Feedback");
 
  /** @generated */
  final Feature casFeat_promptId;
  /** @generated */
  final int     casFeatCode_promptId;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getPromptId(int addr) {
        if (featOkTst && casFeat_promptId == null)
      jcas.throwFeatMissing("promptId", "de.unidue.ltl.escrito.core.types.Feedback");
    return ll_cas.ll_getStringValue(addr, casFeatCode_promptId);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPromptId(int addr, String v) {
        if (featOkTst && casFeat_promptId == null)
      jcas.throwFeatMissing("promptId", "de.unidue.ltl.escrito.core.types.Feedback");
    ll_cas.ll_setStringValue(addr, casFeatCode_promptId, v);}
    
  
 
  /** @generated */
  final Feature casFeat_feedback;
  /** @generated */
  final int     casFeatCode_feedback;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getFeedback(int addr) {
        if (featOkTst && casFeat_feedback == null)
      jcas.throwFeatMissing("feedback", "de.unidue.ltl.escrito.core.types.Feedback");
    return ll_cas.ll_getStringValue(addr, casFeatCode_feedback);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFeedback(int addr, String v) {
        if (featOkTst && casFeat_feedback == null)
      jcas.throwFeatMissing("feedback", "de.unidue.ltl.escrito.core.types.Feedback");
    ll_cas.ll_setStringValue(addr, casFeatCode_feedback, v);}
    
  
 
  /** @generated */
  final Feature casFeat_question;
  /** @generated */
  final int     casFeatCode_question;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getQuestion(int addr) {
        if (featOkTst && casFeat_question == null)
      jcas.throwFeatMissing("question", "de.unidue.ltl.escrito.core.types.Feedback");
    return ll_cas.ll_getStringValue(addr, casFeatCode_question);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuestion(int addr, String v) {
        if (featOkTst && casFeat_question == null)
      jcas.throwFeatMissing("question", "de.unidue.ltl.escrito.core.types.Feedback");
    ll_cas.ll_setStringValue(addr, casFeatCode_question, v);}
    
  
 
  /** @generated */
  final Feature casFeat_targetAnswer;
  /** @generated */
  final int     casFeatCode_targetAnswer;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTargetAnswer(int addr) {
        if (featOkTst && casFeat_targetAnswer == null)
      jcas.throwFeatMissing("targetAnswer", "de.unidue.ltl.escrito.core.types.Feedback");
    return ll_cas.ll_getStringValue(addr, casFeatCode_targetAnswer);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTargetAnswer(int addr, String v) {
        if (featOkTst && casFeat_targetAnswer == null)
      jcas.throwFeatMissing("targetAnswer", "de.unidue.ltl.escrito.core.types.Feedback");
    ll_cas.ll_setStringValue(addr, casFeatCode_targetAnswer, v);}
    
  
 
  /** @generated */
  final Feature casFeat_answer;
  /** @generated */
  final int     casFeatCode_answer;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAnswer(int addr) {
        if (featOkTst && casFeat_answer == null)
      jcas.throwFeatMissing("answer", "de.unidue.ltl.escrito.core.types.Feedback");
    return ll_cas.ll_getStringValue(addr, casFeatCode_answer);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnswer(int addr, String v) {
        if (featOkTst && casFeat_answer == null)
      jcas.throwFeatMissing("answer", "de.unidue.ltl.escrito.core.types.Feedback");
    ll_cas.ll_setStringValue(addr, casFeatCode_answer, v);}
    
  
 
  /** @generated */
  final Feature casFeat_label;
  /** @generated */
  final int     casFeatCode_label;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getLabel(int addr) {
        if (featOkTst && casFeat_label == null)
      jcas.throwFeatMissing("label", "de.unidue.ltl.escrito.core.types.Feedback");
    return ll_cas.ll_getStringValue(addr, casFeatCode_label);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setLabel(int addr, String v) {
        if (featOkTst && casFeat_label == null)
      jcas.throwFeatMissing("label", "de.unidue.ltl.escrito.core.types.Feedback");
    ll_cas.ll_setStringValue(addr, casFeatCode_label, v);}
    
  
 
  /** @generated */
  final Feature casFeat_numOfFeedback;
  /** @generated */
  final int     casFeatCode_numOfFeedback;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getNumOfFeedback(int addr) {
        if (featOkTst && casFeat_numOfFeedback == null)
      jcas.throwFeatMissing("numOfFeedback", "de.unidue.ltl.escrito.core.types.Feedback");
    return ll_cas.ll_getIntValue(addr, casFeatCode_numOfFeedback);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNumOfFeedback(int addr, int v) {
        if (featOkTst && casFeat_numOfFeedback == null)
      jcas.throwFeatMissing("numOfFeedback", "de.unidue.ltl.escrito.core.types.Feedback");
    ll_cas.ll_setIntValue(addr, casFeatCode_numOfFeedback, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Feedback_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_promptId = jcas.getRequiredFeatureDE(casType, "promptId", "uima.cas.String", featOkTst);
    casFeatCode_promptId  = (null == casFeat_promptId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_promptId).getCode();

 
    casFeat_feedback = jcas.getRequiredFeatureDE(casType, "feedback", "uima.cas.String", featOkTst);
    casFeatCode_feedback  = (null == casFeat_feedback) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_feedback).getCode();

 
    casFeat_question = jcas.getRequiredFeatureDE(casType, "question", "uima.cas.String", featOkTst);
    casFeatCode_question  = (null == casFeat_question) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_question).getCode();

 
    casFeat_targetAnswer = jcas.getRequiredFeatureDE(casType, "targetAnswer", "uima.cas.String", featOkTst);
    casFeatCode_targetAnswer  = (null == casFeat_targetAnswer) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_targetAnswer).getCode();

 
    casFeat_answer = jcas.getRequiredFeatureDE(casType, "answer", "uima.cas.String", featOkTst);
    casFeatCode_answer  = (null == casFeat_answer) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_answer).getCode();

 
    casFeat_label = jcas.getRequiredFeatureDE(casType, "label", "uima.cas.String", featOkTst);
    casFeatCode_label  = (null == casFeat_label) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_label).getCode();

 
    casFeat_numOfFeedback = jcas.getRequiredFeatureDE(casType, "numOfFeedback", "uima.cas.Integer", featOkTst);
    casFeatCode_numOfFeedback  = (null == casFeat_numOfFeedback) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_numOfFeedback).getCode();

  }
}



    