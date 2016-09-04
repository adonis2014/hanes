package com.mowenxi;

import com.hankcs.hanlp.HanLP;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

import java.util.Set;

public final class HanLPAnalyzer extends Analyzer {

    private boolean indexMode = true;
    private boolean nameRecognize = true;
    private boolean translatedNameRecognize = true;
    private boolean japaneseNameRecognize = false;
    private boolean placeRecognize = false;
    private boolean organizationRecognize = true;
    private boolean useCustomDictionary = true; // enableCustomDictionary
    private boolean speechTagging = false; // PorterStemming
    private boolean offset = false;
    private boolean numberQuantifierRecognize = false;
    private int threads = 1; // if more than 1, it means use multi-threading
    private Set<String> filter;
    private boolean enablePorterStemming;

    /**
     * @param filter               停用词
     * @param enablePorterStemming 是否分析词干（仅限英文）
     */
    public HanLPAnalyzer(Set<String> filter, boolean enablePorterStemming) {
        this.filter = filter;
        this.enablePorterStemming = enablePorterStemming;
    }

    /**
     * @param enablePorterStemming 是否分析词干.进行单复数,时态的转换
     */
    public HanLPAnalyzer(boolean enablePorterStemming) {
        this.speechTagging = enablePorterStemming;
    }

    public HanLPAnalyzer(boolean indexMode, boolean nameRecognize, boolean translatedNameRecognize,
                         boolean japaneseNameRecognize, boolean placeRecognize, boolean organizationRecognize,
                         boolean useCustomDictionary, boolean speechTagging, boolean offset, boolean numberQuantifierRecognize,
                         int threads, Set<String> filter) {
        this(filter, speechTagging);

        this.indexMode = indexMode;
        this.nameRecognize = nameRecognize;
        this.translatedNameRecognize = translatedNameRecognize;
        this.japaneseNameRecognize = japaneseNameRecognize;
        this.placeRecognize = placeRecognize;
        this.organizationRecognize = organizationRecognize;
        this.useCustomDictionary = useCustomDictionary;
        this.offset = offset;
        this.numberQuantifierRecognize = numberQuantifierRecognize;
        this.threads = threads;
    }

    public HanLPAnalyzer() {
        super();
    }

    @Override
    protected TokenStreamComponents createComponents(String fileName) {
        Tokenizer tokenizer = new HanLPTokenizer(HanLP.newSegment()
                                                      .enableIndexMode(indexMode)
                                                      .enableNameRecognize(nameRecognize)
                                                      .enableTranslatedNameRecognize(translatedNameRecognize)
                                                      .enableJapaneseNameRecognize(japaneseNameRecognize)
                                                      .enablePlaceRecognize(placeRecognize)
                                                      .enableOrganizationRecognize(organizationRecognize)
                                                      .enableCustomDictionary(useCustomDictionary)
                                                      .enablePartOfSpeechTagging(speechTagging)
                                                      .enableOffset(offset)
                                                      .enableNumberQuantifierRecognize(numberQuantifierRecognize)
                                                      .enableMultithreading(threads), filter, speechTagging);
        return new TokenStreamComponents(tokenizer);
    }

}
