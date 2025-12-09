package com.my.ds.code.graphs.shortestPath.domain;

import java.util.ArrayList;
import java.util.List;

// ---------------------
// Floyd-Warshall: all-pairs shortest paths
// Returns distance matrix and next matrix for path reconstruction.
// Handles negative weights; if negative cycles exist, distances on those pairs become -INF.
// ---------------------
public class FWResult<T> {
        public final double[][] dist;    // dist[i][j]
        public final Integer[][] next;   // next[i][j] = index of next node on path from i to j
        public final List<Vertex<T>> vertices;

        public FWResult(double[][] dist, Integer[][] next, List<Vertex<T>> vertices) {
            this.dist = dist; this.next = next; this.vertices = vertices;
        }

        // reconstruct path from i->j as list of Vertex<T>
        public List<Vertex<T>> reconstructPath(int i, int j) {
            List<Vertex<T>> path = new ArrayList<>();
            if (next[i][j] == null) return path; // no path
            int at = i;
            path.add(vertices.get(at));
            while (at != j) {
                at = next[at][j];
                path.add(vertices.get(at));
            }
            return path;
        }
    }