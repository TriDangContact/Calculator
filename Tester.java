/*  Tester for program #1.  This simple tester/driver is just a sample.
    The fact that your program might run without errors with this driver
    does not in any way guarantee that it is error free.  You are responsible
    for testing your program.
    Alan Riggins
    CS310 Summer 2017
    Program #1
*/    



package data_structures;

public class Tester {
    private UnorderedListADT<String> list;
    
    public Tester() {
        list = new LinkedList<>();
        runTests();
        }
        
    private void runTests() {
        list.addFirst("This");
        list.addLast("is");
        list.add("a", 3);


        
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println(list.size());
    }
    public static void main(String [] args) {
        new Tester();
        }
    }