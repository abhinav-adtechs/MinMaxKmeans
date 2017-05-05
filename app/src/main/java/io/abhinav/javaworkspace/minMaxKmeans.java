package io.abhinav.javaworkspace;


import java.util.Arrays;

public class minMaxKmeans {

    private static final int N = 250 ; /*NUMBER OF DATA POINTS*/
    private static final int M = 7; /*NUMBER OF CLUSTERS*/

    private static int t = 0, tMax ;

    private static double inputDataSet[] = new double[N] ;
    private static boolean empty = false ;

    private static double p, pInit = 0, pStep = 0.01, pMax = 0.5 ;


    private static double m[] = new double[M] ; /*Cluster Centers*/

    private static double w[] = new double[M] ; /*New Weights*/
    private static double W[] = new double[M] ; /*Old Weights*/

    private static int del[][] = new int[N][M] ; /*Final Cluster Assignment*/
    private static int delta[][] = new int[N][M] ; /*Final Cluster Assignments Temporary*/

    private static double variance[] = new double[M] ; /*Variance of each cluster*/

    private static double minimizationStepMin = 0 ;

    private static final double beta = 0.1 ;



    public static void main(String[] args){

        init() ;
        implementation() ;
        printResults() ;
    }

    private static void init() {
        Arrays.fill(w, 1/M);

        /*Initilize cluster centers*/
        /*Initilize variance matrix*/
    }

    private static void implementation() {

        do{

            t = t+1 ;

            updateClusterAssignments() ;

            if(ifSingletonClusters()){
                empty = true ;
                p = p - pStep ;
                if (p < pInit){
                    return ;
                }
                revertAssignment() ;
            }

            updateCenters() ;

            if(p < pMax && !empty){

                /*Steps 26 and 27*/
                p = p + pStep ;
            }

            updateWeights() ;


        }while(loopCondition()) ;
    }

    private static boolean ifSingletonClusters() {

        for (int k = 0; k < M; k++) {
            int count = 0 ;
            for (int i = 0; i < N; i++) {
                if(del[i][k] == 1){
                    count++ ;
                }
                if (count > 1){
                    return  false ;
                }
            }
        }
        return true ;
    }

    private static void updateClusterAssignments() {
        for (int i = 1 ; i < N; i++) {
            for (int k = 1; k < M; k++) {
                if(k == minimizationStep(i) ){
                    del[i][k] = 1 ;
                }else {
                    del[i][k] = 0 ;
                }
            }
        }
    }

    private static int minimizationStep(int i) {
        double temVar ;
        int minKdash = 0;

        for (int kdash = 0; kdash < M; kdash++) {
            temVar = (Math.pow(w[kdash], p))*(Math.pow((Math.abs(inputDataSet[i] - m[kdash])), 2)) ;
            if(minimizationStepMin > temVar){
                minimizationStepMin = temVar ;
                minKdash = kdash ;
            }
        }

        return minKdash;
    }

    private static void revertAssignment() {

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < M; k++) {
                del[i][k] = delta[i][k] ;
                w[k] = W[k] ;
            }
            
        }
    }

    private static void updateCenters() {
        for (int k=0, i=0; k < M; k++, i++) {
            m[k] = returnSummation(del[i], inputDataSet) / returnSummation(del[i]) ;
        }
    }

    private static int returnSummation(int[] inp){
        int sumTemp = 0;
        for (int i = 0; i < inp.length; i++) {
            sumTemp += inp[i] ;
        }
        return sumTemp ;
    }

    private static int returnSummation(int[] inp, double[] inp2){
        int sumTemp = 0;
        for (int i = 0; i < inp.length; i++) {
            sumTemp += inp[i]*inp2[i] ;
        }
        return sumTemp ;
    }

    private static void updateWeights() {

        for (int k = 0; k < M; k++) {

                variance[k] = varianceUpdate(k) ;
                w[k] = beta*w[k] + (1-beta)*Math.pow(variance[k], (1/1-p)) / variancePowSummation() ;

                /*Step 31*/
        }
    }

    private static double variancePowSummation() {
        double sumTemp = 0 ;

        for (int i = 0; i < M; i++) {
            sumTemp += Math.pow(variance[i], (1/(1-p))) ;
        }

        return sumTemp ;
    }

    private static double varianceUpdate(int k){

        double sumTemp = 0 ;

        for (int i = 0; i < N; i++) {
            sumTemp =+ del[i][k] * Math.pow((Math.abs(inputDataSet[i] - m[k])), 2) ;
        }

        return sumTemp ;
    }


    private static boolean loopCondition() {
        /*Step 33*/

        return (false || t>= tMax) ;
    }

    private static void printResults() {
        for (int i = 1; i < N; i++) {
            for (int k = 0; k < M ; k++) {
                System.out.println("i:" + i + " k:" + k + " custerId:" + del[i][k] + " center:" + m[k]);
            }
        }
    }
}
