package com.example.duplicatechecksystem.domain;

public class SimilarityInfo {
        private String content1;
        private int startPosition1;
        private int endPosition1;
        private String content2;
        private int startPosition2;
        private int endPosition2;

        public SimilarityInfo(String content1, int startPosition1, int endPosition1, String content2, int startPosition2, int endPosition2) {
            this.content1 = content1;
            this.startPosition1 = startPosition1;
            this.endPosition1 = endPosition1;
            this.content2 = content2;
            this.startPosition2 = startPosition2;
            this.endPosition2 = endPosition2;
        }

    @Override
    public String toString() {
        return "SimilarityInfo{" +
                "content1='" + content1 + '\'' +
                ", startPosition1=" + startPosition1 +
                ", endPosition1=" + endPosition1 +
                ", content2='" + content2 + '\'' +
                ", startPosition2=" + startPosition2 +
                ", endPosition2=" + endPosition2 +
                '}';
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public int getStartPosition1() {
        return startPosition1;
    }

    public void setStartPosition1(int startPosition1) {
        this.startPosition1 = startPosition1;
    }

    public int getEndPosition1() {
        return endPosition1;
    }

    public void setEndPosition1(int endPosition1) {
        this.endPosition1 = endPosition1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public int getStartPosition2() {
        return startPosition2;
    }

    public void setStartPosition2(int startPosition2) {
        this.startPosition2 = startPosition2;
    }

    public int getEndPosition2() {
        return endPosition2;
    }

    public void setEndPosition2(int endPosition2) {
        this.endPosition2 = endPosition2;
    }

    public SimilarityInfo() {
    }
}