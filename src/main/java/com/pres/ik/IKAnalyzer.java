package com.pres.ik;


import org.apache.lucene.analysis.Analyzer;

/**
 * @author Dora
 * @date 2019/11/4 9:55
 **/
public class IKAnalyzer extends Analyzer {
    private boolean useSmart = false;

    public IKAnalyzer() {
        this(false);
    }

    public IKAnalyzer(boolean useSmart) {
        this.useSmart = useSmart;
    }

    public boolean isUseSmart() {
        return useSmart;
    }

    public void setUseSmart(boolean useSmart) {
        this.useSmart = useSmart;
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        IKTokenizer tk = new IKTokenizer(this.useSmart);
        return new TokenStreamComponents(tk);
    }
}
