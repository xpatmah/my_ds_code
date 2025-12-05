package com.my.ds.code.graphs.simple.domain;

public  class VertexDistance {
        private final int index;
        private final double distance;
        public VertexDistance(int i, double d) { index = i; distance = d; }

    public double getDistance() {
        return distance;
    }

    public int getIndex() {
        return index;
    }
}