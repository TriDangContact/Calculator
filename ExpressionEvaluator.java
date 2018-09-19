/**
 *
 * @author Tri Dang
 * Edoras: cssc0110
 * 
 */
import java.util.StringTokenizer;
import data_structures.*;

public class ExpressionEvaluator {
    private Stack<String> stack;
    private Queue<String> queue;
    private String answer;
    private StringTokenizer tokenizer;
    
    public ExpressionEvaluator() {}
    
    public boolean isOperator(String opTest) {
        return (opTest.equals("+") || opTest.equals("-") || 
                opTest.equals("*") || opTest.equals("/") ||
                opTest.equals("^") );
    }
    
    public boolean isParanthese(String paranTest) {
        if (paranTest.equals("(") || paranTest.equals(")")) {
            return true;
        }
        return false;    
    }
    
    public boolean isNumber(String num) {
        try {
            Double d = Double.parseDouble(num);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public boolean greaterOrEqual(String one, String two) {
        int first = 0, second = 0;
        if (one.equals("^")) {
            first = 3;
        }
        else if (one.equals("*") || one.equals("/")) {
            first = 2;
        }
        else if (one.equals("+") || one.equals("-")) {
            first = 1;
        }
        if (two.equals("^")) {
            second = 3;
        }
        else if (two.equals("*") || two.equals("/")) {
            second = 2;
        }
        else if (two.equals("+") || two.equals("-")) {
            second = 1;
        }
        return first >= second;
    }
    
    public String processInput(String s) {
        //create new instances of stack and queue here so when Clear is pressed, you start off with an empty list
        stack = new Stack<>();
        queue = new Queue<>();
        tokenizer = new StringTokenizer(s);
        try { //try processing the expression, output ERROR message on calculator if any error
            while ( tokenizer.hasMoreTokens()) { //for each token in an infix expression do
                String temp = tokenizer.nextToken();
                StringTokenizer tokenizer2 = new StringTokenizer(s);
                String temp2 = tokenizer2.nextToken();
                if ( temp.equals("(")) { //if token is "("
                    stack.push(temp); //push the "(" onto the stack
                    //check for unitary operation
                    String temp3 = tokenizer2.nextToken();
                    if (!isNumber(temp3)) {
                        throw new RuntimeException();
                    }
                }
                else if ( temp.equals(")") ) {  //if token is ")"
                        do {                              //do ( until the "(" is reached )
                            queue.enqueue(stack.pop()); //pop an operator off the stack and enqueue it in the queue
                        } while (!stack.peek().equals("(")); //while the popped element is not a closed paran
                         stack.pop();   //pop off the open paran to prepare for next operator
                    }

                else if (isOperator(temp)) {    //if the token is an operator
                    //while the stack is not empty
                    //AND the top of the stack is not a "(" 
                    //AND the precedence of the token on the top of the stack >= current token
                    while(!stack.isEmpty() && !stack.peek().equals("(") 
                            && greaterOrEqual(stack.peek(),temp)) {
                            queue.enqueue(stack.pop()); //pop the token on the stack and enqueue it
                    } // end while
                    stack.push(temp); //push current token onto the stack
                } // end if token is an operator
                else if (isNumber(temp)) {   //if the token is a number, it will go through
                    queue.enqueue(temp);    //enqueue token
                }
                else {     //if the user entered invalid expressions
                    throw new RuntimeException();
                }
            } // end for each token do        

            while ( !stack.isEmpty()) {
                queue.enqueue(stack.pop()); //pop token off stack and enqueue it
            }// The queue now has the postfix expression   
            
            while ( !queue.isEmpty()) {//queue not empty
                if ( isNumber(queue.peek())) { //if token is a number
                    stack.push(queue.dequeue()); //push onto the stack;
                }
                else if( isOperator(queue.peek())) { //if token is an operator
                    //pop the top two tokens off the stack
                    //perform token2_from_stack operator token1_from_stack
                    String secondStr = stack.pop(), firstStr = stack.pop();
                    Double tempAns = 0.0;
                    String ans = null;
                    //if the two operands are numbers, perform operation
                    if (isNumber(secondStr) && isNumber(firstStr)) {    
                        Double firstNum = Double.parseDouble(firstStr);
                        Double secondNum = Double.parseDouble(secondStr);
                        if (queue.peek().equals("^")) {
                            tempAns = Math.pow(firstNum, secondNum);
                            ans = Double.toString(tempAns);
                        }
                        else if (queue.peek().equals("*")) {
                            tempAns = firstNum * secondNum;
                            ans = Double.toString(tempAns);
                        }
                        else if (queue.peek().equals("/")) {
                            tempAns = firstNum / secondNum;
                            ans = Double.toString(tempAns);
                        }
                        else if (queue.peek().equals("+")) {
                            tempAns = firstNum + secondNum;
                            ans = Double.toString(tempAns);
                        }
                        else if (queue.peek().equals("-")) {
                            tempAns = firstNum - secondNum;
                            ans = Double.toString(tempAns);
                        }
                    stack.push(ans); //push results onto the stack
                    queue.dequeue(); //dequeue current operator in order to move onto next one (IMPORTANT)
                    }
                    else { //if the two operands are not numbers, throw error
                        throw new RuntimeException();
                    }
                }     //end of if token is an operator loop
                else {  //if token is neither a number or operator, throw error (IMPORTANT)
                    throw new RuntimeException();
                }
            }// end while queue not empty
            if (!isNumber(stack.peek())) { //if the final result is not a number
                throw new RuntimeException();    //print out error
            }
            if (stack.size() > 1) {     //if the final stack has more than one final answer
                throw new RuntimeException();    //print out error
            }
            //the stack now has a single entry, the answer
            return answer = stack.pop();   
        } //end of try block
        catch (Exception e){
            stack.push("ERROR");
            return answer = stack.pop();
        } //end of try catch block
    } //end of processInput method
}   //end of class
