package com.my.ds.code.graphs.tsp.impl;

import com.my.ds.code.graphs.tsp.domain.TSPResult;
import com.my.ds.code.graphs.tsp.domain.Vertex;

import java.util.*;

/* -------------------------
   TSP algorithms
   ------------------------- */
public class TSPAllTypesImpl {

    // -------------------------
    // 1) Brute-force (permute all orders). Use only for tiny n (n <= 10 recommended).
    // Returns best Hamiltonian cycle (visits each vertex once and returns to start).
    // -------------------------
    public static <T> TSPResult<T> bruteForceTSP(TSPGraph<T> g) {
        int n = g.size();
        if (n == 0) return new TSPResult<>(Collections.emptyList(), 0.0);
        if (n == 1) return new TSPResult<>(Collections.singletonList(g.getVertices().get(0)), 0.0);

        // We'll fix start at index 0 and permute remaining vertices to avoid duplicate cycles due to rotation.
        List<Integer> indices = new ArrayList<>();
        for (int i = 1; i < n; i++) indices.add(i);

        List<Vertex<T>> bestTour = null;
        double bestCost = Double.POSITIVE_INFINITY;

        // Permute indices
        do {
            double cost = 0.0;
            int prev = 0;
            boolean ok = true;
            // 0 -> perm[0] -> perm[1] -> ... -> perm[k] -> 0
            for (int id : indices) {
                double w = g.getWeight(prev, id);
                if (Double.isInfinite(w)) { ok = false; break; }
                cost += w;
                prev = id;
            }
            // edge from last back to 0
            if (!ok) continue;
            double lastBack = g.getWeight(prev, 0);
            if (Double.isInfinite(lastBack)) continue;
            cost += lastBack;
            if (cost < bestCost) {
                bestCost = cost;
                List<Vertex<T>> tour = new ArrayList<>();
                tour.add(g.getVertices().get(0));
                for (int id : indices) tour.add(g.getVertices().get(id));
                bestTour = tour;
            }
        } while (nextPermutation(indices));

        if (bestTour == null) return new TSPResult<>(Collections.emptyList(), Double.POSITIVE_INFINITY);
        return new TSPResult<>(bestTour, bestCost);
    }

    // helper: next permutation for list of integers (lexicographic). Returns false when wrapped to first permutation.
    private static boolean nextPermutation(List<Integer> a) {
        // find pivot
        int i = a.size() - 2;
        while (i >= 0 && a.get(i) >= a.get(i + 1)) i--;
        if (i < 0) return false;
        int j = a.size() - 1;
        while (a.get(j) <= a.get(i)) j--;
        Collections.swap(a, i, j);
        Collections.reverse(a.subList(i + 1, a.size()));
        return true;
    }

