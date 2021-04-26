package ui;
import java.util.Scanner;
import java.util.Arrays;

/**
 * This class models an IPC Calculator...
 * @author Juan Pablo Ricardo
 * @version 1.0
 *
 */
public class CalculatorCPI{
  public static void CalculatorCPI (String [] args){


    Scanner sc = new Scanner (System.in);
    final int NUM_YEARS = 6, NUM_AREAS=5; //Size of the array
    int foodLeading = 0; //Counter to calculate the year in which food category was leading
    int anArea = 0; //Variable used as a switch to select an specific area
    double average = 0; //Used to calculate the IPC average
    final int FOOD = 0, TRANSPORT=1, SERVICES=2,HEALTH=3,EDUCATION=4;
    String [] areasNames = { "Food", "Transport", "Services", "Health", "Education"}; //Array with most important categories
    int [] years = {2016, 2017, 2018, 2019, 2020, 2021}; //Array with the last 5 years
    double [] cpi;

    double[][] areasByYears;
    cpi = new double [NUM_YEARS];

    areasByYears = new double[NUM_YEARS][NUM_AREAS];

    /**
     * Constructor method
     *
     */
    /**public CalculatorCPI() {

        cpi = new double [NUM_YEARS];

        areasByYears = new double[NUM_YEARS][NUM_AREAS];*/

    }

    /**
     * Method used to create the menu and create each of the options
     */
    public  void menuOption(){

        boolean exit = false;

        int option = 0;

        while (!exit){

            System.out.println ("\n1. Register CPI Values");
            System.out.println("2. Register areas by year");
            System.out.println ("3. Print average value for all years inflation");
            System.out.println ("4. Show Median calculation between the CPI's");
            System.out.println ("5. Print the highest inflation value during those 5 years");
            System.out.println ("6. Shows for how long was the food category leading in the CPI values");
            System.out.println ("7. Register/Show reports from data given");
            System.out.println ("8. Show projection of 2021 CPI");
            System.out.println ("9. Exit");

            System.out.println ("Choose one option from the menu:\n");
            option = sc.nextInt();

            switch(option){

                case 1:
                    registerICP_Rubros();
                    break;

                case 2:
                    registerAreasByYear();
                    break;

                case 3:
                    averageInflation();
                    break;

                case 4:
                    median();
                    break;

                case 5:
                    highestInflationVal();
                    break;

                case 6:
                    foodCategoryLead();
                    break;

                case 7:
                    registerOrGenerateRep();
                    break;

                case 8:
                    cpiCalc2021();
                    break;

                case 9:
                    exit = true;
                    break;

                default:
                    System.out.println ("Only numbers between 1 and 8");
            }
        }

    }

    private void registerAreasByYear() {
        for(int i = 0; i < NUM_YEARS-1; i++)
        {
            for(int j = 0; j < NUM_AREAS; j++)
            {
                System.out.printf("\nEnter the contribution of the area %s for the year %d", areasNames[j],years[i]);
                areasByYears[i][j] = sc.nextDouble();
            }
        }
    }

    /**
     * Method used to keep the value of CPI and categories from the past 5 years
     */
    public  void registerICP_Rubros(){

        System.out.println ("You have chosen to type the ICP");
        System.out.println ("Enter ICP Values from the last 5 years (2016, 2017, 2018, 2019, 2020):");
        for (int pos = 0; pos < NUM_YEARS -1; pos++){

            System.out.print("\nYear "+years[pos]+": ");
            cpi[pos] = sc.nextDouble();
            sc.nextLine();
        }
    }

    /**
     * Method used to calculate the average of all the CPI's
     */
    public void averageInflation(){

        System.out.println ("\nYou have chosen generate the average of inflation");
        double add = 0;

        for (int x = 0; x < cpi.length; x++) {
            add = add + cpi[x];
        }

        double average = add / (double) (cpi.length-1);
        System.out.printf("\nTotal CPI: %.3f. Average: %.3f", add, average);
    }

    /**
     * Method used to calculate the median value of the CPI
     */
    public  void median(){

        System.out.println ("You have chosen option 3");
        double medianData = cpi[cpi.length/2];

        //Static method from class Arrays to sort an array in ascending order
        Arrays.sort(cpi);

        System.out.printf("\nThe median of the CPI data is: %.2f",medianData);

    }

    /**
     * Method used to indicate which was the year with the highest CPI
     */
    public  void highestInflationVal(){ //

        double maxValue = 0;

        for(int i = 0; i < cpi.length; i++)
        {
            if(cpi[i] > maxValue)
                 maxValue = cpi[i];
        }
        System.out.printf("\nThe max inflation value is: %.3f",maxValue);
    }

    public  void foodCategoryLead(){ //Method used to indicate how long food category increased his price

        int numYears = 0, highest=0;
        for(int j = 0; j < NUM_YEARS-1; j++)
        {
           if(highestAreaByYear(j) == FOOD)
               numYears++;
        }
        System.out.printf("\nEl numero de años en el cual food lideró fue: %d", numYears);
    }

    /**
     * highestAreaByYear returns the highest area given a year
     * @param year
     * @return area with the highest value
     */
    public int highestAreaByYear(int year)
    {
        int highestArea=0;
        double highestValue=0.0;
        for(int i = 0; i < NUM_AREAS; i++)
        {
            if(areasByYears[year][i] > highestValue)
            {
                highestValue = areasByYears[year][i];
                highestArea = i;
            }
        }
        return highestArea;
    }

    public  void registerOrGenerateRep(){ //Method used to register, show or generate the reports of a CPI table given

    }

    public  void cpiCalc2021(){ //Method used to calculate the CPI of 2021


        double PIB, ProjectedCPI_low=0.0, ProjectedCPI_high=0.0, projectedCPI=0.0;
        System.out.println("Enter the PIB Value (millions of dollars):");
        PIB = sc.nextDouble();
        if(PIB < 50000.0)
        {
            ProjectedCPI_high = cpi[3]*(1.015); //CPI of the year 2020 plus 1,5%
            ProjectedCPI_low = cpi[3]/(1.015); //CPI of the year 2020 minus 1,5%
            System.out.printf("\nThe projected CPI for 2021 is between %.3f and %.3f",ProjectedCPI_low,ProjectedCPI_high);
        }
        else
        {
            if(PIB >= 50000.0 && PIB <= 55965.0)
            {
                projectedCPI =  cpi[4]*2 + (cpi[2]-cpi[3]); //last year = cpi[4]
                                                            //two years ago = cpi[3]
                                                            //three years ago = cpi[2]
                System.out.printf("\nThe projected CPI for 2021 is :",projectedCPI);
            }
            else
            {
                projectedCPI =  cpi[0]*5 / (cpi[4]*3);
                System.out.printf("\nThe projected CPI for 2021 is :",projectedCPI);
            }
        }

    }



}



    


