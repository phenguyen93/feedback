

/* First created by JCasGen Wed Apr 06 16:57:33 CEST 2022 */
package de.unidue.ltl.escrito.core.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Apr 08 13:29:28 CEST 2022
 * XML source: C:/Users/ENVY/git/feedback/de.uni-due.ltl.feedback/src/main/resources/desc/type/Escrito.xml
 * @generated */
public class Feedback extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Feedback.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Feedback() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Feedback(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Feedback(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Feedback(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: promptId

  /** getter for promptId - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPromptId() {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_promptId == null)
      jcasType.jcas.throwFeatMissing("promptId", "de.unidue.ltl.escrito.core.types.Feedback");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_promptId);}
    
  /** setter for promptId - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPromptId(String v) {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_promptId == null)
      jcasType.jcas.throwFeatMissing("promptId", "de.unidue.ltl.escrito.core.types.Feedback");
    jcasType.ll_cas.ll_setStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_promptId, v);}    
   
    
  //*--------------*
  //* Feature: feedback

  /** getter for feedback - gets 
   * @generated
   * @return value of the feature 
   */
  public String getFeedback() {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_feedback == null)
      jcasType.jcas.throwFeatMissing("feedback", "de.unidue.ltl.escrito.core.types.Feedback");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_feedback);}
    
  /** setter for feedback - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFeedback(String v) {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_feedback == null)
      jcasType.jcas.throwFeatMissing("feedback", "de.unidue.ltl.escrito.core.types.Feedback");
    jcasType.ll_cas.ll_setStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_feedback, v);}    
   
    
  //*--------------*
  //* Feature: question

  /** getter for question - gets 
   * @generated
   * @return value of the feature 
   */
  public String getQuestion() {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_question == null)
      jcasType.jcas.throwFeatMissing("question", "de.unidue.ltl.escrito.core.types.Feedback");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_question);}
    
  /** setter for question - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuestion(String v) {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_question == null)
      jcasType.jcas.throwFeatMissing("question", "de.unidue.ltl.escrito.core.types.Feedback");
    jcasType.ll_cas.ll_setStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_question, v);}    
   
    
  //*--------------*
  //* Feature: targetAnswer

  /** getter for targetAnswer - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTargetAnswer() {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_targetAnswer == null)
      jcasType.jcas.throwFeatMissing("targetAnswer", "de.unidue.ltl.escrito.core.types.Feedback");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_targetAnswer);}
    
  /** setter for targetAnswer - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTargetAnswer(String v) {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_targetAnswer == null)
      jcasType.jcas.throwFeatMissing("targetAnswer", "de.unidue.ltl.escrito.core.types.Feedback");
    jcasType.ll_cas.ll_setStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_targetAnswer, v);}    
   
    
  //*--------------*
  //* Feature: answer

  /** getter for answer - gets 
   * @generated
   * @return value of the feature 
   */
  public String getAnswer() {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_answer == null)
      jcasType.jcas.throwFeatMissing("answer", "de.unidue.ltl.escrito.core.types.Feedback");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_answer);}
    
  /** setter for answer - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnswer(String v) {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_answer == null)
      jcasType.jcas.throwFeatMissing("answer", "de.unidue.ltl.escrito.core.types.Feedback");
    jcasType.ll_cas.ll_setStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_answer, v);}    
   
    
  //*--------------*
  //* Feature: label

  /** getter for label - gets 
   * @generated
   * @return value of the feature 
   */
  public String getLabel() {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "de.unidue.ltl.escrito.core.types.Feedback");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLabel(String v) {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "de.unidue.ltl.escrito.core.types.Feedback");
    jcasType.ll_cas.ll_setStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_label, v);}    
   
    
  //*--------------*
  //* Feature: numOfFeedback

  /** getter for numOfFeedback - gets 
   * @generated
   * @return value of the feature 
   */
  public int getNumOfFeedback() {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_numOfFeedback == null)
      jcasType.jcas.throwFeatMissing("numOfFeedback", "de.unidue.ltl.escrito.core.types.Feedback");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Feedback_Type)jcasType).casFeatCode_numOfFeedback);}
    
  /** setter for numOfFeedback - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setNumOfFeedback(int v) {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_numOfFeedback == null)
      jcasType.jcas.throwFeatMissing("numOfFeedback", "de.unidue.ltl.escrito.core.types.Feedback");
    jcasType.ll_cas.ll_setIntValue(addr, ((Feedback_Type)jcasType).casFeatCode_numOfFeedback, v);}    
  }

    