package junit.fitnesse.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import fitnesse.junit.FitNesseRunner.DebugMode;
import fitnesse.junit.FitNesseRunner.FitnesseDir;
import fitnesse.junit.FitNesseRunner;
import fitnesse.junit.FitNesseRunner.Suite;
import fitnesse.junit.FitNesseRunner.OutputDir;


@RunWith(FitNesseRunner.class)
@FitnesseDir("FitnesseDir") // relative to project directory, must be the parent of FitNesseRoot directory
@Suite("FitNesse.MyFitExamples.DateExample.DateSuite") // defined relative to FitNesseRoot directory
@OutputDir("target/fitnesse-reports") // where fitnesse html reports will be located 
@DebugMode(false) // this must be set to false for FitNesseRunner to correctly launch 

/*
 * Fitnesse directory structure in this project
 * 
 * FitExample
 * |
 * |----FitnesseDir
 *      |----fitnesse-standalone.jar
 *      |----cobertura-2.0.3
 *      |       |----cobertura-2.0.3.jar
 *      |----FitNesseRoot
 *      |       |----FitNesse
 *      |       |      |----MyFitExamples
*/


public class DateFitnesseSuite {

  @Test
  public void dummy(){
  }
  
}