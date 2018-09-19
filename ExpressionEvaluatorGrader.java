/*  template for testing your ExpressionEvaluator class.  Add additional tests as needed.
    Alan Riggins
    Summer 2017
*/    


import data_structures.*;

public class ExpressionEvaluatorGrader {
    ExpressionEvaluator calculator; 
    
    public ExpressionEvaluatorGrader() {
        calculator = new ExpressionEvaluator();
        runTests();
        }
        
    private void runTests() {
        String expression, answer;
        
        expression = "( ( 2 + 3";    
        try {   
            answer = calculator.processInput(expression);
            if(!answer.trim().toUpperCase().equals("ERROR"))
                System.out.println("**** ERROR, expecting ERROR, but the expression " + expression +
                    " returns " + answer);
        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
            }                 
               
        try { 
        expression = "2 + 3 )";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("ERROR"))
            System.out.println("**** ERROR, expecting ERROR, but the expression " + expression +
                " returns " + answer);                                                                                                         
            }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
            }

        
        try { 
        expression = "2 ( - 3 )";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("ERROR"))
            System.out.println("**** ERROR, expecting ERROR, but the expression " + expression +
                " returns " + answer);                                                                                                         
            }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
            }
 
        
        try { 
        expression = "3 + 3 + .";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("ERROR"))
            System.out.println("**** ERROR, expecting ERROR, but the expression " + expression +
                " returns " + answer);                                                                                                         
            }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
            }
        }               
        
    public static void main(String [] args) {
        new ExpressionEvaluatorGrader();
        }
    }
    