package io.abhinav.javaworkspace;


import java.util.Arrays;

public class minMaxKmeans {

    private static final int N = 250 ; /*NUMBER OF DATA POINTS*/
    private static final int M = 7; /*NUMBER OF CLUSTERS*/

    private static int t = 0 ;

    private static int w[] = new int[M] ;
    private static int inputDataSet[] = new int[N] ;

    private static boolean empty = false ;


    private static int p, pInit = 0, pStep, pMax ;

    private static int m[] = new int[M] ; /*Cluster Centers*/
    private static int del[][] = new int[N][M] ; /*Final Cluster Assignment*/


    public static void main(String[] args){

        init() ;
        implementation() ;
        printResults() ;
    }

    private static void init() {
        Arrays.fill(w, 1/M);

        /*Initilize cluster centers*/
    }

    private static void implementation() {

        do{

            t = t+1 ;

            updateClusterAssignments() ;

            if(!empty){
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

    private static void updateClusterAssignments() {
        for (int i = 1 ; i < N; i++) {
            for (int k = 1; k < M; k++) {
                if(k == minimizationStep() ){
                    del[i][k] = 1 ;
                }else {
                    del[i][k] = 0 ;
                }
            }
        }
    }

    private static int minimizationStep() {
        /*Step 10*/
        return 0;
    }

    private static void revertAssignment() {
        /*Steps 19 and 20*/
    }

    private static void updateCenters() {
        /*Step 23*/
    }

    private static void updateWeights() {
        for (int k = 0; k < M; k++) {
                /*Step 31*/
        }
    }

    private static boolean loopCondition() {
        /*Step 33*/

        return true ;
    }

    private static void printResults() {
        for (int i = 1; i < N; i++) {
            for (int k = 0; k < M ; k++) {
                System.out.println("i:" + i + " k:" + k + " custerId:" + del[i][k] + " center:" + m[k]);
            }
        }
    }
}
