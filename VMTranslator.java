package VMTranslator;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class VMTranslator {
	static int symbolCounter=0;
	static String moduleName="MyModule";
	static File outFile;
	static FileWriter myWriter;
	static FileWriter fw;
	static PrintWriter pw;

    /** Generate Hack Assembly code for a VM push operation assessed in Practical Assignment 6 */
    public static String vm_push(String segment, int offset){
    	String indexStr= String.valueOf(offset);
    	String registerStr = registerName(segment, offset);
    	switch(segment) {
    	case "constant":
    		pw.write("\n\t@" + offset + " // push " + segment + " " + offset);
    		pw.write("\n\tD=A");
    		pw.write("\n\t@SP");
    		pw.write("\n\tA=M");
    		pw.write("\n\tM=D");
    		pw.write("\n\t@SP");
    		pw.write("\n\tM=M+1");
    		pw.write("\n");
    		break;
    	case "static":
    		pw.write("\n\t@" + registerStr + " // push " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tA=D+A");
            pw.write("\n\tD=M");
            pw.write("\n\t@SP");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tM=M+1");
            break;
    	case "argument":
    		pw.write("\n\t@" + registerStr + " // push " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tA=D+A");
            pw.write("\n\tD=M");
            pw.write("\n\t@SP");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tM=M+1");
            break;
    	case "local":
    		pw.write("\n\t@" + registerStr + " // push " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tA=D+A");
            pw.write("\n\tD=M");
            pw.write("\n\t@SP");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tM=M+1");
            break;
    	case "this":
    		pw.write("\n\t@" + registerStr + " // push " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tA=D+A");
            pw.write("\n\tD=M");
            pw.write("\n\t@SP");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tM=M+1");
            break;
    	case "that":
    		pw.write("\n\t@" + registerStr + " // push " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tA=D+A");
            pw.write("\n\tD=M");
            pw.write("\n\t@SP");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tM=M+1");
            break;
    	case "temp":
    		pw.write("\n\t@" + registerStr + " // push " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tA=D+A");
            pw.write("\n\tD=M");
            pw.write("\n\t@SP");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tM=M+1");
            break;
    	case "pointer":
    		pw.write("\n\t@" + registerStr + " // push " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tA=D+A");
            pw.write("\n\tD=M");
            pw.write("\n\t@SP");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tM=M+1");
            break;
        default:
        	throw new RuntimeException("vm_push(): Invalid segment");
    		
    	}
    		
    	return "";
    }

    /** Generate Hack Assembly code for a VM pop operation assessed in Practical Assignment 6 */
    public static String vm_pop(String segment, int offset){    
    	String indexStr= String.valueOf(offset);
    	String registerStr = registerName(segment, offset);
    	switch(segment) {
    	case "constant":
    		throw new RuntimeException("vm_pop(): cannot pop to constant");
		case "static":
			pw.write("\n\t@" + registerStr + " // pop " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tD=D+A");
            pw.write("\n\t@R13");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tAM=M-1");
            pw.write("\n\tD=M");
            pw.write("\n\t@R13");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            break;
    	case "argument":
    		pw.write("\n\t@" + registerStr + " // pop " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tD=D+A");
            pw.write("\n\t@R13");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tAM=M-1");
            pw.write("\n\tD=M");
            pw.write("\n\t@R13");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            break;	
    	case "local":
    		pw.write("\n\t@" + registerStr + " // pop " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tD=D+A");
            pw.write("\n\t@R13");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tAM=M-1");
            pw.write("\n\tD=M");
            pw.write("\n\t@R13");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            break;
    	case "this":
    		pw.write("\n\t@" + registerStr + " // pop " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tD=D+A");
            pw.write("\n\t@R13");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tAM=M-1");
            pw.write("\n\tD=M");
            pw.write("\n\t@R13");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            break;
    	case "that":
    		pw.write("\n\t@" + registerStr + " // pop " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tD=D+A");
            pw.write("\n\t@R13");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tAM=M-1");
            pw.write("\n\tD=M");
            pw.write("\n\t@R13");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            break;
    	case "temp":
    		pw.write("\n\t@" + registerStr + " // pop " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tD=D+A");
            pw.write("\n\t@R13");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tAM=M-1");
            pw.write("\n\tD=M");
            pw.write("\n\t@R13");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            break;
    	case "pointer":
    		pw.write("\n\t@" + registerStr + " // pop " + segment + " " + indexStr);
            pw.write("\n\tD=M");
            pw.write("\n\t@" + indexStr);
            pw.write("\n\tD=D+A");
            pw.write("\n\t@R13");
            pw.write("\n\tM=D");
            pw.write("\n\t@SP");
            pw.write("\n\tAM=M-1");
            pw.write("\n\tD=M");
            pw.write("\n\t@R13");
            pw.write("\n\tA=M");
            pw.write("\n\tM=D");
            break;
        default:
        	throw new RuntimeException("vm_pop(): Invalid segment");
    		
    	}
    	return "";
    }

    /** Generate Hack Assembly code for a VM add operation assessed in Practical Assignment 6 
     * @throws IOException */
    public static String vm_add() throws IOException{
    	pw.write("\n\t@SP // add");
    	pw.write("\n\tAM=M-1");
        pw.write("\n\tD=M");
    	pw.write("\n\tA=A-1");
    	pw.write("\n\tM=D+M");
    	return "";
    }

    /** Generate Hack Assembly code for a VM sub operation assessed in Practical Assignment 6 */
    public static String vm_sub(){
    	pw.write("\n\t@SP // sub");
        pw.write("\n\tAM=M-1");
        pw.write("\n\tD=M");
        pw.write("\n\tA=A-1");
        pw.write("\n\tM=M-D");
    	return "";
    }

    /** Generate Hack Assembly code for a VM neg operation assessed in Practical Assignment 6 */
    public static String vm_neg(){
    	pw.write("\n\t@SP // neg");
        pw.write("\n\tA=M");
        pw.write("\n\tA=A-1");
        pw.write("\n\tM=-M");
    	return "";
    }

    /** Generate Hack Assembly code for a VM eq operation assessed in Practical Assignment 6 */
    public static String vm_eq(){
    	String labelTrue = "JGT_TRUE_" + moduleName + "_" + String.valueOf(symbolCounter);
        String labelFalse = "JGT_FALSE_" + moduleName + "_" + String.valueOf(symbolCounter);

        pw.write("\n\t@SP // eq");
        pw.write("\n\tAM=M-1");
        pw.write("\n\tD=M");
        pw.write("\n\t@SP");
        pw.write("\n\tAM=M-1");
        pw.write("\n\tD=M-D");
        pw.write("\n\t@" + labelTrue);
        pw.write("\n\tD;JEQ");
        pw.write("\n\tD=0");
        pw.write("\n\t@" + labelFalse);
        pw.write("\n\t0;JMP");
        pw.write("\n\t(" + labelTrue + ")");
        pw.write("\n\tD=-1");
        pw.write("\n\t(" + labelFalse + ")");
        pw.write("\n\t@SP");
        pw.write("\n\tA=M");
        pw.write("\n\tM=D");
        pw.write("\n\t@SP");
        pw.write("\n\tM=M+1");

        symbolCounter++;
    	return "";
    }

    /** Generate Hack Assembly code for a VM gt operation assessed in Practical Assignment 6 */
    public static String vm_gt(){
    	String labelTrue = "JGT_TRUE_" + moduleName + "_" + String.valueOf(symbolCounter);
        String labelFalse = "JGT_FALSE_" + moduleName + "_" + String.valueOf(symbolCounter);

        pw.write("\n\t@SP // gt");
        pw.write("\n\tAM=M-1");
        pw.write("\n\tD=M");
        pw.write("\n\t@SP");
        pw.write("\n\tAM=M-1");
        pw.write("\n\tD=M-D");
        pw.write("\n\t@" + labelTrue);
        pw.write("\n\tD;JGT");
        pw.write("\n\tD=0");
        pw.write("\n\t@" + labelFalse);
        pw.write("\n\t0;JMP");
        pw.write("\n\t(" + labelTrue + ")");
        pw.write("\n\tD=-1");
        pw.write("\n\t(" + labelFalse + ")");
        pw.write("\n\t@SP");
        pw.write("\n\tA=M");
        pw.write("\n\tM=D");
        pw.write("\n\t@SP");
        pw.write("\n\tM=M+1");

        symbolCounter++;
        return "";
    }

    /** Generate Hack Assembly code for a VM lt operation assessed in Practical Assignment 6 */
    public static String vm_lt(){
    	String labelTrue = "JLT_TRUE_" + moduleName + "_" + String.valueOf(symbolCounter);
        String labelFalse = "JLT_FALSE_" + moduleName + "_" + String.valueOf(symbolCounter);

        pw.write("\n\t@SP // lt");
        pw.write("\n\tAM=M-1");
        pw.write("\n\tD=M");
        pw.write("\n\t@SP");
        pw.write("\n\tAM=M-1");
        pw.write("\n\tD=M-D");
        pw.write("\n\t@" + labelTrue);
        pw.write("\n\tD;JLT");
        pw.write("\n\tD=0");
        pw.write("\n\t@" + labelFalse);
        pw.write("\n\t0;JMP");
        pw.write("\n\t(" + labelTrue + ")");
        pw.write("\n\tD=-1");
        pw.write("\n\t(" + labelFalse + ")");
        pw.write("\n\t@SP");
        pw.write("\n\tA=M");
        pw.write("\n\tM=D");
        pw.write("\n\t@SP");
        pw.write("\n\tM=M+1");

        symbolCounter++;
        return "";
    }

    /** Generate Hack Assembly code for a VM and operation assessed in Practical Assignment 6 */
    public static String vm_and(){
    	pw.write("\n\t@SP // and");
        pw.write("\n\tAM=M-1");
        pw.write("\n\tD=M");
        pw.write("\n\tA=A-1");
        pw.write("\n\tM=D&M");
        return "";
    }

    /** Generate Hack Assembly code for a VM or operation assessed in Practical Assignment 6 */
    public static String vm_or(){
    	pw.write("\n\t@SP // or");
        pw.write("\n\tAM=M-1");
        pw.write("\n\tD=M");
        pw.write("\n\tA=A-1");
        pw.write("\n\tM=D|M");
        return "";
    }

    /** Generate Hack Assembly code for a VM not operation assessed in Practical Assignment 6 */
    public static String vm_not(){
    	pw.write("\n\t@SP // not");
        pw.write("\n\tA=M");
        pw.write("\n\tA=A-1");
        pw.write("\n\tM=!M");
        return "";
    }

    /** Generate Hack Assembly code for a VM label operation assessed in Practical Assignment 7 */
    public static String vm_label(String label){
        return "";
    }

    /** Generate Hack Assembly code for a VM goto operation assessed in Practical Assignment 7 */
    public static String vm_goto(String label){
        return "";
    }

    /** Generate Hack Assembly code for a VM if-goto operation assessed in Practical Assignment 7 */
    public static String vm_if(String label){
        return "";
    }

    /** Generate Hack Assembly code for a VM function operation assessed in Practical Assignment 7 */
    public static String vm_function(String function_name, int n_vars){
        return "";
    }

    /** Generate Hack Assembly code for a VM call operation assessed in Practical Assignment 7 */
    public static String vm_call(String function_name, int n_args){
        return "";
    }

    /** Generate Hack Assembly code for a VM return operation assessed in Practical Assignment 7 */
    public static String vm_return(){
        return "";
    }
    
    public static String registerName(String segment, int index) {
    	if (segment == "static") return "16";
    	if (segment == "local") return "LCL";
    	if (segment == "argument") return "ARG";
    	if (segment == "this") return "THIS";
    	if (segment == "that") return "THAT";
    	if (segment == "pointer") return "R" + String.valueOf(3 +index);
    	if (segment == "temp") return "R" + String.valueOf(5 +index);
		return "";
    }
    
    public static String vm_end() {
    	pw.write("\n\n(END)");
        pw.write("\n\t@END");
        pw.write("\n\t0;JMP");
    	return "";
    	
    }

    /** A quick-and-dirty parser when run standalone. 
     * @throws IOException */ 
    public static void main(String[] args) throws IOException{
        if(args.length > 0){
            try {
                Scanner sc = new Scanner(new File(args[0]));
                String FName=args[0];
                String FOut = FName.split(".vm")[0] + ".asm";
                outFile = new File(FOut);
                fw = new FileWriter(outFile); 
                pw = new PrintWriter(fw);
               
                while (sc.hasNextLine()) {
                    String[] tokens = sc.nextLine().trim().toLowerCase().split("\\s+");
                    if(tokens.length==1){
                        if(tokens[0].equals("add")){
                            vm_add();
                        } else if(tokens[0].equals("sub")){
                            vm_sub();
                        } else if(tokens[0].equals("neg")){
                            System.out.println(vm_neg());
                        } else if(tokens[0].equals("eq")){
                            System.out.println(vm_eq());
                        } else if(tokens[0].equals("gt")){
                            System.out.println(vm_gt());
                        } else if(tokens[0].equals("lt")){
                            System.out.println(vm_lt());
                        } else if(tokens[0].equals("and")){
                            System.out.println(vm_and());
                        } else if(tokens[0].equals("or")){
                            System.out.println(vm_or());
                        } else if(tokens[0].equals("not")){
                            System.out.println(vm_not());
                        } else if(tokens[0].equals("return")){
                            System.out.println(vm_return());
                        }
                    } else if(tokens.length==2){
                        if(tokens[0].equals("label")){
                            System.out.println(vm_label(tokens[1]));
                        } else if(tokens[0].equals("goto")){
                            System.out.println(vm_goto(tokens[1]));
                        } else if(tokens[0].equals("if")){
                            System.out.println(vm_if(tokens[1]));
                        }
                    } else if(tokens.length==3){
                        int t2;
                        try {
                            t2 = Integer.parseInt(tokens[2]);
                        } catch (Exception e) {
                            System.err.println("Unable to parse int.");
                            break;
                        }
                        if(tokens[0].equals("push")){
                            System.out.println(vm_push(tokens[1],t2));
                        } else if(tokens[0].equals("pop")){
                            System.out.println(vm_pop(tokens[1],t2));
                        } else if(tokens[0].equals("function")){
                            System.out.println(vm_function(tokens[1],t2));
                        } else if(tokens[0].equals("call")){
                            System.out.println(vm_call(tokens[1],t2));
                        }
                    }
                }
                vm_end();
                sc.close();
                pw.close();
            } catch (FileNotFoundException e) {
                System.err.println("File not found.");
            }
        }
    }
        
}