package net.sf.freecol.common.debug;

import java.util.logging.Logger;
import java.util.HashMap;

public class CodeCoverage {

  public static Logger logger = Logger.getLogger(CodeCoverage.class.getName());
  public static HashMap<String, HashMap<Integer, Integer>> functions = new HashMap<>();
  public static HashMap<String, Integer> branchCounts = new HashMap<String, Integer>() {
      {
          put("initializeCaches", 23);
          put("writeChildren", 38);
          put("readChild", 28);
          put("setLocation", 27);
          put("getDefaultDisplay", 22);
          put("updateTileImprovementPlans", 25);
          put("readOption", 20);
          put("actionIsValid", 16);
          put("createLandRegions", 55);
          put("findMapPath", 14);
          put("getCost", 27);
          put("equals", 30);
      }
  };
  public static void run(String functionName) {
    int lineNumber = getLineNumber();
    HashMap<Integer, Integer> functionEntries = CodeCoverage.functions.getOrDefault(functionName, new HashMap<>());
    int touches = functionEntries.getOrDefault(lineNumber, 1);
    functionEntries.put(lineNumber, touches + 1);
    CodeCoverage.functions.put(functionName, functionEntries);
  }

  public static void print() {
      logger.info("mobergliuslefors CodeCoverage tool\n" + CodeCoverage.functions.keySet().size() + " functions were tested");
      StringBuilder sb = new StringBuilder();
      for (String function : CodeCoverage.functions.keySet()) {
          sb.append(function).append("()\n");
          HashMap<Integer, Integer> functionEntries = CodeCoverage.functions.get(function);
          sb.append(functionEntries.size()).append(" branch(es)");
          if (branchCounts.get(function) != null) {
              sb.append(" out of ").append(branchCounts.get(function));
              sb.append(" were tested\n");
              sb.append(((double) functionEntries.size()) / branchCounts.get(function) * 100).append("% coverage\n");
          } else {
              sb.append(" were tested\n");
          }
          sb.append("The following branches were run: (line nr, #runs)\n");

          for (int lineNumber : functionEntries.keySet()) {
              sb.append("(").append(lineNumber).append(", ").append(functionEntries.get(lineNumber)).append(")\n");
          }
          logger.info(sb.toString().trim());
          sb.setLength(0);
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