/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mowenxi.hanes.analysis;

import org.elasticsearch.index.analysis.AnalysisModule;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class HanLpAnalysisBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {

    @Override
    public void processAnalyzers(AnalyzersBindings analyzersBindings) {
        analyzersBindings.processAnalyzer("hanlp", HanLpAnalyzerProvider.class);
    }

    @Override
    public void processTokenizers(TokenizersBindings tokenizersBindings) {
        tokenizersBindings.processTokenizer("hanlp_tokenizer", HanLpTokenizerTokenizerFactory.class);
        // This is an alias to "hanlp_tokenizer"; it's here for backwards compat
        tokenizersBindings.processTokenizer("hanlp_sentence", HanLpTokenizerTokenizerFactory.class);
    }

    @Override
    public void processTokenFilters(TokenFiltersBindings tokenFiltersBindings) {
        // This is a noop token filter; it's here for backwards compat before we had "hanlp_tokenizer"
        tokenFiltersBindings.processTokenFilter("hanlp_word", HanLpNoOpTokenFilterFactory.class);
    }

}
