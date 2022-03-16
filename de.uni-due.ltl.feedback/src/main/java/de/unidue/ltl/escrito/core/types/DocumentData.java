package de.unidue.ltl.escrito.core.types;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.DocumentAnnotation;

/**
 * @generated
 */
public class DocumentData
    extends DocumentAnnotation
{
    /**
     * @generated
     * @ordered
     */
    public final static int typeIndexID = JCasRegistry.register(DocumentData.class);
    /**
     * @generated
     * @ordered
     */
    public final static int type = typeIndexID;

    /** @generated */
    @Override
    public int getTypeIndexID()
    {
        return typeIndexID;
    }

    /**
     * Never called. Disable default constructor
     *
     * @generated
     */
    protected DocumentData()
    {
    }

    /**
     * Internal - constructor used by generator
     * 
     * @generated
     * @param addr
     *            low level Feature Structure reference
     * @param type
     *            the type of this Feature Structure
     */
    public DocumentData(int addr, TOP_Type type)
    {
        super(addr, type);
        readObject();
    }

    /**
     * @generated
     * @param jcas
     *            JCas to which this Feature Structure belongs
     */
    public DocumentData(JCas jcas)
    {
        super(jcas);
        readObject();
    }

    /**
     * @generated
     * @param jcas
     *            JCas to which this Feature Structure belongs
     * @param begin
     *            offset to the begin spot in the SofA
     * @param end
     *            offset to the end spot in the SofA
     */
    public DocumentData(JCas jcas, int begin, int end)
    {
        super(jcas);
        setBegin(begin);
        setEnd(end);
        readObject();
    }

    /**
     * <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    private void readObject()
    {
    }

    // *--------------*
    // * Feature: documentTitle

    /**
     * getter for documentTitle - gets The human readable title of the document.
     * 
     * @generated
     * @return value of the feature
     */
    public String getDocumentTitle()
    {
        if (DocumentData_Type.featOkTst
                && ((DocumentData_Type) jcasType).casFeat_documentTitle == null) {
            jcasType.jcas.throwFeatMissing("documentTitle",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        return jcasType.ll_cas.ll_getStringValue(addr,
                ((DocumentData_Type) jcasType).casFeatCode_documentTitle);
    }

    /**
     * setter for documentTitle - sets The human readable title of the document.
     * 
     * @generated
     * @param v
     *            value to set into the feature
     */
    public void setDocumentTitle(String v)
    {
        if (DocumentData_Type.featOkTst
                && ((DocumentData_Type) jcasType).casFeat_documentTitle == null) {
            jcasType.jcas.throwFeatMissing("documentTitle",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        jcasType.ll_cas.ll_setStringValue(addr,
                ((DocumentData_Type) jcasType).casFeatCode_documentTitle, v);
    }

    // *--------------*
    // * Feature: documentId

    /**
     * getter for documentId - gets The id of the document.
     * 
     * @generated
     * @return value of the feature
     */
    public String getDocumentId()
    {
        if (DocumentData_Type.featOkTst
                && ((DocumentData_Type) jcasType).casFeat_documentId == null) {
            jcasType.jcas.throwFeatMissing("documentId",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        return jcasType.ll_cas.ll_getStringValue(addr,
                ((DocumentData_Type) jcasType).casFeatCode_documentId);
    }

    /**
     * setter for documentId - sets The id of the document.
     * 
     * @generated
     * @param v
     *            value to set into the feature
     */
    public void setDocumentId(String v)
    {
        if (DocumentData_Type.featOkTst
                && ((DocumentData_Type) jcasType).casFeat_documentId == null) {
            jcasType.jcas.throwFeatMissing("documentId",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        jcasType.ll_cas.ll_setStringValue(addr,
                ((DocumentData_Type) jcasType).casFeatCode_documentId, v);
    }

    // *--------------*
    // * Feature: documentUri

    /**
     * getter for documentUri - gets The URI of the document.
     * 
     * @generated
     * @return value of the feature
     */
    public String getDocumentUri()
    {
        if (DocumentData_Type.featOkTst
                && ((DocumentData_Type) jcasType).casFeat_documentUri == null) {
            jcasType.jcas.throwFeatMissing("documentUri",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        return jcasType.ll_cas.ll_getStringValue(addr,
                ((DocumentData_Type) jcasType).casFeatCode_documentUri);
    }

    /**
     * setter for documentUri - sets The URI of the document.
     * 
     * @generated
     * @param v
     *            value to set into the feature
     */
    public void setDocumentUri(String v)
    {
        if (DocumentData_Type.featOkTst
                && ((DocumentData_Type) jcasType).casFeat_documentUri == null) {
            jcasType.jcas.throwFeatMissing("documentUri",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        jcasType.ll_cas.ll_setStringValue(addr,
                ((DocumentData_Type) jcasType).casFeatCode_documentUri, v);
    }

    // *--------------*
    // * Feature: collectionId

    /**
     * getter for collectionId - gets The ID of the whole document collection.
     * 
     * @generated
     * @return value of the feature
     */
    public String getCollectionId()
    {
        if (DocumentData_Type.featOkTst
                && ((DocumentData_Type) jcasType).casFeat_collectionId == null) {
            jcasType.jcas.throwFeatMissing("collectionId",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        return jcasType.ll_cas.ll_getStringValue(addr,
                ((DocumentData_Type) jcasType).casFeatCode_collectionId);
    }

    /**
     * setter for collectionId - sets The ID of the whole document collection.
     * 
     * @generated
     * @param v
     *            value to set into the feature
     */
    public void setCollectionId(String v)
    {
        if (DocumentData_Type.featOkTst
                && ((DocumentData_Type) jcasType).casFeat_collectionId == null) {
            jcasType.jcas.throwFeatMissing("collectionId",
                    "de.unidue.ltl.escrito.core.types.DocumentData");
        }
        jcasType.ll_cas.ll_setStringValue(addr,
                ((DocumentData_Type) jcasType).casFeatCode_collectionId, v);
    }

    
    /**
     * Create a new {@link DocumentMetaData} annotation in the given CAS. The meta data fields can
     * then be set on the returned object.
     *
     * @param aCas
     *            the CAS to create the meta data for.
     * @return a {@link DocumentMetaData} annotation that has already been added to the CAS indexes.
     * @throws IllegalStateException
     *             if there is already a {@link DocumentMetaData} annotation
     * @throws CASException
     *             if the JCas cannot be accessed from the provided CAS.
     */
    public static DocumentData create(final CAS aCas) throws IllegalStateException, CASException
    {
        try {
            get(aCas);
            throw new IllegalStateException("CAS already contains DocumentData");
        }
        catch (IllegalArgumentException e) {
            DocumentData docMetaData = new DocumentData(aCas.getJCas());
            initDocumentData(docMetaData);
            return docMetaData;
        }
    }

    /**
     * Create a new {@link DocumentMetaData} annotation in the given CAS. The meta data fields can
     * then be set on the returned object.
     *
     * @param aJcas
     *            the CAS to create the meta data for.
     * @return a {@link DocumentMetaData} annotation that has already been added to the CAS indexes.
     * @throws IllegalStateException
     *             if there is already a {@link DocumentMetaData} annotation
     */
    public static DocumentData create(final JCas aJcas) throws IllegalStateException
    {
        try {
            get(aJcas);
            throw new IllegalStateException("CAS already contains DocumentData");
        }
        catch (IllegalArgumentException e) {
            DocumentData docMetaData = new DocumentData(aJcas);
            initDocumentData(docMetaData);
            return docMetaData;
        }
    }

    private static DocumentData initDocumentData(DocumentData aMetaData)
    {
        // If there is already a DocumentAnnotation copy it's information and delete it
        DocumentAnnotation da = getDocumentAnnotation(aMetaData.getView());
        if (da != null) {
            aMetaData.setLanguage(da.getLanguage());
            aMetaData.setBegin(da.getBegin());
            aMetaData.setEnd(da.getEnd());
            da.removeFromIndexes();
        }
        else if (aMetaData.getView().getDocumentText() != null) {
            aMetaData.setBegin(0);
            aMetaData.setEnd(aMetaData.getView().getDocumentText().length());
        }

        aMetaData.addToIndexes();
        return aMetaData;
    }

    /**
     * Copy the {@link DocumentMetaData} annotation from one view to another.
     *
     * @param aSourceView
     *            the source.
     * @param aTargetView
     *            the target.
     */
    public static void copy(final JCas aSourceView, final JCas aTargetView)
    {
        // First get the DMD then create. In case the get fails, we do not create.
        DocumentData dmd = get(aSourceView);
        DocumentData docMetaData = create(aTargetView);
        docMetaData.setCollectionId(dmd.getCollectionId());
        
        docMetaData.setDocumentId(dmd.getDocumentId());
        docMetaData.setDocumentTitle(dmd.getDocumentTitle());
        docMetaData.setDocumentUri(dmd.getDocumentUri());
       
        docMetaData.setLanguage(dmd.getLanguage());
    }

    /**
     * Get the {@link DocumentMetaData} from the CAS.
     * 
     * @param aCas
     *            a CAS.
     * @return the {@link DocumentMetaData} from the CAS.
     * @throws IllegalArgumentException
     *             if no {@link DocumentMetaData} exists in the CAS.
     */
    public static DocumentData get(final CAS aCas)
    {
        FSIterator<FeatureStructure> iterator = aCas.getIndexRepository()
                .getAllIndexedFS(CasUtil.getType(aCas, DocumentData.class));

        if (!iterator.hasNext()) {
            throw new IllegalArgumentException(
                    new Throwable("CAS does not contain any " + DocumentData.class.getName()));
        }

        DocumentData result = (DocumentData) iterator.next();

        if (iterator.hasNext()) {
            throw new IllegalArgumentException(new Throwable(
                    "CAS contains more than one " + DocumentData.class.getName()));
        }

        return result;
    }

    /**
     * Get the {@link DocumentAnnotation} from the CAS if it already exists.
     * 
     * @param aCas
     *            a CAS.
     * @return the {@link DocumentAnnotation} from the CAS.
     */
    private static DocumentAnnotation getDocumentAnnotation(final CAS aCas)
    {
        FSIterator<FeatureStructure> iterator = aCas.getIndexRepository()
                .getAllIndexedFS(CasUtil.getType(aCas, DocumentAnnotation.class));

        if (!iterator.hasNext()) {
            return null;
        }

        DocumentAnnotation result = (DocumentAnnotation) iterator.next();

        if (iterator.hasNext()) {
            throw new IllegalArgumentException(new Throwable(
                    "CAS contains more than one " + DocumentAnnotation.class.getName()));
        }

        return result;
    }

    /**
     * Get the {@link DocumentMetaData} from the CAS.
     *
     * @param aJCas
     *            the JCas.
     * @return the {@link DocumentMetaData} from the CAS.
     * @throws IllegalArgumentException
     *             if no {@link DocumentMetaData} exists in the CAS.
     */
    public static DocumentData get(final JCas aJCas)
    {
        return get(aJCas.getCas());
    }
}
