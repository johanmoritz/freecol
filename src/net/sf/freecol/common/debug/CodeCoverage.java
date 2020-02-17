package net.sf.freecol.common.debug;

import java.util.logging.Logger;
import java.util.HashMap;

public class CodeCoverage {
  public static HashMap<String, HashMap<Integer, Integer>> functions = new HashMap<String, HashMap<Integer, Integer>>();
  public static void run(String functionName) {
    int lineNumber = getLineNumber();
    HashMap<Integer, Integer> functionEntries = CodeCoverage.functions.getOrDefault(functionName, new HashMap<Integer, Integer>());
    int touches = functionEntries.getOrDefault(lineNumber, 1);
    functionEntries.put(lineNumber, touches + 1);
    CodeCoverage.functions.put(functionName, functionEntries);
  }

  public static void print() {
    Logger logger = Logger.getLogger(CodeCoverage.class.getName());
    logger.info(CodeCoverage.functions.keySet().size() + " functions were tested");
    for (String function : CodeCoverage.functions.keySet()) {
      logger.info(function + "()");
      HashMap<Integer, Integer> functionEntries = CodeCoverage.functions.get(function);
      logger.info(functionEntries.size() + "branch(es) were tested");
      logger.info("The following branches were run: (line nr, #runs)");
      
      for (int lineNumber : functionEntries.keySet()) { 
        logger.info("(" + lineNumber + ", " + functionEntries.get(lineNumber) + ")");
      }
      
    }
  }

/** @return The line number of the code that ran this method
 * @author Brian_Entei */
public static int getLineNumber() {
  return ___8drrd3148796d_Xaf();
}

/** This methods name is ridiculous on purpose to prevent any other method
* names in the stack trace from potentially matching this one.
* 
* @return The line number of the code that called the method that called
*         this method(Should only be called by getLineNumber()).
* @author Brian_Entei */
private static int ___8drrd3148796d_Xaf() {
  boolean thisOne = false;
  int thisOneCountDown = 2;
  StackTraceElement[] elements = Thread.currentThread().getStackTrace();
  for(StackTraceElement element : elements) {
      String methodName = element.getMethodName();
      int lineNum = element.getLineNumber();
      if(thisOne && (thisOneCountDown == 0)) {
          return lineNum;
      } else if(thisOne) {
          thisOneCountDown--;
      }
      if(methodName.equals("___8drrd3148796d_Xaf")) {
          thisOne = true;
      }
  }
  return -1;
}
}