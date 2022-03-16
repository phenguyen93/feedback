package de.unidue.ltl.escrito.core.types;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.tcas.DocumentAnnotation_Type;

/**
 * @generated
 */
public class DocumentData_Type
    extends DocumentAnnotation_Type
{
    /** @generated */
    @Override
    protected FSGenerator getFSGenerator()
    {
        return fsGenerator;
    }

    /** @generated */
    private final FSGenerator fsGenerator = new FSGenerator()
    {
        @Override
        public FeatureStructure createFS(int addr, CASImpl cas)
        {
            if (DocumentData_Type.this.useExistingInstance) {
                // Return eq fs instance if already created
                FeatureStructure fs = DocumentData_Type.this.jcas.getJfsFromCaddr(addr);
                if (null == fs) {
                    fs = new DocumentData(addr, DocumentData_Type.this);
                    DocumentData_Type.this.jcas.putJfsFromCaddr(addr, fs);
                    return fs;
                }
                return fs;
            }
            else {
                return new DocumentData(addr, DocumentData_Type.this);
            }
        }
    };
    /** @generated */
    public final static int typeIndexID = DocumentData.typeIndexID;
    /**
     * @generated
     * @modifiable
     */
    public final static boolean featOkTst = JCasRegistry
            .getFeatOkTst("de.unidue.ltl.escrito.core.types.DocumentData");

    /** @generated */
    final Feature casFeat_documentTitle;
    /** @generated */
    final int casFeatCode_documentTitle;

    /**
     * @generated
     * @param addr
     *            low level Feature Structure reference
     * @return the feature value
     */
    public String getDocumentTitle(int addr)
    {
        if (featOkTst && casFeat_documentTitle == null) {
            jcas.throwFeatMissing("documentTitle",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        return ll_cas.ll_getStringValue(addr, casFeatCode_documentTitle);
    }

    /**
     * @generated
     * @param addr
     *            low level Feature Structure reference
     * @param v
     *            value to set
     */
    public void setDocumentTitle(int addr, String v)
    {
        if (featOkTst && casFeat_documentTitle == null) {
            jcas.throwFeatMissing("documentTitle",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        ll_cas.ll_setStringValue(addr, casFeatCode_documentTitle, v);
    }

    /** @generated */
    final Feature casFeat_documentId;
    /** @generated */
    final int casFeatCode_documentId;

    /**
     * @generated
     * @param addr
     *            low level Feature Structure reference
     * @return the feature value
     */
    public String getDocumentId(int addr)
    {
        if (featOkTst && casFeat_documentId == null) {
            jcas.throwFeatMissing("documentId",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        return ll_cas.ll_getStringValue(addr, casFeatCode_documentId);
    }

    /**
     * @generated
     * @param addr
     *            low level Feature Structure reference
     * @param v
     *            value to set
     */
    public void setDocumentId(int addr, String v)
    {
        if (featOkTst && casFeat_documentId == null) {
            jcas.throwFeatMissing("documentId",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        ll_cas.ll_setStringValue(addr, casFeatCode_documentId, v);
    }

    /** @generated */
    final Feature casFeat_documentUri;
    /** @generated */
    final int casFeatCode_documentUri;

    /**
     * @generated
     * @param addr
     *            low level Feature Structure reference
     * @return the feature value
     */
    public String getDocumentUri(int addr)
    {
        if (featOkTst && casFeat_documentUri == null) {
            jcas.throwFeatMissing("documentUri",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        return ll_cas.ll_getStringValue(addr, casFeatCode_documentUri);
    }

    /**
     * @generated
     * @param addr
     *            low level Feature Structure reference
     * @param v
     *            value to set
     */
    public void setDocumentUri(int addr, String v)
    {
        if (featOkTst && casFeat_documentUri == null) {
            jcas.throwFeatMissing("documentUri",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        ll_cas.ll_setStringValue(addr, casFeatCode_documentUri, v);
    }

    /** @generated */
    final Feature casFeat_collectionId;
    /** @generated */
    final int casFeatCode_collectionId;

    /**
     * @generated
     * @param addr
     *            low level Feature Structure reference
     * @return the feature value
     */
    public String getCollectionId(int addr)
    {
        if (featOkTst && casFeat_collectionId == null) {
            jcas.throwFeatMissing("collectionId",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        return ll_cas.ll_getStringValue(addr, casFeatCode_collectionId);
    }

    /**
     * @generated
     * @param addr
     *            low level Feature Structure reference
     * @param v
     *            value to set
     */
    public void setCollectionId(int addr, String v)
    {
        if (featOkTst && casFeat_collectionId == null) {
            jcas.throwFeatMissing("collectionId",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        ll_cas.ll_setStringValue(addr, casFeatCode_collectionId, v);
    }

   
    /**
     * initialize variables to correspond with Cas Type and Features
     * 
     * @generated
     * @param jcas
     *            JCas
     * @param casType
     *            Type
     */
    public DocumentData_Type(JCas jcas, Type casType)
    {
        super(jcas, casType);
        casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

        casFeat_documentTitle = jcas.getRequiredFeatureDE(casType, "documentTitle",
                "uima.cas.String", featOkTst);
        casFeatCode_documentTitle = (null == casFeat_documentTitle) ? JCas.INVALID_FEATURE_CODE
                : ((FeatureImpl) casFeat_documentTitle).getCode();

        casFeat_documentId = jcas.getRequiredFeatureDE(casType, "documentId", "uima.cas.String",
                featOkTst);
        casFeatCode_documentId = (null == casFeat_documentId) ? JCas.INVALID_FEATURE_CODE
                : ((FeatureImpl) casFeat_documentId).getCode();

        casFeat_documentUri = jcas.getRequiredFeatureDE(casType, "documentUri", "uima.cas.String",
                featOkTst);
        casFeatCode_documentUri = (null == casFeat_documentUri) ? JCas.INVALID_FEATURE_CODE
                : ((FeatureImpl) casFeat_documentUri).getCode();

        casFeat_collectionId = jcas.getRequiredFeatureDE(casType, "collectionId", "uima.cas.String",
                featOkTst);
        casFeatCode_collectionId = (null == casFeat_collectionId) ? JCas.INVALID_FEATURE_CODE
                : ((FeatureImpl) casFeat_collectionId).getCode();

       

    }
}
