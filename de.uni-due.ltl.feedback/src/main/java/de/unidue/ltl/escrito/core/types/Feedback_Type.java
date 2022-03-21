

package de.unidue.ltl.escrito.core.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * 
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
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "de.unidue.ltl.escrito.core.types.Feedback");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "de.unidue.ltl.escrito.core.types.Feedback");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);}
  
  
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Feedback_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_feedback = jcas.getRequiredFeatureDE(casType, "feedback", "uima.cas.String", featOkTst);
    casFeatCode_feedback  = (null == casFeat_feedback) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_feedback).getCode();

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

  }
}



    