    // -------------------------
    // 2) Held-Karp DP (bitmask) — O(n^2 * 2^n) time, O(n * 2^n) memory.
    // Returns exact optimal solution. Practical up to n ~ 20.
    // -------------------------
    public static <T> TSPResult<T> heldKarpTSP(TSPGraph<T> g) {
        int n = g.size();
        if (n == 0) return new TSPResult<>(Collections.emptyList(), 0.0);
        if (n == 1) return new TSPResult<>(Collections.singletonList(g.getVertices().get(0)), 0.0);
        if (n > 22) System.err.println("Warning: Held-Karp grows exponentially; n=" + n + " may be infeasible.");

        final int N = 1 << n;
        double INF = Double.POSITIVE_INFINITY;

        // dp[mask][j] = minimal cost to start at 0, visit set mask (which includes 0 and j), and end at j
        // We'll only store masks that include 0 (bit 0 = 1).
        double[][] dp = new double[N][n];
        int[][] parent = new int[N][n]; // store predecessor index for path reconstruction
        for (int mask = 0; mask < N; mask++) Arrays.fill(dp[mask], INF);
        for (int[] row : parent) Arrays.fill(row, -1);

        // base: mask with only 0
        dp[1 << 0][0] = 0.0;

        for (int mask = 1; mask < N; mask++) {
            if ((mask & 1) == 0) continue; // must include start 0
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) == 0) continue;
                double cur = dp[mask][j];
                if (Double.isInfinite(cur)) continue;
                // try to go to k not in mask
                for (int k = 0; k < n; k++) {
                    if ((mask & (1 << k)) != 0) continue;
                    double w = g.getWeight(j, k);
                    if (Double.isInfinite(w)) continue;
                    int nmask = mask | (1 << k);
                    double nv = cur + w;
                    if (nv < dp[nmask][k]) {
                        dp[nmask][k] = nv;
                        parent[nmask][k] = j;
                    }
                }
            }
        }

        // close the tour: end at j then back to 0
        int full = (1 << n) - 1;
        double bestCost = INF;
        int bestEnd = -1;
        for (int j = 1; j < n; j++) {
            if (Double.isInfinite(dp[full][j])) continue;
            double wback = g.getWeight(j, 0);
            if (Double.isInfinite(wback)) continue;
            double total = dp[full][j] + wback;
            if (total < bestCost) { bestCost = total; bestEnd = j; }
        }

        if (bestEnd == -1) return new TSPResult<>(Collections.emptyList(), INF);

        // reconstruct path backward
        List<Integer> rev = new ArrayList<>();
        int mask = full;
        int cur = bestEnd;
        while (cur != -1) {
            rev.add(cur);
            int p = parent[mask][cur];
            mask = mask ^ (1 << cur);
            cur = p;
        }
        // last should be 0
        Collections.reverse(rev);
        List<Vertex<T>> tour = new ArrayList<>();
        for (int idx : rev) tour.add(g.getVertices().get(idx));
        // ensure starting with vertex 0; if not, rotate (but rev starts at 0 by construction)
        return new TSPResult<>(tour, bestCost);
    }

    // -------------------------
    // 3) Nearest Neighbour heuristic (fast O(n^2)) starting from vertex 0.
    // -------------------------
    public static <T> TSPResult<T> nearestNeighborTSP(TSPGraph<T> g) {
        int n = g.size();
        if (n == 0) return new TSPResult<>(Collections.emptyList(), 0.0);
        boolean[] seen = new boolean[n];
        List<Vertex<T>> tour = new ArrayList<>();
        int cur = 0;
        tour.add(g.getVertices().get(0));
        seen[0] = true;
        double cost = 0.0;

        for (int step = 1; step < n; step++) {
            int best = -1;
            double bestW = Double.POSITIVE_INFINITY;
            for (int k = 0; k < n; k++) {
                if (seen[k]) continue;
                double w = g.getWeight(cur, k);
                if (!Double.isInfinite(w) && w < bestW) {
                    bestW = w; best = k;
                }
            }
            if (best == -1) {
                // no route to remaining vertices
                return new TSPResult<>(Collections.emptyList(), Double.POSITIVE_INFINITY);
            }
            tour.add(g.getVertices().get(best));
            seen[best] = true;
            cost += bestW;
            cur = best;
        }
        // back to start
        double back = g.getWeight(cur, 0);
        if (Double.isInfinite(back)) return new TSPResult<>(Collections.emptyList(), Double.POSITIVE_INFINITY);
        cost += back;
        return new TSPResult<>(tour, cost);
    }

    // -------------------------
    // 4) 2-Opt local search improvement for a given tour (list of vertex indices).
    // Usually applied to heuristic tour to improve it quickly.
    // Complexity O(n^2) per pass; we run until no improvement or maxIter.
    // -------------------------
    public static <T> TSPResult<T> twoOptImprove(TSPGraph<T> g, TSPResult<T> initial, int maxIter) {
        List<Vertex<T>> tourVertices = new ArrayList<>(initial.tour);
        if (tourVertices.isEmpty()) return initial;
        int n = tourVertices.size();
        // convert to index list
        Map<Vertex<T>, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < g.size(); i++) idxMap.put(g.getVertices().get(i), i);

        List<Integer> tour = new ArrayList<>();
        for (Vertex<T> v : tourVertices) tour.add(idxMap.get(v));

        double bestCost = initial.cost;
        boolean improved = true;
        int iter = 0;
        while (improved && iter++ < maxIter) {
            improved = false;
            for (int i = 1; i < n - 1; i++) {
                for (int k = i + 1; k < n; k++) {
                    double delta = twoOptDelta(g, tour, i - 1, i, k - 1, k % n);
                    if (delta < -1e-9) {
                        // perform 2-opt: reverse tour[i..k]
                        reverseSubList(tour, i, k);
                        bestCost += delta;
                        improved = true;
                        break;
                    }
                }
                if (improved) break;
            }
        }

        // build vertex tour
        List<Vertex<T>> improvedTour = new ArrayList<>();
        for (int idx : tour) improvedTour.add(g.getVertices().get(idx));
        return new TSPResult<>(improvedTour, bestCost);
    }

    // compute cost change if we replace edges (a-b) and (c-d) by (a-c) and (b-d)
    // aIdx,bIdx,... are indices into tour list; we compute nodes a,b,c,d circularly
    private static double twoOptDelta(TSPGraph<?> g, List<Integer> tour, int aIdx, int bIdx, int cIdx, int dIdx) {
        int a = tour.get(aIdx);
        int b = tour.get(bIdx);
        int c = tour.get(cIdx);
        int d = tour.get(dIdx);
        double wab = g.getWeight(a, b);
        double wcd = g.getWeight(c, d);
        double wac = g.getWeight(a, c);
        double wbd = g.getWeight(b, d);
        double oldSum = (Double.isInfinite(wab) ? Double.POSITIVE_INFINITY : wab)
                      + (Double.isInfinite(wcd) ? Double.POSITIVE_INFINITY : wcd);
        double newSum = (Double.isInfinite(wac) ? Double.POSITIVE_INFINITY : wac)
                      + (Double.isInfinite(wbd) ? Double.POSITIVE_INFINITY : wbd);
        if (Double.isInfinite(oldSum) && Double.isInfinite(newSum)) return 0;
        if (Double.isInfinite(newSum)) return Double.POSITIVE_INFINITY;
        if (Double.isInfinite(oldSum)) return -Double.POSITIVE_INFINITY;
        return newSum - oldSum;
    }

    private static void reverseSubList(List<Integer> list, int i, int k) {
        while (i < k) {
            Collections.swap(list, i, k);
            i++; k--;
        }
    }
}