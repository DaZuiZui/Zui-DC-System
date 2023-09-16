package com.example.duplicatechecksystem.domain.vo;

import com.example.duplicatechecksystem.domain.SimilarityInfo;

import java.io.Serializable;
import java.util.List;

public class DuplicationCheckerVo implements Serializable {
    private List<SimilarityInfo> similarities;
    private Double combinedSimilarity;

    @Override
    public String toString() {
        return "DuplicationCheckerVo{" +
                "similarities=" + similarities +
                ", combinedSimilarity=" + combinedSimilarity +
                '}';
    }

    public List<SimilarityInfo> getSimilarities() {
        return similarities;
    }

    public void setSimilarities(List<SimilarityInfo> similarities) {
        this.similarities = similarities;
    }

    public Double getCombinedSimilarity() {
        return combinedSimilarity;
    }

    public void setCombinedSimilarity(Double combinedSimilarity) {
        this.combinedSimilarity = combinedSimilarity;
    }

    public DuplicationCheckerVo(List<SimilarityInfo> similarities, Double combinedSimilarity) {
        this.similarities = similarities;
        this.combinedSimilarity = combinedSimilarity;
    }

    public DuplicationCheckerVo() {
    }
}
