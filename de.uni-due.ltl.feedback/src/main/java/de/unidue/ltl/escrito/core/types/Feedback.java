

/* First created by JCasGen Thu May 20 14:40:01 CEST 2021 */
package de.unidue.ltl.escrito.core.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri May 21 18:45:18 CEST 2021
 * XML source: /Users/andrea/Documents/Programmierung/workspace_ASAS/de.uni-due.ltl.Feedback/src/main/resources/desc/type/Escrito.xml
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
  //* Feature: id

  /** getter for id - gets 
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "de.unidue.ltl.escrito.core.types.Feedback");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (Feedback_Type.featOkTst && ((Feedback_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "de.unidue.ltl.escrito.core.types.Feedback");
    jcasType.ll_cas.ll_setStringValue(addr, ((Feedback_Type)jcasType).casFeatCode_id, v);}    
  }

    